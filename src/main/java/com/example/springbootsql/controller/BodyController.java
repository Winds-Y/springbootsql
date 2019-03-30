package com.example.springbootsql.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.springbootsql.entity.User;
import com.example.springbootsql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BodyController {
    private final UserRepository userRepository;

    @Autowired
    public BodyController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public String loginResult(@ModelAttribute User user){
        System.out.println(user.getName());
        String name=user.getName();
        User newUser;
        newUser=userRepository.findByName(name);
        if(newUser==null){
            System.out.println("in loginResult:用户不存在");
            return "redirect:/loginErr.html";
        }else {
            System.out.println("in loginResult:登陆成功");
            System.out.println(newUser.getCity());
            System.out.println(newUser.getEmail());
            System.out.println("in loginResult");
            return "redirect:/index.html";
        }
    }
}
