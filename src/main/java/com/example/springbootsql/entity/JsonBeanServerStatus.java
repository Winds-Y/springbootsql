package com.example.springbootsql.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class JsonBeanServerStatus {
    @JSONField(ordinal = 1,name = "ui_mask")
    private String uiMask;
    @JSONField(ordinal = 2,name = "server_status_list")
    private List<ServerStatus> serverStatusList;

    public String getUiMask() {
        return uiMask;
    }

    public void setUiMask(String uiMask) {
        this.uiMask = uiMask;
    }

    public List<ServerStatus> getServerStatusList() {
        return serverStatusList;
    }

    public void setServerStatusList(List<ServerStatus> serverStatusList) {
        this.serverStatusList = serverStatusList;
    }
}
