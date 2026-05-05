import { createRouter, createWebHistory } from 'vue-router'

import LoginForm from './components/Login.vue'
import Dashboard from './components/Dashboard.vue'
import AsginTask from './components/AsginTask.vue'

const routes = [
  {path: '/',name: 'Login',component: LoginForm},
  {path: '/dashboard', name: 'Dashboard',component: Dashboard, meta: { requiresAuth: true }},
  {path: '/asgintask', name: 'AssignTask', component: AsginTask, meta: { requiresAuth: true }}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  
  const isLoggedIn = localStorage.getItem("isLoggedIn")
  if (to.meta.requiresAuth && !isLoggedIn) {
    return next('/')
  }

  if (to.path === '/' && isLoggedIn) {
    return next('/dashboard')
  }
  next()
})

export default router