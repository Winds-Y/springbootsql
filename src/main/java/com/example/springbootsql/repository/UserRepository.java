package com.example.springbootsql.repository;

import com.example.springbootsql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
