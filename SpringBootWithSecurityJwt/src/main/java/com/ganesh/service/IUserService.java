package com.ganesh.service;

import java.util.Optional;

import com.ganesh.model.User;

public interface IUserService {
	
	Integer saveUser(User user);
	
    Optional<User> findByUsername(String username);
}
