package com.campusactivity.controller;

import com.campusactivity.entity.Activity;
import com.campusactivity.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;
    
    private static final String UPLOAD_DIR = "uploads/activities/";

    // 管理员活动管理页面
    @GetMapping("/manage")
    public String manage(Model model) {
        List<Activity> activities = activityService.getAll();
        model.addAttribute("activities", activities);
        return "admin/activity/manage";
    }

    // 待审核活动页面
    @GetMapping("/pending")
    public String pending(Model model) {
        List<Activity> activities = activityService.getByStatus("draft");
        model.addAttribute("activities", activities);
        return "admin/activity/pending";
    }

    // 添加活动页面
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("activity", new Activity());
        return "admin/activity/add";
    }

    // 编辑活动页面
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Activity activity = activityService.getById(id);
        model.addAttribute("activity", activity);
        return "admin/activity/edit";
    }

    // 保存活动
    @PostMapping("/save")
    public String save(Activity activity, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        // 处理图片上传
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String imageUrl = saveImage(imageFile);
                activity.setImage(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (activity.getActivityId() == null) {
            // 设置默认组织者为管理员（假设管理员ID为1）
            activity.setOrganizerId(1);
            activityService.save(activity);
        } else {
            activityService.update(activity);
        }
        return "redirect:/activity/manage";
    }
    
    // 图片上传接口
    @PostMapping("/upload-image")
    @ResponseBody
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return "error";
        }
        
        try {
            String imageUrl = saveImage(file);
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
    
    // 保存图片文件
    private String saveImage(MultipartFile file) throws IOException {
        // 检查文件类型
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IOException("只能上传图片文件");
        }
        
        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String filename = UUID.randomUUID().toString() + extension;
        
        // 确保上传目录存在
        String uploadPath = System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/static/" + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        
        // 保存文件
        Path filePath = Paths.get(uploadPath + filename);
        Files.write(filePath, file.getBytes());
        
        // 返回相对URL路径（不带前导斜杠，模板中用 @{|/${image}|} 拼接）
        return UPLOAD_DIR + filename;
    }

    // 删除活动
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        activityService.delete(id);
        return "redirect:/activity/manage";
    }

    // 审核通过
    @GetMapping("/approve/{id}")
    public String approve(@PathVariable("id") Integer id) {
        activityService.approve(id);
        return "redirect:/activity/pending";
    }

    // 审核拒绝
    @GetMapping("/reject/{id}")
    public String reject(@PathVariable("id") Integer id) {
        activityService.reject(id);
        return "redirect:/activity/pending";
    }

    // 活动详情页面
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        Activity activity = activityService.getById(id);
        model.addAttribute("activity", activity);
        return "activity/detail";
    }

    // 前台活动列表
    @GetMapping("/list")
    public String list(Model model) {
        List<Activity> activities = activityService.getByStatus("published");
        model.addAttribute("activities", activities);
        return "activity/list";
    }
}
