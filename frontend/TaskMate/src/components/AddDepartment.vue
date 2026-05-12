<template>
  <div class="page-container">

    <Navbar />

    <!-- Header -->
    <div class="page-header">

      <div class="header-left">

        <!-- Back Button -->
        <button
          class="back-btn"
          @click="goToDashboard"
        >
          <span>←</span>
        </button>

        <div>
          <h2>Department</h2>
        </div>

      </div>

    </div>

    <!-- Form Section -->
    <div class="left-panel">

      <div class="form-card">

        <h2>Add Department</h2>

        <div class="field-row">

          <!-- Department Code -->
          <div class="field-box">

            <label for="departmentCode">
              Department Code
            </label>

            <input
              id="departmentCode"
              name="departmentCode"
              v-model="department.departmentCode"
              placeholder="Enter Department Code"
            />

          </div>

          <!-- Department Name -->
          <div class="field-box">

            <label for="departmentName">
              Department Name
            </label>

            <input
              id="departmentName"
              name="departmentName"
              v-model="department.departmentName"
              placeholder="Enter Department Name"
            />

          </div>

        </div>

        <!-- Buttons -->
        <div class="button-group">

          <button
            class="save-btn"
            @click="saveDepartment"
          >
            Save Department
          </button>

          <button
            class="clear-btn"
            @click="clearForm"
          >
            Clear
          </button>

        </div>

      </div>

    </div>

    <!-- Table -->
    <div class="table-card">

      <div class="table-header">

        <h2>Department List</h2>

        <div class="table-count">
          Total : {{ departments.length }}
        </div>

      </div>

      <div class="table-wrapper">

        <table>

          <thead>

            <tr>
              <th>ID</th>
              <th>Department Code</th>
              <th>Department Name</th>
              <th>Created Date</th>
              <th>Updated Date</th>
              <th>Status</th>
            </tr>

          </thead>

          <tbody>

            <tr
              v-for="dept in departments"
              :key="dept.id"
            >

              <td>{{ dept.id }}</td>

              <td>{{ dept.departmentCode }}</td>

              <td>{{ dept.departmentName }}</td>

              <td>{{ formatDate(dept.createdAt) }}</td>

              <td>{{ formatDate(dept.updatedAt) }}</td>

              <td>

                <span
                  :class="dept.active ? 'active' : 'inactive'"
                >
                  {{ dept.active ? "Active" : "Inactive" }}
                </span>

              </td>

            </tr>

            <!-- Empty -->
            <tr v-if="departments.length === 0">

              <td
                colspan="6"
                class="empty-data"
              >
                No Department Found
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

export default {

  name: "DepartmentPage",

  data() {

    return {

      department: {
        departmentCode: "",
        departmentName: ""
      },

      departments: []

    };

  },

  mounted() {

    this.fetchDepartments();

  },

  methods: {

    goToDashboard() {

      this.$router.push("/dashboard");

    },

    formatDate(date) {

      if (!date) return "";

      const onlyDate = date.split("T")[0];
      const parts = onlyDate.split("-");

      return `${parts[2]}-${parts[1]}-${parts[0]}`;

    },

    async fetchDepartments() {

      try {

        const response = await axios.get(
          "http://localhost:8080/api/departments/get"
        );

        this.departments = response.data;

      } catch (error) {

        console.error(error);

      }

    },

    async saveDepartment() {

      try {

        const response = await axios.post(
          "http://localhost:8080/api/departments/save",
          this.department,
          {
            headers: {
              "Content-Type": "application/json"
            }
          }
        );

        alert(response.data.message);

        await this.fetchDepartments();

        this.clearForm();

      } catch (error) {

        console.error(error);

        alert("Save Failed");

      }

    },

    clearForm() {

      this.department = {
        departmentCode: "",
        departmentName: ""
      };

    }

  }

};
</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.page-container {
  width: 100%;
  min-height: 100vh;
  background: #f4f7fb;
  padding: 25px;
  font-family: Arial, sans-serif;
}

/* Header */

.page-header {
  margin-bottom: 25px;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 18px;
}

.header-left h2 {
  font-size: 30px;
  color: #1e293b;
  margin-bottom: 4px;
}

.header-left p {
  color: #64748b;
  font-size: 14px;
}

/* Back Button */

.back-btn {
  display: flex;
  align-items: center;
  gap: 8px;
  background: linear-gradient(135deg, #ffffff, #f8fafc);
  color: #1e293b;
  border: 1px solid #dbe4ee;
  padding: 12px 20px;
  border-radius: 14px;
  font-size: 14px;
  font-weight: 700;
  cursor: pointer;
  transition: all 0.3s ease;
}

.back-btn:hover {
  background: linear-gradient(135deg, #48bb78, #2f9e61);
  color: white;
  border-color: #48bb78;
  transform: translateY(-2px);
}

/* Form */

.left-panel {
  margin-bottom: 25px;
}

.form-card {
  background: white;
  border-radius: 18px;
  padding: 30px;
  box-shadow: 0 8px 20px rgba(0,0,0,0.06);
}

.form-card h2 {
  margin-bottom: 20px;
  color: #1e293b;
}

.field-row {
  display: flex;
  gap: 20px;
}

.field-box {
  width: 50%;
  display: flex;
  flex-direction: column;
  gap: 8px;
}

label {
  font-size: 14px;
  font-weight: 600;
  color: #334155;
}

input {
  width: 100%;
  padding: 14px;
  border: 1px solid #dbe4ee;
  border-radius: 12px;
  font-size: 14px;
  outline: none;
}

input:focus {
  border-color: #48bb78;
  box-shadow: 0 0 0 3px rgba(72,187,120,0.12);
}

/* Buttons */

.button-group {
  display: flex;
  gap: 14px;
  margin-top: 25px;
}

.save-btn {
  background: linear-gradient(135deg, #48bb78, #2f9e61);
  color: white;
  border: none;
  padding: 13px 24px;
  border-radius: 12px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
}

.clear-btn {
  background: #eef2f7;
  color: #334155;
  border: none;
  padding: 13px 24px;
  border-radius: 12px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
}

/* Table */

.table-card {
  background: white;
  border-radius: 18px;
  padding: 30px;
  box-shadow: 0 8px 20px rgba(0,0,0,0.06);
}

.table-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.table-header h2 {
  font-size: 24px;
  color: #1e293b;
}

.table-count {
  background: #ecfdf3;
  color: #15803d;
  padding: 8px 14px;
  border-radius: 30px;
  font-size: 13px;
  font-weight: 600;
}

.table-wrapper {
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
}

thead {
  background: linear-gradient(135deg, #48bb78, #2f9e61);
  color: white;
}

th,
td {
  padding: 15px;
  text-align: left;
  border-bottom: 1px solid #eef2f7;
  font-size: 14px;
}

tbody tr:hover {
  background: #f8fafc;
}

/* Status */

.active {
  background: #dcfce7;
  color: #15803d;
  padding: 6px 12px;
  border-radius: 30px;
  font-size: 12px;
  font-weight: 600;
}

.inactive {
  background: #fee2e2;
  color: #dc2626;
  padding: 6px 12px;
  border-radius: 30px;
  font-size: 12px;
  font-weight: 600;
}

/* Empty */

.empty-data {
  text-align: center;
  color: #64748b;
  padding: 25px;
  font-weight: 600;
}

/* Responsive */

@media(max-width: 768px) {

  .field-row {
    flex-direction: column;
  }

  .field-box {
    width: 100%;
  }

  .header-left {
    flex-direction: column;
    align-items: flex-start;
  }

  .table-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

}
</style>