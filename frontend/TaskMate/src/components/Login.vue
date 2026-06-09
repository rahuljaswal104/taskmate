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

          <!-- Department -->

          <div class="input-group">

            <select v-model="register.department">

              <option disabled :value="null">
                Select Department
              </option>

              <option
                v-for="dept in departmentNameList"
                :key="dept.id"
                :value="dept"
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

          <!-- Designation -->

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

          <!-- Role -->

          <div class="input-group">

            <select v-model="register.role">

              <option disabled :value="null">
                Select Role
              </option>

              <option
                v-for="role in roleList"
                :key="role.id"
                :value="role"
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

          <!-- Gender -->

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

        department: null,

        designation: "",

        role: null,

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

    login() {

      if (!this.loginusername || !this.password) {

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

      .then(res => res.json())

      .then(data => {

        if (data.code === 200) {

          localStorage.setItem("isLoggedIn","true");

          localStorage.setItem("role", data.data.role.roleName);

          localStorage.setItem("username",data.data.username);

          localStorage.setItem("name",data.data.name);

          localStorage.setItem("departmentid", data.data.department.id);

          localStorage.setItem("userId", data.data.id);

          if (data.data.role.roleName === "SUPERADMIN" || data.data.role.roleName === "DEPARTMENT ADMIN" || data.data.role.roleName === "MANAGER") {

            this.$router.push("/dashboard");

          }

          else {

            this.$router.push("/taskList");

          }

        }

        else {

          alert(data.message);

        }

      })

      .catch(error => {

        console.log(error);

        alert("Login Failed");

      });

    },

    async registerUser() {

      this.errors = {};

      if (!this.register.name.trim()) {

        this.errors.name =
          "Full name is required";

      }

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

      if (!this.register.password) {

        this.errors.password =
          "Password is required";

      }

      if (
        this.register.password !==
        this.register.repassword
      ) {

        this.errors.repassword =
          "Passwords do not match";

      }



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

      if (!this.register.department) {

      alert("Department is required");
      return;

    }

    if (!this.register.designation) {

      alert("Designation is required");
      return;

    }

    if (!this.register.role) {

      alert("Role is required");
      return;

    }

    if (!this.register.gender) {

      alert("Gender is required");
      return;

    }

      if (
        Object.keys(this.errors)
          .length > 0
      ) {

        return;

      }

      try {

        const response =
          await axios.post(

            "http://localhost:8080/api/register",

            this.register

          );

        alert(
          response.data.message ||
          "User Registered Successfully"
        );

        this.showPopup = false;
        
          this.register = {
            name: "",
            username: "",
            password: "",
            repassword: "",
            phone: "",
            department: null,
            designation: "",
            role: null,
            gender: ""
          };


      }

      catch(error) {

        console.log(error);

        alert(
          "Registration Failed"
        );

      }

    },

    async fetchDepartments() {

      try {

        const response =
          await axios.get(

            "http://localhost:8080/api/departments/get"

          );

        this.departmentNameList =
          response.data;

      }

      catch(error) {

        console.log(error);

      }

    },

    async getRoles() {

      try {

        const response =
          await axios.get(

            "http://localhost:8080/api/roles/roleList"

          );

        this.roleList =
          response.data.data;

      }

      catch(error) {

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
  background:#eef1f5;
}

/* Navbar */

.navbar{
  height:90px;
  background:#43c18c;
  display:flex;
  justify-content:space-between;
  align-items:center;
  padding:0 40px;
}

/* Logo */

.logo-area{
  display:flex;
  align-items:center;
  gap:16px;
}

.logo-circle{
  width:62px;
  height:62px;
  border-radius:16px;
  background:white;
  color:#43c18c;
  display:flex;
  justify-content:center;
  align-items:center;
  font-size:26px;
  font-weight:bold;
}

.logo-area h2{
  color:white;
  font-size:42px;
  font-weight:700;
}

.logo-area p{
  color:#f4fff8;
  font-size:15px;
  margin-top:3px;
}

/* Buttons */

.register-btn,
.login-btn{
  border:none;
  cursor:pointer;
  font-weight:600;
}

.register-btn{
  background:white;
  color:#43c18c;
  padding:14px 28px;
  border-radius:14px;
  font-size:16px;
}

.login-btn{
  background:#43c18c;
  color:white;
  padding:13px 30px;
  border-radius:12px;
  font-size:17px;
  margin-top:10px;
}

/* Container */

.container{
  height:calc(100vh - 90px);
  display:flex;
  justify-content:center;
  align-items:center;
}

/* Login Card */

.login-card{
  width:430px;
  background:white;
  padding:40px;
  border-radius:30px;
  text-align:center;
  box-shadow:0 10px 30px rgba(0,0,0,0.08);
}

.login-card h1{
  font-size:42px;
  margin-bottom:12px;
  color:#1f2937;
  line-height:1.2;
}

.login-card p{
  color:#6b7280;
  font-size:16px;
  margin-bottom:28px;
}

/* Inputs */

.input-group{
  width:100%;
  margin-bottom:18px;
}

input,
select{
  width:100%;
  padding:15px;
  border-radius:12px;
  border:1px solid #d1d5db;
  outline:none;
  font-size:15px;
  color:#4b5563;
  background:white;
}

input::placeholder{
  color:#9ca3af;
}

input:focus,
select:focus{
  border-color:#43c18c;
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
  border-radius:30px;
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
  font-size:32px;
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
    font-size:28px;
  }

}

</style>