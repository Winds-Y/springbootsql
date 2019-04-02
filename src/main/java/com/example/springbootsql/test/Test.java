package com.example.springbootsql.test;


import com.example.springbootsql.component.SysKafkaClient;

import java.io.IOException;

public class Test {
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
        try {
            SysKafkaClient.session.getBasicRemote().sendText("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        TestStatic.testStatic();
    }
}
