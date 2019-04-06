package com.example.springbootsql.utils;

import com.alibaba.fastjson.JSON;
import com.example.springbootsql.component.DataWebSocketClient;
import com.example.springbootsql.component.SyWebSocketClient;
import com.example.springbootsql.entity.JsonBeanServerStatus;
import com.example.springbootsql.entity.ServerStatus;
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

    public static void updateServerStatus(String status,int serverIndex,String server){
        String updateTask="update server_status set status =? where server=? and server_index = ?";
        DBConnFactory.initDBconn();
        try {
            DBConnFactory.setSql(updateTask);
            DBConnFactory.cbsetString(1,status);
            DBConnFactory.cbsetString(2,server);
            DBConnFactory.cbsetInt(3,serverIndex);
            DBConnFactory.executeUpdate();
            DBConnFactory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryProcessDataAndSent2Ui(){
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

    public static void findAllServerStatusAndSent2Ui(){
        String updateTask="select * from server_status";
        DBConnFactory.initDBconn();
        List<ServerStatus> serverStatusList=new ArrayList<>();
        try {
            DBConnFactory.setSql(updateTask);
            ResultSet resultSet=DBConnFactory.executeQuery();
            while (resultSet.next()){
                String server=resultSet.getString("server");
                String status=resultSet.getString("status");
                int server_index=resultSet.getInt("server_index");
                ServerStatus serverStatus=new ServerStatus();
                serverStatus.setServer(server);
                serverStatus.setServer_index(server_index);
                serverStatus.setStatus(status);
                serverStatusList.add(serverStatus);
            }

            JsonBeanServerStatus jsonBeanServerStatus=new JsonBeanServerStatus();
            jsonBeanServerStatus.setUiMask("serverStatus");
            jsonBeanServerStatus.setServerStatusList(serverStatusList);

//            String jsonListstr=JSON.toJSONString(serverStatusList);
//            System.out.println(jsonListstr);

            String jsonStr= JSON.toJSONString(jsonBeanServerStatus);
            System.out.println(jsonStr);
            SyWebSocketClient.session.getAsyncRemote().sendText(jsonStr);
            DBConnFactory.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
