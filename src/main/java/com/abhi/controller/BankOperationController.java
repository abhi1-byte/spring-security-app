package com.abhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bank")
public class BankOperationController {

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/offers")
    public String showOffers() {
        return "offers";
    }

    @GetMapping("/balance")
    public String checkBalance() {
        return "balance";
    }

    @GetMapping("/loan")
    public String approveLoan() {
        return "loan";
    }
}
