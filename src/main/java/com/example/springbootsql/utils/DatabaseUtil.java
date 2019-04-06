package com.example.springbootsql.utils;

import com.example.springbootsql.entity.User;
import com.example.springbootsql.repository.TaskMessageRepository;
import com.example.springbootsql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseUtil {
    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private static TaskMessageRepository taskMessageRepository;

    public static User userFindByName(String name){
        return userRepository.findByName(name);
    }
}
