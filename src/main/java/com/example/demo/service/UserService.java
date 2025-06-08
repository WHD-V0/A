package com.example.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;

public interface UserService {
	boolean register(User user);

	List<User> selectAllUsers();

	User selectUserById(long id);

	/**
	 * 分页查询
	 */
	IPage<User> selectUserByPage(Page<User> page);
}
