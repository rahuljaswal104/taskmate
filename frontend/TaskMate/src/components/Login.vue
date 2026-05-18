<template>

  <div class="page">

    <!-- Navbar -->

    <nav class="navbar">

      <div class="logo-area">

        <div class="logo-circle">
          TM
        </div>

        <div>

          <h2>TaskMate</h2>

          <p>Task Management System</p>

        </div>

      </div>

      <button
        class="register-btn"
        @click="openPopup"
      >
        Register
      </button>

    </nav>

    <!-- Login Section -->

    <div class="container">

      <div class="login-card">

        <h1>Welcome Back</h1>

        <p>Please login to continue</p>

        <!-- Username -->

        <div class="input-group">

          <input
            type="text"
            v-model="loginusername"
            placeholder="Enter Username"
          />

        </div>

        <!-- Password -->

        <div class="input-group">

          <input
            type="password"
            v-model="password"
            placeholder="Enter Password"
          />

        </div>

        <!-- Login Button -->

        <button
          class="login-btn"
          @click="login"
        >
          Login
        </button>

      </div>

    </div>

    <!-- Register Popup -->

    <div
      v-if="showPopup"
      class="popup-overlay"
    >

      <div class="popup-box">

        <!-- Close -->

        <span
          class="close-btn"
          @click="closePopup"
        >
          ×
        </span>

        <!-- Header -->

        <div class="popup-header">

          <h2>Register Employee</h2>

          <p>Create new employee account</p>

        </div>

        <!-- Full Name -->

        <div class="input-group">

          <input
            type="text"
            v-model="register.name"
            placeholder="Full Name"
          />

          <small
            v-if="errors.name"
            class="error-text"
          >
            {{ errors.name }}
          </small>

        </div>

        <!-- Email -->

        <div class="input-group">

          <input
            type="email"
            v-model="register.username"
            placeholder="Enter Email"
          />

          <small
            v-if="errors.username"
            class="error-text"
          >
            {{ errors.username }}
          </small>

        </div>

        <!-- Password Row -->

        <div class="row">

          <div class="input-group">

            <input
              type="password"
              v-model="register.password"
              placeholder="Password"
            />

            <small
              v-if="errors.password"
              class="error-text"
            >
              {{ errors.password }}
            </small>

          </div>

          <div class="input-group">

            <input
              type="password"
              v-model="register.repassword"
              placeholder="Confirm Password"
            />

            <small
              v-if="errors.repassword"
              class="error-text"
            >
              {{ errors.repassword }}
            </small>

          </div>

        </div>

        <!-- Phone -->

        <div class="input-group">

          <input
            type="text"
            v-model="register.phone"
            placeholder="Phone Number"
          />

          <small
            v-if="errors.phone"
            class="error-text"
          >
            {{ errors.phone }}
          </small>

        </div>

        <!-- Department + Designation -->

        <div class="row">

          <div class="input-group">

            <select v-model="register.department">

              <option disabled value="">
                Select Department
              </option>

              <option
                v-for="dept in departmentNameList"
                :key="dept.id"
                :value="dept.departmentName"
              >
                {{ dept.departmentName }}
              </option>

            </select>

            <small
              v-if="errors.department"
              class="error-text"
            >
              {{ errors.department }}
            </small>

          </div>

          <div class="input-group">

            <select v-model="register.designation">

              <option disabled value="">
                Select Designation
              </option>

              <option
                v-for="des in designationList"
                :key="des"
                :value="des"
              >
                {{ des }}
              </option>

            </select>

            <small
              v-if="errors.designation"
              class="error-text"
            >
              {{ errors.designation }}
            </small>

          </div>

        </div>

        <!-- Role + Gender -->

        <div class="row">

          <div class="input-group">

            <select v-model="register.role">

              <option disabled value="">
                Select Role
              </option>

              <option
                v-for="role in roleList"
                :key="role.id"
                :value="role.roleName"
              >
                {{ role.roleName }}
              </option>

            </select>

            <small
              v-if="errors.role"
              class="error-text"
            >
              {{ errors.role }}
            </small>

          </div>

          <div class="input-group">

            <select v-model="register.gender">

              <option disabled value="">
                Select Gender
              </option>

              <option value="Male">
                Male
              </option>

              <option value="Female">
                Female
              </option>

              <option value="Other">
                Other
              </option>

            </select>

            <small
              v-if="errors.gender"
              class="error-text"
            >
              {{ errors.gender }}
            </small>

          </div>

        </div>

        <!-- Register Button -->

        <div class="btn-center">

          <button
            class="login-btn"
            @click="registerUser"
          >
            Register Employee
          </button>

        </div>

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

      loginusername: "",

      password: "",

      showPopup: false,

      errors: {},

      departmentNameList: [],

      designationList: [

        "Managing Director",

        "Director",

        "Manager",

        "Team Leader",

        "Developer",

        "Tester",

        "Computer Operator"

      ],

      roleList: [],

      register: {

        name: "",

        username: "",

        password: "",

        repassword: "",

        phone: "",

        department: "",

        designation: "",

        role: "",

        gender: ""
      }
    };
  },

  mounted() {

    this.fetchDepartments();

    this.getRoles();
  },

  methods: {

    openPopup() {

      this.showPopup = true;

      this.errors = {};
    },

    closePopup() {

      this.showPopup = false;

      this.errors = {};
    },

    // Login

    login() {

      if (
        !this.loginusername ||
        !this.password
      ) {

        alert("Please fill all fields");

        return;
      }

      fetch(
        "http://localhost:8080/api/login",
        {

          method: "POST",

          headers: {

            "Content-Type":
              "application/json"
          },

          body: JSON.stringify({

            username:
              this.loginusername,

            password:
              this.password
          })
        }
      )

      .then(res => res.text())

      .then(data => {

        if (
          data.toLowerCase().includes("login success")) {

          localStorage.setItem("isLoggedIn","true");

          this.$router.push(
            "/dashboard"
          );

        } else {
          alert(data);
        }
      })

      .catch(() => {

        alert("Login Failed");
      });
    },

    // Register

    async registerUser() {

      // Clear Old Errors

      this.errors = {};

      // Name Validation

      if (!this.register.name.trim()) {

        this.errors.name =
          "Full name is required";
      }

      // Email Validation

      const emailRegex =
        /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

      if (
        !emailRegex.test(
          this.register.username
        )
      ) {

        this.errors.username =
          "Username must be a valid email";
      }

      // Password Validation

      if (
        !this.register.password
      ) {

        this.errors.password =
          "Password is required";
      }

      // Confirm Password

      if (
        this.register.password !==
        this.register.repassword
      ) {

        this.errors.repassword =
          "Passwords do not match";
      }

      // Phone Validation

      const phoneRegex =
        /^[0-9]{10}$/;

      if (
        !phoneRegex.test(
          this.register.phone
        )
      ) {

        this.errors.phone =
          "Phone number must be exactly 10 digits";
      }

      // Department Validation

      if (
        !this.register.department
      ) {

        this.errors.department =
          "Department is required";
      }

      // Designation Validation

      if (
        !this.register.designation
      ) {

        this.errors.designation =
          "Designation is required";
      }

      // Role Validation

      if (
        !this.register.role
      ) {

        this.errors.role =
          "Role is required";
      }

      // Gender Validation

      if (
        !this.register.gender
      ) {

        this.errors.gender =
          "Gender is required";
      }

      // Stop If Any Error Exists

      if (
        Object.keys(this.errors).length > 0
      ) {

        return;
      }

      try {

        const response = await axios.post(
          "http://localhost:8080/api/register",
          this.register
        );

        // Success Message

        if (
          response.data &&
          response.data.message
        ) {

          alert(
            response.data.message
          );

        } else {

          alert(
            "User Registered Successfully"
          );
        }

        // Close Popup

        this.showPopup = false;

        // Reset Form

        this.register = {

          name: "",

          username: "",

          password: "",

          repassword: "",

          phone: "",

          department: "",

          designation: "",

          role: "",

          gender: ""
        };

      } catch(error) {

        console.log(error);

        // Backend Validation Errors

        if (
          error.response &&
          error.response.data &&
          error.response.data.data
        ) {

          // Merge Backend Errors

          this.errors = {

            ...this.errors,

            ...error.response.data.data
          };

        } else {

          alert(
            "Registration Failed"
          );
        }
      }
    },

    // Fetch Departments

    async fetchDepartments() {

      try {

        const response =
          await axios.get(
            "http://localhost:8080/api/departments/get"
          );

        this.departmentNameList =
          response.data;

      } catch(error) {

        console.log(error);
      }
    },

    // Fetch Roles

    async getRoles() {

      try {

        const response =
          await axios.get(
            "http://localhost:8080/api/roles/roleList"
          );

        this.roleList =
          response.data.data;

      } catch(error) {

        console.log(error);
      }
    }
  }
};

