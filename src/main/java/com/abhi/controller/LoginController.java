package com.abhi.controller;

import com.abhi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLogin() {
        System.out.println("LoginController.showLogin");
        return "custom_login";
    }


    /*@PostMapping("/login")
    public String validateLogin(@RequestParam String username, @RequestParam String password, Map<String,Object> model){
        System.out.println("Impl os MODEL ::"+model.getClass().getName());
        System.out.println("Username ::"+username+"Password ::"+password);
        model.put("username",username);
        return "login_success";
    }*/

    @GetMapping("/test")
    public String validateUser() {
        return "";
    }
}
