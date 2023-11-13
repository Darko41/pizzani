package com.pizzani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzani.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmailIgnoreCase(String email);
	public Boolean existsByEmail(String email);

}
