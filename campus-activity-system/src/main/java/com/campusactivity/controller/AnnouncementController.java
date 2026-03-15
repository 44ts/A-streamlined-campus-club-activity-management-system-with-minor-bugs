package com.campusactivity.controller;

import com.campusactivity.entity.Announcement;
import com.campusactivity.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/announcement")
    public String announcement(Model model) {
        model.addAttribute("announcements", announcementService.getAnnouncementsByStatus("published"));
        return "announcement";
    }

    @GetMapping("/announcement/list")
    public String list(Model model) {
        model.addAttribute("announcements", announcementService.getAllAnnouncements());
        return "admin/announcement/list";
    }

    @GetMapping("/announcement/add")
    public String add(Model model) {
        model.addAttribute("announcement", new Announcement());
        return "admin/announcement/add";
    }

    @PostMapping("/announcement/add")
    public String add(@ModelAttribute Announcement announcement, RedirectAttributes redirectAttributes) {
        try {
            announcementService.addAnnouncement(announcement);
            redirectAttributes.addFlashAttribute("message", "公告发布成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "公告发布失败");
        }
        return "redirect:/announcement/list";
    }

    @GetMapping("/announcement/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("announcement", announcementService.getAnnouncementById(id));
        return "admin/announcement/edit";
    }

    @PostMapping("/announcement/edit")
    public String edit(@ModelAttribute Announcement announcement, RedirectAttributes redirectAttributes) {
        try {
            announcementService.updateAnnouncement(announcement);
            redirectAttributes.addFlashAttribute("message", "公告更新成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "公告更新失败");
        }
        return "redirect:/announcement/list";
    }

    @GetMapping("/announcement/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            announcementService.deleteAnnouncement(id);
            redirectAttributes.addFlashAttribute("message", "公告删除成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "公告删除失败");
        }
        return "redirect:/announcement/list";
    }
}
