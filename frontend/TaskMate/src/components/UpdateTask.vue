
<template>

  <div class="update-page">

    <!-- HEADER -->

    <div class="page-header">

      <button
        class="back-btn"
        @click="$router.back()"
      >
        ← Back
      </button>

      <h1>Update Task</h1>

    </div>

    <!-- MAIN CARD -->

    <div
      v-if="task"
      class="main-card"
    >

      <!-- PRIORITY -->

      <div
        class="priority-badge"
        :class="task.priority.toLowerCase()"
      >
        {{ task.priority }}
      </div>

      <!-- TITLE -->

      <h2 class="task-title">
        {{ task.title }}
      </h2>

      <!-- DESCRIPTION -->

      <!-- <p class="task-desc">
        {{ task.description }}
      </p> -->

      <!-- ================================= -->
      <!-- TASK DETAILS -->
      <!-- ================================= -->

      <div class="section-wrapper">

        <div class="section-title">
          Task Details
        </div>

        <div class="details-grid">

          <!-- LEFT -->

          <div class="details-column">

            <div class="detail-row">
              <span>Task Title:</span>
              <strong>{{ task.title }}</strong>
            </div>

            <div class="detail-row">
              <span>Project:</span>
              <strong>{{ task.project.projectName }}</strong>
            </div>

            <div class="detail-row">
              <span>Task Type:</span>
              <strong>{{ task.taskType }}</strong>
            </div>

            <div class="detail-row">
              <span>Priority:</span>
              <strong>{{ task.priority }}</strong>
            </div>

          </div>

          <!-- RIGHT -->

          <div class="details-column">

            <div class="detail-row">
              <span>Assigned By:</span>
              <strong>{{ task.assignedBy.name }}</strong>
            </div>

            <div class="detail-row">
              <span>Assigned Date:</span>
              <strong>{{ formatDate(task.assignedDate )}}</strong>
            </div>

            <div class="detail-row">
              <span>Target Date:</span>
              <strong>{{formatDate(task.targetDate)  }}</strong>
            </div>

          </div>

        </div>

      </div>


      <div class="section-wrapper">

        <div class="section-title">
          Update Progress
        </div>

        <div class="form-grid">

          <!-- STATUS -->

          <div class="form-group">

            <label>Status</label>

            <select v-model="task.taskStatus" @change="updateEndDate"
             >

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

          <!-- START DATE -->

          <!-- <div class="form-group">

            <label>Start Date</label>

            <input
              type="date"
              v-model="task.startDate"
            />

          </div> -->

          <!-- END DATE -->
<!-- 
          <div class="form-group">

            <label>End Date</label>

            <input
              type="date"
              v-model="task.endDate"
            />

              </div> -->

          <!-- FILE -->

          <div class="form-group">

            <label>Upload File</label>

            <!-- <input type="file" /> -->

            <input
             type="file"
             accept=".pdf,.png,.jpg,.jpeg,.docx,.xls,.xlsx"
             @change="handleFileUpload"
             />

          </div>

          <!-- COMMENT -->

          <div class="form-group">

            <label>Comment</label>

            <textarea
              v-model="task.remarks"
              placeholder="Enter Comment"
            ></textarea>

          </div>


        </div>

      </div>

      <!-- BUTTON -->
<div class="button-container">
      <button
        class="update-btn"
        @click="updateTask"
      >
        Update Task
      </button>
</div>
    </div>

  </div>

</template>

<script>

import axios from "axios";

