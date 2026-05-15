<template>
  <div class="container">

    <div class="content">

      <div class="top-section">

        <div>
          <h1>My Tasks</h1>
          <p>Manage and update your assigned tasks</p>
        </div>

        <input
          type="text"
          placeholder="Search task..."
          v-model="search"
        />

      </div>

      <div class="filters">

        <button
          :class="{ active: filter === 'All' }"
          @click="filter = 'All'"
        >
          All
        </button>

        <button
          :class="{ active: filter === 'Pending' }"
          @click="filter = 'Pending'"
        >
          Pending
        </button>

        <button
          :class="{ active: filter === 'In Progress' }"
          @click="filter = 'In Progress'"
        >
          In Progress
        </button>

        <button
          :class="{ active: filter === 'Completed' }"
          @click="filter = 'Completed'"
        >
          Completed
        </button>

      </div>

      <div class="task-grid">

        <TaskCard
          v-for="task in filteredTasks"
          :key="task.id"
          :task="task"
          @update-task="updateTask"
        />

      </div>

    </div>

  </div>
</template>

<script>
import TaskCard from "./TaskCard.vue";

export default {
  components: {
    TaskCard,
  },

  data() {
    return {
      search: "",
      filter: "All",

      tasks: [
        {
          id: 1,
          title: "Homepage UI Design",
          description: "Create responsive homepage design",
          deadline: "2026-05-20",
          priority: "High",
          status: "Pending",
          assignedBy: "Admin",
          comment: "",
          completionNote: "",
        },
        {
          id: 2,
          title: "Fix Authentication Bug",
          description: "Resolve login issue",
          deadline: "2026-05-22",
          priority: "Medium",
          status: "In Progress",
          assignedBy: "Project Manager",
          comment: "",
          completionNote: "",
        },
      ],
    };
  },

  computed: {
    filteredTasks() {
      return this.tasks.filter((task) => {

        const matchesSearch =
          task.title.toLowerCase().includes(this.search.toLowerCase());

        const matchesFilter =
          this.filter === "All" || task.status === this.filter;

        return matchesSearch && matchesFilter;
      });
    },
  },

  methods: {
    updateTask(updatedTask) {

      const index = this.tasks.findIndex(
        (task) => task.id === updatedTask.id
      );

      if (index !== -1) {
        this.tasks[index] = updatedTask;
      }
    },
  },
};
</script>

<style scoped>
.container {
  min-height: 100vh;

  background: linear-gradient(135deg, #43b77a, #238a52);

  padding: 40px;

  display: flex;
  justify-content: center;
}

.content {
  width: 100%;
  max-width: 1400px;
}

.top-section {
  display: flex;
  justify-content: space-between;
  align-items: center;

  margin-bottom: 30px;
}

.top-section h1 {
  color: white;
  font-size: 42px;
  margin-bottom: 5px;
}

.top-section p {
  color: rgba(255,255,255,0.8);
}

.top-section input {
  width: 300px;

  padding: 14px 18px;

  border: none;
  border-radius: 14px;

  outline: none;

  font-size: 15px;
}

.filters {
  display: flex;
  gap: 15px;

  margin-bottom: 35px;
}

.filters button {
  padding: 12px 22px;

  border: none;
  border-radius: 12px;

  background: rgba(255,255,255,0.15);

  color: white;
  font-weight: bold;

  cursor: pointer;

  transition: 0.3s;
}

.filters button:hover {
  background: rgba(255,255,255,0.25);
}

.filters button.active {
  background: white;
  color: #238a52;
}

/* .task-grid {
  display: grid;

  grid-template-columns: repeat(auto-fit, minmax(420px, 1fr));

  gap: 25px;
} */

.task-grid {
  display: flex;
  flex-wrap: wrap;

  gap: 30px;

  align-items: flex-start;
}
</style>