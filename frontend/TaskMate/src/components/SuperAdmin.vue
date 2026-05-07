<template>
  <div class="dashboard">

    <!-- Navbar -->
    <header class="top-nav">

      <!-- Left -->
      <div class="logo">
        <h2>TaskMate</h2>
      </div>

      <!-- Center -->
      <nav class="nav-menu">
        <button class="nav-btn">Dashboard</button>
        <button class="nav-btn">Reports</button>
        <button class="nav-btn">Tasks</button>

        <div class="dropdown">
          <button class="dropdown-btn" @click="toggleDropdown">
            Manage ▼
          </button>

          <div v-if="showDropdown" class="dropdown-menu">
            <a href="#">Add Role</a>
            <a href="#">Add Designation</a>
            <a href="#">Add Employee</a>
            <a href="#">Assign Task</a>
          </div>
        </div>
      </nav>

      <!-- Right -->
      <div class="user-section">
        <span class="username">Welcome, Anjali</span>
        <button class="logout-btn">Logout</button>
      </div>

    </header>

    <!-- Main Content -->
    <main class="main-content">

      <!-- Cards -->
      <section class="cards">
        <div class="card" v-for="card in stats" :key="card.title">
          <h3>{{ card.title }}</h3>
          <p>{{ card.value }}</p>
        </div>
      </section>

      <!-- Reports -->
      <section class="reports">

        <div class="report-box">
          <h2>Department Progress</h2>

          <div v-for="dept in departments" :key="dept.name" class="progress-row">
            <span>{{ dept.name }}</span>

            <div class="progress-bar">
              <div
                class="progress"
                :style="{ width: dept.progress + '%' }"
              ></div>
            </div>

            <span>{{ dept.progress }}%</span>
          </div>
        </div>

        <div class="report-box">
          <h2>Recent Activity</h2>

          <ul>
            <li v-for="activity in activities" :key="activity">
              {{ activity }}
            </li>
          </ul>
        </div>

      </section>

      <!-- Employee Table -->
      <section class="employee-table">
        <h2>Employees</h2>

        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Department</th>
              <th>Designation</th>
              <th>Status</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="employee in employees" :key="employee.name">
              <td>{{ employee.name }}</td>
              <td>{{ employee.department }}</td>
              <td>{{ employee.designation }}</td>
              <td>{{ employee.status }}</td>
            </tr>
          </tbody>
        </table>
      </section>

    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const showDropdown = ref(false)

const toggleDropdown = () => {
  showDropdown.value = !showDropdown.value
}

const stats = [
  { title: 'Employees', value: 120 },
  { title: 'Departments', value: 8 },
  { title: 'Assigned Tasks', value: 340 },
  { title: 'Pending Tasks', value: 45 }
]

const departments = [
  { name: 'IT', progress: 80 },
  { name: 'HR', progress: 65 },
  { name: 'Sales', progress: 50 }
]

const activities = [
  'New employee added',
  'Task assigned to IT team',
  'Role updated successfully',
  'Designation created'
]

const employees = [
  {
    name: 'Rahul',
    department: 'IT',
    designation: 'Developer',
    status: 'Active'
  },
  {
    name: 'Priya',
    department: 'HR',
    designation: 'Manager',
    status: 'Active'
  },
  {
    name: 'Amit',
    department: 'Sales',
    designation: 'Executive',
    status: 'Pending'
  }
]
</script>

<style scoped>
body {
  margin: 0;
}

.dashboard {
  min-height: 100vh;
  background: #f9fbfa;
  font-family: Arial, sans-serif;
}

/* Navbar */
.top-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: linear-gradient(135deg, #eef7f0, #ffffff);
  padding: 18px 35px;
  box-shadow: 0 6px 18px rgba(0,0,0,0.08);
  border-bottom: 2px solid #dcebdd;
  position: sticky;
  top: 0;
  z-index: 100;
}

.logo h2 {
  color: #4b6352;
  font-size: 24px;
  font-weight: bold;
}

.nav-menu {
  display: flex;
  gap: 14px;
  align-items: center;
}

.nav-btn,
.dropdown-btn {
  background: #dff0e3;
  border: none;
  padding: 10px 18px;
  border-radius: 10px;
  cursor: pointer;
  color: #48604e;
  font-weight: 600;
  transition: 0.3s;
}

.nav-btn:hover,
.dropdown-btn:hover {
  background: #cde6d2;
  transform: translateY(-2px);
}

/* Dropdown */
.dropdown {
  position: relative;
}

.dropdown-menu {
  position: absolute;
  top: 48px;
  right: 0;
  background: white;
  width: 190px;
  border-radius: 12px;
  box-shadow: 0 5px 18px rgba(0,0,0,0.08);
  overflow: hidden;
}

.dropdown-menu a {
  display: block;
  padding: 14px;
  text-decoration: none;
  color: #4e6655;
}

.dropdown-menu a:hover {
  background: #eef7f0;
}

/* User */
.user-section {
  display: flex;
  align-items: center;
  gap: 14px;
}

.username {
  color: #4e6655;
  font-weight: 600;
}

.logout-btn {
  background: #f8dede;
  color: #8b4a4a;
  border: none;
  padding: 10px 16px;
  border-radius: 10px;
  cursor: pointer;
  font-weight: 600;
}

.logout-btn:hover {
  background: #f3caca;
}

/* Main */
.main-content {
  padding: 30px 40px;
}

/* Cards */
.cards {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 18px;
}

.card {
  background: white;
  padding: 22px;
  border-radius: 14px;
  box-shadow: 0 3px 10px rgba(0,0,0,0.04);
}

.card h3 {
  color: #64796a;
}

.card p {
  font-size: 28px;
  color: #36493b;
}

/* Reports */
.reports {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 20px;
  margin-top: 25px;
}

.report-box {
  background: white;
  padding: 22px;
  border-radius: 14px;
  box-shadow: 0 3px 10px rgba(0,0,0,0.04);
}

.progress-row {
  display: flex;
  align-items: center;
  gap: 12px;
  margin: 14px 0;
}

.progress-bar {
  flex: 1;
  height: 10px;
  background: #eef4ef;
  border-radius: 10px;
}

.progress {
  height: 100%;
  background: #a5c7ac;
  border-radius: 10px;
}

/* Table */
.employee-table {
  margin-top: 25px;
  background: white;
  padding: 22px;
  border-radius: 14px;
  box-shadow: 0 3px 10px rgba(0,0,0,0.04);
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  color: #55695a;
}

th,
td {
  padding: 14px;
  border-bottom: 1px solid #eef4ef;
}

tr:hover {
  background: #f7fbf8;
}
</style>