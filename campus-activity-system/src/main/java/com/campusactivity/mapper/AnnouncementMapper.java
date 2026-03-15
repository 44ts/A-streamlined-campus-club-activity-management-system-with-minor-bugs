package com.campusactivity.mapper;

import com.campusactivity.entity.Announcement;
import java.util.List;

public interface AnnouncementMapper {
    void insert(Announcement announcement);
    void update(Announcement announcement);
    void delete(Integer announcementId);
    Announcement selectByPrimaryKey(Integer announcementId);
    List<Announcement> selectAll();
    List<Announcement> selectByStatus(String status);
}
