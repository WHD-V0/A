package com.example.demo.service.impl;

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
}

