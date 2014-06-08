package com.example.pt_assistant;

import org.junit.Test;

import junit.framework.TestCase;

public class Patient_ProfileTest extends TestCase {

	@Test
	public void testCreate_patient_profile() {
		
		//create Patient object
		Patient p = new Patient();
		
		//set the Patient's ID, Name, and Injury
		p.setPatientID(1);
		p.setName("John Doe");
		p.setInjury(5);
		
		/*Asserts that two integers are equal. The "create_patient" method 
		 * will return an integer (either 1 for the row count of a successful
		 * SQL statement or a 0 for SQL statements that return nothing
		*/
		assertEquals(1, p.create_patient());
	}

}
