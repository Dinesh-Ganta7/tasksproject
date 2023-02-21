package com.dgss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgss.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
