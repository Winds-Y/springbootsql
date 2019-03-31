package com.example.springbootsql.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TaskMessage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JSONField(serialize = false)
    private Integer id;

    @JSONField(ordinal = 1)
    private String time;

    @JSONField(ordinal = 2,name = "target_person_url")
    private String targetPersonUrl;

    @JSONField(ordinal = 3,name = "task_code")
    private String taskCode;

    @JSONField(ordinal = 4,name = "task_name")
    private String taskName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTargetPersonUrl() {
        return targetPersonUrl;
    }

    public void setTargetPersonUrl(String targetPersonUrl) {
        this.targetPersonUrl = targetPersonUrl;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", taskName=" + taskName + ", taskCode=" + taskCode + "]";
    }
}
