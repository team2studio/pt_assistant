package com.example.pt_assistant;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import android.content.Context;
import android.test.InstrumentationTestCase;

public class JUnit_TestCases extends InstrumentationTestCase {
		 
	private Context context;
	Patient p;
	PT_SQLiteHelper ptsql;
	Patient_Notes pn;
	ArrayList<String> patientMedications;
	StringBuilder diagnosis;
	StringBuilder plan_notes;
	TreatmentPlan tp;
	
	  public JUnit_TestCases() {
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
				
			//lets create an object for patient notes
			pn = new Patient_Notes();
			
			//lets create an ArrayList of patient medications
			patientMedications = new ArrayList<String>();
			
			//lets create our StringBuilder objects
			diagnosis = new StringBuilder();
			plan_notes = new StringBuilder();
			
			tp = new TreatmentPlan();
	  }
	 
	  
	  @Test
		public void testCreate_patient_profile() {
		  	
			//set the Patient's ID, Name, Injury, Age, DOB, and Sex
			p.setPatientID(1002);
			p.setName("Tonya Smith");
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
		  	p.setPatientID(1002);
			p.setName("Tonya Dogwood");
			p.setInjury(6);		//lets update the patients injury
			p.setAge(30);
			p.setDOB("02/03/1983");
			p.setSex(1);
			
			int patientUpdateVal = ptsql.updatePatient(p);
			
			//Assert whether 1 row was updated in the database
			assertEquals(1, patientUpdateVal);
	  } 
	  

		@Test
		public void testEnterNotes() {
		  	
		    //lets test entering the patients notes
		    //***SUBJECTIVE***
		  	pn.setPastDiagnosis("Upper Neck Injury");
		  	patientMedications.add("Ibuprofen");	//add medication
		  	patientMedications.add("Ketoprofen");	//add other medications
		  	pn.setMedications(patientMedications);			  	
		  	pn.setOther_PatientHistory("Patient had a lower back injury back in 2010");
		  	
		  	//***OBJECTIVE***
		  	pn.setPain(1);
		  	pn.setStrength(1);
		  	pn.setRangeOfMotion(30);	//in degrees
		  	pn.setPalpatation(0);
		  	pn.setJointMobilization(1);
		  	
		  	//***ASSESSMENT***
		  	diagnosis.append("Poor blood flow around the elbow. There are lacerations on the"
		  			+ "elbow skin. There are other areas of tenderness around the elbow.");
		  	pn.setPatient_diagnosis(diagnosis);
		  	
		  	//***PLAN***
		  	plan_notes.append("Patient has inability to bend elbow and has sudden intense pain. ");
		  	pn.setPatient_special_plan_notes(plan_notes);
		  	
		  	//add the patient notes to the database
			long patientAddNotesVal = ptsql.addPatientNotes(p, pn);
			
			//determine if 1 record was inserted into the database
			assertTrue(patientAddNotesVal >= 1);
	  } 
		
		@Test
		public void testGenerateTreatmentPlan() {
		  
		  //pass in the patient and the patients notes
		  int treatmentPlanVal = tp.generateTreatmentPlan(p, pn);
		  
		  //determine if the treatment plan generated a value of 1
		  assertEquals(1, treatmentPlanVal);
	  }	
}
