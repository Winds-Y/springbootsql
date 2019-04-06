package com.example.springbootsql.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServerStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JSONField(serialize = false)
    private Integer id;

    @JSONField(ordinal = 1,name = "server")
    private String server;
    @JSONField(ordinal = 2,name = "server_index")
    private int server_index;
    @JSONField(ordinal = 3,name = "status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getServer_index() {
        return server_index;
    }

    public void setServer_index(int server_index) {
        this.server_index = server_index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
