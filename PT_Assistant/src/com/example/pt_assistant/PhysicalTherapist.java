package com.example.pt_assistant;

public class PhysicalTherapist {
	private int therapistID;
	private String therapistName;
	private int exam;
	private int report;
	
	public int getTherapistID() {
		return therapistID;
	}

	public void setTherapistID(int therapistID) {
		this.therapistID = therapistID;
	}

	public String getTherapistName() {
		return therapistName;
	}

	public void setTherapistName(String therapistName) {
		this.therapistName = therapistName;
	}

	public int getExam() {
		return exam;
	}

	public void setExam(int exam) {
		this.exam = exam;
	}

	public int getReport() {
		return report;
	}

	public void setReport(int report) {
		this.report = report;
	}
	
	public void getTherapist(String ID)
	{
		therapistName = "John Doe";
		exam = 1;
		report = 10;
	}
	

}
