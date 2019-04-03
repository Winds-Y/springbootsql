package com.example.springbootsql.utils;

import com.example.springbootsql.component.DataQueryWebSocketServer;
import com.example.springbootsql.entity.TaskMessage;
import com.example.springbootsql.repository.TaskMessageRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class QueryDataThread implements Runnable {
    private boolean stopStatus=true;
    @Autowired
    private  TaskMessageRepository taskMessageRepository;
    public QueryDataThread(TaskMessageRepository taskMessageRepository){
        this.taskMessageRepository=taskMessageRepository;
    }
    public void stopQuery(){
        stopStatus=false;
    }


    @Override
    public void run() {
//        DataQueryWebSocketServer dataQueryWebSocketServer=new DataQueryWebSocketServer();
//        while (stopStatus){
//            try {
//                Iterable<TaskMessage> tasks = taskMessageRepository.findAll();
//                System.out.println("--------------------Database Query------------------");
//                List<TaskMessage> taskList= Lists.newArrayList(tasks);
//                for(TaskMessage taskMessage:taskList){
//                    try {
//                        dataQueryWebSocketServer.onMessage(taskMessage.getTaskName());
//                        System.out.println("--------------------Database Query-  taskMessage.getTaskName()-----------------");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                Thread.sleep(1000*60);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
