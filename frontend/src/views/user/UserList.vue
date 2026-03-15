<template>
  <div class="user-list-container">
    <el-card class="user-list-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <h3>用户管理</h3>
        </div>
      </template>
      <div class="card-body">
        <el-table :data="userList" style="width: 100%" border>
          <el-table-column prop="userId" label="用户ID" width="80" />
          <el-table-column prop="username" label="用户名" width="120" />
          <el-table-column prop="name" label="真实姓名" width="120" />
          <el-table-column prop="email" label="邮箱" />
          <el-table-column prop="phone" label="手机号" width="120" />
          <el-table-column prop="role" label="角色" width="100" />
          <el-table-column prop="status" label="状态" width="100">
            <template #default="scope">
              <el-tag :type="scope.row.status === 'enabled' ? 'success' : 'danger'">
                {{ scope.row.status === 'enabled' ? '启用' : '禁用' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="150" fixed="right">
            <template #default="scope">
              <el-button 
                type="primary" 
                size="small" 
                @click="handleEnable(scope.row)"
                v-if="scope.row.status === 'disabled'"
              >
                启用
              </el-button>
              <el-button 
                type="danger" 
                size="small" 
                @click="handleDisable(scope.row)"
                v-else
              >
                禁用
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import axios from 'axios'

export default {
  name: 'UserList',
  setup() {
    const userList = ref([])
    
    const loadUserList = () => {
      // 这里应该调用后端API获取用户列表
      // 由于后端API尚未完全实现，这里使用模拟数据
      userList.value = [
        {
          userId: 1,
          username: 'admin',
          name: '管理员',
          email: 'admin@example.com',
          phone: '13800138000',
          role: 'admin',
          status: 'enabled'
        },
        {
          userId: 2,
          username: 'user1',
          name: '用户1',
          email: 'user1@example.com',
          phone: '13800138001',
          role: 'user',
          status: 'enabled'
        },
        {
          userId: 3,
          username: 'organizer1',
          name: '组织者1',
          email: 'organizer1@example.com',
          phone: '13800138002',
          role: 'organizer',
          status: 'enabled'
        }
      ]
    }
    
    const handleEnable = (user) => {
      // 这里应该调用后端API启用用户
      // 由于后端API尚未完全实现，这里使用模拟操作
      user.status = 'enabled'
      ElMessage.success('用户已启用')
    }
    
    const handleDisable = (user) => {
      // 这里应该调用后端API禁用用户
      // 由于后端API尚未完全实现，这里使用模拟操作
      user.status = 'disabled'
      ElMessage.success('用户已禁用')
    }
    
    onMounted(() => {
      loadUserList()
    })
    
    return {
      userList,
      handleEnable,
      handleDisable
    }
  }
}
</script>

<style scoped>
.user-list-container {
  padding: 20px;
}

.user-list-card {
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
</style>
