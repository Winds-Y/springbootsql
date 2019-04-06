package com.example.springbootsql.service;

import com.example.springbootsql.entity.User;
import com.example.springbootsql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByName(String name){
        return userRepository.findByName(name);
    }
}
