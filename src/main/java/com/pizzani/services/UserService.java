package com.pizzani.services;

import com.pizzani.entities.User;

public interface UserService {
	
	public User saveUser(User user);
	public Boolean verifyToken(String token);
}
