<template>

  <div class="dashboard">

    <Navbar />

    <!-- Content -->

    <div class="content">

      <h2>Welcome to Dashboard</h2>

    </div>

  </div>

</template>

<script>

import Navbar from "../components/Navbar.vue";

export default {

  components: {

    Navbar

  }

}

</script>

<style scoped>

.dashboard {

  min-height: 100vh;

  background: #f5f7fb;

}

.content {

  padding: 30px;

}

</style>