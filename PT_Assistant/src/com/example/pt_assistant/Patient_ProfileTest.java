package com.example.pt_assistant;

import org.junit.BeforeClass;
import org.junit.Test;

import android.content.Context;
import android.test.InstrumentationTestCase;

public class Patient_ProfileTest extends InstrumentationTestCase {
		 
	private Context context;
	Patient p;
	PT_SQLiteHelper ptsql;
	  
	  public Patient_ProfileTest() {
	    super();
	  }
	  
	  @BeforeClass
	  public void setUp()
	  {
			//lets create a new patient
			p = new Patient();
			
			//lets get the application context
			context = getInstrumentation().getContext();
			
			//lets create a sql lite helper object passing the context
			ptsql = new PT_SQLiteHelper(context);
	  }
	  
	  @Test
		public void testCreate_patient_profile() {
		  	
			//set the Patient's ID, Name, Injury, Age, DOB, and Sex
			p.setPatientID(80);
			p.setName("Jim Curry");
			p.setInjury(5);
			p.setAge(30);
			p.setDOB("02/03/1983");
			p.setSex(1);
			
			long patientVal = ptsql.addPatient(p);
			assertTrue(patientVal >= 1);
	  } 
	  
	  @Test
		public void testUpdate_patient_profile() {
		  	
		    //lets set the Patient attributes with an existing Patient record
		  	p.setPatientID(80);
			p.setName("Jim Curry");
			p.setInjury(6);		//lets update the patients injury
			p.setAge(30);
			p.setDOB("02/03/1983");
			p.setSex(1);
			
			int patientUpdateVal = ptsql.updatePatient(p);
			
			//Assert whether 1 row was updated in the database
			assertEquals(1, patientUpdateVal);
	  } 

}
