package com.abhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/showLogin")
    public String showLogin() {
        System.out.println("LoginController.showLogin");
        return "custom_login";
    }

    @GetMapping("/denial")
    public String denialAccess() {
        return "access_denied";
    }

    @PostMapping("/logout-success")
    public String showLogoutPage() {
        return "logout-success"; // This is the name of your new HTML file
    }

}
