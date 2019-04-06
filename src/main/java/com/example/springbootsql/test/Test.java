package com.example.springbootsql.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootsql.component.DataWebSocketClient;
import com.example.springbootsql.component.SyWebSocketClient;
import com.example.springbootsql.component.TestKafkaConsumer;
import com.example.springbootsql.controller.BodyController;
import com.example.springbootsql.entity.TaskMessage;
import com.example.springbootsql.entity.User;
import com.example.springbootsql.service.UserService;
import com.example.springbootsql.utils.DBConnFactory;
import com.example.springbootsql.utils.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Test {
    public static boolean run;
    @Autowired
    private static UserService userService;

    public static void main(String[] args) {
//        String serverUrl="ws://localhost:8080/websocket";
//        try {
//            URI uri=new URI(serverUrl);
//            WebSocketClient client=new TestWebSocketClient(uri,new Draft_6455());
//            client.connect();
//            client.send("this a message");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        try {
//            SyWebSocketClient.session.getBasicRemote().sendText("hello");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        TestKafkaConsumer consumerKafka = new TestKafkaConsumer();
        consumerKafka.start();

    }
}
