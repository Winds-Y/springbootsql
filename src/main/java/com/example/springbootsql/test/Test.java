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
import org.springframework.web.socket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;

@Controller
public class Test {
    public static boolean run=true;
    public static boolean startThread=false;
    @Autowired
    private static UserService userService;

    private void testWebSocket(){
        String serverUrl="ws://localhost:8080/websocket";
        try {
            URI uri=new URI(serverUrl);
//            WebSocketClient client=new TestWebSocketClient(uri,new Draft_6455());
//            client.connect();
//            client.send("this a message");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            SyWebSocketClient.session.getBasicRemote().sendText("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String jsonStr="{'taskcode': \"test\",'data':1}";
    }
}
