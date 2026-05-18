<template>

  <div class="assign-task-page">

    <!-- Header -->
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
        </div>

      </div>

    </div>

    <!-- Main Container -->
    <div class="task-container">

      <!-- ========================= -->
      <!-- TASK DETAILS -->
      <!-- ========================= -->

      <div class="section-card compact-card">

        <div class="section-title">
          <h2>Task Details</h2>
        </div>

        <div class="form-grid">

          <!-- Task Title -->
          <div class="form-group">

            <label>Task Title</label>

            <input
              type="text"
              v-model="task.title"
              placeholder="Enter Task Title"
            />

          </div>

          <!-- Project -->
          <div class="form-group">

            <label>Project</label>

            <select v-model="task.project">

              <option value="">
                Select Project
              </option>

              <option>HMIS</option>
              <option>eAushadi</option>
              <option>TaskMate</option>

            </select>

          </div>

          <!-- Task Type -->
          <div class="form-group">

            <label>Task Type</label>

            <select v-model="task.taskType">

              <option value="">
                Select Type
              </option>

              <option value="BUG_FIX">
                Bug
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

          <!-- Priority -->
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

          <!-- Description -->
          <div class="form-group full-width">

            <label>Description</label>

            <textarea
              v-model="task.description"
              placeholder="Enter task description"
            ></textarea>

          </div>

        </div>

      </div>

      <!-- ========================= -->
      <!-- ASSIGN TO -->
      <!-- ========================= -->

      <div class="section-card compact-card">

        <div class="section-title">
          <h2>Assign To</h2>
        </div>

        <div class="form-grid">

          <!-- Employee Dropdown -->
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

          <!-- Designation -->
          <div class="form-group">

            <label>Designation</label>

            <input
              type="text"
              v-model="task.designation"
              readonly
              placeholder="Auto Filled"
            />

          </div>

          <!-- Department -->
          <div class="form-group">

            <label>Department</label>

            <input
              type="text"
              v-model="task.department"
              readonly
              placeholder="Auto Filled"
            />

          </div>

          <!-- Assigned By -->
          <div class="form-group">

            <label>Assigned By</label>

            <input
              type="text"
              v-model="task.assignedBy"
              placeholder="Admin / Manager"
            />

          </div>

        </div>

      </div>

      <!-- ========================= -->
      <!-- TIME & STATUS -->
      <!-- ========================= -->

      <div class="section-card compact-card">

        <div class="section-title">
          <h2>Time & Status</h2>
        </div>

        <div class="form-grid">

          <!-- Assign Date -->
          <div class="form-group">

            <label>Assign Date</label>

            <input
              type="date"
              v-model="task.assignDate"
            />

          </div>

          <!-- Target Date -->
          <div class="form-group">

            <label>Target Date</label>

            <input
              type="date"
              v-model="task.targetDate"
            />

          </div>

          <!-- Status -->
          <div class="form-group">

            <label>Status</label>

            <select v-model="task.status">

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

          <!-- Remarks -->
          <div class="form-group full-width">

            <label>Remarks</label>

            <textarea
              v-model="task.remarks"
              placeholder="Additional instructions or notes"
            ></textarea>

          </div>

        </div>

      </div>

    </div>

    <!-- ========================= -->
    <!-- BUTTONS -->
    <!-- ========================= -->

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

      // Employee List
      userList: [],

      // Selected Employee
      selectedEmployeeId: "",

      // Task Object
      task: {

        title: "",

        project: "",

        taskType: "",

        priority: "MEDIUM",

        description: "",

        employeeId: "",

        employeeName: "",

        designation: "",

        department: "",

        assignedBy: "",

        assignDate: "",

        targetDate: "",

        status: "PENDING",

        remarks: ""

      }

    };

  },

  // =========================
  // PAGE LOAD
  // =========================

  mounted() {

    this.getUserList();

  },

  methods: {

    // =========================
    // GO TO DASHBOARD
    // =========================

    goToDashboard() {

      this.$router.push('/dashboard');

    },

    // =========================
    // FETCH EMPLOYEE LIST
    // =========================

    async getUserList() {

      try {

        const response = await axios.get(
          "http://localhost:8080/api/assgintask/getuserlist"
        );

        // IMPORTANT
        this.userList = response.data.data;

        console.log("User List:", this.userList);

      } catch (error) {

        console.error("Employee Fetch Failed:", error);

      }

    },

    // =========================
    // AUTO FILL EMPLOYEE DETAILS
    // =========================

    onEmployeeChange() {

      const selectedEmp = this.userList.find(
        emp => emp.id === this.selectedEmployeeId
      );

      if (selectedEmp) {

        // SAVE EMPLOYEE ID
        this.task.employeeId = selectedEmp.id;

        // SAVE EMPLOYEE NAME
        this.task.employeeName = selectedEmp.name;

        // AUTO FILL DATA
        this.task.designation = selectedEmp.designation;

        this.task.department = selectedEmp.department;

      }

    },

    // =========================
    // SAVE TASK
    // =========================

    async saveTask() {

      try {

        // VALIDATION

        if (!this.task.title) {

          alert("Please Enter Task Title");

          return;

        }

        if (!this.task.project) {

          alert("Please Select Project");

          return;

        }

        if (!this.task.taskType) {

          alert("Please Select Task Type");

          return;

        }

        if (!this.task.employeeId) {

          alert("Please Select Employee");

          return;

        }

        // PAYLOAD

        const payload = {

          // TASK DETAILS

          title: this.task.title,

          project: this.task.project,

          taskType: this.task.taskType,

          priority: this.task.priority,

          description: this.task.description,

          // MANY TO MANY EMPLOYEE LIST

          employees: [
            {
              id: this.task.employeeId
            }
          ],

          // ASSIGNMENT DETAILS

          assignedBy: this.task.assignedBy,

          assignedDate: this.task.assignDate,

          startDate: this.task.assignDate,

          endDate: this.task.targetDate,

          // STATUS

          taskStatus: this.task.status,

          remarks: this.task.remarks,

          progressPercentage: 0,

          status: "ACTIVE"

        };

        console.log("Payload:", payload);

        // API CALL

        const response = await axios.post(
          "http://localhost:8080/api/assgintask/save",
          payload,
          {
            headers: {
              "Content-Type": "application/json"
            }
          }
        );

        console.log("Task Saved:", response.data);

        alert("Task Assigned Successfully");

        // RESET FORM

        this.resetForm();

      } catch (error) {

        console.error("Save Failed:", error);

        alert("Failed To Save Task");

      }

    },

    // =========================
    // RESET FORM
    // =========================

    resetForm() {

      this.selectedEmployeeId = "";

      this.task = {

        title: "",

        project: "",

        taskType: "",

        priority: "MEDIUM",

        description: "",

        employeeId: "",

        employeeName: "",

        designation: "",

        department: "",

        assignedBy: "",

        assignDate: "",

        targetDate: "",

        status: "PENDING",

        remarks: ""

      };

    }

  }

};

