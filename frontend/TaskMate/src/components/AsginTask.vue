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

          <p>
            Create and assign tasks professionally
          </p>

        </div>

      </div>

    </div>

    <!-- Main Container -->
    <div class="task-container">

      <!-- Task Details -->
      <div class="section-card compact-card">

        <div class="section-title">
          <h2>Task Details</h2>
        </div>

        <div class="form-grid">

          <div class="form-group">

            <label>Task Title</label>

            <input
              type="text"
              v-model="task.title"
              placeholder="Enter Task Title"
            />

          </div>

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

          <div class="form-group">

            <label>Task Type</label>

            <select v-model="task.taskType">

              <option value="">
                Select Type
              </option>

              <option>Bug</option>
              <option>Feature</option>
              <option>Enhancement</option>
              <option>Testing</option>
              <option>Support</option>

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

          <div class="form-group full-width">

            <label>Description</label>

            <textarea
              v-model="task.description"
              placeholder="Enter task description"
            ></textarea>

          </div>

        </div>

      </div>

      <!-- Assign To -->
      <div class="section-card compact-card">

        <div class="section-title">
          <h2>Assign To</h2>
        </div>

        <div class="form-grid">

          <div class="form-group">

            <label>Employee Name</label>

            <input
              type="text"
              v-model="task.employeeName"
              placeholder="Enter Employee Name"
            />

          </div>

          <div class="form-group">

            <label>Designation</label>

            <select v-model="task.designation">

              <option value="">
                Select Designation
              </option>

              <option>Director</option>
              <option>Manager</option>
              <option>Developer</option>
              <option>Computer Operator</option>

            </select>

          </div>

          <div class="form-group">

            <label>Department</label>

            <select v-model="task.department">

              <option value="">
                Select Department
              </option>

              <option>IT Cell</option>
              <option>Procurement</option>
              <option>Finance</option>
              <option>Education Board</option>

            </select>

          </div>

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

      <!-- Time & Status -->
      <div class="section-card compact-card">

        <div class="section-title">
          <h2>Time & Status</h2>
        </div>

        <div class="form-grid">

          <div class="form-group">

            <label>Assign Date</label>

            <input
              type="date"
              v-model="task.assignDate"
            />

          </div>

          <div class="form-group">

            <label>Target Date</label>

            <input
              type="date"
              v-model="task.targetDate"
            />

          </div>

          <div class="form-group">

            <label>Status</label>

            <select v-model="task.status">

              <option>Pending</option>
              <option>In Progress</option>
              <option>Completed</option>

            </select>

          </div>

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

    <!-- Buttons -->
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

export default {

  data() {

    return {

      task: {

        title: "",
        description: "",
        project: "",
        taskType: "",
        priority: "Medium",

        employeeName: "",
        designation: "",
        department: "",

        assignedBy: "",

        assignDate: "",
        targetDate: "",

        status: "Pending",

        remarks: ""

      }

    };

  },

  methods: {

    goToDashboard() {

      this.$router.push('/dashboard');

    },

    saveTask() {

      console.log(this.task);

      alert("Task Assigned Successfully!");

    },

    resetForm() {

      this.task = {

        title: "",
        description: "",
        project: "",
        taskType: "",
        priority: "Medium",

        employeeName: "",
        designation: "",
        department: "",

        assignedBy: "",

        assignDate: "",
        targetDate: "",

        status: "Pending",

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

/* Header */

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

/* Heading */

.heading-text h1 {

  color: #1e293b;
  font-size: 32px;
  margin-bottom: 4px;

}

.heading-text p {

  color: #64748b;
  font-size: 14px;

}

/* Back Button */

.back-btn {

  width: 48px;
  height: 48px;

  display: flex;
  align-items: center;
  justify-content: center;

  background: white;

  border: 1px solid #dbe4ee;

  border-radius: 14px;

  font-size: 24px;
  font-weight: bold;

  color: #1e293b;

  cursor: pointer;

  transition: all 0.3s ease;

  box-shadow:
    0 4px 12px rgba(0,0,0,0.05),
    inset 0 1px 0 rgba(255,255,255,0.8);

}

.back-btn:hover {

  background: linear-gradient(
    135deg,
    #48bb78,
    #2f9e61
  );

  color: white;

  border-color: #48bb78;

  transform: translateY(-2px);

}

/* Layout */

.task-container {

  display: flex;
  flex-direction: column;
  gap: 16px;

}

/* Cards */

.section-card {

  background: white;
  border-radius: 18px;
  padding: 20px 24px;

  box-shadow:
    0 4px 14px rgba(0,0,0,0.05);

}

/* Section Title */

.section-title {

  margin-bottom: 16px;

}

.section-title h2 {

  color: #43b77a;
  font-size: 21px;

}

/* Grid */

.form-grid {

  display: grid;

  grid-template-columns: repeat(2, 1fr);

  gap: 14px 18px;

}

.full-width {

  grid-column: span 2;

}

/* Form Group */

.form-group label {

  display: block;

  margin-bottom: 7px;

  font-size: 14px;
  font-weight: 600;

  color: #334155;

}

/* Inputs */

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

/* Buttons */

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

/* Responsive */

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