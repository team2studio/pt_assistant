package com.example.pt_assistant;

public class patient_profile {
	
	
	Patient current_patient;
	public void create_patient_profile(String name, int ID, int injury_ID)
	{   
		
		current_patient = new Patient();
		current_patient.setInjury(injury_ID);
		current_patient.setName(name);
		current_patient.setPatientID(ID);
		
		
	}
	public Patient get_patient_profile()
	{
		return current_patient;
		
	}
	

}
