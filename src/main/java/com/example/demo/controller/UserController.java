package com.example.demo.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

	@PostMapping("/login")
	public String login(@RequestBody User user) {
		boolean success = userService.login(user.getUsername(), user.getPassword());
		return success ? "登录成功" : "用户名或密码错误";
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

	// 新增分页查询接口
	@GetMapping("/page")
	public IPage<User> getUserByPage(@RequestParam(defaultValue = "1") long current,
									 @RequestParam(defaultValue = "2") long size) {
		Page<User> page = new Page<>(current, size);
		return userService.selectUserByPage(page);
	}

	@PostMapping("/update")
	public String updateUser(@RequestBody User user) {
		boolean success = userService.updateUser(user);
		return success ? "更新成功" : "更新失败";
	}
}







