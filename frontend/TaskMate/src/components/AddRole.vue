<template>

  <div class="dashboard">

    <!-- Navbar -->

    <Navbar />

    <!-- Content -->

    <div class="content">

      <!-- Add Role -->

      <div class="card">

        <h2>Add Role</h2>

        <form @submit.prevent="saveRole">

          <label>Select Role</label>

          <select v-model="role.roleName">

            <option value="">
              -- Select Role --
            </option>

            <option value="SUPER_ADMIN">
              SUPER_ADMIN
            </option>

            <option value="DEPARTMENT_ADMIN">
              DEPARTMENT_ADMIN
            </option>

            <option value="MANAGER">
              MANAGER
            </option>

            <option value="EMPLOYEE">
              EMPLOYEE
            </option>

            <option value="VIEWER">
              VIEWER
            </option>

          </select>

          <button type="submit" class="save-btn">
            Save Role
          </button>

        </form>

      </div>

      <!-- Role List -->

      <div class="card">

        <h2>Role List</h2>

        <table>

          <thead>

            <tr>

              <th>#</th>

              <th>Role Name</th>

            </tr>

          </thead>

          <tbody>

            <tr
              v-for="(item,index) in roleList"
              :key="index"
            >

              <!-- <td>{{ index + 1 }}</td> -->

              <td>{{ item.roleName }}</td>

            </tr>

            <tr v-if="roleList.length === 0">

              <td colspan="2">
                No Role Found
              </td>

            </tr>

          </tbody>

        </table>

      </div>

    </div>

  </div>

</template>

<script>

import axios from "axios";

import Navbar from "./Navbar.vue";

export default {

  components: {

    Navbar

  },

  data() {

    return {

      role: {

        roleName: ""

      },

      roleList: []

    };

  },

  mounted() {

    this.getRoles();

  },

  methods: {

    async saveRole() {

      try {

        const response = await axios.post(
          "http://localhost:8080/api/roles/save",
          this.role
        );

        alert(response.data.message);

        this.role.roleName = "";

        this.getRoles();

      } catch(error) {

        alert(error.response.data.message);

        console.log(error);

      }

    },

    async getRoles() {

      try {

        const response = await axios.get(
          "http://localhost:8080/api/roles/roleList"
        );

        // IMPORTANT

        this.roleList = response.data;

      } catch(error) {

        console.log(error);

      }

    }

  }

}

</script>

<style scoped>

.dashboard {

  min-height: 100vh;

  background: #f4f7fc;

  font-family: Arial;

}

/* Content */

.content {

  display: flex;

  gap: 20px;

  padding: 30px;

}

/* Card */

.card {

  width: 50%;

  background: white;

  padding: 20px;

  border-radius: 10px;

  box-shadow: 0 2px 10px rgba(0,0,0,0.1);

}

/* Form */

label {

  display: block;

  margin-top: 15px;

  margin-bottom: 5px;

}

select {

  width: 100%;

  padding: 10px;

}

.save-btn {

  width: 100%;

  margin-top: 20px;

  padding: 10px;

  border: none;

  background: #2563eb;

  color: white;

  border-radius: 5px;

  cursor: pointer;

}

/* Table */

table {

  width: 100%;

  margin-top: 20px;

  border-collapse: collapse;

}

th,
td {

  border: 1px solid #ddd;

  padding: 10px;

  text-align: left;

}

th {

  background: #1e293b;

  color: white;

}

</style>