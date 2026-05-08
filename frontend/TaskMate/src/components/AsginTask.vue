```vue
<template>
  <div>
    <Navbar />

    <div class="assign-task-page">
      <div class="page-header">
        <h2>Assign Task</h2>
      </div>

      <div class="task-container">

        <!-- Task Details -->
        <div class="section-card">
          <h2>Task Details</h2>

          <div class="task-detail-grid">
            <div class="form-group">
              <label>Task Title</label>
              <input type="text" v-model="task.title" />
            </div>

            <div class="form-group">
              <label>Description</label>
              <textarea v-model="task.description"></textarea>
            </div>

            <div class="form-group">
              <label>Project</label>
              <select v-model="task.project">
                <option value="">Select Project</option>
                <option>HMIS</option>
                <option>eAushadi</option>
              </select>
            </div>

            <div class="form-group">
              <label>Priority</label>
              <select v-model="task.priority">
                <option>Low</option>
                <option>Medium</option>
                <option>High</option>
              </select>
            </div>
          </div>
        </div>

        <!-- Assign To -->
        <div class="section-card">
          <h2>Task Assign To</h2>

          <div class="form-grid">
            <div class="form-group">
              <label>Employee Name</label>
              <input type="text" v-model="task.employeeName" />
            </div>

            <div class="form-group">
              <label>Designation</label>
              <select v-model="task.designation">
                <option value="">Select Designation</option>
                <option>Director</option>
                <option>Manager</option>
                <option>Developer</option>
                <option>Computer Operator</option>
              </select>
            </div>

            <div class="form-group">
              <label>Department</label>
              <select v-model="task.department">
                <option value="">Select Department</option>
                <option>IT Cell</option>
                <option>Procurement</option>
                <option>Education Board</option>
                <option>Other</option>
              </select>
            </div>

            <div class="form-group">
              <label>Employee ID</label>
              <input type="text" v-model="task.employeeId" />
            </div>
          </div>
        </div>

        <!-- Time Period -->
        <div class="section-card">
          <h2>Time Period</h2>

          <div class="form-grid">
            <div class="form-group">
              <label>Assign Date</label>
              <input type="date" v-model="task.assignDate" />
            </div>

            <div class="form-group">
              <label>Target Date</label>
              <input type="date" v-model="task.targetDate" />
            </div>

            <div class="form-group">
              <label>Estimated Hours</label>
              <input type="number" v-model="task.hours" />
            </div>

            <div class="form-group">
              <label>Status</label>
              <select v-model="task.status">
                <option>Pending</option>
                <option>In Progress</option>
                <option>Completed</option>
              </select>
            </div>
          </div>
        </div>

      </div>

      <div class="action-buttons">
        <button class="save-btn" @click="saveTask">Save Task</button>
        <button class="reset-btn" @click="resetForm">Clear</button>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from "./Navbar.vue";

export default {
  components: { Navbar },

  data() {
    return {
      task: {
        title: "",
        description: "",
        project: "",
        priority: "Medium",
        employeeName: "",
        designation: "",
        department: "",
        employeeId: "",
        assignDate: "",
        targetDate: "",
        hours: "",
        status: "Pending"
      }
    };
  },

  methods: {
    saveTask() {
      alert("Task Assigned Successfully!");
    },

    resetForm() {
      this.task = {
        title: "",
        description: "",
        project: "",
        priority: "Medium",
        employeeName: "",
        designation: "",
        department: "",
        employeeId: "",
        assignDate: "",
        targetDate: "",
        hours: "",
        status: "Pending"
      };
    }
  }
};
</script>

<style scoped>
.assign-task-page {
  background: #f8fbf9;
  min-height: 100vh;
  padding: 10px 20px;
}

.page-header {
  text-align: left;
  margin-bottom: 10px;
}

.page-header h2 {
  color: #47b37b;
  font-size: 24px;
}

.task-container {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.section-card {
  background: white;
  padding: 16px;
  border-radius: 10px;
  border-top: 4px solid #47b37b;
  box-shadow: 0 4px 10px rgba(71, 179, 123, 0.08);
}

.section-card h2 {
  color: #47b37b;
  margin-bottom: 12px;
  font-size: 17px;
}

.task-detail-grid,
.form-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 14px;
  padding: 4px;
}

.form-group label {
  display: block;
  margin-bottom: 4px;
  font-size: 13px;
  font-weight: 600;
}

input,
textarea,
select {
  width: 100%;
  padding: 8px 10px;
  border: 1px solid #d9e8df;
  border-radius: 6px;
  font-size: 13px;
  box-sizing: border-box;
  outline: none;
}

textarea {
  min-height: 42px;
  max-height: 42px;
  resize: none;
}

input:focus,
textarea:focus,
select:focus {
  border-color: #47b37b;
}

.action-buttons {
  margin-top: 14px;
  display: flex;
  justify-content: center;
  gap: 14px;
}

.save-btn,
.reset-btn {
  padding: 9px 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 600;
}

.save-btn {
  background: #47b37b;
  color: white;
}

.reset-btn {
  background: white;
  color: #47b37b;
  border: 2px solid #47b37b;
}
</style>
```
