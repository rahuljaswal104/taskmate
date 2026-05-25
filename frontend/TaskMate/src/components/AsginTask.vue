# Complete Assign Task Vue Component

```vue
<template>

  <div class="assign-task-page">

    <!-- HEADER -->

    <div class="page-header">

      <div class="header-left">

        <button
          class="back-btn"
          @click="goToDashboard"
        >
          ←
        </button>

        <div class="heading-text">
          <h1>Assign Task</h1>
          <p>Create and assign tasks to employees easily</p>
        </div>

      </div>

    </div>

    <!-- MAIN CONTAINER -->

    <div class="task-container">

      <!-- TASK DETAILS -->

      <div class="section-card">

        <div class="section-title">
          <h2>Task Details</h2>
        </div>

        <div class="form-grid">

          <!-- TASK TITLE -->

          <div class="form-group">

            <label>Task Title</label>

            <input
              type="text"
              v-model="task.title"
              placeholder="Enter Task Title"
            />

          </div>

          <!-- PROJECT -->

          <div class="form-group">

            <label>Project</label>

            <select v-model="task.project.id">

              <option value="">
                Select Project
              </option>

              <option :value="1">
                HMIS
              </option>

              <option :value="2">
                eAushadi
              </option>

              <option :value="3">
                TaskMate
              </option>

            </select>

          </div>

          <!-- TASK TYPE -->

          <div class="form-group">

            <label>Task Type</label>

            <select v-model="task.taskType">

              <option value="">
                Select Type
              </option>

              <option value="BUG_FIX">
                Bug Fix
              </option>

              <option value="FEATURE">
                Feature
              </option>

              <option value="ENHANCEMENT">
                Enhancement
              </option>

              <option value="TESTING">
                Testing
              </option>

              <option value="SUPPORT">
                Support
              </option>

            </select>

          </div>

          <!-- PRIORITY -->

          <div class="form-group">

            <label>Priority</label>

            <select v-model="task.priority">

              <option value="LOW">
                Low
              </option>

              <option value="MEDIUM">
                Medium
              </option>

              <option value="HIGH">
                High
              </option>

            </select>

          </div>

          <!-- DESCRIPTION -->

          <div class="form-group full-width">

            <label>Description</label>

            <textarea
              v-model="task.description"
              placeholder="Enter Task Description"
            ></textarea>

          </div>

        </div>

      </div>

      <!-- ASSIGN TO -->

      <div class="section-card">

        <div class="section-title">
          <h2>Assign To</h2>
        </div>

        <div class="form-grid">

          <!-- EMPLOYEE -->

          <div class="form-group">

            <label>Employee Name</label>

            <select
              v-model="selectedEmployeeId"
              @change="onEmployeeChange"
            >

              <option value="">
                Select Employee
              </option>

              <option
                v-for="emp in userList"
                :key="emp.id"
                :value="emp.id"
              >
                {{ emp.name }}
              </option>

            </select>

          </div>

          <!-- DESIGNATION -->

          <div class="form-group">

            <label>Designation</label>

            <input
              type="text"
              v-model="task.designation"
              readonly
              placeholder="Auto Filled"
            />

          </div>

          <!-- DEPARTMENT -->

          <div class="form-group">

            <label>Department</label>

            <input
              type="text"
              v-model="task.department.departmentName"
              readonly
              placeholder="Auto Filled"
            />

          </div>

          <!-- ASSIGNED BY -->

          <div class="form-group">

            <label>Assigned By</label>

            <select v-model="task.assignedBy.id">

              <option value="">
                Select Manager
              </option>

              <option
                v-for="emp in userList"
                :key="emp.id"
                :value="emp.id"
              >
                {{ emp.name }}
              </option>

            </select>

          </div>

        </div>

      </div>

      <!-- TIME & STATUS -->

      <div class="section-card">

        <div class="section-title">
          <h2>Time & Status</h2>
        </div>

        <div class="form-grid">

          <!-- ASSIGN DATE -->

          <div class="form-group">

            <label>Assign Date</label>

            <input
              type="date"
              v-model="task.assignDate"
            />

          </div>

          <!-- TARGET DATE -->

          <div class="form-group">

            <label>Target Date</label>

            <input
              type="date"
              v-model="task.targetDate"
            />

          </div>

          <!-- STATUS -->

          <div class="form-group">

            <label>Status</label>

            <select v-model="task.status" disabled>

              <option value="PENDING">
                Pending
              </option>

              <option value="IN_PROGRESS">
                In Progress
              </option>

              <option value="COMPLETED">
                Completed
              </option>

            </select>

          </div>

          <!-- REMARKS -->

          <div class="form-group full-width">

            <label>Remarks</label>

            <textarea
              v-model="task.remarks"
              placeholder="Additional Instructions"
            ></textarea>

          </div>

        </div>

      </div>

    </div>

    <!-- BUTTONS -->

    <div class="action-buttons">

      <button
        class="save-btn"
        @click="saveTask"
      >
        Save Task
      </button>

      <button
        class="reset-btn"
        @click="resetForm"
      >
        Clear
      </button>

    </div>

  </div>

</template>

<script>

import axios from "axios";

export default {

  data() {

    return {

      userList: [],

      selectedEmployeeId: "",

      task: {

        title: "",

        project: {
          id: ""
        },

        taskType: "",

        priority: "MEDIUM",

        description: "",

        employeeId: "",

        employeeName: "",

        designation: "",

        department: {
          id: "",
          departmentName: ""
        },

        assignedBy: {
          id: ""
        },

        assignDate: "",

        targetDate: "",

        status: "PENDING",

        remarks: ""

      }

    };

  },

  mounted() {

    this.getUserList();

  },

  methods: {

    goToDashboard() {

      this.$router.push('/dashboard');

    },

    // GET USER LIST

    async getUserList() {

      try {

        const response = await axios.get(
          'http://localhost:8080/api/assgintask/getuserlist'
        );

        this.userList = response.data.data;

      } catch (error) {

        console.error('User Fetch Failed:', error);

      }

    },

    // AUTO FILL EMPLOYEE DATA

    onEmployeeChange() {

      const selectedEmp = this.userList.find(
        emp => emp.id == this.selectedEmployeeId
      );

      if (selectedEmp) {

        this.task.employeeId = selectedEmp.id;

        this.task.employeeName = selectedEmp.name;

        this.task.designation = selectedEmp.designation;

        this.task.department = selectedEmp.department || {
          id: '',
          departmentName: ''
        };

      }

    },

    // SAVE TASK

    async saveTask() {

      try {

        // VALIDATION

        if (!this.task.title) {

          alert('Please Enter Task Title');
          return;

        }

        if (!this.task.project.id) {

          alert('Please Select Project');
          return;

        }

        if (!this.task.taskType) {

          alert('Please Select Task Type');
          return;

        }

        if (!this.task.employeeId) {

          alert('Please Select Employee');
          return;

        }

        if (!this.task.assignedBy.id) {

          alert('Please Select Assigned By');
          return;

        }

        // PAYLOAD

        const payload = {

          title: this.task.title,

          project: {
            id: this.task.project.id
          },

          taskType: this.task.taskType,

          priority: this.task.priority,

          description: this.task.description,

          employees: [
            {
              id: this.task.employeeId
            }
          ],

          assignedBy: {
            id: this.task.assignedBy.id
          },

          assignedDate: this.task.assignDate,

          targetDate: this.task.targetDate,

          taskStatus: this.task.status,

          remarks: this.task.remarks,

          progressPercentage: 0,

          status: 'ACTIVE'

        };

        console.log('Payload:', payload);

        const response = await axios.post(
          'http://localhost:8080/api/assgintask/save',
          payload,
          {
            headers: {
              'Content-Type': 'application/json'
            }
          }
        );

        console.log('Task Saved:', response.data);

        alert('Task Assigned Successfully');

        this.resetForm();

      } catch (error) {

        console.error('Save Failed:', error);

        alert('Failed To Save Task');

      }

    },

    // RESET FORM

    resetForm() {

      this.selectedEmployeeId = '';

      this.task = {

        title: '',

        project: {
          id: ''
        },

        taskType: '',

        priority: 'MEDIUM',

        description: '',

        employeeId: '',

        employeeName: '',

        designation: '',

        department: {
          id: '',
          departmentName: ''
        },

        assignedBy: {
          id: ''
        },

        assignDate: '',

        targetDate: '',

        status: 'PENDING',

        remarks: ''

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

.assign-task-page {
  min-height: 100vh;
  background: #f4f7fb;
  padding: 24px;
  font-family: Arial, sans-serif;
}

/* HEADER */

.page-header {
  display: flex;
  align-items: center;
  margin-bottom: 28px;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 18px;
}

.heading-text h1 {
  font-size: 34px;
  color: #1e293b;
  margin-bottom: 4px;
}

.heading-text p {
  color: #64748b;
  font-size: 14px;
}

/* BACK BUTTON */

.back-btn {
  width: 46px;
  height: 46px;
  border: none;
  border-radius: 14px;
  background: linear-gradient(135deg, #48bb78, #2f9e61);
  color: white;
  font-size: 22px;
  cursor: pointer;
  transition: 0.3s;
  box-shadow: 0 8px 18px rgba(72, 187, 120, 0.25);
}

.back-btn:hover {
  transform: translateY(-2px);
}

/* MAIN */

.task-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* CARD */

.section-card {
  background: white;
  border-radius: 20px;
  padding: 24px;
  box-shadow: 0 6px 18px rgba(0,0,0,0.05);
}

/* TITLE */

.section-title {
  margin-bottom: 20px;
  border-bottom: 1px solid #e2e8f0;
  padding-bottom: 10px;
}

.section-title h2 {
  color: #43b77a;
  font-size: 22px;
}

/* GRID */

.form-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 18px 20px;
}

.full-width {
  grid-column: span 2;
}

/* FORM GROUP */

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group label {
  margin-bottom: 8px;
  font-size: 14px;
  font-weight: 600;
  color: #334155;
}

/* INPUTS */

input,
textarea,
select {
  width: 100%;
  padding: 13px 14px;
  border-radius: 12px;
  border: 1px solid #dbe4ee;
  font-size: 14px;
  transition: 0.3s;
  background: white;
}

input:focus,
textarea:focus,
select:focus {
  outline: none;
  border-color: #48bb78;
  box-shadow: 0 0 0 4px rgba(72, 187, 120, 0.15);
}

textarea {
  min-height: 100px;
  resize: vertical;
}

input[readonly] {
  background: #f8fafc;
}

/* BUTTONS */

.action-buttons {
  margin-top: 28px;
  display: flex;
  justify-content: center;
  gap: 16px;
}

.save-btn,
.reset-btn {
  border: none;
  padding: 14px 30px;
  border-radius: 12px;
  font-size: 15px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.3s;
}

.save-btn {
  background: linear-gradient(135deg, #48bb78, #2f9e61);
  color: white;
  box-shadow: 0 8px 16px rgba(72, 187, 120, 0.22);
}

.save-btn:hover {
  transform: translateY(-2px);
}

.reset-btn {
  background: white;
  border: 1px solid #dbe4ee;
  color: #334155;
}

.reset-btn:hover {
  background: #f8fafc;
}

/* RESPONSIVE */

@media (max-width: 768px) {

  .form-grid {
    grid-template-columns: 1fr;
  }

  .full-width {
    grid-column: span 1;
  }

  .assign-task-page {
    padding: 16px;
  }

  .heading-text h1 {
    font-size: 28px;
  }

  .action-buttons {
    flex-direction: column;
  }

}

</style>
