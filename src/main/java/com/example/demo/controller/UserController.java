package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*") // 允许所有域名的跨域请求
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String register(@RequestBody User user) {
		boolean success = userService.register(user);
		return success ? "注册成功" : "用户名已存在";
	}

	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userService.selectAllUsers();
	}

	@GetMapping("/selectById")
	public User getUserById(@RequestParam("id") long id) {
		return userService.selectUserById(id);
	}
	//	@GetMapping("/selectById")
	//	public User getUserById(@RequestParam("id") long id) {
	//		return userService.selectUserById(id);
	//	}
}







