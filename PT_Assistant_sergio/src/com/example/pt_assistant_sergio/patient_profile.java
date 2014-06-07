package com.example.pt_assistant_sergio;

public class patient_profile {
	
	
	patient current_patient;
	public void create_patient_profile(String name, int ID, int injury_ID)
	{   
		
		current_patient = new patient();
		current_patient.injury = injury_ID;
		current_patient.name = name;
		current_patient.patientID  = ID;
		
		
	}
	public patient get_patient_profile()
	{
		return current_patient;
		
	}
	

}
