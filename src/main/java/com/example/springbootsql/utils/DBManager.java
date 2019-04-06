package com.example.springbootsql.utils;

import com.alibaba.fastjson.JSON;
import com.example.springbootsql.component.DataWebSocketClient;
import com.example.springbootsql.entity.TaskMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    public static void updateProcess(String task_code,int process){
        String updateTask="update task_message set progress =? where task_code=?";
        DBConnFactory.initDBconn();
        try {
            DBConnFactory.setSql(updateTask);
            DBConnFactory.cbsetInt(1,process);
            DBConnFactory.cbsetString(2,task_code);
            DBConnFactory.executeUpdate();
            DBConnFactory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryDataAndSent2Ui(){
        String updateTask="select * from task_message";
        DBConnFactory.initDBconn();
        List<TaskMessage> taskMessageList=new ArrayList<>();
        try {
            DBConnFactory.setSql(updateTask);
            ResultSet resultSet=DBConnFactory.executeQuery();
            while (resultSet.next()){
                String time=resultSet.getString("time");
                String task_code=resultSet.getString("task_code");
                int process=resultSet.getInt("progress");
                String target_person_url=resultSet.getString("target_person_url");
                TaskMessage taskMessage=new TaskMessage();
                taskMessage.setTime(time);
                taskMessage.setTaskCode(task_code);
                taskMessage.setProgress(process);
                taskMessage.setTargetPersonUrl(target_person_url);
                taskMessageList.add(taskMessage);
            }
            String jsonStr= JSON.toJSONString(taskMessageList);
            DataWebSocketClient.session.getAsyncRemote().sendText(jsonStr);
            DBConnFactory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
