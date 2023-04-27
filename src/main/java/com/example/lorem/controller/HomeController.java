package com.example.lorem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean loggedIn = (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn"));
        model.addAttribute("memberEmail", session.getAttribute("memberEmail"));
        model.addAttribute("loggedIn", loggedIn);
        return "index";
    }
}
