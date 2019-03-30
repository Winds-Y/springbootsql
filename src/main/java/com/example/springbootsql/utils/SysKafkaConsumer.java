package com.example.springbootsql.utils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class SysKafkaConsumer {
    private static KafkaConsumer<String,String>consumer;
    private final static String topic="testkafka";
    private SysKafkaConsumer(){
        Properties properties=new Properties();
        properties.put("bootstrap.servers", "192.168.237.6:9092"); //每个消费者分配独立的组号
        properties.put("group.id", "test2"); //如果value合法，则自动提交偏移量
        properties.put("enable.auto.commit", "true"); //设置多久一次更新被消费消息的偏移量
        properties.put("auto.commit.interval.ms", "1000"); //设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
        properties.put("session.timeout.ms", "30000"); //自动重置offset
        properties.put("auto.offset.reset","earliest");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer= new KafkaConsumer<>(properties);
    }
    private void consume(){
        consumer.subscribe(Collections.singletonList(topic));
        while (true){
            ConsumerRecords<String, String> record=consumer.poll(100);
            for(ConsumerRecord<String,String> record1:record){
                System.out.printf("offset = %d, key = %s, value = %s\n",record1.offset(), record1.key(), record1.value());
            }
        }
    }

    public static void main(String[] args) {
        new SysKafkaConsumer().consume();
    }
}
