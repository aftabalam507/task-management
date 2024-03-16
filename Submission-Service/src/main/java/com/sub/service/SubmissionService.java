package com.sub.service;

import java.util.List;

import com.sub.modal.Submission;

public interface SubmissionService {

	Submission submitTask(Long taskId, String githubLink, Long userId, String jwt) throws Exception;

	Submission getTaskSubmissionById(Long submissionId) throws Exception;
	
	List<Submission> getTaskSubmissions();
	
	List<Submission> getTaskSubmissionByTaskId(Long taskId);
	
	Submission acceptDeclineSubmission(Long id, String status) throws Exception;
}
