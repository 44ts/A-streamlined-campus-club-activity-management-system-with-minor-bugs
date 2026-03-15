<template>
  <div class="register-container">
    <el-card class="register-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <h3>用户注册</h3>
        </div>
      </template>
      <div class="card-body">
        <el-form :model="registerForm" :rules="registerRules" ref="registerFormRef" label-width="100px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="registerForm.username" placeholder="请输入用户名" prefix-icon="User" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="registerForm.password" type="password" placeholder="请输入密码" prefix-icon="Lock" show-password />
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input v-model="registerForm.confirmPassword" type="password" placeholder="请确认密码" prefix-icon="Check" show-password />
          </el-form-item>
          <el-form-item label="真实姓名" prop="name">
            <el-input v-model="registerForm.name" placeholder="请输入真实姓名" prefix-icon="Avatar" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="registerForm.email" type="email" placeholder="请输入邮箱" prefix-icon="Message" />
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="registerForm.phone" placeholder="请输入手机号" prefix-icon="Phone" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" class="register-button" @click="handleRegister" :loading="loading">注册</el-button>
          </el-form-item>
          <div class="login-link">
            <span>已有账号？</span>
            <el-link type="primary" @click="navigateToLogin">立即登录</el-link>
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
  name: 'Register',
  setup() {
    const router = useRouter()
    const registerFormRef = ref(null)
    const loading = ref(false)
    
    const registerForm = reactive({
      username: '',
      password: '',
      confirmPassword: '',
      name: '',
      email: '',
      phone: ''
    })
    
    const registerRules = {
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, max: 20, message: '用户名长度应在3-20个字符之间', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, message: '密码长度至少为6个字符', trigger: 'blur' }
      ],
      confirmPassword: [
        { required: true, message: '请确认密码', trigger: 'blur' },
        {
          validator: (rule, value, callback) => {
            if (value !== registerForm.password) {
              callback(new Error('两次输入的密码不一致'))
            } else {
              callback()
            }
          },
          trigger: 'blur'
        }
      ],
      name: [
        { required: true, message: '请输入真实姓名', trigger: 'blur' }
      ],
      email: [
        { required: true, message: '请输入邮箱', trigger: 'blur' },
        { type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur' }
      ],
      phone: [
        { required: true, message: '请输入手机号', trigger: 'blur' },
        { pattern: /^1[3-9]\d{9}$/, message: '请输入有效的手机号', trigger: 'blur' }
      ]
    }
    
    const handleRegister = async () => {
      if (!registerFormRef.value) return
      
      try {
        await registerFormRef.value.validate()
        loading.value = true
        
        // 这里应该调用后端API进行注册
        // 由于后端API尚未完全实现，这里使用模拟数据
        console.log('注册信息:', registerForm)
        
        // 模拟注册成功
        ElMessage.success('注册成功，请登录')
        router.push('/user/login')
      } catch (error) {
        console.error('注册失败:', error)
        ElMessage.error('注册失败，请重试')
      } finally {
        loading.value = false
      }
    }
    
    const navigateToLogin = () => {
      router.push('/user/login')
    }
    
    return {
      registerForm,
      registerRules,
      registerFormRef,
      loading,
      handleRegister,
      navigateToLogin
    }
  }
}
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 600px;
  padding: 20px;
}

.register-card {
  width: 100%;
  max-width: 550px;
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

.register-button {
  width: 100%;
  height: 40px;
  font-size: 16px;
}

.login-link {
  margin-top: 20px;
  text-align: center;
  font-size: 14px;
  color: #666;
}

.login-link span {
  margin-right: 5px;
}
</style>
