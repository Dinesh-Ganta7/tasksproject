package com.dgss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsersDTO {
	
	private long id;
	
	private String name;
	
	private String email;
	
	private String password;

}
