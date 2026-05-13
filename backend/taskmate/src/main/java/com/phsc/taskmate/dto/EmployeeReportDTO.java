package com.phsc.taskmate.dto;

public class EmployeeReportDTO {
	
	 private String employeeName;

	    private Long totalTasks;

	    private Long completedTasks;

	    private Long pendingTasks;

	    private Long inProgressTasks;

	    private Double completionPercentage;

		public EmployeeReportDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		

		public EmployeeReportDTO(String employeeName, Long totalTasks, Long completedTasks, Long pendingTasks,
				Long inProgressTasks, Double completionPercentage) {
			super();
			this.employeeName = employeeName;
			this.totalTasks = totalTasks;
			this.completedTasks = completedTasks;
			this.pendingTasks = pendingTasks;
			this.inProgressTasks = inProgressTasks;
			this.completionPercentage = completionPercentage;
		}



		public String getEmployeeName() {
			return employeeName;
		}

		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}

		public Long getTotalTasks() {
			return totalTasks;
		}

		public void setTotalTasks(Long totalTasks) {
			this.totalTasks = totalTasks;
		}

		public Long getCompletedTasks() {
			return completedTasks;
		}

		public void setCompletedTasks(Long completedTasks) {
			this.completedTasks = completedTasks;
		}

		public Long getPendingTasks() {
			return pendingTasks;
		}

		public void setPendingTasks(Long pendingTasks) {
			this.pendingTasks = pendingTasks;
		}

		public Long getInProgressTasks() {
			return inProgressTasks;
		}

		public void setInProgressTasks(Long inProgressTasks) {
			this.inProgressTasks = inProgressTasks;
		}

		public Double getCompletionPercentage() {
			return completionPercentage;
		}

		public void setCompletionPercentage(Double completionPercentage) {
			this.completionPercentage = completionPercentage;
		}


	    
}
