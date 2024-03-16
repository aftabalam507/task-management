package com.sub.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sub.modal.Submission;

public interface SubmissionRepo extends JpaRepository<Submission, Long> {
	
	List<Submission> findByTaskId(Long taskId);
	
}
