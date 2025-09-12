package com.abhi.controller;

import com.abhi.model.UserDetails;
import com.abhi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(@ModelAttribute("userInfo") UserDetails userDetails) {
        return "user_register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userInfo") UserDetails userDetails, Map<String, Object> map) {
        System.out.println("Implementation object of MODEL" + map.getClass().getName());
        String registerMsg = userService.userRegistration(userDetails);
        map.put("register", registerMsg);
        return "user_registered_success";
    }
}
