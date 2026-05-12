<template>
  <div class="page">

    <!-- Main Content -->
    <div class="main">

      <!-- Add Project Card -->
      <section class="card form-card">

        <!-- Header -->
        <div class="top-bar">

          <!-- Back Arrow -->
          <button
            class="back-btn"
            @click="goToDashboard"
          >
            ←
          </button>

          <h2>Add Project</h2>

        </div>

        <!-- Form -->
        <div class="form-grid">

          <div class="field full-width">

            <label>Project Name</label>

            <input
              v-model="project.projectName"
              type="text"
              placeholder="Enter Project Name"
            />

          </div>

        </div>

        <!-- Buttons -->
        <div class="actions">

          <button
            class="btn save"
            @click="saveProject"
          >
            Save
          </button>

          <button
            class="btn clear"
            @click="clearForm"
          >
            Clear
          </button>

        </div>

      </section>

      <!-- Project List -->
      <section class="card table-card">

        <h2 class="table-title">
          Project List
        </h2>

        <div class="table-wrapper">

          <table>

            <thead>

              <tr>
                <th>ID</th>
                <th>Project Name</th>
                <th>Created Date</th>
                <th>Updated Date</th>
                <th>Status</th>
              </tr>

            </thead>

            <tbody>

              <tr
                v-for="item in projectList"
                :key="item.id"
              >

                <td>{{ item.id }}</td>

                <td>{{ item.projectName }}</td>

                <td>{{ item.createdAt?.split('T')[0] }}</td>

                <td>{{ item.updatedAt?.split('T')[0] }}</td>

                <td>

                  <span
                    :class="
                      item.status === 'ACTIVE'
                        ? 'active'
                        : 'inactive'
                    "
                  >
                    {{ item.status }}
                  </span>

                </td>

              </tr>

              <tr v-if="projectList.length === 0">

                <td
                  colspan="5"
                  class="empty"
                >
                  No Project Found
                </td>

              </tr>

            </tbody>

          </table>

        </div>

      </section>

    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {

  data() {

    return {

      project: {
        projectName: ""
      },

      projectList: []

    };

  },

  mounted() {

    this.getProjects();

  },

  methods: {

    goToDashboard() {

      this.$router.push("/dashboard");

    },

    async saveProject() {

      try {

        await axios.post(
          "http://localhost:8080/api/project/save",
          this.project
        );

        this.clearForm();

        this.getProjects();

      } catch (err) {

        console.log(err);

      }

    },

    clearForm() {

      this.project = {
        projectName: ""
      };

    },

    async getProjects() {

      try {

        const res = await axios.get(
          "http://localhost:8080/api/project/projectList"
        );

        this.projectList = res.data.data;

      } catch (err) {

        console.log(err);

      }

    }

  }

};
</script>

<style scoped>

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.page{
  min-height: 100vh;
  background: #f3f7fb;
  font-family: Arial, sans-serif;
}

/* Main */
.main{
  padding: 30px;
  display: flex;
  flex-direction: column;
  gap: 25px;
}

/* Cards */
.card{
  background: #ffffff;
  border-radius: 18px;
  padding: 28px;
  box-shadow: 0 10px 25px rgba(0,0,0,0.06);
  border: 1px solid #edf2f7;
}

/* Header */
.top-bar{
  display: flex;
  align-items: center;
  gap: 15px;
  margin-bottom: 25px;
}

.top-bar h2{
  color: #1e293b;
  font-size: 28px;
  font-weight: 700;
}

/* Back Button */
.back-btn{
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

.back-btn:hover{
  transform: translateX(-3px);
  background: #369867;
}

/* Form */
.form-grid{
  margin-top: 10px;
}

.field{
  width: 100%;
}

.field label{
  display: block;
  margin-bottom: 10px;
  font-size: 15px;
  font-weight: 600;
  color: #334155;
}

.field input{
  width: 50%;
  padding: 14px;
  border-radius: 12px;
  border: 1px solid #dbe4ee;
  font-size: 15px;
  outline: none;
  transition: 0.3s;
  background: #f8fafc;
}

.field input:focus{
  border-color: #43b77a;
  background: white;
  box-shadow: 0 0 0 4px rgba(67,183,122,0.12);
}

/* Buttons */
.actions{
  margin-top: 24px;
  display: flex;
  gap: 14px;
}

.btn{
  border: none;
  padding: 12px 28px;
  border-radius: 10px;
  font-size: 15px;
  font-weight: 600;
  cursor: pointer;
  transition: 0.3s;
}

.save{
  background: #43b77a;
  color: white;
}

.save:hover{
  background: #369867;
  transform: translateY(-2px);
}

.clear{
  background: #e2e8f0;
  color: #334155;
}

.clear:hover{
  background: #cbd5e1;
}

/* Table */
.table-title{
  color: #1e293b;
  font-size: 24px;
  margin-bottom: 20px;
}

.table-wrapper{
  overflow-x: auto;
}

table{
  width: 100%;
  border-collapse: collapse;
}

thead{
  background: #43b77a;
  color: white;
}

th{
  padding: 16px;
  text-align: left;
  font-size: 14px;
  font-weight: 600;
}

td{
  padding: 16px;
  border-bottom: 1px solid #edf2f7;
  color: #475569;
  font-size: 14px;
}

tbody tr{
  transition: 0.3s;
}

tbody tr:hover{
  background: #f8fafc;
}

/* Status */
.active{
  background: #dcfce7;
  color: #15803d;
  padding: 6px 14px;
  border-radius: 30px;
  font-size: 12px;
  font-weight: bold;
}

.inactive{
  background: #fee2e2;
  color: #dc2626;
  padding: 6px 14px;
  border-radius: 30px;
  font-size: 12px;
  font-weight: bold;
}

/* Empty */
.empty{
  text-align: center;
  padding: 25px;
  color: #94a3b8;
  font-weight: 600;
}

/* Responsive */
@media(max-width: 768px){

  .main{
    padding: 15px;
  }

  .field input{
    width: 100%;
  }

  .top-bar h2{
    font-size: 22px;
  }

}

</style>