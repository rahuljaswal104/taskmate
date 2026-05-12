<template>

  <div class="dashboard-layout">

    <!-- Sidebar -->
    <aside class="sidebar">

      <!-- Logo -->
      <div>

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
            class="menu-item active"
            @click="goDashboard"
          >
            Dashboard
          </div>

          <div
            class="menu-item"
            @click="goAddProject"
          >
            Projects
          </div>

          <div
            class="menu-item"
            @click="goAddDepartment"
          >
            Departments
          </div>

          <div
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

        <!-- Right -->
        <div class="topbar-right">

          <!-- <input
            type="text"
            placeholder="Search..."
          /> -->

          <!-- Admin -->
          <div class="admin-box">

            <div class="admin-avatar">
              S
            </div>

            <div class="admin-info">

              <h4>Super Admin</h4>
              <p>Administrator</p>

            </div>

            <!-- Logout -->
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

          <div class="hero-buttons">
          </div>
        </div>

        <!-- Right Stats -->
        <div class="hero-right">

          <div class="mini-card">

            <h3>24</h3>
            <p>Total Projects</p>

          </div>

          <div class="mini-card">

            <h3>40</h3>
            <p>Total Employees</p>

          </div>

          <div class="mini-card">

            <h3>18</h3>
            <p>Pending Tasks</p>

          </div>

          <div class="mini-card">

            <h3>12</h3>
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

            <h2>24</h2>

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

            <h2>40</h2>

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

            <h2>18</h2>

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

            <h2>62</h2>

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

export default {

  methods: {

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

    logout() {

      localStorage.clear();
      this.$router.push("/");

    }

  }

};

</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

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

.menu-item {

  padding: 14px 16px;

  border-radius: 12px;

  cursor: pointer;

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

.topbar p {

  margin-top: 5px;
  color: #6b7280;

}

.topbar-right {

  display: flex;
  align-items: center;
  gap: 18px;

}

.topbar-right input {

  width: 220px;

  padding: 12px;

  border: 1px solid #d1d5db;

  border-radius: 12px;

  outline: none;

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

  cursor: pointer;

  font-size: 13px;
  font-weight: 600;

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

/* Hero Left */

.hero-left {

  width: 55%;
  color: white;

}

.hero-badge {

  display: inline-block;

  background: rgba(255,255,255,0.18);

  padding: 8px 14px;

  border-radius: 30px;

  font-size: 12px;
  letter-spacing: 1px;

}

.hero-left h2 {

  margin-top: 22px;

  font-size: 42px;
  line-height: 1.3;

}

.hero-left p {

  margin-top: 18px;

  line-height: 1.7;

  opacity: 0.9;

}

/* Buttons */

.hero-buttons {

  margin-top: 28px;

  display: flex;
  gap: 14px;

}

.primary-btn,
.secondary-btn {

  border: none;

  padding: 14px 24px;

  border-radius: 12px;

  cursor: pointer;

  font-weight: 600;

}

.primary-btn {

  background: white;
  color: #238a52;

}

.secondary-btn {

  background: transparent;
  border: 2px solid white;
  color: white;

}

/* Hero Right */

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

  .topbar-right input {

    width: 100%;

  }

}

</style>