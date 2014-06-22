package com.example.pt_assistant;

import java.io.Serializable;

public class Patient implements Serializable {
	private int patientID;
	private String name;
	private int injury;
	private int sex;
	private int age;
	private String DOB;
	private static final long serialVersionUID = -2813029214068905410L;
	
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInjury() {
		return injury;
	}

	public void setInjury(int injuryid) {
		injury = injuryid;
	}

	public void get_patient(String ID) {
		// name = "John Doe";
		// patientID = Integer.parseInt(ID);
		// injury = 23;
	}
}
