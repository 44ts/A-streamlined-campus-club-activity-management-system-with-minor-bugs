package com.campusactivity.service.impl;

import com.campusactivity.entity.Announcement;
import com.campusactivity.mapper.AnnouncementMapper;
import com.campusactivity.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public Announcement addAnnouncement(Announcement announcement) {
        if (announcement.getStatus() == null) {
            announcement.setStatus("published");
        } else if (announcement.getStatus().equals("1")) {
            announcement.setStatus("published");
        } else if (announcement.getStatus().equals("0")) {
            announcement.setStatus("draft");
        }
        announcementMapper.insert(announcement);
        return announcement;
    }

    @Override
    public Announcement updateAnnouncement(Announcement announcement) {
        if (announcement.getStatus().equals("1")) {
            announcement.setStatus("published");
        } else if (announcement.getStatus().equals("0")) {
            announcement.setStatus("draft");
        }
        announcementMapper.update(announcement);
        return announcement;
    }

    @Override
    public void deleteAnnouncement(Integer announcementId) {
        announcementMapper.delete(announcementId);
    }

    @Override
    public Announcement getAnnouncementById(Integer announcementId) {
        return announcementMapper.selectByPrimaryKey(announcementId);
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.selectAll();
    }

    @Override
    public List<Announcement> getAnnouncementsByStatus(String status) {
        return announcementMapper.selectByStatus(status);
    }
}
