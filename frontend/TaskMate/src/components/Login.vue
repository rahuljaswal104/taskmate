<template>
  <div>
    <!-- 🔷 Navbar -->
    <nav class="navbar">
      <h2>TaskMate</h2>
      <button class="register-btn" @click="showPopup = true">Register</button>
    </nav>

    <!-- 🔷 Login -->
    <div class="container">
      <div class="form-box">
        <h2>Welcome</h2>
        <p>Please login to continue</p>

        <div class="form-group">
          <input type="text" v-model="loginusername" placeholder="Enter username" />
        </div>

        <div class="form-group">
          <input type="password" v-model="password" placeholder="Enter password" />
        </div>

        <button class="primary-btn" @click="login">Login</button>
      </div>
    </div>

    <!-- 🔷 Register Modal -->
    <div v-if="showPopup" class="modal-overlay">
      <div class="modal">
        <span class="close-icon" @click="showPopup = false">&times;</span>
        <h2>Register User</h2>

        <div class="form-group">
          <input type="text" v-model="register.name" placeholder="Name" />
          <small v-if="errors.name" class="error">{{ errors.name }}</small>
        </div>

        <div class="form-group">
          <input type="email" v-model="register.username" placeholder="Email" />
          <small v-if="errors.username" class="error">{{ errors.username }}</small>
        </div>

        <div class="form-group">
          <input type="password" v-model="register.password" placeholder="Password" />
        </div>

        <div class="form-group">
          <input type="password" v-model="register.repassword" placeholder="Confirm Password" />
          <small v-if="errors.password" class="error">{{ errors.password }}</small>
          <small v-if="errors.repassword" class="error">{{ errors.repassword }}</small>
        </div>

        <div class="form-group">
          <select v-model="register.role">
            <option disabled value="">Select Role</option>
            <option>SUPERADMIN</option>
            <option>ADMIN</option>
            <option>EMPLOYEE</option>
          </select>
          <small v-if="errors.role" class="error">{{ errors.role }}</small>
        </div>

        <div class="form-group">
          <select v-model="register.gender">
            <option disabled value="">Select Gender</option>
            <option>Male</option>
            <option>Female</option>
            <option>Other</option>
          </select>
          <small v-if="errors.gender" class="error">{{ errors.gender }}</small>
        </div>

        <button class="primary-btn" @click="registerUser">Submit</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "LoginForm",

  data() {
    return {
      errors: {},
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
      .then(res => res.text())
      .then(data => {
        if (data.toLowerCase().includes("login success")) {
          this.$router.push('/dashboard');
        } else {
          alert(data);
        }
      })
      .catch(() => alert("Login failed"));
    },

    registerUser() {
      this.errors = {};
      const r = this.register;

      if (!r.name) this.errors.name = "Name is required";
      if (!r.username) this.errors.username = "Email is required";
      if (!r.password) this.errors.password = "Password is required";
      if (!r.repassword) this.errors.repassword = "Confirm password required";
      if (!r.role) this.errors.role = "Role is required";
      if (!r.gender) this.errors.gender = "Gender is required";

      if (Object.keys(this.errors).length > 0) return;

      if (r.password !== r.repassword) {
        this.errors.repassword = "Passwords do not match";
        return;
      }

      axios.post("http://localhost:8080/api/register", r)
      .then(res => {
        alert(res.data.message);

        this.register = {
          name: "",
          username: "",
          password: "",
          repassword: "",
          role: "",
          gender: ""
        };

        this.showPopup = false;
      })
      .catch(err => {
        if (err.response?.data?.data) {
          this.errors = err.response.data.data;
        } else {
          alert("Something went wrong");
        }
      });
    }
  }
};
</script>

<style>
body {
  margin: 0;
  font-family: Arial, sans-serif;
}

/* Navbar */
.navbar {
  background-color: #1f6f54;
  color: white;
  padding: 15px 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* Buttons */
.primary-btn {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

.primary-btn:hover {
  background-color: #369870;
}

.register-btn {
  background: white;
  color: #1f6f54;
  border: none;
  padding: 8px 15px;
  border-radius: 5px;
  cursor: pointer;
}

/* Layout */
.container {
  height: calc(100vh - 70px);
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f4f6f8;
}

/* Form box */
.form-box {
  padding: 30px;
  width: 320px;
  border-radius: 12px;
  background: white;
  text-align: center;
  box-shadow: 0 6px 18px rgba(0,0,0,0.1);
}

/* KEY FIX HERE */
.form-group {
  margin-bottom: 15px;
}

input,
select {
  width: 100%;
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  box-sizing: border-box; /* 🔥 IMPORTANT */
}

/* Errors */
.error {
  color: red;
  font-size: 12px;
}

/* Modal */
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
  border-radius: 12px;
  width: 350px;
}

.close-icon {
  position: absolute;
  top: 10px;
  right: 15px;
  font-size: 22px;
  cursor: pointer;
}
</style>