package com.example.springbootsql.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.example.springbootsql.entity.FbUser;
import com.example.springbootsql.entity.Friends;
import com.example.springbootsql.entity.JsonFriends;
import com.example.springbootsql.utils.DBManager;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                            int currentNum=jsonObject.getInteger("current_num");
                            int totalNum=jsonObject.getInteger("total_num");
                            JSONArray dataArray=jsonObject.getJSONArray("data");
                            if(dataArray.size()!=0){
                                FbUser fbUser=new FbUser();
                                JSONObject data=dataArray.getJSONObject(0);
                                JSONArray users=data.getJSONArray("user");
                                if (users.size() != 0) {
                                    JSONObject oneUser=users.getJSONObject(0);
                                    String name=oneUser.getString("name");
                                    String facebookUrl=oneUser.getString("facebook_url");
                                    String relationship=oneUser.getString("relationship");
                                    String favorite_quotes=oneUser.getString("favorite_quotes");
                                    String introduction=oneUser.getString("introduction");
                                    String gender=oneUser.getString("gender");
                                    String language=oneUser.getString("language");
                                    String interest_in=oneUser.getString("interest_in");
                                    String religious_views=oneUser.getString("religious_views");
                                    String political_views=oneUser.getString("political_views");
                                    String birth_day=oneUser.getString("birth_day");
                                    String professional_skills=oneUser.getString("professional_skills");

                                    fbUser.setUi_mask("bubble");
                                    fbUser.setName(name);
                                    fbUser.setFacebookUrl(facebookUrl);
                                    fbUser.setRelationship(relationship);
                                    fbUser.setFavorite_quotes(favorite_quotes);
                                    fbUser.setIntroduction(introduction);
                                    fbUser.setGender(gender);
                                    fbUser.setLanguage(language);
                                    fbUser.setInterest_in(interest_in);
                                    fbUser.setReligious_views(religious_views);
                                    fbUser.setPolitical_views(political_views);
                                    fbUser.setBirth_day(birth_day);
                                    fbUser.setProfessional_skills(professional_skills);

                                    JSONArray friendshipArray=data.getJSONArray("friendship");
                                    List<String> friendNameList=new ArrayList<>();
                                    for(int i=0;i<friendshipArray.size();i++){
                                        JSONObject friendship=friendshipArray.getJSONObject(i);
                                        String friendName=friendship.getString("friend_name");
                                        friendNameList.add(friendName);
                                    }
                                    fbUser.setFriendsList(friendNameList);
                                    String fbUserJsonStr= JSON.toJSONString(fbUser);
                                    SyWebSocketClient.session.getAsyncRemote().sendText(fbUserJsonStr);

                                }


                                JSONArray friendsArray=data.getJSONArray("friends");
                                if(friendsArray.size()!=0){
                                    List<Friends> friendsList=new ArrayList<>();
                                    for(int i=0;i<friendsArray.size();i++){
                                        JSONObject friends=friendsArray.getJSONObject(i);
                                        String name=friends.getString("name");
                                        String facebook_url=friends.getString("facebook_url");
                                        String gender=friends.getString("gender");
                                        String birth_day=friends.getString("birth_day");
                                        String introduction=friends.getString("introduction");

                                        Friends friends1=new Friends();
                                        friends1.setName(name);
                                        friends1.setFacebook_url(facebook_url);
                                        friends1.setGender(gender);
                                        friends1.setBirth_day(birth_day);
                                        friends1.setIntroduction(introduction);
                                        friendsList.add(friends1);
                                    }
                                    JsonFriends jsonFriends=new JsonFriends();
                                    jsonFriends.setUi_mask("friendsData");
                                    jsonFriends.setFriendsList(friendsList);
                                    String friendsJsonStr=JSON.toJSONString(jsonFriends);
                                    SyWebSocketClient.session.getAsyncRemote().sendText(friendsJsonStr);
                                }

                            }


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
