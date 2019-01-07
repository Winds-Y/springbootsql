package com.example.springbootsql.repository;

import com.example.springbootsql.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
