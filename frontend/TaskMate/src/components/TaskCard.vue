<!-- <template>
  <div class="card">

    <div class="header">

      <h2>{{ localTask.title }}</h2>

      <span
        class="priority"
        :class="localTask.priority.toLowerCase()"
      >
        {{ localTask.priority }}
      </span>

    </div>

    <p class="description">
      {{ localTask.description }}
    </p>

    <div class="info">
      <p><strong>Deadline:</strong> {{ localTask.deadline }}</p>
      <p><strong>Assigned By:</strong> {{ localTask.assignedBy }}</p>
    </div>

    <div class="section">
      <label>Status</label>

      <select v-model="localTask.status">
        <option>Pending</option>
        <option>In Progress</option>
        <option>Completed</option>
      </select>
    </div>

    <div class="section">
      <label>Progress Comment</label>

      <textarea
        v-model="localTask.comment"
        placeholder="Add progress update..."
      ></textarea>
    </div>

    <div class="section">
      <label>Upload File</label>

      <input
        type="file"
        @change="handleFileUpload"
      />
    </div>

    <div class="section">
      <label>Completion Note</label>

      <textarea
        v-model="localTask.completionNote"
        placeholder="Write completion note..."
      ></textarea>
    </div>

    <button @click="updateTask">
      Update Task
    </button>

  </div>
</template>

<script>
export default {
  props: {
    task: Object,
  },

  data() {
    return {
      localTask: { ...this.task },
    };
  },

  methods: {
    handleFileUpload(event) {
      this.localTask.file = event.target.files[0];
    },

    updateTask() {
      this.$emit("update-task", this.localTask);

      alert("Task Updated Successfully!");
    },
  },
};
</script>

<style scoped>
.card {
    
  background: white;

  border-radius: 24px;

  padding: 25px;

  box-shadow: 0 10px 25px rgba(0,0,0,0.12);

  transition: 0.3s;
}



.card:hover {
  transform: translateY(-5px);
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;

  margin-bottom: 15px;
}

.header h2 {
  font-size: 28px;
  color: #222;
}

.priority {
  padding: 7px 15px;

  border-radius: 20px;

  color: white;

  font-size: 12px;
  font-weight: bold;
}

.high {
  background: #ff4d4f;
}

.medium {
  background: #faad14;
}

.low {
  background: #52c41a;
}

.description {
  color: #555;
  margin-bottom: 20px;
  line-height: 1.6;
}

.info p {
  margin: 8px 0;
}

.section {
  margin-top: 20px;
}

label {
  display: block;

  margin-bottom: 8px;

  font-weight: bold;
  color: #333;
}

select,
textarea,
input[type="file"] {
  width: 100%;
}

select,
textarea {
  padding: 13px;

  border-radius: 12px;

  border: 1px solid #ddd;

  outline: none;
}

textarea {
  min-height: 90px;
  resize: vertical;
}

button {
  width: 100%;

  margin-top: 25px;

  padding: 15px;

  border: none;
  border-radius: 14px;

  background: linear-gradient(135deg, #43b77a, #238a52);

  color: white;

  font-size: 16px;
  font-weight: bold;

  cursor: pointer;

  transition: 0.3s;
}

button:hover {
  transform: scale(1.02);
}
</style> -->
<template></template>