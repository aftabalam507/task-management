package com.task.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.modal.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	public List<Task> findByAssignedUserId(Long userId);
}
