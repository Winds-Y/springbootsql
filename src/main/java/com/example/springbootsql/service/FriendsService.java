package com.example.springbootsql.service;

import com.example.springbootsql.entity.Friends;

import java.util.List;

/**
 * Created by: Changze
 * Date: 2019/4/22
 * Time: 20:12
 */
public interface FriendsService {
    void save(Friends friends);
    List<Friends> findAll();
}
