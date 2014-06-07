package com.example.pt_assistant_sergio;

import junit.framework.TestCase;

public class patient_profileTest extends TestCase {

	public void testCreate_patient_profile() {
		String name = "Sergio";
		int patientId = 25;
		int injury_ID = 1234;
		
		patient_profile newPatientTest = new patient_profile();
		patient result_patient = new patient();
		
		newPatientTest.create_patient_profile(name, patientId, injury_ID);
		
		result_patient = newPatientTest.get_patient_profile( );
		
		assertEquals(name, result_patient.name);
		assertEquals(patientId, result_patient.patientID);
		assertEquals(injury_ID, result_patient.injury);
		
		
		
		
		
	}
	
	

}
