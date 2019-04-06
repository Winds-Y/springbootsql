package com.example.springbootsql.component;

import com.example.springbootsql.entity.TaskMessage;
import com.example.springbootsql.repository.TaskMessageRepository;
import com.example.springbootsql.repository.UserRepository;
import com.example.springbootsql.utils.QueryDataThread;
import com.google.common.collect.Lists;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint("/freshDatabase")
@Component
public class DataQueryWebSocketServer {
    private static int onlineCount=0;
    private static CopyOnWriteArrayList<DataQueryWebSocketServer> webSocketSet= new CopyOnWriteArrayList<>();
    private Session session;

//    @Autowired
//    private  TaskMessageRepository taskMessageRepository;
//    private QueryDataThread queryDataThread;
//



//    private Thread dataThread=new Thread(queryDataThread);

    @OnOpen
    public void onOpen(Session session) {
        this.session=session;
        webSocketSet.add(this);
        System.out.println("DataQueryWebSocketServer start....");
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
//        dataThread.start();
    }

    @OnClose
    public void onClose(){
//        queryDataThread.stopQuery();
        webSocketSet.remove(this);
    }

    @OnMessage
    public void onMessage(String s) {
        System.out.println("DataQueryWebSocketServer onMessage is "+s);
        try {
            sendMessage(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnError
    public void onError(Throwable error){
        System.out.println("DataQueryWebSocketServer wrong");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException{
        //群发消息
        for(DataQueryWebSocketServer item: webSocketSet){
            item.session.getBasicRemote().sendText(message);
        }
    }
}
