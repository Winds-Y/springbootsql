package com.example.springbootsql.controller;

import com.example.springbootsql.entity.TestPartFresh;
import com.example.springbootsql.entity.TaskMessage;
import com.example.springbootsql.entity.User;
import com.example.springbootsql.repository.TaskMessageRepository;
import com.example.springbootsql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

    @PostMapping("/distributeTask")
    public void distributeTask(@ModelAttribute TaskMessage taskMessage, HttpServletResponse response){
        System.out.println("in distributeTask");
        System.out.println(taskMessage.getTaskName());
        System.out.println(taskMessage.getTargetPersonUrl());
        System.out.println(taskMessage.getTaskCode());
        try {
            response.getWriter().print("afdsfsadf dsfsfsdfsdfsdafsd ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/fruitsssss")
    public String mydetail(Model model){
        model.addAttribute("partial",22);
        System.out.println("in mydetail");
        return "main::partial";
    }

    @RequestMapping("/fruit")
    public String fruit(Model model){
        return "fruit";
    }


    @RequestMapping("detail")
    public String detail(Model model,int id) {

        List<TestPartFresh> fruits = new ArrayList<>();

        if(id == 0) {
            String[] strings={"香蕉","苹果","凤梨","西瓜"};
            for(int i = 1; i <= strings.length; i++) {
                fruits.add(new TestPartFresh(i,strings[i-1]));
            }
        } else if(id == 1) {
            String[] strings={"菠萝","草莓","西红柿","黑莓","百香果","葡萄"};
            for(int i = 1; i <= strings.length; i++) {
                fruits.add(new TestPartFresh(i,strings[i-1]));
            }
        }
        model.addAttribute("fruits",fruits);
        return "fruit::fruit-list";
    }
}
