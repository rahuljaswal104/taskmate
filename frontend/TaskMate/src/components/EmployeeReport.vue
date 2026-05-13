<template>

  <div class="report-container">

    <!-- Header -->

    <div class="header-section">

      <button
        class="back-btn"
        @click="goToDashboard"
      >
        ←
      </button>

      <h2 class="page-title">

        Employee Task Report

      </h2>

      <div class="total-box">

        Total : {{ reports.length }}

      </div>

    </div>

    <!-- Table -->

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

          <!-- Dynamic Data -->

          <tr
            v-for="(report, index) in reports"
            :key="index"
          >

            <td>{{ index + 1 }}</td>

            <td>{{ report.employeeName }}</td>

            <td>{{ report.totalTasks }}</td>

            <td>{{ report.completedTasks }}</td>

            <td>{{ report.pendingTasks }}</td>

            <td>{{ report.inProgressTasks }}</td>

            <!-- Progress -->

            <td width="250">

              <div class="progress-bar-bg">

                <div
                  class="progress-bar-fill"
                  :class="getProgressClass(report.completionPercentage)"
                  :style="{ width: report.completionPercentage + '%' }"
                >

                  {{ report.completionPercentage }}%

                </div>

              </div>

            </td>

            <!-- Status -->

            <td>

              <span
                :class="getStatusClass(report.completionPercentage)"
              >

                {{ getStatus(report.completionPercentage) }}

              </span>

            </td>

          </tr>

          <!-- Empty Data -->

          <tr v-if="reports.length === 0">

            <td
              colspan="8"
              class="empty-data"
            >

              No Employee Report Found

            </td>

          </tr>

        </tbody>

      </table>

    </div>

  </div>

</template>

<script>

import axios from "axios";

export default {

  name: "EmployeeTaskReport",

  data() {

    return {

      reports: []
    };
  },

  mounted() {

    this.loadReports();
  },

  methods: {

    // Dashboard Navigation

    goToDashboard() {

      this.$router.push('/dashboard');
    },

    // Fetch Reports From Backend

    async loadReports() {

      try {

        const response = await axios.get(
          "http://localhost:8080/reports/employee"
        );

        this.reports = response.data;

      } catch (error) {

        console.error(
          "Error Fetching Employee Reports : ",
          error
        );
      }
    },

    // Progress Bar Color

    getProgressClass(percent) {

      if (percent > 70) {

        return "progress-success";

      } else if (percent >= 50) {

        return "progress-warning";

      } else {

        return "progress-danger";
      }
    },

    // Status Color

    getStatusClass(percent) {

      if (percent > 70) {

        return "active";

      } else if (percent >= 50) {

        return "warning";

      } else {

        return "inactive";
      }
    },

    // Status Text

    getStatus(percent) {

      if (percent > 70) {

        return "Excellent";

      } else if (percent >= 50) {

        return "Average";

      } else {

        return "Poor";
      }
    }
  }
};

</script>

<style scoped>

/* Global */

* {

  margin: 0;
  padding: 0;
  box-sizing: border-box;

  user-select: none;

  cursor: default;
}

button {

  cursor: pointer;
}

/* Main */

.report-container {

  background: #f4f7fb;
  min-height: 100vh;
  padding: 30px;
  font-family: Arial, sans-serif;
}

/* Header */

.header-section {

  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 25px;
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

  background: #2ea665;
}

/* Title */

.page-title {

  font-size: 34px;
  font-weight: 700;
  color: #0f172a;
}

/* Total */

.total-box {

  background: #dcfce7;
  color: #15803d;
  padding: 10px 20px;
  border-radius: 30px;
  font-size: 14px;
  font-weight: 700;
}

/* Table Wrapper */

.table-wrapper {

  background: white;
  border-radius: 20px;
  padding: 20px;
  box-shadow: 0px 2px 10px rgba(0,0,0,0.06);
  overflow-x: auto;
}

/* Table */

table {

  width: 100%;
  border-collapse: collapse;
}

/* Head */

thead {

  background: linear-gradient(to right, #49c174, #2ea85f);
}

th {

  color: white;
  text-align: left;
  padding: 20px;
  font-size: 15px;
  font-weight: 700;
}

/* Body */

td {

  padding: 22px 20px;
  border-bottom: 1px solid #edf2f7;
  font-size: 15px;
  color: #1e293b;
}

/* Hover */

tbody tr:hover {

  background: #f8fafc;
}

/* Progress */

.progress-bar-bg {

  width: 100%;
  height: 24px;
  background: #e2e8f0;
  border-radius: 30px;
  overflow: hidden;
}

.progress-bar-fill {

  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  font-weight: 700;
  color: white;
  transition: width 0.4s ease;
}

/* Progress Colors */

.progress-success {

  background: #16a34a;
}

.progress-warning {

  background: #eab308;
  color: #000;
}

.progress-danger {

  background: #dc2626;
}

/* Status */

.active {

  background: #dcfce7;
  color: #15803d;
  padding: 8px 18px;
  border-radius: 30px;
  font-size: 13px;
  font-weight: 700;
}

.warning {

  background: #fef9c3;
  color: #ca8a04;
  padding: 8px 18px;
  border-radius: 30px;
  font-size: 13px;
  font-weight: 700;
}

.inactive {

  background: #fee2e2;
  color: #dc2626;
  padding: 8px 18px;
  border-radius: 30px;
  font-size: 13px;
  font-weight: 700;
}

/* Empty */

.empty-data {

  text-align: center;
  padding: 25px;
  color: #64748b;
  font-weight: 600;
}

/* Responsive */

@media(max-width: 768px) {

  .header-section {

    flex-direction: column;
    gap: 18px;
    align-items: flex-start;
  }

  .page-title {

    font-size: 28px;
  }

  table {

    min-width: 900px;
  }
}

</style>