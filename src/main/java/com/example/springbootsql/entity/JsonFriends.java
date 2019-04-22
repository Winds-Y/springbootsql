package com.example.springbootsql.entity;

import java.util.List;

/**
 * Created by: Changze
 * Date: 2019/4/22
 * Time: 21:19
 */
public class JsonFriends {
    private String ui_mask;
    private List<Friends>friendsList;

    public String getUi_mask() {
        return ui_mask;
    }

    public void setUi_mask(String ui_mask) {
        this.ui_mask = ui_mask;
    }

    public List<Friends> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<Friends> friendsList) {
        this.friendsList = friendsList;
    }
}
