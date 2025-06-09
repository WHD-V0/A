package com.example.demo.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

import jakarta.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public boolean register(User user) {
		// 判断用户名是否重复
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", user.getUsername());
		if (userMapper.selectOne(queryWrapper) != null) {
			return false; // 用户名已存在
		}

		// 插入新用户
		return userMapper.insert(user) > 0;
	}

	@Override
	public List<User> selectAllUsers() {
		return userMapper.selectList(null);
	}

	@Override
	public User selectUserById(long id) {
		return userMapper.selectById(id);
	}

	@Override
	public IPage<User> selectUserByPage(Page<User> page){
		return userMapper.selectPage(page, null);
	}

	@Override  // 重写父类登录方法
	public boolean login(String username, String password) {
		// 创建QueryWrapper对象，用于构建查询条件
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		// 设置查询条件：用户名和密码必须同时匹配
		queryWrapper.eq("username", username)  // 用户名等于参数username
				.eq("password", password); // 密码等于参数password
		// 执行查询，如果找到匹配记录返回true，否则返回false
		return userMapper.selectOne(queryWrapper) != null;
	}

	@Override
	public boolean updateUser(User user) {
		return userMapper.updateById(user) > 0;
	}

}

