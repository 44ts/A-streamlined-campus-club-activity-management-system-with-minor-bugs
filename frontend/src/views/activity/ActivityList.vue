<template>
  <div class="activity-list-container">
    <el-card class="activity-list-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <h3>活动列表</h3>
        </div>
      </template>
      <div class="card-body">
        <el-row :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" v-for="activity in activityList" :key="activity.activityId" class="activity-item">
            <el-card shadow="hover" class="activity-card">
              <template #header>
                <div class="activity-card-header">
                  <h4>{{ activity.title }}</h4>
                  <el-tag :type="getActivityStatusType(activity.status)">{{ getActivityStatusText(activity.status) }}</el-tag>
                </div>
              </template>
              <div class="activity-card-body">
                <p class="activity-description">{{ activity.description }}</p>
                <div class="activity-info">
                  <el-row :gutter="10" class="info-row">
                    <el-col :span="12">
                      <el-icon class="info-icon"><calendar /></el-icon>
                      <span>{{ formatDate(activity.startTime) }}</span>
                    </el-col>
                    <el-col :span="12">
                      <el-icon class="info-icon"><position /></el-icon>
                      <span>{{ activity.location }}</span>
                    </el-col>
                  </el-row>
                  <el-row :gutter="10" class="info-row">
                    <el-col :span="24">
                      <el-icon class="info-icon"><user /></el-icon>
                      <span>组织者: {{ activity.organizerName }}</span>
                    </el-col>
                  </el-row>
                </div>
                <div class="activity-actions">
                  <el-button type="primary" size="small" class="action-button">查看详情</el-button>
                  <el-button type="success" size="small" class="action-button">立即报名</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { Calendar, Position, User } from '@element-plus/icons-vue'

export default {
  name: 'ActivityList',
  components: {
    Calendar,
    Position,
    User
  },
  setup() {
    const activityList = ref([])
    
    const loadActivityList = () => {
      // 这里应该调用后端API获取活动列表
      // 由于后端API尚未完全实现，这里使用模拟数据
      activityList.value = [
        {
          activityId: 1,
          title: '校园科技节',
          description: '展示校园科技成果，包括机器人、人工智能、物联网等项目',
          startTime: '2026-03-15 09:00:00',
          endTime: '2026-03-16 17:00:00',
          location: '图书馆大厅',
          type: '科技',
          organizerId: 3,
          organizerName: '科技创新协会',
          status: 'approved'
        },
        {
          activityId: 2,
          title: '春季运动会',
          description: '校园春季运动会，包括田径、球类等多个项目',
          startTime: '2026-04-01 08:00:00',
          endTime: '2026-04-02 17:00:00',
          location: '操场',
          type: '体育',
          organizerId: 3,
          organizerName: '体育协会',
          status: 'approved'
        },
        {
          activityId: 3,
          title: '校园音乐会',
          description: '校园音乐会，由学校音乐社团和乐队表演',
          startTime: '2026-03-20 19:00:00',
          endTime: '2026-03-20 21:00:00',
          location: '大礼堂',
          type: '文艺',
          organizerId: 3,
          organizerName: '音乐协会',
          status: 'approved'
        }
      ]
    }
    
    const getActivityStatusType = (status) => {
      switch (status) {
        case 'approved':
          return 'success'
        case 'pending':
          return 'warning'
        case 'rejected':
          return 'danger'
        case 'ongoing':
          return 'info'
        case 'ended':
          return 'info'
        default:
          return 'default'
      }
    }
    
    const getActivityStatusText = (status) => {
      switch (status) {
        case 'approved':
          return '已通过'
        case 'pending':
          return '待审核'
        case 'rejected':
          return '已驳回'
        case 'ongoing':
          return '进行中'
        case 'ended':
          return '已结束'
        default:
          return status
      }
    }
    
    const formatDate = (dateString) => {
      const date = new Date(dateString)
      return date.toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
      })
    }
    
    onMounted(() => {
      loadActivityList()
    })
    
    return {
      activityList,
      getActivityStatusType,
      getActivityStatusText,
      formatDate
    }
  }
}
</script>

<style scoped>
.activity-list-container {
  padding: 20px;
}

.activity-list-card {
  border-radius: 8px;
  overflow: hidden;
}

.card-header {
  text-align: center;
  padding: 20px 0;
}

.card-header h3 {
  margin: 0;
  color: #333;
  font-size: 20px;
}

.card-body {
  padding: 0 20px 20px;
}

.activity-item {
  margin-bottom: 20px;
}

.activity-card {
  height: 100%;
  transition: all 0.3s ease;
}

.activity-card:hover {
  transform: translateY(-5px);
}

.activity-card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.activity-card-header h4 {
  margin: 0;
  color: #333;
  font-size: 16px;
}

.activity-description {
  margin: 10px 0;
  color: #666;
  line-height: 1.5;
  height: 60px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}

.activity-info {
  margin: 15px 0;
}

.info-row {
  margin-bottom: 8px;
}

.info-icon {
  margin-right: 5px;
  color: #409EFF;
}

.activity-actions {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
}

.action-button {
  flex: 1;
  margin: 0 5px;
}

.action-button:first-child {
  margin-left: 0;
}

.action-button:last-child {
  margin-right: 0;
}
</style>
