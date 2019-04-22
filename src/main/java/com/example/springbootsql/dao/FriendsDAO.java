package com.example.springbootsql.dao;

import com.example.springbootsql.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by: Changze
 * Date: 2019/4/22
 * Time: 20:07
 */
@Component
public interface FriendsDAO extends JpaRepository<Friends,Long> {

}
