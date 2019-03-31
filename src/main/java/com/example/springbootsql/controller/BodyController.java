package com.example.springbootsql.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootsql.entity.TestPartFresh;
import com.example.springbootsql.entity.TaskMessage;
import com.example.springbootsql.entity.User;
import com.example.springbootsql.repository.TaskMessageRepository;
import com.example.springbootsql.repository.UserRepository;
import com.example.springbootsql.utils.SysKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BodyController {
    private final UserRepository userRepository;
    private final TaskMessageRepository taskMessageRepository;

    @Autowired
    public BodyController(UserRepository userRepository,TaskMessageRepository taskMessageRepository) {
        this.userRepository = userRepository;
        this.taskMessageRepository=taskMessageRepository;
    }

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        System.out.println("in greetingForm");
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute User user) {
        System.out.println("in greetingSubmit");
        System.out.println(user.getAge());
        User newUser = new User();

        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setGender(user.getGender());
        newUser.setEmail(user.getEmail());
        newUser.setCity(user.getCity());
        userRepository.save(user);

        return "result";

    }

    @GetMapping("/all")
    public String getMessage(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "all";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("user", new User());
        System.out.println("in loginPage");
        return "login";
    }

    @GetMapping("/tt")
    public String getStatic(){
        return "redirect:/success.html";
    }

    @PostMapping("/loginResult")
    public String loginResult(@ModelAttribute User user, Model model){
        System.out.println(user.getName());
        String name=user.getName();
        User newUser;
        newUser=userRepository.findByName(name);
        if(newUser==null){
            System.out.println("in loginResult:用户不存在");
            return "redirect:/loginErr.html";
        }else {
            TaskMessage taskMessage=new TaskMessage();
            model.addAttribute("taskMessage",taskMessage);
            System.out.println("in loginResult:登陆成功");
            System.out.println(newUser.getCity());
            System.out.println(newUser.getEmail());
            System.out.println("in loginResult");
//            return "redirect:/success.html";
            return "main";
        }
    }

    @RequestMapping(value = "/distributeTask",method = RequestMethod.POST)
    public String distributeTask(String jsonStr){
        System.out.println("in distributeTask");
        System.out.println(jsonStr);
        JSONObject jsonObject=JSONObject.parseObject(jsonStr);
        String taskName=jsonObject.getString("task_name");
        String targetPersonUrl=jsonObject.getString("target_person_url");
        String taskCode=jsonObject.getString("task_code");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String now=df.format(new Date());

        TaskMessage taskMessage=new TaskMessage();
        taskMessage.setTargetPersonUrl(targetPersonUrl);
        taskMessage.setTaskCode(taskCode);
        taskMessage.setTime(now);
        taskMessage.setTaskName(taskName);
        taskMessageRepository.save(taskMessage);

        String newJsonStr= JSON.toJSONString(taskMessage);
        System.out.println(newJsonStr);
        kafkaTemplate.send("cuc_receive_target_url",newJsonStr);
        return "main::part_div";
    }

//    @KafkaListener(topics = {"cuc_receive_target_url"})
//    public void consumer(String message){
//        System.out.println(message);
//    }

    @RequestMapping("/fruit")
    public String fruit(Model model){
        return "fruit";
    }

    @RequestMapping("detail")
    public String detail(Model model,int id) {

        List<TestPartFresh> fruits = new ArrayList<>();

        if(id == 0) {
            String[] strings={"a","b","c","d"};
            for(int i = 1; i <= strings.length; i++) {
                fruits.add(new TestPartFresh(i,strings[i-1]));
            }
        } else if(id == 1) {
            String[] strings={"e","f","g","h","i","j"};
            for(int i = 1; i <= strings.length; i++) {
                fruits.add(new TestPartFresh(i,strings[i-1]));
            }
        }
        model.addAttribute("fruits",fruits);
        return "fruit::fruit-list";
    }
}
