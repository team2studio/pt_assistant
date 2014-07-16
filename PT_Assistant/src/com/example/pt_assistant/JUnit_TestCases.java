package com.example.pt_assistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import android.test.InstrumentationTestCase;

public class JUnit_TestCases extends InstrumentationTestCase {
	
	private static final String CREATE_PATIENT_URL = "http://199.255.250.71/register_patient.php";
	private static final String UPDATE_PATIENT_URL = "http://199.255.250.71/update_patient.php";
	private static final String CREATE_PATIENT_NOTES_URL = "http://199.255.250.71/patient_create_notes.php";
	private static final String TAG_SUCCESS = "success";
	
	Patient p;
	Patient_Notes pn;
	TreatmentPlan tp;
	List<NameValuePair> params;
	JSONParser jsonParser;
	
	  public JUnit_TestCases() {
	    super();
	  }
	  
	  
	  @Before
	  public void setUp()
	  {
			//lets create a new patient
			p = new Patient();
			
			//lets get the application context
			//context = getInstrumentation().getContext();
			
			//lets create a sql lite helper object passing the context
			//ptsql = new PT_SQLiteHelper(context);
				
			//lets create an object for patient notes
			pn = new Patient_Notes();
			
			//lets create an ArrayList of patient medications
			//patientMedications = new ArrayList<String>();
			
			//lets create our StringBuilder objects
			//diagnosis = new StringBuilder();
			//plan_notes = new StringBuilder();
			
			//lets create our treatment plan object
			tp = new TreatmentPlan();
			
			//lets create an arraylist to store name-value-pair
			params = new ArrayList<NameValuePair>();
			
			//lets create our json parse object
			jsonParser = new JSONParser();
	  }
	 
	  
	  @Test
		public void testCreate_patient_profile() {
		  	
		  	int success = 0;	//initialize success variable
		  	
			//set the Patient's ID, Name, Injury, Age, DOB, and Sex
			p.setPatientID(1011);
			p.setName("Amy Gibson");
			p.setInjury(3);
			p.setAge(30);
			p.setDOB("02/03/1983");
			p.setSex(0);
			
			String patient_name = p.getName();
			String patient_id = String.valueOf(p.getPatientID());
			String injury_id = String.valueOf(p.getInjury());
			String birth_date = p.getDOB();
			String age = String.valueOf(p.getAge());
			String sex = String.valueOf(p.getSex());
			
			try{
				
				//build parameters sent in the http request
				params.add(new BasicNameValuePair("patient_id", patient_id));
				params.add(new BasicNameValuePair("patient_name", patient_name));
				params.add(new BasicNameValuePair("b_date", birth_date));
				params.add(new BasicNameValuePair("patient_age", age));
				params.add(new BasicNameValuePair("patient_sex", sex));
				params.add(new BasicNameValuePair("injury_id", injury_id));
			
				// make http request
				JSONObject json = jsonParser.makeHttpRequest(
					CREATE_PATIENT_URL, "POST", params);

				// json success tag
				success = json.getInt(TAG_SUCCESS);
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			//if success equals 1, then the patient was successfully created
			assertTrue(success == 1);
	  } 
	  
	  
	  
	  @Test
		public void testUpdate_patient_profile() {
		  	
		  int success = 0;	//initialize success variable
		  	
			//set the Patient's ID, Name, Injury, Age, DOB, and Sex
			p.setPatientID(1007);
			p.setName("Craig Smithson");
			p.setInjury(1);		//LETS UPDATE THE PATIENT INJURY
			p.setAge(30);
			p.setDOB("02/06/1983");	//UPDATE THE PATIENTS DOB
			p.setSex(1);
			
			String patient_name = p.getName();
			String patient_id = String.valueOf(p.getPatientID());
			String injury_id = String.valueOf(p.getInjury());
			String birth_date = p.getDOB();
			String age = String.valueOf(p.getAge());
			String sex = String.valueOf(p.getSex());
			
			try{
				
				//build parameters sent in the http request
				params.add(new BasicNameValuePair("patient_id", patient_id));
				params.add(new BasicNameValuePair("patient_name", patient_name));
				params.add(new BasicNameValuePair("b_date", birth_date));
				params.add(new BasicNameValuePair("patient_age", age));
				params.add(new BasicNameValuePair("patient_sex", sex));
				params.add(new BasicNameValuePair("injury_id", injury_id));
			
				// make http request
				JSONObject json = jsonParser.makeHttpRequest(
						UPDATE_PATIENT_URL, "POST", params);

				// json success tag
				success = json.getInt(TAG_SUCCESS);
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			//if success equals 1, then the patients injury id was successfully updated
			assertTrue(success == 1);
	  } 
	  

	  
		@Test
		public void testEnterNotes() {
		  	
			int success = 0;	//initialize success variable
			
			//lets set a patient id to use
			p.setPatientID(1011);
			
		    //lets test entering the patients notes
		    //***SUBJECTIVE***
		  	pn.setPastDiagnosis("jamel test");
		  	pn.setMedications("meds");			  	
		  	pn.setOther_PatientHistory("test");
		  	pn.setGoals("none");
		  	pn.setReasons("test");
		  	
		  	//***OBJECTIVE***
		  	pn.setPain(1);
		  	pn.setStrength(2);
		  	pn.setRangeOfMotion(30);	//in degrees
		  	pn.setPalpation(1);
		  	pn.setJointMobilization(1);
		  	pn.setSpecialTest(1);
		  	
		  	//***ASSESSMENT***
		  	pn.setPatient_diagnosis("diagnosis");
		  	pn.setInjury("LOWER BACK INJURY");
		  	
		  	//***PLAN***
		  	pn.setAdditionalPlanNotes("test");
			
			String patient_id = String.valueOf(p.getPatientID());
			String past_diagnosis = pn.getPastDiagnosis();
			String other = pn.getOther_PatientHistory();
			String medications = pn.getMedications();
			String goals = pn.getGoals();
			String reasons = pn.getReasons();
			String range_of_motion = String.valueOf(pn.getRangeOfMotion());
			String strength = String.valueOf(pn.getStrength());
			String joint_mobilization = String.valueOf(pn.getJointMobilization());
			String pain = String.valueOf(pn.getPain());
			String palpation = String.valueOf(pn.getPalpation());
			String special_test = String.valueOf(pn.getSpecialTest());
			String injury_name = pn.getInjury();
			String diagnosis = pn.getPatient_diagnosis();
			String additional_plan_notes = pn.getAdditionalPlanNotes();
			
			try{
				
				//build parameters sent in the http request
				params.add(new BasicNameValuePair("patient_id", patient_id));
				params.add(new BasicNameValuePair("past_diagnosis", past_diagnosis));
				params.add(new BasicNameValuePair("other", other));
				params.add(new BasicNameValuePair("medications", medications));
				params.add(new BasicNameValuePair("goals", goals));
				params.add(new BasicNameValuePair("reasons", reasons));
				params.add(new BasicNameValuePair("range_of_motion", range_of_motion));
				params.add(new BasicNameValuePair("strength", strength));
				params.add(new BasicNameValuePair("joint_mobilization", joint_mobilization));
				params.add(new BasicNameValuePair("pain", pain));
				params.add(new BasicNameValuePair("palpation", palpation));
				params.add(new BasicNameValuePair("special_test", special_test));
				params.add(new BasicNameValuePair("injury_name", injury_name));
				params.add(new BasicNameValuePair("diagnosis", diagnosis));
				params.add(new BasicNameValuePair("additional_plan_notes", additional_plan_notes));
				
				// make http request
				JSONObject json = jsonParser.makeHttpRequest(
						CREATE_PATIENT_NOTES_URL, "POST", params);

				// json success tag
				success = json.getInt(TAG_SUCCESS);
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			//if success equals 1, then the patient's notes was successfully created
			assertTrue(success == 1);
	  } 
		
		
		@Test
		public void testInitializeTreatmentPlans() {
		  
		  //initialize each treatment plan arraylist object
		  tp.initializeTreatmentPlans();
		  
		  assertTrue(tp.getIsTreatmentPlansInitialized() == true);
	  }	
		
		@Test
		public void testGenerateTreatmentPlan() {
			//lets test entering the patients notes
		    //***SUBJECTIVE***
		  	pn.setPastDiagnosis("Bulging Disc in lower back");
		  	pn.setMedications("meds");			  	
		  	pn.setOther_PatientHistory("Severe Arthritis in both knees; Surgery for herniated disc");
		  	pn.setGoals("none at this time");
		  	pn.setReasons("bulging disc causes sever pain");
		  	
		  	//***OBJECTIVE***
		  	//BASED ON THIS SECTION, THIS PATIENT WILL HAVE PHASE 2 TREATMENT for 
		  	//LOWER BACK INJURY
		  	pn.setPain(6);
		  	pn.setStrength(4);
		  	pn.setRangeOfMotion(55);	//in degrees
		  	pn.setPalpation(1);
		  	pn.setJointMobilization(1);
		  	pn.setSpecialTest(1);
		  	
		  	//***ASSESSMENT***
		  	pn.setPatient_diagnosis("diagnosis test");
		  	pn.setInjury("LOWER BACK INJURY LUMBAR");
		  	
		  	//***PLAN***
		  	pn.setAdditionalPlanNotes("More plan notes");
		  	
		  //pass in the patient and the patients notes
		  int treatmentPlanVal = tp.generateTreatmentPlan(pn);
		  
		  //determine if the treatment plan generated a value of 2
		  //if true, we selected the correct treatment plan based on the
		  //patients SOAP data
		  assertEquals(2, treatmentPlanVal);
	  }	
	  
	  
}
