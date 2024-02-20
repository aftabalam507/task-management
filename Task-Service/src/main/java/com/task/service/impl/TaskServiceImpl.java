package com.task.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.modal.Task;
import com.task.modal.TaskStatus;
import com.task.repo.TaskRepository;
import com.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task createTask(Task task, String requesterRole) throws Exception {

		if (!requesterRole.equals(("ROLE_ADMIN"))) {
			throw new Exception("only admin can create task");
		}

		task.setStatus(TaskStatus.PENDING);
		task.setCreatedAt(LocalDateTime.now());

		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(Long id) throws Exception {

		return this.taskRepository.findById(id).orElseThrow(() -> new Exception("task not found with id " + id));
	}

	@Override
	public List<Task> getAllTask(TaskStatus status) {

		List<Task> tasks = this.taskRepository.findAll();

		List<Task> collect = tasks.stream()
				.filter(task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString()))
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public Task updateTask(Long id, Task updateTask, Long userId) throws Exception {
		
		Task existingTask = getTaskById(id);
		
		if(updateTask.getTitle() != null) {
			existingTask.setTitle(updateTask.getTitle());
		}
		
		if(updateTask.getImage() != null) {
			existingTask.setImage(updateTask.getImage());
		}
		
		if(updateTask.getDescription() != null) {
			existingTask.setDescription(updateTask.getDescription());
		}		
		
		
		if(updateTask.getStatus() != null) {
			existingTask.setStatus(updateTask.getStatus());
		}
		
		if(updateTask.getDeadline() != null) {
			existingTask.setDeadline(updateTask.getDeadline());
		}

		return taskRepository.save(existingTask);
	}

	@Override
	public void deleteTask(Long id) throws Exception {
		
		getTaskById(id);
		
		this.taskRepository.deleteById(id);

	}

	@Override
	public Task assignedToUser(Long userId, Long taskId) throws Exception {
		
		Task task = getTaskById(taskId);
		task.setAssignedUserId(userId);
		task.setStatus(TaskStatus.DONE);
		
		return this.taskRepository.save(task);
	}

	@Override
	public List<Task> assignedUsersTask(Long userId, TaskStatus status) {
	
		List<Task> tasks = this.taskRepository.findByAssignedUserId(userId);
		
		List<Task> collect = tasks.stream()
				.filter(task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString()))
				.collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public Task completeTask(Long taskId) throws Exception {
		
		Task task = getTaskById(taskId);
		task.setStatus(TaskStatus.DONE);
		return this.taskRepository.save(task);
	}

}
