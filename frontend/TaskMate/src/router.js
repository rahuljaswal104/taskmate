import { createRouter, createWebHistory } from 'vue-router'

import LoginForm from './components/Login.vue'
import Dashboard from './components/Dashboard.vue'
import AsginTask from './components/AsginTask.vue'
import AddRole from './components/AddRole.vue'
import Navbar from './components/Navbar.vue'
import SuperAdmin from './components/SuperAdmin.vue'
import AddDepartment from './components/AddDepartment.vue'
import AddProject from './components/AddProject.vue'
import EmployeeReport from './components/EmployeeReport.vue'
import MyTasks from './components/MyTasks.vue'

const routes = [
  {path: '/',name: 'Login',component: LoginForm},
  {path:'/project', name: 'AddProject', component: AddProject, meta: { requiresAuth: true }},
  {path:'/navbar', name: 'Navbar', component: Navbar, meta: { requiresAuth: true }},
  {path:'/role', name: 'AddRole', component: AddRole, meta: { requiresAuth: true }},
  {path: '/dashboard', name: 'Dashboard',component: Dashboard, meta: { requiresAuth: true }},
  {path: '/asgintask', name: 'AssignTask', component: AsginTask, meta: { requiresAuth: true }},
   {path: '/department', name: 'AddDepartment', component: AddDepartment, meta: { requiresAuth: true }},
   {path: '/SuperAdmin', name: 'SuperAdmin', component: SuperAdmin, meta: { requiresAuth: true }},
    {path: '/employeereport', name: 'EmployeeReport', component: EmployeeReport, meta: { requiresAuth: true }},
    {path: '/mytasks', name: 'MyTasks', component: MyTasks, meta: { requiresAuth: true }}
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