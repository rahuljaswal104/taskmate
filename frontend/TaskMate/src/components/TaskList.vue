<template>
  <div class="page-container">

    <!-- Back Button -->
    <div class="top-bar">
      <button class="back-btn" @click="$router.back()">
        ← Back
      </button>
    </div>

    <!-- Table Card -->
    <div class="table-card">

      <!-- Header -->
      <div class="card-header">
        <h2>Task List</h2>

        <div class="total-badge">
          Total : {{ tasks.length }}
        </div>
      </div>

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
            </tr>
          </thead>

          <tbody>

            <tr v-for="(task, index) in tasks" :key="index">

              <td>{{ task.title }}</td>

              <td>{{ task.project }}</td>

              <td>{{ task.name }}</td>

              <td>{{ task.assignedBy }}</td>

              <td>{{ formatDate(task.assignedDate) }}</td>

              <td>{{ formatDate(task.endDate) }}</td>

              <td>
                <span
                  class="status-badge"
                  :class="getStatusClass(task.taskStatus)"
                >
                  {{ task.taskStatus }}
                </span>
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

export default {
  name: "TaskTable",

  data() {
    return {
      tasks: [],
    };
  },

  mounted() {
    this.fetchTasks();
  },

  methods: {

    async fetchTasks() {

      try {

        const response = await axios.get(
          "http://localhost:8080/api/assgintask/getTaskList"
        );

        console.log(response.data);

        // If using CustomResponse

        this.tasks = response.data.data;

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
  background: #f4f7fb;
  min-height: 100vh;
  padding: 30px;
  font-family: Arial, Helvetica, sans-serif;
}

/* Top Bar */

.top-bar {
  margin-bottom: 20px;
}

/* Back Button */

.back-btn {
  background: linear-gradient(90deg, #49c46c, #2fa85a);
  color: white;
  border: none;
  padding: 10px 18px;
  border-radius: 12px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.3s;
  box-shadow: 0 2px 10px rgba(0,0,0,0.08);
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
  padding: 8px 14px;
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

</style>