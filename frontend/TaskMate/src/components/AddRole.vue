<template>

  <div class="dashboard">

    <!-- Navbar -->
    <Navbar />

    <!-- Content -->
    <div class="content">

      <!-- Add Role -->
      <div class="card">

        <h2 class="title">
          Add Role
        </h2>

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

        <h2 class="title">
          Role List
        </h2>

        <div class="table-container">

          <table>

            <thead>

              <tr>

                <th>Role Name</th>

                <th>Created Date</th>

                <th>Status</th>

              </tr>

            </thead>

            <tbody>

              <tr v-for="item in roleList" :key="item.id">

                <td>{{ item.roleName }}</td>

                <td>{{ item.createdAt.split("T")[0] }}</td>

                <td>{{ item.active == true ? 'Active' : 'Inactive' }}</td>

              </tr>

              <tr v-if="roleList.length === 0">

                <td colspan="3" class="empty-data">
                  No Role Found
                </td>

              </tr>

            </tbody>

          </table>

        </div>

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

        this.roleList = response.data.data;

      } catch(error) {

        console.log(error);

      }

    }

  }

}

</script>

<style scoped>

/* Dashboard */

.dashboard {

  min-height: 100vh;
  background: #f6fff9;
  font-family: Arial, sans-serif;

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
  padding: 25px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(71,179,123,0.12);
  border-top: 4px solid #47b37b;

}

/* Title */

.title {

  color: #47b37b;
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;

}

/* Form */

label {

  display: block;
  margin-top: 15px;
  margin-bottom: 8px;
  color: #2f855a;
  font-weight: 600;

}

select {

  width: 100%;
  padding: 12px;
  border: 1px solid #ccebd9;
  border-radius: 8px;
  outline: none;
  font-size: 14px;

}

select:focus {

  border-color: #47b37b;

}

/* Save Button */

.save-btn {

  width: 100%;
  margin-top: 20px;
  padding: 12px;
  border: none;
  background: #47b37b;
  color: white;
  border-radius: 8px;
  cursor: pointer;
  font-size: 15px;
  font-weight: bold;
  transition: 0.3s;

}

.save-btn:hover {

  background: #379764;

}

/* Table */

.table-container {

  overflow-x: auto;

}

table {

  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;

}

thead {

  background: #47b37b;
  color: white;

}

th {

  padding: 14px;
  text-align: left;
  font-size: 14px;

}

td {

  padding: 14px;
  border-bottom: 1px solid #e5f5ec;
  font-size: 14px;

}

tbody tr:hover {

  background: #f3fff7;

}

/* Status */

.active-status {

  background: #dcfce7;
  color: #15803d;
  padding: 5px 12px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: bold;

}

.inactive-status {

  background: #fee2e2;
  color: #dc2626;
  padding: 5px 12px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: bold;

}

/* Empty Data */

.empty-data {

  text-align: center;
  color: gray;
  font-weight: bold;

}

/* Responsive */

@media(max-width: 900px) {

  .content {

    flex-direction: column;

  }

  .card {

    width: 100%;

  }

}

</style>