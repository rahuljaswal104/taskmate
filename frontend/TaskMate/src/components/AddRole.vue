<template>

  <div class="dashboard">

    <!-- Main Content -->
    <div class="content">

      <!-- Add Role Card -->
      <div class="card form-card">

        <!-- Header -->
        <div class="top-bar">

          <!-- Back Arrow -->
          <button
            class="back-btn"
            @click="goToDashboard"
          >
            ←
          </button>

          <h2 class="title">
            Add Role
          </h2>

        </div>

        <!-- Form -->
        <form @submit.prevent="saveRole">

          <label>Select Role</label>

          <select v-model="role.roleName">

            <option value="">
              -- Select Role --
            </option>

            <option value="SUPERADMIN">
              SUPERADMIN
            </option>

            <option value="DEPARTMENT ADMIN">
              DEPARTMENT ADMIN
            </option>

            <option value="MANAGER">
              MANAGER
            </option>

            <option value="EMPLOYEE">
              EMPLOYEE
            </option>

            <!-- <option value="VIEWER">
              VIEWER
            </option> -->

          </select>

          <button
            type="submit"
            class="save-btn"
          >
            Save Role
          </button>

        </form>

      </div>

      <!-- Role List -->
      <div class="card table-card">

        <h2 class="table-title">
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

              <tr
                v-for="item in roleList"
                :key="item.id"
              >

                <td>{{ item.roleName }}</td>

                <td>{{ item.createdAt.split("T")[0] }}</td>

                <td>

                  <span
                    :class="
                      item.active
                        ? 'active-status'
                        : 'inactive-status'
                    "
                  >
                    {{ item.active ? 'Active' : 'Inactive' }}
                  </span>

                </td>

              </tr>

              <tr v-if="roleList.length === 0">

                <td
                  colspan="3"
                  class="empty-data"
                >
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

export default {

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

    goToDashboard() {

      this.$router.push('/dashboard');

    },

    async saveRole() {
      if(this.role.roleName==""){
      alert("please select role")
      return ;
    }

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

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* Dashboard */

.dashboard {

  min-height: 100vh;
  background: #f3f7fb;
  font-family: Arial, sans-serif;

}

/* Content */

.content {

  display: flex;
  gap: 25px;
  padding: 30px;

}

/* Card */

.card {

  width: 50%;
  background: white;
  padding: 28px;
  border-radius: 18px;
  box-shadow: 0 10px 25px rgba(0,0,0,0.06);
  border: 1px solid #edf2f7;

}

/* Top Header */

.top-bar{

  display: flex;
  align-items: center;
  gap: 15px;
  margin-bottom: 25px;

}

/* Back Button */

.back-btn{

  width: 42px;
  height: 42px;
  border: none;
  border-radius: 12px;
  background: #43b77a;
  color: white;
  font-size: 22px;
  cursor: pointer;
  transition: 0.3s;
  box-shadow: 0 4px 12px rgba(67,183,122,0.25);

}

.back-btn:hover{

  transform: translateX(-3px);
  background: #369867;

}

/* Titles */

.title,
.table-title {

  color: #1e293b;
  font-size: 28px;
  font-weight: 700;

}

/* Form */

label {

  display: block;
  margin-bottom: 10px;
  color: #334155;
  font-weight: 600;
  font-size: 15px;

}

select {

  width: 100%;
  padding: 14px;
  border: 1px solid #dbe4ee;
  border-radius: 12px;
  outline: none;
  font-size: 15px;
  background: #f8fafc;
  transition: 0.3s;

}

select:focus {

  border-color: #43b77a;
  background: white;
  box-shadow: 0 0 0 4px rgba(67,183,122,0.12);

}

/* Save Button */

.save-btn {

  width: 100%;
  margin-top: 22px;
  padding: 14px;
  border: none;
  background: #43b77a;
  color: white;
  border-radius: 12px;
  cursor: pointer;
  font-size: 15px;
  font-weight: 600;
  transition: 0.3s;

}

.save-btn:hover {

  background: #369867;
  transform: translateY(-2px);

}

/* Table */

.table-container {

  overflow-x: auto;
  margin-top: 20px;

}

table {

  width: 100%;
  border-collapse: collapse;

}

thead {

  background: #43b77a;
  color: white;

}

th {

  padding: 16px;
  text-align: left;
  font-size: 14px;
  font-weight: 600;

}

td {

  padding: 16px;
  border-bottom: 1px solid #edf2f7;
  font-size: 14px;
  color: #475569;

}

tbody tr{

  transition: 0.3s;

}

tbody tr:hover {

  background: #f8fafc;

}

/* Status */

.active-status {

  background: #dcfce7;
  color: #15803d;
  padding: 6px 14px;
  border-radius: 30px;
  font-size: 12px;
  font-weight: bold;

}

.inactive-status {

  background: #fee2e2;
  color: #dc2626;
  padding: 6px 14px;
  border-radius: 30px;
  font-size: 12px;
  font-weight: bold;

}

/* Empty */

.empty-data {

  text-align: center;
  color: #94a3b8;
  padding: 25px;
  font-weight: 600;

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