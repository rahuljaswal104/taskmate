<template>
  <div>

    <!-- MY TASK REPORT -->
    <div class="report-container" v-if="role !== 'SUPERADMIN'">
      <div class="header-section">

        <button
          class="back-btn"
          @click="goToDashboard"
        >
          ←
        </button>

        <h2 class="page-title">
          My Task Report
        </h2>

        <div class="total-box">
          Total : {{ myReports.length }}
        </div>

      </div>

      <div class="table-wrapper">

        <table>

          <thead>
            <tr>
              <th>ID</th>
              <th>Employee Name</th>
              <th>Total Tasks</th>
              <th>Completed</th>
              <th>Pending</th>
              <th>In Progress</th>
              <th>Progress</th>
              <th>Status</th>
            </tr>
          </thead>

          <tbody>

            <tr
              v-for="(report,index) in myReports"
              :key="'my'+index"
            >
              <td>{{ index + 1 }}</td>
              <td>{{ report.employeeName }}</td>
              <td>{{ report.totalTasks }}</td>
              <td>{{ report.completedTasks }}</td>
              <td>{{ report.pendingTasks }}</td>
              <td>{{ report.inProgressTasks }}</td>

              <td width="250">

                <div class="progress-bar-bg">

            <div
              class="progress-bar-fill"
              :class="getProgressClass(report.performanceStatus)"
            >
              {{ report.completionPercentage }}%
            </div>

          

                </div>

              </td>

              <td>

                <span
                  :class="getStatusClass(report.performanceStatus)"
                >
                  {{ report.performanceStatus }}
                </span>

              </td>
,
            </tr>

            <tr v-if="myReports.length === 0">
              <td colspan="8" class="empty-data">
                No My Report Found
              </td>
            </tr>

          </tbody>

        </table>

      </div>

    </div>

    <!-- EMPLOYEE REPORT -->
    <div class="report-container">

      <div class="header-section employee-header">

        <button
          class="back-btn"
          @click="goToDashboard"
          v-if="role === 'SUPERADMIN'"
        >
          ←
        </button>

        <h2 class="employee-page-title">
          Employee Task Report
        </h2>

        <div class="total-box">
          Total : {{ reports.length }}
        </div>

      </div>

      <div class="table-wrapper">

        <table>

          <thead>
            <tr>
              <th>ID</th>
              <th>Employee Name</th>
              <th>Total Tasks</th>
              <th>Completed</th>
              <th>Pending</th>
              <th>In Progress</th>
              <th>Progress</th>
              <th>Status</th>
            </tr>
          </thead>

          <tbody>

            <tr
              v-for="(report,index) in reports"
              :key="'emp'+index"
            >
              <td>{{ index + 1 }}</td>
              <td>{{ report.employeeName }}</td>
              <td>{{ report.totalTasks }}</td>
              <td>{{ report.completedTasks }}</td>
              <td>{{ report.pendingTasks }}</td>
              <td>{{ report.inProgressTasks }}</td>

              <td width="250">

                <div class="progress-bar-bg">

                  <div
              class="progress-bar-fill"
              :class="getProgressClass(report.performanceStatus)"
            >
              {{ report.completionPercentage }}%
            </div>

                </div>

              </td>

              <td>

                <span
                  :class="getStatusClass(report.performanceStatus)"
                >
                  {{ report.performanceStatus }}
                </span>

              </td>

            </tr>

            <tr v-if="reports.length === 0">
              <td colspan="8" class="empty-data">
                No Employee Report Found
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

  name: "EmployeeTaskReport",

  data() {
    return {

      userId: localStorage.getItem("userId"),
      role: localStorage.getItem("role"),
      name: localStorage.getItem("name"),

      reports: [],
      myReports: []

    };
  },

  mounted() {
    this.loadReports();
  },

  methods: {

    goToDashboard() {
      this.$router.push("/dashboard");
    },

    async loadReports() {

      try {

        const response = await axios.get(
          `http://localhost:8080/reports/employee/${this.userId}`
        );

        const allReports = Array.isArray(response.data) ? response.data : [response.data];

        this.myReports = allReports.filter(
          emp => emp.employeeName === this.name
        );

        this.reports = allReports.filter(
          emp => emp.employeeName !== this.name
        );

        if (this.role === "SUPERADMIN") {
          this.reports = allReports;
        }

      } catch (error) {

        console.error(
          "Error Fetching Employee Reports:",
          error
        );

      }

    },

    
  getProgressClass(status) {
   
  switch(status) {

    case "EXCELLENT":
      return "progress-success";

    case "AVERAGE":
      return "progress-warning";

    case "POOR":
      return "progress-danger";

    default:
      return "progress-danger";
  }

},
    getStatusClass(status) {

      switch (status) {
      
        case "EXCELLENT":
          return "active";

        case "AVERAGE":
          return "warning";

        case "POOR":
          return "inactive";

        default:
          return "inactive";

      }

    }

  }

};
</script>

