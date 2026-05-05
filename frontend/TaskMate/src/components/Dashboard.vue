<template>
  <div class="dashboard">

    <!-- Navbar -->
    <nav class="navbar">
      <h2 class="logo">TaskMate</h2>

      <div class="nav-actions">
        <button class="nav-btn" @click="goAssignTask">Assign Task</button>
        <button class="nav-btn logout" @click="logout">Logout</button>
      </div>
    </nav>

    <!-- Blank Content -->
    <div class="content">
      <h2>Welcome to Dashboard</h2>
    </div>

  </div>
</template>

<script>
export default {
  methods: {
    goAssignTask() {
      this.$router.push('/asgintask');
    },
    logout() {
      localStorage.clear();
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>
.dashboard {
  min-height: 100vh;
  background: #f5f7fb;
}

/* Navbar */
.navbar {
  background: #47b37b;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 30px;
}

.logo {
  font-size: 22px;
  font-weight: bold;
}

.nav-actions {
  display: flex;
  gap: 10px;
}

.nav-btn {
  background: white;
  color: #47b37b;
  border: none;
  padding: 8px 15px;
  border-radius: 6px;
  cursor: pointer;
}

.logout {
  background: #ef4444;
  color: white;
}

/* Content */
.content {
  padding: 30px;
}
</style>