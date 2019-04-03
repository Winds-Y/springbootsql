package com.example.springbootsql.component;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;

public class SyWebSocketClient {
    public static Session session=null;
    static {
        WebSocketContainer container= ContainerProvider.getWebSocketContainer();
        String serverUrl="ws://localhost:8080/websocket";
        try {
            session=container.connectToServer(Client.class, URI.create(serverUrl));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }
}
