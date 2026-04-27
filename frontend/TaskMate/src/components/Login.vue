<template>
   <router-view></router-view>
  <div>
    <!-- 🔷 Navbar -->
    <nav class="navbar">
      <h2>TaskMate</h2>
      <button class="register-btn" @click="showPopup = true">Register</button>
    </nav>

    <!-- 🔷 Login Box -->
    <div class="container">
      <div class="form-box">
        <h2>Welcome</h2>
        <p>Please login to continue</p>

        <input type="text" v-model="loginusername" placeholder="Enter username" />
        <br /><br />

        <input type="password" v-model="password" placeholder="Enter password" />
        <br /><br />

        <button @click="login">Login</button>
      </div>
    </div>

    <!-- Popup start -->
    <div v-if="showPopup" class="modal-overlay">
      <div class="modal">
        <h2>Register User</h2>
        <span class="close-icon" @click="showPopup = false">&times;</span>

        <input type="text"  v-model="register.name" placeholder="Name" /><br /><br />
        <input type="email" v-model="register.username" placeholder="Email" /><br /><br />
        <input type="password" v-model="register.password" placeholder="Password" /><br /><br />
        <input type="password" v-model="register.repassword" placeholder="Re-enter Password" /><br /><br />

        <select v-model="register.role">
          <option disabled value="">Select Role</option>
          <option>SUPERADMIN</option>
          <option>ADMIN</option>
          <option>EMPLOYEE</option>
        </select>
        <br /><br />

        <select v-model="register.gender">
          <option disabled value="">Select Gender</option>
          <option>Male</option>
          <option>Female</option>
          <option>Other</option>
        </select>
        <br /><br />

        <button @click="registerUser">Submit</button>
      </div>
    </div>
    <!-- Popup End -->
  </div>
</template>

<script>
export default {
  name: "LoginForm",
  data() {
    return {
      loginusername: "",
      password: "",
      showPopup: false,
      register: {
        name: "",
        username: "",
        password: "",
        repassword: "",
        role: "",
        gender: "",
      }
    };
  },

  methods: {

    login() {
  if (!this.loginusername || !this.password) {
    alert("Please fill in both fields");
    return;
  }

  fetch("http://localhost:8080/api/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      username: this.loginusername,
      password: this.password
    })
  })
  .then(res =>
    res.text().then(data => {
      if (!res.ok) {
        throw new Error(data || "Login failed");
      }
      return data; // ✅ IMPORTANT
    })
  )
  .then(data => {

    if (data.toLowerCase().includes("login success")) {
      // ✅ redirect
      this.$router.push('/dashboard');
    } else {
      // ❌ show error
      alert(data);
    }

  })
  .catch(err => {
    console.error(err);
    alert(err.message);
  });
},

    registerUser() {
      const r = this.register;

      if (!r.name || !r.username || !r.password || !r.repassword || !r.role || !r.gender) {
        alert("Please fill all fields");
        return;
      }

      if (r.password !== r.repassword) {
        alert("Passwords do not match");
        return;
      }

      fetch("http://localhost:8080/api/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.register)
      })
      .then(res =>
        res.text().then(data => {
          if (!res.ok) {
            throw new Error(data || "Something went wrong");
          }
          return data;
        })
      )
      .then(data => {
        alert(data);
        this.showPopup = false;

        this.register = {
          name: "",
          username: "",
          password: "",
          repassword: "",
          role: "",
          gender: ""
        };
      })
      .catch(err => {
        console.error(err);
        alert(err.message);
      });
    }

  }
};
</script>

<style>
/* (same CSS — unchanged) */
.navbar {
  background-color: #42b983;
  color: white;
  padding: 15px 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.register-btn {
  width: auto;
  background: white;
  color: #42b983;
  border: none;
  padding: 8px 15px;
  border-radius: 5px;
  cursor: pointer;
}

.container {
  height: calc(100vh - 70px);
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f5f5;
}

.form-box {
  padding: 30px;
  width: 300px;
  border-radius: 10px;
  background: white;
  text-align: center;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

.form-box button {
  width: 100%;
}

select {
  width: 89%;
  padding: 10px;
}

input {
  width: 80%;
  padding: 10px;
}

button {
  padding: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #369870;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  position: relative;
  background: white;
  padding: 25px;
  border-radius: 10px;
  width: 320px;
  text-align: center;
}

.close-icon {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 22px;
  cursor: pointer;
  color: #555;
}

.close-icon:hover {
  color: red;
}
</style>