package com.example.pt_assistant;

import java.util.ArrayList;
import android.util.Log;

public class TreatmentPlan {
	private static final String LOWER_BACK_INJURY = "LOWER BACK INJURY LUMBAR";	//LOWER BACK INJURY
	private static final String SHOULDER_INJURY = "ROTATOR CUFF TENDINITIS";	//SHOULDER INJURY
	private static final String PHASE1 = "Phase I (Beginner)";
	private static final String PHASE2 = "Phase II (Intermediate)";
	private static final String PHASE3 = "Phase III (Advanced)";
	private static final String TREATMENT_TAG = "Treatment Plan Selected is: ";
	
	//treatment plan options for lower back
	private ArrayList<String> TREATMENT_PLAN1_LOWER_BACK = new ArrayList<String>();
	private ArrayList<String> TREATMENT_PLAN2_LOWER_BACK = new ArrayList<String>();
	private ArrayList<String> TREATMENT_PLAN3_LOWER_BACK = new ArrayList<String>();
	
	//treatment plan options for shoulder
	private ArrayList<String> TREATMENT_PLAN1_SHOULDER = new ArrayList<String>();
	private ArrayList<String> TREATMENT_PLAN2_SHOULDER = new ArrayList<String>();
	private ArrayList<String> TREATMENT_PLAN3_SHOULDER = new ArrayList<String>();
	
	private int planID;
	private ArrayList<String> treatmentDescription = null;
	private String treatmentType;
	private int exerciseReps;
	private int exerciseMinTime;
	private int exerciseMaxTime;
	private boolean treatmentPlansInitialized;
	
	public int getExerciseReps() {
		return exerciseReps;
	}
	public void setExerciseReps(int exerciseReps) {
		this.exerciseReps = exerciseReps;
	}
	public int getExerciseMinTime() {
		return exerciseMinTime;
	}
	public void setExerciseMinTime(int exerciseMinTime) {
		this.exerciseMinTime = exerciseMinTime;
	}
	public int getExerciseMaxTime() {
		return exerciseMaxTime;
	}
	public void setExerciseMaxTime(int exerciseMaxTime) {
		this.exerciseMaxTime = exerciseMaxTime;
	}
	
