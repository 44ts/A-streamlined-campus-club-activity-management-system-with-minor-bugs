<template>
  <div class="login-container">
    <el-card class="login-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <h3>用户登录</h3>
        </div>
      </template>
      <div class="card-body">
        <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef" label-width="80px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入用户名" prefix-icon="User" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="loginForm.password" type="password" placeholder="请输入密码" prefix-icon="Lock" show-password />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" class="login-button" @click="handleLogin" :loading="loading">登录</el-button>
          </el-form-item>
          <div class="register-link">
            <span>还没有账号？</span>
            <el-link type="primary" @click="navigateToRegister">立即注册</el-link>
          </div>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

export default {
  name: 'Login',
  setup() {
    const router = useRouter()
    const loginFormRef = ref(null)
    const loading = ref(false)
    
    const loginForm = reactive({
      username: '',
      password: ''
    })
    
    const loginRules = {
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' }
      ]
    }
    
    const handleLogin = async () => {
      if (!loginFormRef.value) return
      
      try {
        await loginFormRef.value.validate()
        loading.value = true
        
        // 这里应该调用后端API进行登录验证
        // 由于后端API尚未完全实现，这里使用模拟数据
        if (loginForm.username === 'admin' && loginForm.password === 'admin') {
          const user = {
            userId: 1,
            username: 'admin',
            name: '管理员',
            role: 'admin',
            status: 'enabled'
          }
          localStorage.setItem('user', JSON.stringify(user))
          router.push('/')
        } else {
          ElMessage.error('用户名或密码错误')
        }
      } catch (error) {
        console.error('登录失败:', error)
        ElMessage.error('登录失败，请重试')
      } finally {
        loading.value = false
      }
    }
    
    const navigateToRegister = () => {
      router.push('/user/register')
    }
    
    return {
      loginForm,
      loginRules,
      loginFormRef,
      loading,
      handleLogin,
      navigateToRegister
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 600px;
  padding: 20px;
}

.login-card {
  width: 100%;
  max-width: 450px;
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
  padding: 0 30px 30px;
}

.login-button {
  width: 100%;
  height: 40px;
  font-size: 16px;
}

.register-link {
  margin-top: 20px;
  text-align: center;
  font-size: 14px;
  color: #666;
}

.register-link span {
  margin-right: 5px;
}
</style>
