package com.campusactivity.config;

import com.campusactivity.entity.Activity;
import com.campusactivity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ActivityService activityService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 检查数据库中是否已有活动数据
        if (activityService.getAll().isEmpty()) {
            // 初始化活动数据
            initializeActivities();
        }
    }

    private void initializeActivities() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        try {
            // 乒乓球团队赛
            Activity activity1 = new Activity();
            activity1.setTitle("乒乓球团队赛");
            activity1.setType("体育社团");
            activity1.setDescription("校园乒乓球团队赛，欢迎各位同学踊跃参加，展现团队合作精神。");
            activity1.setLocation("学校文全室");
            activity1.setStartTime(sdf.parse("2026-01-06 09:00"));
            activity1.setEndTime(sdf.parse("2026-01-06 12:00"));
            activity1.setOrganizerId(1);
            activity1.setStatus("published");
            activityService.save(activity1);

            // 趣味乒乓球比赛
            Activity activity2 = new Activity();
            activity2.setTitle("趣味乒乓球比赛");
            activity2.setType("体育社团");
            activity2.setDescription("趣味乒乓球比赛，以娱乐为主，增进同学间的友谊。");
            activity2.setLocation("体育馆");
            activity2.setStartTime(sdf.parse("2026-01-08 14:00"));
            activity2.setEndTime(sdf.parse("2026-01-08 17:00"));
            activity2.setOrganizerId(1);
            activity2.setStatus("published");
            activityService.save(activity2);

            // 英语比赛开幕报名
            Activity activity3 = new Activity();
            activity3.setTitle("英语比赛开幕报名");
            activity3.setType("英语社");
            activity3.setDescription("校园英语比赛开幕报名，展示你的英语能力。");
            activity3.setLocation("行政楼2楼214");
            activity3.setStartTime(sdf.parse("2026-01-04 09:00"));
            activity3.setEndTime(sdf.parse("2026-01-04 11:00"));
            activity3.setOrganizerId(1);
            activity3.setStatus("published");
            activityService.save(activity3);

            // 单词拼写大赛
            Activity activity4 = new Activity();
            activity4.setTitle("单词拼写大赛");
            activity4.setType("英语社");
            activity4.setDescription("单词拼写大赛，考验你的词汇量和拼写能力。");
            activity4.setLocation("明德楼3楼3306");
            activity4.setStartTime(sdf.parse("2026-01-08 10:00"));
            activity4.setEndTime(sdf.parse("2026-01-08 12:00"));
            activity4.setOrganizerId(1);
            activity4.setStatus("published");
            activityService.save(activity4);

            // 校园风光摄影展
            Activity activity5 = new Activity();
            activity5.setTitle("校园风光摄影展");
            activity5.setType("摄影社");
            activity5.setDescription("校园风光摄影展，展示校园美丽的风景和人文景观。");
            activity5.setLocation("图书馆一楼展厅");
            activity5.setStartTime(sdf.parse("2026-01-10 09:00"));
            activity5.setEndTime(sdf.parse("2026-01-15 17:00"));
            activity5.setOrganizerId(1);
            activity5.setStatus("published");
            activityService.save(activity5);

            // 新年音乐晚会
            Activity activity6 = new Activity();
            activity6.setTitle("新年音乐晚会");
            activity6.setType("音乐社");
            activity6.setDescription("新年音乐晚会，用音乐迎接新的一年。");
            activity6.setLocation("学校礼堂");
            activity6.setStartTime(sdf.parse("2026-01-12 19:00"));
            activity6.setEndTime(sdf.parse("2026-01-12 21:00"));
            activity6.setOrganizerId(1);
            activity6.setStatus("published");
            activityService.save(activity6);

            // 社区志愿服务活动
            Activity activity7 = new Activity();
            activity7.setTitle("社区志愿服务活动");
            activity7.setType("志愿者协会");
            activity7.setDescription("社区志愿服务活动，为社区居民提供帮助和服务。");
            activity7.setLocation("阳光社区");
            activity7.setStartTime(sdf.parse("2026-01-13 09:00"));
            activity7.setEndTime(sdf.parse("2026-01-13 16:00"));
            activity7.setOrganizerId(1);
            activity7.setStatus("published");
            activityService.save(activity7);

            System.out.println("活动数据初始化完成！");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("活动数据初始化失败！");
        }
    }
}