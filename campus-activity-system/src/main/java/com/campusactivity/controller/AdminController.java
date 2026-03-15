package com.campusactivity.controller;

import com.campusactivity.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @GetMapping("/admin/profile")
    public String adminProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        return "admin/profile";
    }
}