<style scoped>

*{
  margin:0;
  padding:0;
  box-sizing:border-box;
}

.report-container{
  background:#f4f7fb;
  padding:30px;
}

/* ================= HEADER ================= */

.header-section{
  display:flex;
  justify-content:space-between;
  align-items:center;
  margin-bottom:20px;
}

/* My Task Report */

.back-btn{
  width:42px;
  height:42px;
  border:none;
  border-radius:10px;
  background:#43b77a;
  color:#fff;
  font-size:22px;
  cursor:pointer;
}

.page-title{
  flex:1;
  text-align:center;
  font-size:28px;
  margin:0;
}

/* Employee Task Report */

.employee-header{
  position:relative;
  display:flex;
  align-items:center;
  margin-bottom:20px;
}

.employee-page-title{
  position:absolute;
  left:50%;
  transform:translateX(-50%);
  font-size:28px;
  margin:0;
}

.employee-header .total-box{
  margin-left:auto;
}

/* ================= TOTAL BOX ================= */

.total-box{
  background:#dcfce7;
  color:#15803d;
  padding:10px 20px;
  border-radius:30px;
  font-weight:700;
}

/* ================= TABLE ================= */

.table-wrapper{
  background:#fff;
  border-radius:20px;
  padding:20px;
  overflow-x:auto;
  box-shadow:0 2px 10px rgba(0,0,0,0.06);
}

table{
  width:100%;
  border-collapse:collapse;
}

thead{
  background:linear-gradient(
    to right,
    #49c174,
    #2ea85f
  );
}

th{
  color:#fff;
  text-align:left;
  padding:18px;
  font-size:15px;
  font-weight:700;
}

td{
  padding:18px;
  border-bottom:1px solid #edf2f7;
  font-size:15px;
}

tbody tr:hover{
  background:#f8fafc;
}

/* ================= PROGRESS BAR ================= */

.progress-bar-bg{
  width:100%;
  height:24px;
  background:#e2e8f0;
  border-radius:30px;
  overflow:hidden;
}

.progress-bar-fill{
  height:100%;
  display:flex;
  justify-content:center;
  align-items:center;
  color:white;
  font-size:12px;
  font-weight:bold;
}

.progress-success{
  background:#16a34a;
}

.progress-warning{
  background:#eab308;
  color:black;
}

.progress-danger{
  background:#dc2626;
}


/* ================= STATUS ================= */

.active{
  background:#dcfce7;
  color:#15803d;
  padding:8px 18px;
  border-radius:30px;
  font-size:13px;
  font-weight:700;
}

.warning{
  background:#fef9c3;
  color:#ca8a04;
  padding:8px 18px;
  border-radius:30px;
  font-size:13px;
  font-weight:700;
}

.inactive{
  background:#fee2e2;
  color:#dc2626;
  padding:8px 18px;
  border-radius:30px;
  font-size:13px;
  font-weight:700;
}

/* ================= EMPTY DATA ================= */

.empty-data{
  text-align:center;
  padding:25px;
  font-weight:600;
  color:#64748b;
}

/* ================= RESPONSIVE ================= */

@media(max-width:768px){

  .header-section{
    flex-direction:column;
    gap:15px;
  }

  .employee-header{
    flex-direction:column;
    gap:15px;
  }

  .employee-page-title{
    position:absolute;
    transform:none;
    text-align:center;
  }

  table{
    min-width:900px;
  }
}

</style>