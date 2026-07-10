# Complete Assign Task Vue Component



<template>


  <div class="assign-task-page">

    <!-- HEADER -->

  <div class="header-section">

         <!-- Back Button -->
      <button class="back-btn" @click="$router.back()">
        ← 
      </button>

      <h2 class="page-title">
        Assign Task
      </h2>
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
        <select v-model="selectedProjectId">

            <option value="" disabled hidden>
            Select Project
          </option>

          <option
            v-for="project in projectList"
            :key="project.id"
            :value="project.id"
          >
            {{ project.projectName }}
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

<!-- 
            <div class="form-group">

            <label>Employee Name</label>

             <select

              v-model="selectedEmployeeId"
              @change="onEmployeeChange"
            >

               <option value="" disabled hidden>
                Select Employee

              </option>

              <option
                v-for="emp in filteredEmployees"
                :key="emp.id"
                :value="emp.id"
              >
                {{ emp.name }}
              </option>

            </select>

            </div> -->

            <div class="form-group">

        <label>Employee Name</label>

  <!-- <v-autocomplete
    v-model="selectedEmployeeIds"
    :items="filteredEmployees"
    item-title="name"
    item-value="id"
    label="Select Employee"
    multiple
    chips
    clearable
    variant="outlined"
    density="comfortable"
  /> -->

        <v-autocomplete
        v-model="selectedEmployeeIds"
        :items="filteredEmployees"
        placeholder="Select Employee"
        item-title="name"
        item-value="id"
        multiple
        chips
        closable-chips
        density="compact"
        variant="outlined"
        class="employee-select"
      />

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

          <input
            type="text"
            :value="name"
            readonly
          />

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

      name: localStorage.getItem('name'),
      role: localStorage.getItem('role'),
      departmentId: localStorage.getItem('departmentid'),
      userId: localStorage.getItem('userId'),
      
      selectedProjectId:"",
      projectList:[],
      userList: [],

        // selectedEmployeeId: "",
      selectedEmployeeIds: [],
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

        assignDate: new Date().toISOString().split("T")[0],

        targetDate: "",

        status: "PENDING",

        remarks: "", 
        
        loginId: "",
         
        

      },

      currentUser: {
      role: '', // superadmin | department_admin | manager
      department_id: null
    }

    };

  },

  mounted() {

    this.getUserList();

    this.getProjects();

  },


computed: {
  filteredEmployees() {

    // SUPERADMIN
    if (this.role === 'SUPERADMIN') {
      return this.userList.filter(emp =>     
        emp.id != this.userId );
    }

    // DEPARTMENT ADMIN
    if (this.role === 'DEPARTMENT ADMIN') {
      return this.userList.filter(emp =>
        emp.department.id == this.departmentId &&     // same department
        emp.role.roleName !== 'SUPERADMIN' &&         // super admin hide
        emp.id != this.userId                         // khud ko hide
      );
    }

    // MANAGER
    if (this.role === 'MANAGER') {
      return this.userList.filter(emp =>
        emp.department.id == this.departmentId &&  
        emp.role.roleName !== 'SUPERADMIN' &&             // same department
        emp.role.roleName !== 'DEPARTMENT ADMIN' &&             // department admin hide
        emp.id != this.userId                                   // khud ko hide
      );
    }

    return [];
  }
},

