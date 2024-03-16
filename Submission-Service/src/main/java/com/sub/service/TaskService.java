package com.sub.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.sub.modal.TaskDto;


@FeignClient(name = "TASK-SERVICE", url = "http://localhost:5002/")
public interface TaskService {

	@GetMapping("/api/tasks/{id}")
	public TaskDto getTaskById(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws Exception;

	@PutMapping("/api/tasks/{id}/complete")
	public ResponseEntity<TaskDto> completeTask(@PathVariable Long id) throws Exception ;
	
}
