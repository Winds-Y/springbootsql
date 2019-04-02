package com.example.springbootsql.utils;

import com.example.springbootsql.component.SysKafkaClient;
import com.example.springbootsql.component.WebSocket;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import static com.example.springbootsql.component.WebSocket.wbSockets;


public class SysKafkaConsumer {
    private static KafkaConsumer<String,String> consumer;
    private final static String topic="cuc_receive_target_url";
    static {
        Properties properties=new Properties();
        properties.put("bootstrap.servers", "154.8.139.155:9092"); //每个消费者分配独立的组号
        properties.put("group.id", "test1"); //如果value合法，则自动提交偏移量
        properties.put("enable.auto.commit", "true"); //设置多久一次更新被消费消息的偏移量
        properties.put("auto.commit.interval.ms", "1000"); //设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
        properties.put("session.timeout.ms", "30000"); //自动重置offset
        properties.put("auto.offset.reset","earliest");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer= new KafkaConsumer<String, String>(properties);
    }
    public static void consume(){
        consumer.subscribe(Collections.singletonList(topic));
        while (true){
            ConsumerRecords<String, String> record=consumer.poll(100);
            for(ConsumerRecord<String,String> record1:record){
                System.out.printf("offset = %d, key = %s, value = %s\n",record1.offset(), record1.key(), record1.value());
                String message=record1.value();
                try {
                    SysKafkaClient.session.getBasicRemote().sendText(message);
                    System.out.println("kafka send message to websocket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        consume();
    }
}
