//package com.example.demo;
//
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//@MapperScan("com.example.demo.mapper")
//public class DemoApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//	}
//
//}
package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll().and().csrf(csrf -> csrf.disable()); // 使用新的 CSRF 配置方式
		return http.build();
	}
}