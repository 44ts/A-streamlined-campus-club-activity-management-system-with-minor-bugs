package com.campusactivity.controller;

import com.campusactivity.entity.ActivityRegistration;
import com.campusactivity.entity.User;
import com.campusactivity.service.ActivityRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动报名控制器
 */
@Controller
@RequestMapping("/registration")
public class ActivityRegistrationController {
    
    @Resource
    private ActivityRegistrationService registrationService;
    
    /**
     * 用户报名活动（AJAX）
     */
    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(@RequestParam("activityId") Integer activityId,
                                       @RequestParam(value = "remark", required = false) String remark,
                                       HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        // 检查登录状态
        User user = (User) session.getAttribute("user");
        if (user == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            result.put("needLogin", true);
            return result;
        }
        
        // 检查是否已报名
        if (registrationService.isRegistered(user.getUserId(), activityId)) {
            result.put("success", false);
            result.put("message", "您已报名该活动");
            return result;
        }
        
        // 执行报名
        boolean success = registrationService.register(user.getUserId(), activityId, remark);
        if (success) {
            result.put("success", true);
            result.put("message", "报名成功，等待审核");
        } else {
            result.put("success", false);
            result.put("message", "报名失败，请稍后重试");
        }
        
        return result;
    }
    
    /**
     * 取消报名
     */
    @GetMapping("/cancel/{registrationId}")
    public String cancel(@PathVariable("registrationId") Integer registrationId,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        // 验证权限：只能取消自己的报名
        ActivityRegistration registration = registrationService.getById(registrationId);
        if (registration != null && registration.getUserId().equals(user.getUserId())) {
            boolean success = registrationService.cancelRegistration(registrationId);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "取消报名成功");
            } else {
                redirectAttributes.addFlashAttribute("error", "取消报名失败");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "无权取消该报名");
        }
        
        return "redirect:/registration/my";
    }
    
    /**
     * 我的报名记录
     */
    @GetMapping("/my")
    public String myRegistrations(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        List<ActivityRegistration> registrations = registrationService.getUserRegistrations(user.getUserId());
        model.addAttribute("registrations", registrations);
        
        return "registration/my";
    }
    
    /**
     * 查看活动报名列表（管理员）
     */
    @GetMapping("/activity/{activityId}")
    public String activityRegistrations(@PathVariable("activityId") Integer activityId,
                                       @RequestParam(value = "status", required = false) String status,
                                       Model model,
                                       HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        // 只有管理员可以查看
        if (!"admin".equals(user.getRole())) {
            return "redirect:/";
        }
        
        List<ActivityRegistration> registrations;
        if (status != null) {
            registrations = registrationService.getActivityRegistrationsByStatus(activityId, status);
        } else {
            registrations = registrationService.getActivityRegistrations(activityId);
        }
        
        // 打印用户头像路径，用于调试
        for (ActivityRegistration reg : registrations) {
            if (reg.getUser() != null) {
                System.out.println("User: " + reg.getUser().getUsername() + ", Avatar: " + reg.getUser().getAvatar());
            }
        }
        
        model.addAttribute("registrations", registrations);
        model.addAttribute("activityId", activityId);
        model.addAttribute("status", status);
        
        return "admin/registration/list";
    }
    
    /**
     * 查看所有活动报名列表（管理员）
     */
    @GetMapping("/manage")
    public String manageRegistrations(@RequestParam(value = "status", required = false) String status,
                                     Model model,
                                     HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        // 只有管理员可以查看
        if (!"admin".equals(user.getRole())) {
            return "redirect:/";
        }
        
        List<ActivityRegistration> registrations;
        if (status != null) {
            registrations = registrationService.getAllRegistrationsByStatus(status);
        } else {
            registrations = registrationService.getAllRegistrations();
        }
        model.addAttribute("registrations", registrations);
        model.addAttribute("status", status);
        
        return "admin/registration/manage";
    }
    
    /**
     * 审核报名 - 通过
     */
    @GetMapping("/approve/{registrationId}")
    public String approve(@PathVariable("registrationId") Integer registrationId,
                         @RequestParam(value = "activityId", required = false) Integer activityId,
                         @RequestParam(value = "from", required = false) String from,
                         RedirectAttributes redirectAttributes,
                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"admin".equals(user.getRole())) {
            return "redirect:/user/login";
        }
        
        boolean success = registrationService.approveRegistration(registrationId);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "审核通过");
        } else {
            redirectAttributes.addFlashAttribute("error", "审核失败");
        }
        
        if ("manage".equals(from)) {
            return "redirect:/registration/manage";
        } else {
            return "redirect:/registration/activity/" + activityId;
        }
    }
    
    /**
     * 审核报名 - 拒绝
     */
    @GetMapping("/reject/{registrationId}")
    public String reject(@PathVariable("registrationId") Integer registrationId,
                        @RequestParam(value = "activityId", required = false) Integer activityId,
                        @RequestParam(value = "from", required = false) String from,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"admin".equals(user.getRole())) {
            return "redirect:/user/login";
        }
        
        boolean success = registrationService.rejectRegistration(registrationId);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "已拒绝");
        } else {
            redirectAttributes.addFlashAttribute("error", "操作失败");
        }
        
        if ("manage".equals(from)) {
            return "redirect:/registration/manage";
        } else {
            return "redirect:/registration/activity/" + activityId;
        }
    }
    
    /**
     * 检查报名状态（AJAX）
     */
    @GetMapping("/check/{activityId}")
    @ResponseBody
    public Map<String, Object> checkRegistration(@PathVariable("activityId") Integer activityId,
                                                 HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        User user = (User) session.getAttribute("user");
        if (user == null) {
            result.put("isRegistered", false);
            result.put("needLogin", true);
            return result;
        }
        
        boolean isRegistered = registrationService.isRegistered(user.getUserId(), activityId);
        result.put("isRegistered", isRegistered);
        result.put("needLogin", false);
        
        return result;
    }
}
