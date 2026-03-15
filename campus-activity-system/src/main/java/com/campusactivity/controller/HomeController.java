package com.campusactivity.controller;

import com.campusactivity.entity.Activity;
import com.campusactivity.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class HomeController {

    @Resource
    private ActivityService activityService;

    @GetMapping("/")
    public String index(Model model) {
        // 从数据库获取已发布的活动
        List<Activity> activities = activityService.getByStatus("published");
        model.addAttribute("activities", activities);
        return "index";
    }
}
