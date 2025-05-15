package com.example.demo.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("user") // 绑定表名
public class User {

	@TableId(type = IdType.AUTO)
	private Long id;

	private String username;
	private String password;
	private String email;

	private LocalDateTime createTime;
}

