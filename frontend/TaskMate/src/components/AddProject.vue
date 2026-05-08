<template>
  <div class="page">

    <!-- Navbar -->
    <Navbar />

    <!-- Main Content -->
    <div class="main">

      <!-- Add Project Card -->
      <section class="card form-card">

        <h2>Add Project</h2>

        <div class="form-grid">

          <!-- Project Name Only -->
          <div class="field full-width">
            <label>Project Name</label>

            <input
              v-model="project.projectName"
              type="text"
              placeholder="Enter Project Name"
            />
          </div>

        </div>

        <div class="actions">

          <button class="btn save" @click="saveProject">
            Save
          </button>

          <button class="btn clear" @click="clearForm">
            Clear
          </button>

        </div>

      </section>

      <!-- Project List -->
      <section class="card table-card">

        <h2>Project List</h2>

        <div class="table-wrapper">

          <table>

            <thead>
              <tr>
                <th>ID</th>
                <th>Project Name</th>
                <th>Created Date</th>
                <th>Updated Date</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>

              <tr
                v-for="item in projectList"
                :key="item.id"
              >
                <td>{{ item.id }}</td>
                <td>{{ item.projectName }}</td>

                <td>{{ item.createdAt?.split('T')[0] }}</td>
                <td>{{ item.updatedAt?.split('T')[0] }}</td>

                <td>
                  <span
                    :class="item.status === 'ACTIVE'
                      ? 'active'
                      : 'inactive'"
                  >
                    {{ item.status }}
                  </span>
                </td>
              </tr>

              <tr v-if="projectList.length === 0">
                <td colspan="5" class="empty">
                  No Project Found
                </td>
              </tr>

            </tbody>

          </table>

        </div>

      </section>

    </div>

  </div>
</template>

<script>
import Navbar from "./Navbar.vue";
import axios from "axios";

export default {

  components: {
    Navbar
  },

  data() {
    return {

      project: {
        projectName: ""
      },

      projectList: []

    };
  },

  mounted() {
    this.getProjects();
  },

  methods: {

    async saveProject() {

      try {

        await axios.post(
          "http://localhost:8080/api/project/save",
          this.project
        );

        this.clearForm();
        this.getProjects();

      } catch (err) {
        console.log(err);
      }

    },

    clearForm() {

      this.project = {
        projectName: ""
      };

    },

    async getProjects() {

      try {

        const res = await axios.get(
          "http://localhost:8080/api/project/projectList"
        );

        this.projectList = res.data.data;

      } catch (err) {
        console.log(err);
      }

    }

  }

};
</script>

<style scoped>

.page {
  background: #f4f7fb;
  min-height: 100vh;
}

/* Main Layout */
.main {
  padding: 30px;
  display: flex;
  flex-direction: column;
  gap: 25px;
}

/* Card */
.card {
  background: #fff;
  border-radius: 14px;
  padding: 25px;
  box-shadow: 0 6px 18px rgba(0,0,0,0.08);
}

/* Form */
.form-grid {
  margin-top: 20px;
}

.full-width {
  width: 100%;
}

.field label {
  display: block;
  margin-bottom: 8px;
  font-weight: 600;
}

.field input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 8px;
  outline: none;
  transition: 0.3s;
}

.field input:focus {
  border-color: #43b77a;
}

/* Buttons */
.actions {
  margin-top: 20px;
  display: flex;
  gap: 12px;
}

.btn {
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
}

.save {
  background: #43b77a;
  color: white;
}

.clear {
  background: #e5e7eb;
}

/* Table */
.table-wrapper {
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

th,
td {
  padding: 14px;
  border-bottom: 1px solid #eee;
  text-align: left;
}

tbody tr:hover {
  background: #f9fafb;
}

/* Status */
.active {
  color: green;
  font-weight: bold;
}

.inactive {
  color: red;
  font-weight: bold;
}

/* Empty State */
.empty {
  text-align: center;
  padding: 20px;
  color: #888;
}

</style>