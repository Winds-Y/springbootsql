package com.example.springbootsql.utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SysKafkaProducer {
    private static KafkaProducer<String,String> producer;
    private final static String topic="cuc_distribute_task";
    private SysKafkaProducer(){
        Properties properties=new Properties();
        //kafka服务器的ip地址
        properties.put("bootstrap.servers", "154.8.139.155:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer= new KafkaProducer<>(properties);
    }
    public static void produce(String data){
        producer.send(new ProducerRecord<String, String>(topic,data));
        producer.close();
    }

}
