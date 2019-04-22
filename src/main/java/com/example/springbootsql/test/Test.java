package com.example.springbootsql.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootsql.component.DataWebSocketClient;
import com.example.springbootsql.component.SyWebSocketClient;
import com.example.springbootsql.component.TestKafkaConsumer;
import com.example.springbootsql.controller.BodyController;
import com.example.springbootsql.entity.Friends;
import com.example.springbootsql.entity.TaskMessage;
import com.example.springbootsql.entity.User;
import com.example.springbootsql.service.FriendsService;
import com.example.springbootsql.service.UserService;
import com.example.springbootsql.utils.DBConnFactory;
import com.example.springbootsql.utils.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;

@Component
public class Test {
    public static boolean run=true;
    public static boolean startThread=false;
    @Autowired
    private static UserService userService;
    @Autowired
    FriendsService friendsService;

    private static Test test;
    @PostConstruct
    public void init(){
        test=this;
        test.friendsService=this.friendsService;
    }
    public void run(){
        Friends friends=new Friends();
        friends.setName("test");
        friends.setOther_names("test1");
        friends.setFacebook_url("sss");
        friends.setRelationship("xx");
        friends.setFavorite_quotes("xx");
        friends.setIntroduction("xx");
        friends.setGender("dd");
        friends.setLanguage("dx");
        friends.setInterest_in("xx");
        friends.setReligious_views("xx");
        friends.setPolitical_views("xx");
        friends.setBirth_day("xx");
        friends.setBlood_type("xx");
        friends.setEmail("xx");
        friends.setWebsites("xx");
        friends.setSocial_link("xx");
        friends.setMobile_phone("xx");
        friends.setProfessional_skills("xx");
        test.friendsService.save(friends);
    }

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
        Test test1=new Test();
        test1.run();
    }
}
