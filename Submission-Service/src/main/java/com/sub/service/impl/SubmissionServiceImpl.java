package com.sub.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sub.modal.Submission;
import com.sub.modal.TaskDto;
import com.sub.repo.SubmissionRepo;
import com.sub.service.SubmissionService;
import com.sub.service.TaskService;

@Service
public class SubmissionServiceImpl implements SubmissionService {

	@Autowired
	private SubmissionRepo submissionRepo;

	@Autowired
	private TaskService taskService;

	@Override
	public Submission submitTask(Long taskId, String githubLink, Long userId, String jwt) throws Exception {

		TaskDto task = taskService.getTaskById(userId, jwt);

		if (task != null) {
			Submission submission = new Submission();
			submission.setTaskId(taskId);
			submission.setUserId(userId);
			submission.setGithubLink(githubLink);
			submission.setSubmissionTime(LocalDateTime.now());

			return submissionRepo.save(submission);
		}

		throw new Exception("Task not found with id :" + taskId);
	}

	@Override
	public Submission getTaskSubmissionById(Long submissionId) throws Exception {

		return this.submissionRepo.findById(submissionId)
				.orElseThrow(() -> new Exception("Task Submission not found with id :" + submissionId));
	}

	@Override
	public List<Submission> getTaskSubmissions() {

		return this.submissionRepo.findAll();
	}

	@Override
	public List<Submission> getTaskSubmissionByTaskId(Long taskId) {

		return this.submissionRepo.findByTaskId(taskId);
	}

	@Override
	public Submission acceptDeclineSubmission(Long id, String status) throws Exception {

		Submission submission = getTaskSubmissionById(id);
		submission.setStatus(status);
			
		if(status.equals("ACCEPT")) {
			taskService.completeTask(submission.getTaskId());
		}	
		
		return this.submissionRepo.save(submission);
		
	}

}
