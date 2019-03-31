package com.example.springbootsql.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static com.example.springbootsql.component.WebSocket.wbSockets;

@Component
public class SysKafkaConsumer extends Thread{
    private KafkaConsumer<String,String> consumer;
    private String topic = "cuc_receive_target_url";

    public SysKafkaConsumer(){

    }

    @Override
    public void run(){
        //加载kafka消费者参数
        Properties props = new Properties();
        props.put("bootstrap.servers", "154.8.139.155:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "15000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //创建消费者对象
        consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList(this.topic));
        //死循环，持续消费kafka
        while (true){
            try {
                //消费数据，并设置超时时间
                ConsumerRecords<String, String> records = consumer.poll(100);
                //Consumer message
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                    //Send message to every client
//                    for (WebSocket webSocket :wbSockets){
//                        webSocket.sendMessage(record.value());
//                    }
                }
            }catch (Exception e){

//                System.out.println(e.getMessage());
            }
        }
    }

    public void close() {
        try {
            consumer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //供测试用，若通过tomcat启动需通过其他方法启动线程
    public static void main(String[] args){
        SysKafkaConsumer consumerKafka = new SysKafkaConsumer();
        consumerKafka.start();
    }
}
