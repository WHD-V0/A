package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {
	boolean register(User user);

	List<User> selectAllUsers();
}