export default {

  name: "UpdateTask",

 data() {
    return {
      task:null,
      selectedFile: null
    };
  },
  
  mounted() {

    this.fetchTaskById();

  },

  methods: {
 updateEndDate() {
  if (this.task.taskStatus === "IN_PROGRESS" || this.task.taskStatus === "COMPLETED") {

    this.task.endDate = new Date().toISOString().split("T")[0];

  } else {

    this.task.endDate = null;

  }

},

   formatDate(date) {

      if (!date) return "-";

      return new Date(date).toLocaleDateString("en-GB").replace(/\//g, "-");
    },

    async fetchTaskById() {

      try {

        const taskId = this.$route.params.id;

        const response = await axios.get(
          `http://localhost:8080/api/assgintask/getTaskById/${taskId}`
        );

        this.task = response.data.data.data;

      } catch (error) {

        console.error(error);

      }

    },


 handleFileUpload(event) {

    const file = event.target.files[0];

    if (!file) return;

    const allowedTypes = [

      "application/pdf",

      "image/png",

      "image/jpeg",

      "application/vnd.openxmlformats-officedocument.wordprocessingml.document",

      "application/vnd.ms-excel",

      "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"

    ];

    if (!allowedTypes.includes(file.type)) {

      alert(
        "Only PDF, JPG, PNG, DOCX and Excel files are allowed"
      );

      event.target.value = "";

      return;
    }

    this.selectedFile = file;
  },

  async updateTask() {

    try {

      const formData = new FormData();

      const taskData = {

        taskStatus: this.task.taskStatus,

        startDate: null,

        endDate: this.task.endDate,

        remarks: this.task.remarks

      };

      formData.append(
        "task",
        JSON.stringify(taskData)
      );

      if (this.selectedFile) {

        formData.append(
          "file",
          this.selectedFile
        );

      }

      await axios.put(

        `http://localhost:8080/api/assgintask/update/${this.task.id}`,

        formData,

        {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        }

      );

      alert("Task Updated Successfully");

    } catch (error) {

      console.error(error);

      alert("Update Failed");

    }

  }







    // async updateTask() {

    //   try {

    //     await axios.put(
    //       `http://localhost:8080/api/assgintask/update/${this.task.id}`,
    //       this.task
    //     );

    //     alert("Task Updated Successfully");

    //   } catch (error) {

    //     console.error(error);

    //     alert("Update Failed");

    //   }

    // }

  }

};

</script>

<style scoped>

.update-page {

  min-height: 100vh;

  background: #f4f7fb;

  padding: 30px;

  font-family: Arial, sans-serif;

}

/* HEADER */

.page-header {

  display: flex;

  align-items: center;

  gap: 18px;

  margin-bottom: 24px;

}

.page-header h1 {

  margin: 0;

  color: #111827;

  font-size: 34px;

}

/* BACK BUTTON */

.back-btn {

  border: none;

  background: #22c55e;

  color: white;

  padding: 10px 18px;

  border-radius: 10px;

  font-size: 14px;

  font-weight: 600;

  cursor: pointer;

}

/* MAIN CARD */

.main-card {

  width: 100%;

  background: white;

  border-radius: 24px;

  padding: 35px;

  position: relative;

  box-shadow:
    0 4px 18px rgba(0,0,0,0.05);

  border-left: 8px solid #22c55e;

}

/* PRIORITY */

.priority-badge {

  position: absolute;

  top: 30px;
  right: 30px;

  padding: 8px 18px;

  border-radius: 999px;

  color: white;

  font-size: 13px;

  font-weight: 700;

}

.high {

  background: #ef4444;

}

.medium {

  background: #f59e0b;

}

.low {

  background: #10b981;

}

/* TITLE */

.task-title {

  font-size: 38px;

  color: #111827;

  margin-bottom: 10px;

}

/* DESCRIPTION */

.task-desc {

  color: #4b5563;

  font-size: 16px;

  margin-bottom: 35px;

}

/* SECTION */

.section-wrapper {

  margin-top: 40px;

}

.section-title {

  font-size: 24px;

  font-weight: 700;

  color: #111827;

  margin-bottom: 20px;

}

/* DETAILS GRID */

.details-grid {

  display: grid;

  grid-template-columns: 1fr 1fr;

  gap: 40px;

}

.details-column {

  width: 100%;

}

/* DETAIL ROW */

.detail-row {

  display: flex;

  justify-content: space-between;

  align-items: center;

  padding: 8px 0;

}

.detail-row span {

  color: #6b7280;

  font-size: 14px;

  font-weight: 600;

}

.detail-row strong {

  color: #111827;

  font-size: 15px;

  font-weight: 600;

}

/* FORM GRID */

.form-grid {

  display: grid;

  grid-template-columns: 1fr 1fr;

  gap: 22px;

}

.form-group {

  display: flex;

  flex-direction: column;

}

.form-group label {

  margin-bottom: 4px;

  font-size: 14px;

  font-weight: 600;

  color: #111827;

}

/* INPUT */

input,
select,
textarea {

  width: 100%;

  padding: 14px;

  border: 1px solid #dbe4ee;

  border-radius: 14px;

  font-size: 14px;

  outline: none;

  box-sizing: border-box;

}

textarea {

  min-height: 120px;

  resize: vertical;

}

input:focus,
select:focus,
textarea:focus {

  border-color: #22c55e;

  box-shadow:
    0 0 0 3px rgba(34,197,94,0.10);

}

/* BUTTON */

.update-btn {

  width: auto;
  

  margin-top: 30px;

  border: none;

  background: linear-gradient(
    135deg,
    #4ade80,
    #16a34a
  );

  color: white;

  padding: 15px;

  border-radius: 14px;

  font-size: 15px;

  font-weight: 700;

  cursor: pointer;

}


.button-container{
  display: flex;
  text-align: center;
  justify-content: center;
}


/* MOBILE */

@media (max-width: 768px) {

  .details-grid,
  .form-grid {

    grid-template-columns: 1fr;

  }

  .task-title {

    font-size: 28px;

  }

  .main-card {

    padding: 24px;

  }

}

</style>