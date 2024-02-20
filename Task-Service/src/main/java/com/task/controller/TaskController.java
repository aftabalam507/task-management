package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.modal.Task;
import com.task.modal.TaskStatus;
import com.task.modal.UserDto;
import com.task.service.TaskService;
import com.task.service.UserService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt)
			throws Exception {
		UserDto userDto = userService.getUserProfile(jwt);
		Task createdTask = taskService.createTask(task, userDto.getRole());
		return new ResponseEntity<Task>(createdTask, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws Exception {

		UserDto userDto = userService.getUserProfile(jwt);

		Task task = taskService.getTaskById(id);

		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAssignedUsersTask(@RequestParam(required = false) TaskStatus status,
			@RequestHeader("Authorization") String jwt) throws Exception {

		UserDto userDto = userService.getUserProfile(jwt);

		List<Task> tasks = taskService.assignedUsersTask(userDto.getId(), status);

		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Task>> getAllTask(@RequestParam(required = false) TaskStatus status,
			@RequestHeader("Authorization") String jwt) throws Exception {

		UserDto userDto = userService.getUserProfile(jwt);

		List<Task> tasks = taskService.getAllTask(status);

		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@PutMapping("/{id}/user/{userId}/assigned")
	public ResponseEntity<Task> assignedTaskToUser(@PathVariable Long id, @PathVariable Long userId,
			@RequestHeader("Authorization") String jwt) throws Exception {

		UserDto userDto = userService.getUserProfile(jwt);

		Task assignedTask = taskService.assignedToUser(userId, id);

		return new ResponseEntity<>(assignedTask, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task req,
			@RequestHeader("Authorization") String jwt) throws Exception {

		UserDto userDto = userService.getUserProfile(jwt);

		Task updatedTask = taskService.updateTask(id, req, userDto.getId());

		return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	}

	@PutMapping("/{id}/complete")
	public ResponseEntity<Task> completeTask(@PathVariable Long id) throws Exception {

		
		Task completeTask = taskService.completeTask(id);

		return new ResponseEntity<>(completeTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws Exception {

		
		taskService.deleteTask(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
