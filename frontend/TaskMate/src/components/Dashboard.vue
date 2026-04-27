<template>
  <div>

    <nav class="navbar">
      <h2>TaskMate</h2>

      <div class="nav-buttons">
        <button @click="goAddEmployee">Add Employee</button>
        <button @click="goAssignTask">Assign Task</button>
        <button @click="logout">Logout</button>
      </div>
    </nav>

    <div class="content">
      <h2>Welcome to Dashboard</h2>
    </div>

  </div>
</template>

<script>
export default {
  methods: {
    goAddEmployee() {
      //this.$router.push('/add-employee')
    },
    goAssignTask() {
     // this.$router.push('/assign-task')
    },
    logout() {
      localStorage.clear()
      this.$router.push('/')
    }
  }
}
</script>