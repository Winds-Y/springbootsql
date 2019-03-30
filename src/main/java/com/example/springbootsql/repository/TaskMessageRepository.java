package com.example.springbootsql.repository;

import com.example.springbootsql.entity.TaskMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskMessageRepository extends JpaRepository<TaskMessage,Long> {
}
