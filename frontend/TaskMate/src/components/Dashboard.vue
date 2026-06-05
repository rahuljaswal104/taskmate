<template>

  <div class="dashboard-layout">

    <!-- Sidebar -->

    <aside class="sidebar">

      <div>

        <!-- Logo -->

        <div class="logo-section">

          <div class="logo-circle">
            TM
          </div>

          <div>

            <h2>TaskMate</h2>

            <p>Task Management System</p>

          </div>

        </div>

        <!-- Menu -->

        <div class="menu-section">

          <div
            class="menu-items active"
            @click="goDashboard"
          >
            Dashboard
          </div>

          <div
           v-if="role === 'SUPERADMIN'"
            class="menu-item"
            @click="goAddProject"
          >
            Projects
          </div>

          <div
           v-if="role === 'SUPERADMIN'"
            class="menu-item"
            @click="goAddDepartment"
          >
            Departments
          </div>

          <div
           v-if="role === 'SUPERADMIN'"
            class="menu-item"
            @click="goAddRole"
          >
            Roles
          </div>

          <div
            class="menu-item"
            @click="goAssignTask"
          >
            Assign Tasks
          </div>

          <div
            class="menu-item"
            @click="goTaskList"
          >
            Task List
          </div>

          <div
            class="menu-item"
            @click="goEmployeeReport"
          >
            Reports
          </div>

        </div>

      </div>

    </aside>

    <!-- Main Content -->

    <main class="main-content">

      <!-- Topbar -->

      <div class="topbar">

        <div>

          <h1>Dashboard</h1>

        </div>

        <div class="topbar-right">

          <!-- Admin -->

          <div class="admin-box">

            <div class="admin-avatar">
                {{ name.charAt(0).toUpperCase() }}
            </div>

            <div class="admin-info">

              <h4>{{name}}</h4>

              <p>{{role}}</p>

            </div>

            <button 
              class="logout-btn"
              @click="logout"
            >
              Logout
            </button>

          </div>

        </div>

      </div>

      <!-- Hero Section -->

      <div class="hero-section">

        <div class="hero-left">

          <h2>

            Manage Projects,
            Employees & Tasks
            Professionally

          </h2>

        </div>

        <!-- Right Cards -->

        <div class="hero-right">

          <div class="mini-card">

            <h3>{{ projectCount }}</h3>

            <p>Total Projects</p>

          </div>

          <div class="mini-card">

            <h3>{{ totalEmployeeCount }}</h3>

            <p>Total Employees</p>

          </div>

          <div class="mini-card">

            <h3>{{ pendingTaskCount }}</h3>

            <p>Pending Tasks</p>

          </div>

          <div class="mini-card">

            <h3>{{departmentCount}}</h3>

            <p>Departments</p>

          </div>

        </div>

      </div>

      <!-- Stats -->

      <div class="stats-grid">

        <div class="stats-card">

          <div>

            <span class="card-label">

              TOTAL PROJECTS

            </span>

            <h2>{{ projectCount }}</h2>

          </div>

          <div class="status green">
            +12%
          </div>

        </div>

        <div class="stats-card">

          <div>

            <span class="card-label">

              TOTAL EMPLOYEES

            </span>

            <h2>{{ totalEmployeeCount }}</h2>

          </div>

          <div class="status blue">
            +8%
          </div>

        </div>

        <div class="stats-card">

          <div>

            <span class="card-label">

              PENDING TASKS

            </span>

            <h2>{{pendingTaskCount}}</h2>

          </div>

          <div class="status orange">
            Active
          </div>

        </div>

        <div class="stats-card">

          <div>

            <span class="card-label">

              COMPLETED TASKS

            </span>

            <h2>{{ completeTaskCount }}</h2>

          </div>

          <div class="status green">
            Done
          </div>

        </div>

      </div>

    </main>

  </div>

</template>

<script>
import axios from 'axios';
export default {

  data(){
    return{
      projectCount:0,
      departmentCount:0,
      pendingTaskCount:0,
      completeTaskCount:0,
      totalEmployeeCount:0,
      name: localStorage.getItem('name'),
      role: localStorage.getItem('role')
    }
  },

  mounted(){
     this.getProjectCount();
     this.getDepartmentCount();
     this.getCountPendingAndCompleteTask();
     this.getTotalEmployeeCount();
  },

  methods: {

    goTaskList(){
     
    this.$router.push("/tasklist");

    },
    goDashboard() {

      this.$router.push("/dashboard");
    },

    goAddProject() {

      this.$router.push("/project");
    },

    goAddDepartment() {

      this.$router.push("/department");
    },

    goAddRole() {

      this.$router.push("/role");
    },

    goAssignTask() {

      this.$router.push("/asgintask");
    },

    goEmployeeReport() {

      this.$router.push("/employeereport");
    },

    logout() {

      localStorage.clear();

      this.$router.push("/");
    },
  async getProjectCount() {

  try {

    const response = await axios.get(
      "http://localhost:8080/api/project/getProjectCount"
    );

    this.projectCount = response.data.data;

  } catch(error) {

    console.log(error);

  }
   },
   async getDepartmentCount() {

  try {

    const response = await axios.get(
      "http://localhost:8080/api/departments/getDepartmentCount"
    );

    this.departmentCount = response.data.data;

  } catch(error) {

    console.log(error);

  }
   },
   async getCountPendingAndCompleteTask() {

  try {

    const response = await axios.get(
      "http://localhost:8080/api/assgintask/getCountPendingAndCompleteTask"
    );

    this.pendingTaskCount = response.data.data.pendingCount;
    this.completeTaskCount = response.data.data.completedCount;

  } catch(error) {

    console.log(error);

  }
   },
   async getTotalEmployeeCount() {

  try {

    const response = await axios.get(
      "http://localhost:8080/api/employeecount"
    );

    this.totalEmployeeCount = response.data.data;

  } catch(error) {

    console.log(error);

  }
   }
  },
  
};

