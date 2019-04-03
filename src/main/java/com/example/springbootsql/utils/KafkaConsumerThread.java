package com.example.springbootsql.utils;

public class KafkaConsumerThread implements Runnable{
    private boolean stopStatus=true;
    public void stopKafka(){
        stopStatus=false;
    }

    @Override
    public void run() {
        while (stopStatus){

        }
    }
}
