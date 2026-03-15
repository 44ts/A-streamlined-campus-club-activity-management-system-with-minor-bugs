<template>
  <div class="home-container">
    <el-carousel :interval="5000" type="card" height="400px" class="carousel">
      <el-carousel-item v-for="item in carouselItems" :key="item.id">
        <div class="carousel-item">
          <h3>{{ item.title }}</h3>
          <p>{{ item.description }}</p>
        </div>
      </el-carousel-item>
    </el-carousel>
    
    <div class="features-container">
      <h2 class="section-title">系统功能</h2>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card shadow="hover" class="feature-card">
            <template #header>
              <div class="card-header">
                <el-icon class="icon"><calendar /></el-icon>
                <span>活动管理</span>
              </div>
            </template>
            <div class="card-content">
              <p>发布、修改、删除、查看和审核校园活动</p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" class="feature-card">
            <template #header>
              <div class="card-header">
                <el-icon class="icon"><ticket /></el-icon>
                <span>活动报名</span>
              </div>
            </template>
            <div class="card-content">
              <p>报名参加活动、取消报名、查看报名记录</p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" class="feature-card">
            <template #header>
              <div class="card-header">
                <el-icon class="icon"><check /></el-icon>
                <span>活动签到</span>
              </div>
            </template>
            <div class="card-content">
              <p>开启签到入口、用户签到、查看签到统计</p>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mt-4">
        <el-col :span="8">
          <el-card shadow="hover" class="feature-card">
            <template #header>
              <div class="card-header">
                <el-icon class="icon"><star /></el-icon>
                <span>活动反馈</span>
              </div>
            </template>
            <div class="card-content">
              <p>提交活动评价、查看评价列表、统计平均评分</p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" class="feature-card">
            <template #header>
              <div class="card-header">
                <el-icon class="icon"><user /></el-icon>
                <span>用户管理</span>
              </div>
            </template>
            <div class="card-content">
              <p>用户注册、登录、信息管理、账号状态管理</p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" class="feature-card">
            <template #header>
              <div class="card-header">
                <el-icon class="icon"><data-analysis /></el-icon>
                <span>数据统计</span>
              </div>
            </template>
            <div class="card-content">
              <p>用户统计、活动统计、生成报表、系统日志查看</p>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { Calendar, Ticket, Check, Star, User, DataAnalysis } from '@element-plus/icons-vue'

export default {
  name: 'Home',
  components: {
    Calendar,
    Ticket,
    Check,
    Star,
    User,
    DataAnalysis
  },
  setup() {
    const carouselItems = ref([
      {
        id: 1,
        title: '欢迎使用校园活动管理系统',
        description: '为校园内的活动提供全生命周期的管理服务'
      },
      {
        id: 2,
        title: '轻松发布和管理校园活动',
        description: '活动组织者可以方便地发布和管理各类校园活动'
      },
      {
        id: 3,
        title: '便捷参与校园活动',
        description: '学生可以轻松浏览、报名和参与感兴趣的校园活动'
      }
    ])

    return {
      carouselItems
    }
  }
}
</script>

<style scoped>
.home-container {
  padding: 20px;
}

.carousel {
  margin-bottom: 40px;
}

.carousel-item {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #f0f9ff;
  color: #333;
  text-align: center;
  padding: 0 40px;
}

.carousel-item h3 {
  font-size: 24px;
  margin-bottom: 20px;
  color: #333;
}

.carousel-item p {
  font-size: 16px;
  color: #666;
}

.features-container {
  margin-top: 40px;
}

.section-title {
  text-align: center;
  margin-bottom: 30px;
  font-size: 24px;
  color: #333;
}

.feature-card {
  transition: all 0.3s ease;
}

.feature-card:hover {
  transform: translateY(-5px);
}

.card-header {
  display: flex;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
}

.icon {
  margin-right: 8px;
  color: #409EFF;
}

.card-content {
  padding: 20px 0;
}

.card-content p {
  color: #666;
  line-height: 1.5;
}

.mt-4 {
  margin-top: 20px;
}
</style>
