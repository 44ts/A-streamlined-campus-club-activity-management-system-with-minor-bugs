import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/user/login',
    name: 'Login',
    component: () => import('../views/user/Login.vue')
  },
  {
    path: '/user/register',
    name: 'Register',
    component: () => import('../views/user/Register.vue')
  },
  {
    path: '/user/profile',
    name: 'Profile',
    component: () => import('../views/user/Profile.vue')
  },
  {
    path: '/user/list',
    name: 'UserList',
    component: () => import('../views/user/UserList.vue')
  },
  {
    path: '/activity/list',
    name: 'ActivityList',
    component: () => import('../views/activity/ActivityList.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
