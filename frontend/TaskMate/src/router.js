// router/index.js

import {
  createRouter,
  createWebHistory
} from "vue-router";

import LoginForm from "./components/Login.vue";
import Dashboard from "./components/Dashboard.vue";
import AsginTask from "./components/AsginTask.vue";
import AddRole from "./components/AddRole.vue";
import Navbar from "./components/Navbar.vue";
import SuperAdmin from "./components/SuperAdmin.vue";
import AddDepartment from "./components/AddDepartment.vue";
import AddProject from "./components/AddProject.vue";
import EmployeeReport from "./components/EmployeeReport.vue";
import TaskList from "./components/TaskList.vue";
import UpdateTask from "./components/UpdateTask.vue";
const routes = [

  {path: "/", name: "Login",component: LoginForm},
  {path: "/dashboard", name: "Dashboard",component: Dashboard,meta: {requiresAuth: true}},
  {path: "/project", name: "AddProject", component: AddProject, meta: {requiresAuth: true}},
  {path: "/asgintask",name: "AssignTask",component: AsginTask,meta: {requiresAuth: true}},
  {path: "/role", name: "AddRole",component: AddRole, meta: {requiresAuth: true}},
  {path: "/department", name: "AddDepartment",component: AddDepartment, meta: {requiresAuth: true}},
  {path: "/superadmin", name: "SuperAdmin",component: SuperAdmin, meta: {requiresAuth: true}},
  {path: "/navbar", name: "Navbar", component: Navbar,meta: {requiresAuth: true}},
  {path: "/employeereport",name: "EmployeeReport", component: EmployeeReport,meta: {requiresAuth: true}},
  {path: "/tasklist",name: "TaskList", component: TaskList,meta: {requiresAuth: true}},
  {path: "/updatetask/:id",name: "UpdateTask", component: UpdateTask,meta: {requiresAuth: true}}
];

const router = createRouter({

  history: createWebHistory(),

  routes
});

/* Route Guard */

router.beforeEach((to, from, next) => {
  const isLoggedIn = localStorage.getItem("isLoggedIn");
  const role = localStorage.getItem("role");

  const adminRoles = [
    "SUPERADMIN",
    "DEPARTMENT ADMIN",
    "MANAGER"
  ];

  if (to.meta.requiresAuth && !isLoggedIn) {
    return next("/");
  }

  if (to.path === "/" && isLoggedIn) {
    return adminRoles.includes(role) ? next("/dashboard") : next("/tasklist");
  }

  if (to.path === "/dashboard" && !adminRoles.includes(role)) {
    return next("/tasklist");
  }

  // if (to.path === "/tasklist" && adminRoles.includes(role)) {
  //   return next("/dashboard");
  // }

  next();
});

// router.beforeEach((to, from, next) => {

//   const isLoggedIn = localStorage.getItem("isLoggedIn");
//   const role = localStorage.getItem("role");

//   if (to.meta.requiresAuth && !isLoggedIn) {
//     return next("/");
//   }

//   if (to.path === "/" && isLoggedIn) {
//     return role === "SUPERADMIN" || role === "DEPARTMENT ADMIN" || role === "MANAGER"
//       ? next("/dashboard")
//       : next("/taskList");
//   }

//   if (to.path === "/dashboard" && role !== "SUPERADMIN") {
//     return next("/taskList");
//   }

//   if (to.path === "/taskList" && (role === "SUPERADMIN" || role === "DEPARTMENT ADMIN" || role === "MANAGER")) {
//     return next("/dashboard");
//   }

//   next();
// });

export default router;