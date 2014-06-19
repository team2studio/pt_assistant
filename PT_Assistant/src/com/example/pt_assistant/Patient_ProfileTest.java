package com.example.pt_assistant;

import org.junit.Test;
import android.content.Context;
import android.test.InstrumentationTestCase;

public class Patient_ProfileTest extends InstrumentationTestCase {
		 
	private Context context;
	  
	  public Patient_ProfileTest() {
	    super();
	  }
	  
	  @Test
		public void testCreate_patient_profile() {
		  	context = getInstrumentation().getContext();
		  	
		  	//lets create a sql lite helper object passing the context
			PT_SQLiteHelper ptsql = new PT_SQLiteHelper(context);
			
			//lets create a new patient
			Patient p = new Patient();
			
			//set the Patient's ID, Name, and Injury
			p.setPatientID(104);
			p.setName("John Marlon");
			p.setInjury(5);
			
			long patientVal = ptsql.addPatient(p);
			//System.out.println("Long value returned was: "+patientVal);
			assertTrue(patientVal >= 1);
	  } 

}