</script>

<style>

/* Global */

*{
  margin:0;
  padding:0;
  box-sizing:border-box;
  font-family:"Segoe UI",sans-serif;
}

body{
  background:#f4f7fb;
}

/* Navbar */

.navbar{
  height:85px;
  background:#42b883;
  display:flex;
  justify-content:space-between;
  align-items:center;
  padding:0 40px;
}

/* Logo */

.logo-area{
  display:flex;
  align-items:center;
  gap:14px;
}

.logo-circle{
  width:58px;
  height:58px;
  border-radius:14px;
  background:white;
  color:#42b883;
  display:flex;
  justify-content:center;
  align-items:center;
  font-size:20px;
  font-weight:bold;
}

.logo-area h2{
  color:white;
  font-size:32px;
}

.logo-area p{
  color:#eafff5;
  font-size:14px;
}

/* Buttons */

.register-btn,
.login-btn{
  border:none;
  padding:12px 24px;
  border-radius:10px;
  cursor:pointer;
  font-size:15px;
  font-weight:600;
}

.register-btn{
  background:white;
  color:#42b883;
}

.login-btn{
  background:#42b883;
  color:white;
}

/* Container */

.container{
  height:calc(100vh - 85px);
  display:flex;
  justify-content:center;
  align-items:center;
}

