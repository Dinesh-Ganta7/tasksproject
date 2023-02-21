package com.dgss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgss.dto.TaskDTO;
import com.dgss.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private TaskService taskService;

	// Save the task

	@PostMapping("/{userId}/tasks")
	public ResponseEntity<TaskDTO> saveTask(@PathVariable(name = "userId") long userId, @RequestBody TaskDTO taskDto) {
		return new ResponseEntity<TaskDTO>(taskService.saveTask(userId, taskDto), HttpStatus.CREATED);
	}

	// Get all tasks

	@GetMapping("/{userId}/tasks")
	public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable(name = "userId") long userId) {
		return new ResponseEntity<List<TaskDTO>>(taskService.getAllTasks(userId), HttpStatus.OK);

	}

	// Get Individual Task
	@GetMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<TaskDTO> getTask(@PathVariable(name = "userId") long userId,
			@PathVariable(name = "taskId") long taskId) {
		return new ResponseEntity<TaskDTO>(taskService.getTask(userId, taskId), HttpStatus.OK);
	}

	// Delete Task
	@DeleteMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable(name = "userId") long userId,
			@PathVariable(name = "taskId") long taskId) {
		taskService.deleteTask(userId, taskId);
		return new ResponseEntity<String>("User deleted task successfully", HttpStatus.OK);
	}
}
