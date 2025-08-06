package com.example.demo.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Log {
    private Long id;
    private String username; // 操作用户
    private String operation; // 操作描述
    private String method; // 调用方法
    private String params; // 方法参数
    private String ip; // 客户端IP
    private LocalDateTime createTime; // 操作时间
}
