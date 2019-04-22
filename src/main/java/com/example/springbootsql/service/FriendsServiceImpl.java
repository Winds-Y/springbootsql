package com.example.springbootsql.service;

import com.example.springbootsql.dao.FriendsDAO;
import com.example.springbootsql.entity.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by: Changze
 * Date: 2019/4/22
 * Time: 20:19
 */
@Service
public class FriendsServiceImpl implements FriendsService{
    @Autowired
    private FriendsDAO friendsDAO;
    @Override
    public void save(Friends friends) {
        friendsDAO.save(friends);
    }

    @Override
    public List<Friends> findAll() {
        return friendsDAO.findAll();
    }
}