watch: {
  // selectedEmployeeIds(newIds) {

  //   const employees = this.filteredEmployees.filter(emp =>
  //     newIds.includes(emp.id)
  //   );

  //   this.task.designation = employees
  //     .map(emp => emp.designation)
  //     .join(", ");

  //   this.task.department.departmentName = employees
  //     .map(emp => emp.department.departmentName)
  //     .join(", ");
  // }

  selectedEmployeeIds(newIds) {

    // Selected order maintain karega
    const employees = newIds
      .map(id => this.filteredEmployees.find(emp => emp.id === id))
      .filter(Boolean);

    this.task.designation = employees
      .map(emp => emp.designation)
      .join(", ");

    this.task.department.departmentName = employees
      .map(emp => emp.department.departmentName)
      .join(", ");

    // Agar future me employee names bhi chahiye
    this.task.employeeName = employees
      .map(emp => emp.name)
      .join(", ");
  }
},

  methods: {

     async getProjects() {

      try {

        const res = await axios.get(
          "http://localhost:8080/api/project/projectList"
        );

        this.projectList = res.data.data;

      } catch (err) {

        console.log(err);

      }

    },


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
       console.log(this.userList);
     const loginName = this.userList.find(emp => emp.name == this.name);

        if (loginName) {

            this.loginId = loginName.id;
            this.currentUser = loginName;

        }

      } catch (error) {

        console.error('User Fetch Failed:', error);

      }

    },

    // AUTO FILL EMPLOYEE DATA

    // onEmployeeChange() {

    //   const selectedEmp = this.userList.find(
    //     emp => emp.id == this.selectedEmployeeId
    //   );

    //   if (selectedEmp) {

    //     this.task.employeeId = selectedEmp.id;

    //     this.task.employeeName = selectedEmp.name;

    //     this.task.designation = selectedEmp.designation;

    //     this.task.department = selectedEmp.department || {
    //       id: '',
    //       departmentName: ''
    //     };

    //   }

    // },

    // SAVE TASK

    async saveTask() {

      try {

        // VALIDATION

        if (!this.task.title) {

          alert('Please Enter Task Title');
          return;

        }

        if (!this.selectedProjectId) {
          alert('Please Select Project');
          return;
        }

        if (!this.task.taskType) {

          alert('Please Select Task Type');
          return;

        }

        // if (!this.task.employeeId) {

        //   alert('Please Select Employee');
        //   return;

        // }

        if (this.selectedEmployeeIds.length === 0) {
           alert("Please Select Employee");
            return;
        }

        if (!this.loginId) {
          alert('Please Select Assigned By');
          return;

        }

        // PAYLOAD

        const payload = {

          title: this.task.title,

          project: {
            id: this.selectedProjectId
          },

          taskType: this.task.taskType,

          priority: this.task.priority,

          description: this.task.description,

          // employees: [
          //   {
          //     id: this.task.employeeId
          //   }
          // ],

          employees: this.selectedEmployeeIds.map(id => ({
              id: id
            })),

          assignedBy: {
            id:  this.loginId
          },

          assignedDate: this.task.assignDate,

          targetDate: this.task.targetDate,

          taskStatus: this.task.status,

          remarks: this.task.remarks,

          progressPercentage: 0,

          status: 'ACTIVE'

        };

        //console.log('Payload:', payload);

        const response = await axios.post(
          'http://localhost:8080/api/assgintask/save',
          payload,
          {
            headers: {
              'Content-Type': 'application/json'
            }
          }
        );

       // console.log('Task Saved:', response.data);

        alert('Task Assigned Successfully');

        this.resetForm();

      } catch (error) {

        console.error('Save Failed:', error);

        alert('Failed To Save Task');

      }

    },

    // RESET FORM

    resetForm() {

      // this.selectedEmployeeId = '';
      this.selectedEmployeeIds = [];

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
.employee-select :deep(.v-field__input) input{
    padding-top: 4px;
    padding-bottom: 4px;
}

.assign-task-page {
  min-height: 100vh;
  background: #f4f7fb;
  padding: 24px;
  font-family: Arial, sans-serif;
}

/* HEADER */

.header-section {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 12px 16px;
}

.back-btn {
  width: 42px;
  height: 42px;
  border: none;
  border-radius: 12px;
  position: absolute;
  left: 16px;
  background: #43b77a;
  cursor: pointer;
  color: white;
  font-size: 22px;
  transition: 0.3s;
  box-shadow: 0 4px 12px rgba(67,183,122,0.25);
}

.page-title {
  margin: 0;
  text-align: center;
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