/* Login Card */

.login-card{
  width:400px;
  background:white;
  padding:40px;
  border-radius:25px;
  text-align:center;
  box-shadow:0 10px 30px rgba(0,0,0,0.08);
}

.login-card h1{
  margin-bottom:10px;
}

.login-card p{
  color:#6b7280;
  margin-bottom:25px;
}

/* Inputs */

.input-group{
  width:100%;
  margin-bottom:18px;
}

input,
select{
  width:100%;
  padding:14px;
  border-radius:10px;
  border:1px solid #d1d5db;
  outline:none;
  font-size:14px;
  color:#6b7280;
  background:white;
}

input::placeholder{
  color:#9ca3af;
}

input:focus,
select:focus{
  border-color:#42b883;
}

/* Popup */

.popup-overlay{
  position:fixed;
  top:0;
  left:0;
  width:100%;
  height:100%;
  background:rgba(0,0,0,0.45);
  display:flex;
  justify-content:center;
  align-items:center;
}

.popup-box{
  width:650px;
  background:white;
  border-radius:35px;
  padding:30px;
  position:relative;
  max-height:88vh;
  overflow-y:auto;
}

/* Popup Header */

.popup-header{
  text-align:center;
  margin-bottom:25px;
}

.popup-header h2{
  font-size:30px;
}

.popup-header p{
  color:#6b7280;
  margin-top:5px;
}

/* Close */

.close-btn{
  position:absolute;
  top:15px;
  right:20px;
  font-size:30px;
  cursor:pointer;
}

/* Row */

.row{
  display:flex;
  gap:15px;
}

.row .input-group{
  flex:1;
}

/* Button Center */

.btn-center{
  text-align:center;
  margin-top:10px;
}

/* Error */

.error-text{
  color:red;
  font-size:13px;
  margin-top:5px;
  display:block;
  text-align:left;
  padding-left:2px;
}

/* Responsive */

@media(max-width:768px){

  .popup-box{
    width:95%;
    padding:22px;
  }

  .row{
    flex-direction:column;
    gap:0;
  }

  .login-card{
    width:92%;
  }

  .navbar{
    padding:0 18px;
  }

  .logo-area h2{
    font-size:26px;
  }
}

</style>