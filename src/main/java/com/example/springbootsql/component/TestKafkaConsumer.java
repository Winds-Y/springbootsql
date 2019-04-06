package com.example.springbootsql.component;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootsql.utils.DBManager;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;


@Component
public class TestKafkaConsumer extends Thread{
    private static KafkaConsumer<String,String> consumer;
    private String topic = "cuc_master2_client";
    public static boolean isRunning =false;
    public static void stopIt(){
        isRunning =false;
    }
    public TestKafkaConsumer(){

    }

    @Override
    public void run(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "154.8.139.155:9092");
        props.put("group.id", "cuc_master2_client_1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset","earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList(this.topic));
//        User user=userRepository.findByName("changze");
//        if(user!=null){
//            System.out.println(user.getAge());
//        }
        while (true){
            isRunning=true;
            try {
                //消费数据，并设置超时时间
                ConsumerRecords<String, String> records = consumer.poll(100);
                //Consumer message
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("From Kafka cuc_master2_client："+record.value());
                    String jsonStr=record.value();
                    JSONObject jsonObject=JSONObject.parseObject(jsonStr);
                    String mask=jsonObject.getString("mask");
                    switch (mask){
                        case "process":
                            String task_code=jsonObject.getString("task_code");
                            int process=jsonObject.getInteger("process");
                            String target_person_url=jsonObject.getString("target_person_url");
                            DBManager.updateProcess(task_code,process);
                            DBManager.queryProcessDataAndSent2Ui();
                            break;
                        case "data":
                            break;
                        case "server_status":
                            String server=jsonObject.getString("server");
                            int serverIndex=jsonObject.getInteger("server_index");
                            String status=jsonObject.getString("status");
                            DBManager.updateServerStatus(status,serverIndex,server);
                            DBManager.findAllServerStatusAndSent2Ui();
                            break;
                    }

//                    Send message to every client
//                    for (WebSocket webSocket :wbSockets){
//                        webSocket.sendMessage(record.value());
//                    }
                }
            }catch (Exception e){
//                System.out.println(e.getMessage());
            }
        }
    }

    public static void close() {
        try {
            if(consumer!=null){
                consumer.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        TestKafkaConsumer consumerKafka = new TestKafkaConsumer();
        consumerKafka.start();
    }
}