</script>

<style scoped>

.assign-task-page {

  background: #f4f7fb;
  min-height: 100vh;
  padding: 18px 22px;
  font-family: Arial, sans-serif;

}

/* HEADER */

.page-header {

  display: flex;
  align-items: center;
  margin-bottom: 22px;

}

.header-left {

  display: flex;
  align-items: center;
  gap: 18px;

}

/* HEADING */

.heading-text h1 {

  color: #1e293b;
  font-size: 32px;
  margin-bottom: 4px;

}

/* BACK BUTTON */

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

  background: linear-gradient(
    135deg,
    #48bb78,
    #2f9e61
  );

  transform: translateY(-2px);

}

/* LAYOUT */

.task-container {

  display: flex;
  flex-direction: column;
  gap: 16px;

}

/* CARD */

.section-card {

  background: white;
  border-radius: 18px;
  padding: 20px 24px;

  box-shadow:
    0 4px 14px rgba(0,0,0,0.05);

}

/* SECTION TITLE */

.section-title {

  margin-bottom: 16px;

}

.section-title h2 {

  color: #43b77a;
  font-size: 21px;

}

/* GRID */

.form-grid {

  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 14px 18px;

}

.full-width {

  grid-column: span 2;

}

/* FORM GROUP */

.form-group label {

  display: block;
  margin-bottom: 7px;
  font-size: 14px;
  font-weight: 600;
  color: #334155;

}

/* INPUTS */

input,
textarea,
select {

  width: 100%;
  padding: 11px 14px;
  border: 1px solid #dbe4ee;
  border-radius: 12px;
  font-size: 14px;
  outline: none;
  transition: 0.3s;
  box-sizing: border-box;
  background: #fff;

}

textarea {

  min-height: 90px;
  resize: vertical;

}

input:focus,
textarea:focus,
select:focus {

  border-color: #48bb78;

  box-shadow:
    0 0 0 3px rgba(72,187,120,0.14);

}

/* READONLY */

input[readonly] {

  background: #f8fafc;
  cursor: not-allowed;

}

/* BUTTONS */

.action-buttons {

  margin-top: 24px;
  display: flex;
  justify-content: center;
  gap: 14px;

}

.save-btn {

  background: linear-gradient(
    135deg,
    #48bb78,
    #2f9e61
  );

  color: white;
  border: none;
  padding: 12px 28px;
  border-radius: 12px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.3s;

  box-shadow:
    0 6px 14px rgba(72,187,120,0.20);

}

.save-btn:hover {

  transform: translateY(-2px);

}

.reset-btn {

  background: white;
  color: #334155;
  border: 1px solid #dbe4ee;
  padding: 12px 28px;
  border-radius: 12px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;

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

    padding: 14px;

  }

  .heading-text h1 {

    font-size: 26px;

  }

}

</style>