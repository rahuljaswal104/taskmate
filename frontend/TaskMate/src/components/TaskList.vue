<template>
  <Navbar v-if="role === 'EMPLOYEE'" />

    <div class="page-container" v-if="role !== 'SUPERADMIN'">

     <div class="header-section">

         <!-- Back Button -->
      <button v-if="role !== 'EMPLOYEE'"  class="back-btn" @click="$router.back()">
        ← 
      </button>

      <h2 class="page-title">
        My Task List
      </h2>
    </div>

    <!-- Table Card -->
    <div class="table-card">
      <!-- Table -->
      <div class="table-wrapper">

        <table class="task-table">

          <thead>
            <tr>
              <th>Task Title</th>
              <th>Project</th>
              <th>Assign To</th>
              <th>Assign By</th>
              <th>Assign Date</th>
              <th>Target Date</th>
              <th>Status</th>
              <th v-if="role==='EMPLOYEE'">Action</th>
            </tr>
          </thead>

          <tbody>

            <tr v-for="task in myTasks" :key="task.id">

              <td>{{ task.title }}</td>

              <td>{{ task.project.projectName }}</td>

              <td>{{ task.name }}</td>

              <td>{{ task.assignedBy.name }}</td>

              <td>{{ formatDate(task.assignedDate) }}</td>

              <td>{{ formatDate(task.targetDate) }}</td>

              <td>
                <span
                  class="status-badge"
                  :class="getStatusClass(task.taskStatus)"
                >
                  {{ task.taskStatus }}
                </span>
              </td>
             
             <td v-if="role==='EMPLOYEE'">
            <button class="update-btn" @click="gotoUpdateTask(task.id)">
              <i class="fa-solid fa-pen-to-square"></i>

              <span class="tooltip-text">
                Update Task
              </span>
            </button>
          </td>
            </tr>

            <!-- No Data -->

            <tr v-if="myTasks.length === 0">
              <td colspan="7" class="no-data">
                No Tasks Found
              </td>
            </tr>

          </tbody>

        </table>

      </div>

    </div>
  </div>

   <!--Employee TaskList -->

  <div class="page-container" v-if="role !== 'EMPLOYEE'">


     <div class="header-section">

         <!-- Back Button -->
      <button v-if="role == 'SUPERADMIN'"  class="back-btn" @click="$router.back()" >
        ← 
      </button>

      <h2 class="page-title">
        Employee Task List
      </h2>
    </div>

    <!-- Table Card -->
    <div class="table-card">
      <!-- Table -->
      <div class="table-wrapper">

        <table class="task-table">

          <thead>
            <tr>
              <th>Task Title</th>
              <th>Project</th>
              <th>Assign To</th>
              <th>Assign By</th>
              <th>Assign Date</th>
              <th>Target Date</th>
              <th>Status</th>
              <th v-if="role==='EMPLOYEE'">Action</th>
            </tr>
          </thead>

          <tbody>

            <tr v-for="task in tasks" :key="task.id">

              <td>{{ task.title }}</td>

              <td>{{ task.project.projectName }}</td>

              <td>{{ task.name }}</td>

              <td>{{ task.assignedBy.name }}</td>

              <td>{{ formatDate(task.assignedDate) }}</td>

              <td>{{ formatDate(task.targetDate) }}</td>

              <td>
                <span
                  class="status-badge"
                  :class="getStatusClass(task.taskStatus)"
                >
                  {{ task.taskStatus }}
                </span>
              </td>
             
             <td v-if="role==='EMPLOYEE'">
            <button class="update-btn" @click="gotoUpdateTask(task.id)">
              <i class="fa-solid fa-pen-to-square"></i>

              <span class="tooltip-text">
                Update Task
              </span>
            </button>
          </td>
            </tr>

            <!-- No Data -->

            <tr v-if="tasks.length === 0">
              <td colspan="7" class="no-data">
                No Tasks Found
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
  name: "TaskTable",
