package com.example.springbootsql.repository;

import com.example.springbootsql.entity.ServerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerStatusRepository extends JpaRepository<ServerStatus,Long> {

}
