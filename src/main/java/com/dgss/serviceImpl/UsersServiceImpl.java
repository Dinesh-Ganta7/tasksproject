package com.dgss.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dgss.dto.UsersDTO;
import com.dgss.entity.Users;
import com.dgss.repository.UsersRepository;
import com.dgss.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public UsersDTO createUser(UsersDTO userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Users user = usersRepository.save(usersDTOToEntity(userDto));
		return entityToUsersDTO(user);
	}

	private Users usersDTOToEntity(UsersDTO userDto) {

		Users users = Users.builder().build();
		users.setEmail(userDto.getEmail());
		users.setName(userDto.getName());
		users.setPassword(userDto.getPassword());
		return users;
	}

	private UsersDTO entityToUsersDTO(Users user) {

		UsersDTO usersDto = UsersDTO.builder().build();
		usersDto.setEmail(user.getEmail());
		usersDto.setName(user.getName());
		usersDto.setPassword(user.getPassword());
		usersDto.setId(user.getId());
		return usersDto;
	}

}