</script>

<style scoped>

/* Global */

* {

  margin: 0;
  padding: 0;
  box-sizing: border-box;

  user-select: none;

  cursor: default;
}

/* Pointer */

button,
.menu-item,
.menu-items,
.logout-btn {

  cursor: pointer;
}

/* Text Input */

input,
textarea {

  cursor: text;
  user-select: text;
}

/* Remove Tap Highlight */

button,
div,
a {

  -webkit-tap-highlight-color: transparent;
}

/* Layout */

.dashboard-layout {

  display: flex;
  min-height: 100vh;

  background: #f4f7fb;

  font-family: Arial, sans-serif;
}

/* Sidebar */

.sidebar {

  width: 250px;

  background: white;

  border-right: 1px solid #e5e7eb;

  padding: 18px;
}

/* Logo */

.logo-section {

  display: flex;
  align-items: center;
  gap: 12px;

  margin-bottom: 25px;
}

.logo-circle {

  width: 45px;
  height: 45px;

  border-radius: 12px;

  background: linear-gradient(
    135deg,
    #43b77a,
    #238a52
  );

  color: white;

  display: flex;
  justify-content: center;
  align-items: center;

  font-size: 15px;
  font-weight: bold;
}

.logo-section h2 {

  font-size: 22px;

  color: #111827;
}

.logo-section p {

  font-size: 12px;

  color: #6b7280;
}

/* Menu */

.menu-section {

  display: flex;
  flex-direction: column;

  gap: 10px;
}

.menu-items,
.menu-item {

  padding: 14px 16px;

  border-radius: 12px;

  font-size: 15px;
  font-weight: 600;

  color: #374151;

  transition: 0.3s;
}

.menu-item:hover {

  background: #f3f4f6;
}

.active {

  background: linear-gradient(
    135deg,
    #43b77a,
    #2ea665
  );

  color: white;
}

/* Main */

.main-content {

  flex: 1;

  padding: 28px;
}

/* Topbar */

.topbar {

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.topbar h1 {

  font-size: 28px;

  color: #111827;
}

/* Right */

.topbar-right {

  display: flex;
  align-items: center;
  gap: 18px;
}

/* Admin */

.admin-box {

  display: flex;
  align-items: center;
  gap: 12px;

  background: white;

  padding: 10px 14px;

  border-radius: 14px;

  box-shadow: 0 2px 10px rgba(0,0,0,0.05);
}

.admin-avatar {

  width: 42px;
  height: 42px;

  border-radius: 50%;

  background: #43b77a;
  color: white;

  display: flex;
  justify-content: center;
  align-items: center;

  font-weight: bold;
}

.admin-info h4 {

  font-size: 14px;

  color: #111827;
}

.admin-info p {

  font-size: 12px;

  color: #6b7280;
}

/* Logout */

.logout-btn {

  border: none;

  padding: 10px 16px;

  border-radius: 10px;

  background: #ef4444;
  color: white;

  font-size: 13px;
  font-weight: 600;

  transition: 0.3s;
}

.logout-btn:hover {

  opacity: 0.9;
}

/* Hero */

.hero-section {

  margin-top: 28px;

  background: linear-gradient(
    135deg,
    #43b77a,
    #238a52
  );

  border-radius: 28px;

  padding: 45px;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* Left */

.hero-left {

  width: 55%;

  color: white;
}

.hero-left h2 {

  margin-top: 22px;

  font-size: 42px;

  line-height: 1.3;
}

/* Right */

.hero-right {

  width: 38%;

  display: grid;
  grid-template-columns: repeat(2,1fr);

  gap: 16px;
}

.mini-card {

  background: rgba(255,255,255,0.15);

  backdrop-filter: blur(10px);

  border-radius: 18px;

  padding: 24px;

  color: white;
}

.mini-card h3 {

  font-size: 28px;
}

.mini-card p {

  margin-top: 8px;

  font-size: 14px;
}

/* Stats */

.stats-grid {

  margin-top: 28px;

  display: grid;
  grid-template-columns: repeat(4,1fr);

  gap: 20px;
}

.stats-card {

  background: white;

  border-radius: 18px;

  padding: 24px;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-label {

  font-size: 12px;

  letter-spacing: 1px;

  color: #6b7280;
}

.stats-card h2 {

  margin-top: 10px;

  font-size: 34px;

  color: #111827;
}

.status {

  padding: 7px 12px;

  border-radius: 20px;

  font-size: 12px;
  font-weight: bold;
}

.green {

  background: #dcfce7;
  color: #15803d;
}

.blue {

  background: #dbeafe;
  color: #2563eb;
}

.orange {

  background: #ffedd5;
  color: #ea580c;
}

/* Responsive */

@media(max-width: 1200px) {

  .stats-grid {

    grid-template-columns: repeat(2,1fr);
  }
}

@media(max-width: 900px) {

  .dashboard-layout {

    flex-direction: column;
  }

  .sidebar {

    width: 100%;
  }

  .hero-section {

    flex-direction: column;

    gap: 30px;
  }

  .hero-left,
  .hero-right {

    width: 100%;
  }
}

@media(max-width: 768px) {

  .stats-grid,
  .hero-right {

    grid-template-columns: 1fr;
  }

  .topbar {

    flex-direction: column;
    align-items: flex-start;

    gap: 18px;
  }

  .topbar-right {

    width: 100%;

    flex-direction: column;
    align-items: flex-start;
  }
}

</style>