components:{
Navbar
},
  data() {
    return {
      tasks: [],
      role: localStorage.getItem("role"),
      username: localStorage.getItem("username"),
      departmentid: localStorage.getItem("departmentid"),
      loginUserId:localStorage.getItem("userId"),
      name: localStorage.getItem("name"),
      myTasks:[]
    };
  },

  mounted() {
    this.fetchTasks();
  },

  methods: {


gotoUpdateTask(id){
this.$router.push(`/updatetask/${id}`)
},

   async fetchTasks() {
  try {
    let response;
    
    // SUPERADMIN => all tasks
    if (this.role === "SUPERADMIN") {

      response = await axios.get(
        "http://localhost:8080/api/assgintask/getTaskList"
      );

    }
    // DEPARTMENT ADMIN / MANAGER => role + department
    else if (this.role === "DEPARTMENT ADMIN" || this.role === "MANAGER") {

      response = await axios.get(
        `http://localhost:8080/api/assgintask/getTaskListByDepartment/${this.departmentid}`
      );

    }
    // EMPLOYEE => own tasks
    else {

      response = await axios.get(
        `http://localhost:8080/api/assgintask/getTaskByEmployee/${this.username}`
      );

    }

    // this.allTasks = response.data.data;

      const allTasks = response.data.data;

      this.myTasks = allTasks.filter( emp => emp.name === this.name);
      
      this.tasks = allTasks.filter(emp => emp.name !== this.name);


  } catch (error) {

    console.error("Error Fetching Tasks :", error);

  }
},

    formatDate(date) {

      if (!date) return "-";

      return new Date(date).toLocaleDateString("en-GB");
    },

    getStatusClass(status) {

      if (status === "COMPLETED") {
        return "completed";
      }

      if (status === "IN_PROGRESS") {
        return "progress";
      }

      if (status === "PENDING") {
        return "pending";
      }

      return "";
    },

  },
};
</script>

<style scoped>

/* Page */

.page-container {
  margin-bottom: 20px;
}

/* Top Bar */

.top-bar {
  margin-bottom: 20px;
}

/* Back Button */

.back-btn {
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

.back-btn:hover {
  opacity: 0.9;
  transform: translateY(-1px);
}

/* Card */

.table-card {
  background: white;
  border-radius: 24px;
  padding: 24px;
  box-shadow: 0 4px 15px rgba(0,0,0,0.05);
}

/* Header */

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.card-header h2 {
  font-size: 30px;
  font-weight: 700;
  color: #0f2b57;
  margin: 0;
}

/* Total Badge */

.total-badge {
  background: #e4f6ea;
  color: #0d8a43;
  padding: 10px 18px;
  border-radius: 999px;
  font-size: 14px;
  font-weight: 600;
}

/* Table */

.table-wrapper {
  overflow-x: auto;
}

.task-table {
  width: 100%;
  border-collapse: collapse;
}

/* Table Header */

.task-table thead {
  background: linear-gradient(90deg, #4dc37b, #2ca35c);
  color: white;
}

.task-table th {
  padding: 16px;
  text-align: left;
  font-size: 14px;
  font-weight: 700;
  white-space: nowrap;
}

/* Table Body */

.task-table td {
  padding: 16px;
  border-bottom: 1px solid #edf1f7;
  font-size: 14px;
  color: #1b2b48;
}

.task-table tbody tr:hover {
  background: #f8fbff;
  transition: 0.3s;
}

/* Status Badge */

.status-badge {
  padding: 15px 10px 20px;
  border-radius: 999px;
  font-size: 12px;
  font-weight: 600;
  display: inline-block;
}

/* Pending */

.pending {
  background: #fff4d6;
  color: #c98a00;
}

/* Completed */

.completed {
  background: #daf5e4;
  color: #118847;
}

/* In Progress */

.progress {
  background: #dbeafe;
  color: #2563eb;
}

/* No Data */

.no-data {
  text-align: center;
  padding: 25px;
  color: gray;
  font-size: 14px;
}

/* Responsive */

@media (max-width: 768px) {

  .page-container {
    padding: 15px;
  }

  .card-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

  .card-header h2 {
    font-size: 24px;
  }

  .task-table th,
  .task-table td {
    font-size: 13px;
    padding: 12px;
  }

}

.update-btn {
  position: relative;
  background: #2563eb;
  color: white;
  border: none;
  padding: 10px 14px;
  border-radius: 10px;
  cursor: pointer;
  font-size: 14px;
}

/* Tooltip */
.tooltip-text {
  visibility: hidden;
  opacity: 0;

  position: absolute;
  bottom: 120%;
  left: 50%;
  transform: translateX(-50%);

  background: #111827;
  color: white;

  padding: 6px 10px;
  border-radius: 6px;

  font-size: 12px;
  white-space: nowrap;

  transition: 0.3s;

  z-index: 10;
}

.header-section {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 12px 16px;
}

.back-btn {
  position: absolute;
  left: 16px;
  background: #43b77a;
  border: none;
  cursor: pointer;
  font-size: 20px;

}

.page-title {
  margin: 0;
  text-align: center;
}

/* Arrow */
.tooltip-text::after {
  content: "";
  position: absolute;

  top: 100%;
  left: 50%;
  transform: translateX(-50%);

  border-width: 5px;
  border-style: solid;

  border-color: #111827 transparent transparent transparent;
}

/* Show Tooltip */
.update-btn:hover .tooltip-text {
  visibility: visible;
  opacity: 1;
}
</style>