package com.example.lorem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean loggedIn = (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn"));
        model.addAttribute("loggedIn", loggedIn);
        return "index";
    }
}
