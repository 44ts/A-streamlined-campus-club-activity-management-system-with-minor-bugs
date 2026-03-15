<template>
  <div class="app-container">
    <el-container>
      <el-header height="60px" class="header">
        <div class="logo">校园活动管理系统</div>
        <el-menu :default-active="activeIndex" mode="horizontal" class="nav-menu">
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="/activity/list">活动列表</el-menu-item>
          <template v-if="!user">
            <el-menu-item index="/user/login">登录</el-menu-item>
            <el-menu-item index="/user/register">注册</el-menu-item>
          </template>
          <template v-else>
            <el-dropdown>
              <span class="user-dropdown">
                {{ user.name }}
                <el-icon class="el-icon--right"><arrow-down /></el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="navigate('/user/profile')">个人资料</el-dropdown-item>
                  <el-dropdown-item @click="navigate('/user/list')">用户管理</el-dropdown-item>
                  <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </template>
        </el-menu>
      </el-header>
      <el-main>
        <router-view v-slot="{ Component }">
          <transition name="fade" mode="out-in">
            <component :is="Component" />
          </transition>
        </router-view>
      </el-main>
      <el-footer height="40px" class="footer">
        <div class="footer-content">© 2026 校园活动管理系统</div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ArrowDown } from '@element-plus/icons-vue'

export default {
  name: 'App',
  components: {
    ArrowDown
  },
  setup() {
    const router = useRouter()
    const activeIndex = ref('/')
    const user = ref(null)

    const navigate = (path) => {
      router.push(path)
    }

    const logout = () => {
      localStorage.removeItem('user')
      user.value = null
      router.push('/user/login')
    }

    onMounted(() => {
      const userStr = localStorage.getItem('user')
      if (userStr) {
        user.value = JSON.parse(userStr)
      }

      router.beforeEach((to, from, next) => {
        activeIndex.value = to.path
        next()
      })
    })

    return {
      activeIndex,
      user,
      navigate,
      logout
    }
  }
}
</script>

<style scoped>
.app-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  background-color: #409EFF;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
}

.logo {
  font-size: 20px;
  font-weight: bold;
  color: white;
}

.nav-menu {
  background-color: transparent;
  border-bottom: none;
}

.nav-menu .el-menu-item {
  color: white;
}

.nav-menu .el-menu-item.is-active {
  color: white;
  background-color: rgba(255, 255, 255, 0.2);
}

.user-dropdown {
  color: white;
  cursor: pointer;
}

.footer {
  background-color: #f5f7fa;
  border-top: 1px solid #e4e7ed;
}

.footer-content {
  text-align: center;
  line-height: 40px;
  color: #909399;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
