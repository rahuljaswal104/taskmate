<template>
    <div>
  <Navbar />
  <div class="page-container">
  
    <!-- Form Section -->
    <div class="left-panel">
      <div class="form-card">
        <h2>Add Department</h2>

        <div class="field-row">
          <div class="field-box">
           

<label for="departmentCode">Department Code</label>
<input
  id="departmentCode"
  name="departmentCode"
  v-model="department.departmentCode"
  placeholder="Enter Department Code"
/>

          </div>

          <div class="field-box">
          
            <label for="departmentName">Department Name</label>
<input
  id="departmentName"
  name="departmentName"
  v-model="department.departmentName"
  placeholder="Enter Department Name"
/>
          </div>
        </div>

        <div class="button-group">
          <button class="save-btn" @click="saveDepartment">Save</button>
          <button class="clear-btn" @click="clearForm">Clear</button>
        </div>
      </div>
    </div>

    <!-- Table Section -->
    <div class="table-card">
      <h2>Department List</h2>
 
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Name</th>
            <th>Created Date</th>
             <th>Updated Date</th>
            <th>Status</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="dept in departments" :key="dept.id">
            <td>{{ dept.id }}</td>
            <td>{{ dept.departmentCode }}</td>
            <td>{{ dept.departmentName }}</td>
             <!-- <td>{{ dept.createdAt.split('T')[0]}}</td> -->
               <td>{{ formatDate(dept.createdAt) }}</td>
             <td>{{ formatDate(dept.updatedAt) }}</td>
            <!-- <td>{{ dept.updatedAt.split('T')[0] }}</td> -->
            <td>
              <span :class="dept.active ? 'active' : 'inactive'">
                {{ dept.active ? "Active" : "Inactive" }}
              </span>
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

  methods:
  {

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

        console.log("Save button clicked");
  console.log("Data:", this.department);
      
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
 console.log("Success:", response.data);
    alert(response.data.message);
    await this.fetchDepartments();
    this.clearForm();

  } catch (error) {
     console.error("ERROR:", error);
    console.error("Response:", error.response);
    alert("Save failed");
  }
}
    
    
    ,

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
  background: #f8fafc;
  padding: 10px;
  font-family: Arial, sans-serif;
}

/* Form Section */
.left-panel {
  margin-top: 15px;
  margin-bottom: 25px;
}


.form-card {
  /* max-width: 1100px;
  margin: auto; */
  background: white;
  border-radius: 16px;
  padding: 25px;
  box-shadow: 0 8px 20px rgba(0,0,0,0.08);
}

/* .form-card h2 {
  margin-bottom: 20px;
   color: #1e293b; 
} */

/* Fields */
.field-row {
  display: flex;
  justify-content: space-between;
  gap: 20px;
}

.field-box {
  width: 48%;
  display: flex;
  flex-direction: column;
  gap: 8px;
}

label {
  font-weight: 600;
  color: #334155;
}

input {
  width: 100%;
  padding: 12px;
  border: 2px solid #e2e8f0;
  border-radius: 10px;
  font-size: 15px;
}

/* Buttons */
.button-group {
  display: flex;
  gap: 15px;
  margin-top: 20px;
}

button {
  padding: 12px 25px;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-size: 15px;
}

.save-btn {
  background: #48bb78;
  color: white;
}

.clear-btn {
  background: #e2e8f0;
}

/* Table */
.table-card {
  /* max-width: 1100px;
  margin: auto; */
  background: white;
  border-radius: 16px;
  padding: 25px;
  box-shadow: 0 8px 20px rgba(0,0,0,0.08);
}

/* .table-card h2 {
  margin-bottom: 20px;
  color: #1e293b;
} */

.form-card h2,
.table-card h2 {
  width: 100%;
  margin-bottom: 20px;
  font-size: 24px;
  color: #48bb78;
  font-weight: 600;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  background: #48bb78;
  color: white;
  padding: 14px;
  text-align: left;
}

td {
  padding: 14px;
  border-bottom: 1px solid #e2e8f0;
}

.active {
  color: green;
  font-weight: bold;
}

.inactive {
  color: red;
  font-weight: bold;
}
</style>