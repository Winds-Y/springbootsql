package com.example.springbootsql.mapper;

import com.example.springbootsql.entity.TaskMessage;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TaskMessageMapper {
    @Update("update task_message set task_progress = #{task_progress} where task_code=#{task_code}")
    void update(TaskMessage taskMessage);
}