	public int getPlanID() {
		return planID;
	}
	public void setPlanID(int planID) {
		this.planID = planID;
	}
	public ArrayList<String> getTreatmentDescription() {
		return treatmentDescription;
	}
	public void setTreatmentDescription(ArrayList<String> treatmentDescription) {
		this.treatmentDescription = treatmentDescription;
	}
	public String getTreatmentType() {
		return treatmentType;
	}
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}
	
	//added by Jamel
	public void setIsTreatmentPlansInitialized(boolean initialized){
		this.treatmentPlansInitialized = initialized;
	}
	
	//added by Jamel
	public boolean getIsTreatmentPlansInitialized(){
		return treatmentPlansInitialized;
	}
	
	//add treatment plan exercises to arraylist
	public void initializeTreatmentPlans(){
		
		//LOWER BACK
		TREATMENT_PLAN1_LOWER_BACK.add("Ankle Pumps");
		TREATMENT_PLAN1_LOWER_BACK.add("Heel Slides");
		TREATMENT_PLAN1_LOWER_BACK.add("Abdominal Contraction");
		
		
		TREATMENT_PLAN2_LOWER_BACK.add("Single Knee to Chest Stretch");
		TREATMENT_PLAN2_LOWER_BACK.add("Hamstring Stretch");
		TREATMENT_PLAN2_LOWER_BACK.add("Lumbar Stabilization Exercises With Swiss Ball");
		
		TREATMENT_PLAN3_LOWER_BACK.add("Hip Flexor Stretch");
		TREATMENT_PLAN3_LOWER_BACK.add("Piriformis Stretch");
		TREATMENT_PLAN3_LOWER_BACK.add("Aerobic Exercises");
		
		//SHOULDER
		TREATMENT_PLAN1_SHOULDER.add("Pendulums");
		TREATMENT_PLAN1_SHOULDER.add("Standing Scapular Mobility (no resistance)");
		TREATMENT_PLAN1_SHOULDER.add("Supine or Standing Passive External Rotation ");
	
		TREATMENT_PLAN2_SHOULDER.add("Supine Passive External Rotation in scapular plane progressing to 90 deg of Abduction");
		TREATMENT_PLAN2_SHOULDER.add("Table slides in flexion with progression to wall slides"); 
		TREATMENT_PLAN2_SHOULDER.add("Supine or standing cross body stretch");
		
		TREATMENT_PLAN3_SHOULDER.add("Prone Extension");
		TREATMENT_PLAN3_SHOULDER.add("Prone Horizontal Abduction");
		TREATMENT_PLAN3_SHOULDER.add("Standing/Prone Scaption");
		
		setIsTreatmentPlansInitialized(true);	//set it to true so that its only executed once
	}
	
	//generate treatment plan
	public int generateTreatmentPlan(Patient_Notes patientNotes){
		int selectedTreatmentPlanID = 0;
		
		//lets determine if we set up our treatment plans
		if (getIsTreatmentPlansInitialized() == false){
			initializeTreatmentPlans();		//add list of exercises to each arraylist object
		}
		
		//determine a treatment plan based on the injury
		if (patientNotes.getInjury().equalsIgnoreCase(LOWER_BACK_INJURY)){
			selectedTreatmentPlanID = generateLowerBackInjuryTreatmentPlan(patientNotes);
		}
		else if (patientNotes.getInjury().equalsIgnoreCase(SHOULDER_INJURY)){
			selectedTreatmentPlanID = generateShoulderInjuryTreatmentPlan(patientNotes);
		}
		
		return selectedTreatmentPlanID;
}
	public int generateLowerBackInjuryTreatmentPlan(Patient_Notes patientNotes){
		
		//lets determine the patients pain level
		if ((patientNotes.getPain() >= 7) && (patientNotes.getPain() <=10)){
			
			//lets determine the patients strength level
			if ((patientNotes.getStrength() >= 0) && (patientNotes.getStrength() <= 3)){
				
				//lets determine the patients range of motion
				if ((patientNotes.getRangeOfMotion() >= 40) && (patientNotes.getRangeOfMotion() <= 45)){
					
					//set an initial treatment plan 
					setPlanID(1);
					setTreatmentType(PHASE1);
					setTreatmentDescription(TREATMENT_PLAN1_LOWER_BACK);
					setExerciseReps(10);
					setExerciseMinTime(10);
					setExerciseMaxTime(30);
					Log.d(TREATMENT_TAG, "1" );
				}
			}
		}
		else if ((patientNotes.getPain() >= 3) && (patientNotes.getPain() <=6)){
					
					//lets determine the patients strength level
					if ((patientNotes.getStrength() >= 4) && (patientNotes.getStrength() <= 7)){
						
						//lets determine the patients range of motion
						if ((patientNotes.getRangeOfMotion() > 45) && (patientNotes.getRangeOfMotion() <= 55)){
							
							//set an initial treatment plan 
							setPlanID(2);
							setTreatmentType(PHASE2);
							setTreatmentDescription(TREATMENT_PLAN2_LOWER_BACK);
							setExerciseReps(5);
							setExerciseMinTime(10);
							setExerciseMaxTime(30);
							Log.d(TREATMENT_TAG, "2" );
						}
					}
				}
		else if ((patientNotes.getPain() >= 0) && (patientNotes.getPain() <=2)){
					
					//lets determine the patients strength level
					if ((patientNotes.getStrength() >= 8) && (patientNotes.getStrength() <= 10)){
						
						//lets determine the patients range of motion
						if ((patientNotes.getRangeOfMotion() > 55) && (patientNotes.getRangeOfMotion() <= 60)){
							
							//set an initial treatment plan 
							setPlanID(3);
							setTreatmentType(PHASE3);
							setTreatmentDescription(TREATMENT_PLAN3_LOWER_BACK);
							setExerciseReps(5);
							setExerciseMinTime(10);
							setExerciseMaxTime(30);
							Log.d(TREATMENT_TAG, "3" );
						}
					}
				}
		else{
			//lets start the patient off slowly with Phase I
			//set an initial treatment plan 
			setPlanID(1);
			setTreatmentType(PHASE1);
			setTreatmentDescription(TREATMENT_PLAN1_LOWER_BACK);
			setExerciseReps(10);
			setExerciseMinTime(10);
			setExerciseMaxTime(30);
			Log.d(TREATMENT_TAG, "1" );
		}
		
		return getPlanID();
	}
	
	public int generateShoulderInjuryTreatmentPlan(Patient_Notes patientNotes){
				//lets determine the patients pain level
				if ((patientNotes.getPain() >= 7) && (patientNotes.getPain() <=10)){
					
					//lets determine the patients strength level
					if ((patientNotes.getStrength() >= 0) && (patientNotes.getStrength() <= 3)){
						
						//lets determine the patients range of motion
						if ((patientNotes.getRangeOfMotion() >= 0) && (patientNotes.getRangeOfMotion() <= 60)){
							
							//set an initial treatment plan 
							setPlanID(4);
							setTreatmentType(PHASE1);
							setTreatmentDescription(TREATMENT_PLAN1_SHOULDER);
							setExerciseReps(10);
							setExerciseMinTime(1);
							setExerciseMaxTime(3);
							Log.d(TREATMENT_TAG, "4" );
						}
					}
				}
				else if ((patientNotes.getPain() >= 3) && (patientNotes.getPain() <=6)){
							
							//lets determine the patients strength level
							if ((patientNotes.getStrength() >= 4) && (patientNotes.getStrength() <= 7)){
								
								//lets determine the patients range of motion
								if ((patientNotes.getRangeOfMotion() > 61) && (patientNotes.getRangeOfMotion() <= 120)){
									
									//set an initial treatment plan 
									setPlanID(5);
									setTreatmentType(PHASE2);
									setTreatmentDescription(TREATMENT_PLAN2_SHOULDER);
									setExerciseReps(15);
									setExerciseMinTime(1);
									setExerciseMaxTime(3);
									Log.d(TREATMENT_TAG, "5" );
								}
							}
						}
				else if ((patientNotes.getPain() >= 0) && (patientNotes.getPain() <=2)){
							
							//lets determine the patients strength level
							if ((patientNotes.getStrength() >= 8) && (patientNotes.getStrength() <= 10)){
								
								//lets determine the patients range of motion
								if ((patientNotes.getRangeOfMotion() > 121) && (patientNotes.getRangeOfMotion() <= 180)){
									
									//set an initial treatment plan 
									setPlanID(6);
									setTreatmentType(PHASE3);
									setTreatmentDescription(TREATMENT_PLAN3_SHOULDER);
									setExerciseReps(15);
									setExerciseMinTime(1);
									setExerciseMaxTime(3);
									Log.d(TREATMENT_TAG, "6" );
								}
							}
						}
				else{
					//lets start the patient off slowly with Phase I
					//set an initial treatment plan 
					setPlanID(4);
					setTreatmentType(PHASE1);
					setTreatmentDescription(TREATMENT_PLAN1_SHOULDER);
					setExerciseReps(10);
					setExerciseMinTime(1);
					setExerciseMaxTime(3);
					Log.d(TREATMENT_TAG, "4" );
				}
				
				return getPlanID();
	}
}
