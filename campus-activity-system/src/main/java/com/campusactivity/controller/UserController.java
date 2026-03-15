package com.campusactivity.controller;

import com.campusactivity.entity.User;
import com.campusactivity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 头像上传目录(相对于static目录)
    private static final String UPLOAD_DIR = "uploads/avatars/";

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.register(user);
            redirectAttributes.addFlashAttribute("message", "注册成功，请登录");
            return "redirect:/user/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "注册失败，请重试");
            return "redirect:/user/register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes redirectAttributes) {
        // 先检查用户是否被禁用
        if (userService.isUserDisabled(username)) {
            redirectAttributes.addFlashAttribute("error", "用户状态被禁用");
            return "redirect:/user/login";
        }
        // 再验证密码
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        // 刷新用户信息
        User latestUser = userService.getById(user.getUserId());
        session.setAttribute("user", latestUser);
        model.addAttribute("user", latestUser);
        return "user/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User user, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            User currentUser = (User) session.getAttribute("user");
            user.setUserId(currentUser.getUserId());
            user.setAvatar(currentUser.getAvatar()); // 保持原头像
            user.setRole(currentUser.getRole()); // 保持原角色
            user.setStatus(currentUser.getStatus()); // 保持原状态
            userService.update(user);
            session.setAttribute("user", userService.getById(user.getUserId()));
            redirectAttributes.addFlashAttribute("message", "个人信息更新成功");
            return "redirect:/user/profile";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "更新失败，请重试");
            return "redirect:/user/profile";
        }
    }

    @PostMapping("/profile/upload-avatar")
    @ResponseBody
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return "{\"success\": false, \"message\": \"请先登录\"}";
            }

            if (file.isEmpty()) {
                return "{\"success\": false, \"message\": \"请选择文件\"}";
            }

            // 检查文件类型
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return "{\"success\": false, \"message\": \"只能上传图片文件\"}";
            }

            // 创建上传目录
            String uploadPath = System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/static/" + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID().toString() + extension;
            
            // 保存文件
            Path filePath = Paths.get(uploadPath + filename);
            Files.write(filePath, file.getBytes());

            // 更新用户头像路径（不带前导斜杠，与活动图片格式保持一致）
            String avatarUrl = UPLOAD_DIR + filename;
            user.setAvatar(avatarUrl);
            userService.update(user);
            
            // 更新session
            User updatedUser = userService.getById(user.getUserId());
            session.setAttribute("user", updatedUser);

            return "{\"success\": true, \"message\": \"头像上传成功\", \"avatarUrl\": \"" + avatarUrl + "\"}";
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"上传失败: " + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/list")
    public String showUserList(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/enable/{id}")
    public String enableUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.enableUser(id);
            redirectAttributes.addFlashAttribute("message", "用户已启用");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "操作失败，请重试");
        }
        return "redirect:/user/list";
    }

    @GetMapping("/disable/{id}")
    public String disableUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.disableUser(id);
            redirectAttributes.addFlashAttribute("message", "用户已禁用");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "操作失败，请重试");
        }
        return "redirect:/user/list";
    }
}
