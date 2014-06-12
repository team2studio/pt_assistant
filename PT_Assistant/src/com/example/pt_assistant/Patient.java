package com.example.pt_assistant;

import java.io.Serializable;

public class Patient  implements Serializable {
	private int patientID;
	private String name;
	private int injury;
	private static final long serialVersionUID = -2813029214068905410L;
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
	
	
	public int create_patient()
	{
		//NO IMPLEMENTATION
		return 0;
	}
	
	public int udpate_patient()
	{
		//NO IMPLEMENTATION
		return 0;
	}
	public void get_patient(String ID)
	{
		//name = "John Doe";
		//patientID = Integer.parseInt(ID);
		//injury = 23;
	}
}
