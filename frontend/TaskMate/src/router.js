import { createRouter, createWebHistory } from 'vue-router'
import LoginForm from './components/Login.vue'
import Dashboard from './components/Dashboard.vue'

const routes = [
  { path: '/', component: LoginForm },
  { path: '/dashboard', component: Dashboard }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router