<template>
  <nav class="navbar">

    <h2 class="logo">TaskMate</h2>

    <div class="nav-actions">

      <div class="user-info">
        <span class="user-name">{{ name }}</span>
        <span class="user-role">{{ role }}</span>
      </div>

      <button class="nav-btn logout" @click="logout">
        Logout
      </button>

    </div>

  </nav>
</template>

<script>
export default {
  data() {
    return {
      name: localStorage.getItem('name'),
      role: localStorage.getItem('role')
    }
  },

  methods: {
    logout() {
      localStorage.clear();
      this.$router.push("/");
    }
  }
}
</script>

<style scoped>

.navbar {
  background: linear-gradient(135deg, #47b37b, #2d9c67);
  color: white;

  display: flex;
  justify-content: space-between;
  align-items: center;

  padding: 14px 30px;

  box-shadow: 0 4px 12px rgba(0,0,0,0.12);
}

/* LOGO */

.logo {
  font-size: 26px;
  font-weight: 700;
  letter-spacing: 1px;
}

/* RIGHT SECTION */

.nav-actions {
  display: flex;
  align-items: center;
  gap: 18px;
}

/* USER INFO */

.user-info {
  display: flex;
  flex-direction: column;
  align-items: flex-end;

  background: rgba(255,255,255,0.15);
  padding: 8px 14px;
  border-radius: 12px;

  backdrop-filter: blur(8px);
}

.user-name {
  font-size: 15px;
  font-weight: 700;
  color: #fff;
}

.user-role {
  font-size: 12px;
  color: #e6f7ee;
  margin-top: 2px;
  text-transform: capitalize;
}

/* BUTTON */

.nav-btn {
  border: none;
  outline: none;

  padding: 10px 18px;
  border-radius: 10px;

  font-size: 14px;
  font-weight: 600;

  cursor: pointer;
  transition: all 0.3s ease;
}

/* LOGOUT BUTTON */

.logout {
  background: #ff4d4f;
  color: white;

  box-shadow: 0 4px 10px rgba(255, 77, 79, 0.3);
}

.logout:hover {
  background: #e63946;
  transform: translateY(-2px);
}

.logout:active {
  transform: scale(0.96);
}

/* RESPONSIVE */

@media (max-width: 600px) {

  .navbar {
    padding: 12px 16px;
  }

  .logo {
    font-size: 20px;
  }

  .nav-actions {
    gap: 10px;
  }

  .user-info {
    padding: 6px 10px;
  }

  .user-name {
    font-size: 13px;
  }

  .user-role {
    font-size: 11px;
  }

  .nav-btn {
    padding: 8px 12px;
    font-size: 12px;
  }
}

</style>