package com.dgss.service;

import java.util.List;

import com.dgss.dto.TaskDTO;

public interface TaskService {
	
	public TaskDTO saveTask(long userId, TaskDTO taskDTO);
	
	public List<TaskDTO> getAllTasks(long userId);
	
	public TaskDTO getTask(long userId, long taskId);

}
