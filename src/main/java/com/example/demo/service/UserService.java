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

	/**
	 * 用户登录功能
	 * @param username
	 * @param password
	 * @return
	 */
	boolean login(String username, String password);

	/**
	 * 更新信息
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);
}
