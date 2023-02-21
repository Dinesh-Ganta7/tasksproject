package com.dgss.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgss.dto.TaskDTO;
import com.dgss.entity.Task;
import com.dgss.entity.Users;
import com.dgss.exception.APIException;
import com.dgss.exception.TaskNotFoundException;
import com.dgss.exception.UserNotFoundException;
import com.dgss.repository.TaskRepository;
import com.dgss.repository.UsersRepository;
import com.dgss.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDTO saveTask(long userId, TaskDTO taskDTO) {

		Users user = usersRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(String.format("User Id not found", userId)));
		Task task = mapper.map(taskDTO, Task.class);
		task.setUsers(user);
		// After setting the user we are storing the date in the db
		Task savedTask = taskRepository.save(task);

		return mapper.map(savedTask, TaskDTO.class);
	}

	@Override
	public List<TaskDTO> getAllTasks(long userId) {
		Users user = usersRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found", userId)));

		List<Task> tasks = taskRepository.findAllByUsersId(userId);

		return tasks.stream().map((each) -> mapper.map(each, TaskDTO.class)).collect(Collectors.toList());
	}

	@Override
	public TaskDTO getTask(long userId, long taskId) {

		Users user = usersRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found", userId)));

		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(String.format("Task Id %d not found", taskId)));
		
		if(user.getId() != task.getUsers().getId()) {
			throw new APIException(String.format("Task Id %d is not belongs to User Id %d", taskId, userId));
		}

		return mapper.map(task, TaskDTO.class);
	}

	@Override
	public void deleteTask(long userId, long taskId) {
		
		Users user = usersRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found", userId)));

		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(String.format("Task Id %d not found", taskId)));
		
		if(user.getId() != task.getUsers().getId()) {
			throw new APIException(String.format("Task Id %d is not belongs to User Id %d", taskId, userId));
		}
		
		taskRepository.deleteById(taskId);//deletes the task
		
		
	}

}
