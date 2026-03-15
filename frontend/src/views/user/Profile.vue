<template>
  <div class="profile-container">
    <el-card class="profile-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <h3>个人资料</h3>
        </div>
      </template>
      <div class="card-body">
        <el-form :model="userForm" :rules="userRules" ref="userFormRef" label-width="100px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userForm.username" disabled />
          </el-form-item>
          <el-form-item label="真实姓名" prop="name">
            <el-input v-model="userForm.name" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="userForm.email" type="email" />
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="userForm.phone" />
          </el-form-item>
          <el-form-item label="角色" prop="role">
            <el-input v-model="userForm.role" disabled />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-input v-model="userForm.status" disabled />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleUpdate" :loading="loading">更新资料</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

export default {
  name: 'Profile',
  setup() {
    const router = useRouter()
    const userFormRef = ref(null)
    const loading = ref(false)
    
    const userForm = reactive({
      userId: '',
      username: '',
      name: '',
      email: '',
      phone: '',
      role: '',
      status: ''
    })
    
    const userRules = {
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
    
    const loadUserInfo = () => {
      // 从localStorage获取用户信息
      const userStr = localStorage.getItem('user')
      if (userStr) {
        const user = JSON.parse(userStr)
        Object.assign(userForm, user)
      } else {
        // 如果没有用户信息，跳转到登录页
        router.push('/user/login')
      }
    }
    
    const handleUpdate = async () => {
      if (!userFormRef.value) return
      
      try {
        await userFormRef.value.validate()
        loading.value = true
        
        // 这里应该调用后端API更新用户信息
        // 由于后端API尚未完全实现，这里使用模拟数据
        console.log('更新用户信息:', userForm)
        
        // 更新localStorage中的用户信息
        localStorage.setItem('user', JSON.stringify(userForm))
        
        ElMessage.success('个人资料更新成功')
      } catch (error) {
        console.error('更新失败:', error)
        ElMessage.error('更新失败，请重试')
      } finally {
        loading.value = false
      }
    }
    
    onMounted(() => {
      loadUserInfo()
    })
    
    return {
      userForm,
      userRules,
      userFormRef,
      loading,
      handleUpdate
    }
  }
}
</script>

<style scoped>
.profile-container {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.profile-card {
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
</style>
