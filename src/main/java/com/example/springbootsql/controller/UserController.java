package com.example.springbootsql.controller;

import com.example.springbootsql.entity.TaskMessage;
import com.example.springbootsql.entity.User;
import com.example.springbootsql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//测试插入新的数据
	@GetMapping(path="/add")
	public @ResponseBody
    String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "保存成功";
	}
	
	//测试获取全部的数据
	@GetMapping(path="/alls")
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/restDistributeTask")
	public void distributeTask(@ModelAttribute TaskMessage taskMessage){
		System.out.println("in distributeTask");
		System.out.println(taskMessage.getTaskName());
		System.out.println(taskMessage.getTargetPersonUrl());
		System.out.println(taskMessage.getTaskCode());
	}
}
