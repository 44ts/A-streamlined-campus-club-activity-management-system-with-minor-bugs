package com.campusactivity.service;

import com.campusactivity.entity.Announcement;
import java.util.List;

public interface AnnouncementService {
    Announcement addAnnouncement(Announcement announcement);
    Announcement updateAnnouncement(Announcement announcement);
    void deleteAnnouncement(Integer announcementId);
    Announcement getAnnouncementById(Integer announcementId);
    List<Announcement> getAllAnnouncements();
    List<Announcement> getAnnouncementsByStatus(String status);
}
