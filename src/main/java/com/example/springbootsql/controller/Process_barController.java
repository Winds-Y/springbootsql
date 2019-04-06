package com.example.springbootsql.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootsql.component.DataWebSocketClient;
import com.example.springbootsql.entity.TaskMessage;
import com.example.springbootsql.repository.TaskMessageRepository;
import com.example.springbootsql.test.Test;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Process_barController {
    @Autowired
    public TaskMessageRepository taskMessageRepository;
    @GetMapping("/login")
    public String login(Model model){
        Thread queryData=new Thread(() -> {
            while (Test.run){
                //查询数据库
                Iterable<TaskMessage> tasks = taskMessageRepository.findAll();
                List<TaskMessage> taskList= Lists.newArrayList(tasks);

                //如果达到条件修改Test.run为false
//                if(条件){
//                    Test.run=false;
//                }


                String jsonStr= JSON.toJSONString(taskList);
                System.out.println("queryData----: "+jsonStr);
                DataWebSocketClient.session.getAsyncRemote().sendText(jsonStr);
                try {
                    Thread.sleep(1000*60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        queryData.start();
        return "main";
    }
}
