package com.example.pt_assistant;

import java.util.ArrayList;
import android.util.Log;

public class TreatmentPlan {
	private static final String LOWER_BACK_INJURY = "LUMBAR STRAIN"; // LOWER
																		// BACK
																		// INJURY
	private static final String SHOULDER_INJURY = "ROTATOR CUFF TEAR"; // SHOULDER
																		// INJURY
	private static final String ACL_INJURY = "ACL TEAR"; // ACL INJURY
	private static final String PHASE1 = "I (Beginner)";
	private static final String PHASE2 = "II (Intermediate)";
	private static final String PHASE3 = "III (Advanced)";
	private static final String TREATMENT_TAG = "Treatment Plan Selected is: ";
	private static final String LOWER_BACK_INJURY_PHASE1 = "LUMBAR STRAIN_1"; // LOWER
																				// BACK
																				// INJURY
	private static final String LOWER_BACK_INJURY_PHASE2 = "LUMBAR STRAIN_2"; // LOWER
																				// BACK
																				// INJURY
	private static final String LOWER_BACK_INJURY_PHASE3 = "LUMBAR STRAIN_3"; // LOWER
																				// BACK
																				// INJURY
	private static final String SHOULDER_INJURY_PHASE1 = "ROTATOR CUFF TEAR_1"; // SHOULDER
																				// INJURY
	private static final String SHOULDER_INJURY_PHASE2 = "ROTATOR CUFF TEAR_2"; // SHOULDER
																				// INJURY
	private static final String SHOULDER_INJURY_PHASE3 = "ROTATOR CUFF TEAR_3"; // SHOULDER
																				// INJURY
	private static final String ACL_INJURY_PHASE1 = "ACL TEAR_1"; // ACL INJURY
	private static final String ACL_INJURY_PHASE2 = "ACL TEAR_2"; // ACL INJURY
	private static final String ACL_INJURY_PHASE3 = "ACL TEAR_3"; // ACL INJURY
	private ArrayList<String> TREATMENT_PLAN_LOWER_BACK; // = new
															// ArrayList<String>();
	private ArrayList<String> TREATMENT_PLAN_SHOULDER; // = new
														// ArrayList<String>();
	private ArrayList<String> TREATMENT_PLAN_ACL; // = new ArrayList<String>();
	private String planID;
	private ArrayList<String> treatmentDescription = null;
	private String treatmentType;
	private int exerciseReps;
	private int exerciseMinTime;
	private int exerciseMaxTime;
	private int rom_min_val;
	private int rom_mid_val1;
	private int rom_mid_val2;
	private int rom_max_val;

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

	public String getPlanID() {
		return planID;
	}

	public void setPlanID(String planID) {
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

	public int getRom_min_val() {
		return rom_min_val;
	}

	public void setRom_min_val(int rom_min_val) {
		this.rom_min_val = rom_min_val;
	}

	public int getRom_mid_val1() {
		return rom_mid_val1;
	}

	public void setRom_mid_val1(int rom_mid_val1) {
		this.rom_mid_val1 = rom_mid_val1;
	}

	public int getRom_mid_val2() {
		return rom_mid_val2;
	}

	public void setRom_mid_val2(int rom_mid_val2) {
		this.rom_mid_val2 = rom_mid_val2;
	}

	public int getRom_max_val() {
		return rom_max_val;
	}

	public void setRom_max_val(int rom_max_val) {
		this.rom_max_val = rom_max_val;
	}

	// add rehabilitation exercises to arraylist
	public ArrayList<String> setRehabilitationExercises(String injuryType) {
		ArrayList<String> injuryArrayList = null;

		if (injuryType.equalsIgnoreCase(LOWER_BACK_INJURY_PHASE1)) {
			TREATMENT_PLAN_LOWER_BACK.add("Ankle Pumps");
			TREATMENT_PLAN_LOWER_BACK.add("Heel Slides");
			TREATMENT_PLAN_LOWER_BACK.add("Abdominal Contraction");
			injuryArrayList = TREATMENT_PLAN_LOWER_BACK;
		} else if (injuryType.equalsIgnoreCase(LOWER_BACK_INJURY_PHASE2)) {
			TREATMENT_PLAN_LOWER_BACK.add("Single Knee to Chest Stretch");
			TREATMENT_PLAN_LOWER_BACK.add("Hamstring Stretch");
			TREATMENT_PLAN_LOWER_BACK.add("Lumbar Stabilization Exercises");
			injuryArrayList = TREATMENT_PLAN_LOWER_BACK;
		} else if (injuryType.equalsIgnoreCase(LOWER_BACK_INJURY_PHASE3)) {
			TREATMENT_PLAN_LOWER_BACK.add("Hip Flexor Stretch");
			TREATMENT_PLAN_LOWER_BACK.add("Piriformis Stretch");
			TREATMENT_PLAN_LOWER_BACK.add("Aerobic Exercises");
			injuryArrayList = TREATMENT_PLAN_LOWER_BACK;
		} else if (injuryType.equalsIgnoreCase(SHOULDER_INJURY_PHASE1)) {
			TREATMENT_PLAN_SHOULDER.add("Pendulums");
			TREATMENT_PLAN_SHOULDER.add("Supine Passive External Rotation");
			TREATMENT_PLAN_SHOULDER.add("Standing Scapular Mobility");
			injuryArrayList = TREATMENT_PLAN_SHOULDER;
		} else if (injuryType.equalsIgnoreCase(SHOULDER_INJURY_PHASE2)) {
			TREATMENT_PLAN_SHOULDER.add("Supine Passive External Rotation");
			TREATMENT_PLAN_SHOULDER.add("Table slides in flexion");
			TREATMENT_PLAN_SHOULDER.add("Supine cross body stretch");
			injuryArrayList = TREATMENT_PLAN_SHOULDER;
		} else if (injuryType.equalsIgnoreCase(SHOULDER_INJURY_PHASE3)) {
			TREATMENT_PLAN_SHOULDER.add("Prone Extension");
			TREATMENT_PLAN_SHOULDER.add("Prone Horizontal Abduction");
			TREATMENT_PLAN_SHOULDER.add("Standing/Prone Scaption");
			injuryArrayList = TREATMENT_PLAN_SHOULDER;
		} else if (injuryType.equalsIgnoreCase(ACL_INJURY_PHASE1)) {
			TREATMENT_PLAN_ACL.add("Heel Prop");
			TREATMENT_PLAN_ACL.add("Quadriceps Setting");
			TREATMENT_PLAN_ACL.add("Straight Leg Lift");
			injuryArrayList = TREATMENT_PLAN_ACL;
		} else if (injuryType.equalsIgnoreCase(ACL_INJURY_PHASE2)) {
			TREATMENT_PLAN_ACL.add("Chair Squat");
			TREATMENT_PLAN_ACL.add("Wall Slides");
			TREATMENT_PLAN_ACL.add("Step Ups");
			injuryArrayList = TREATMENT_PLAN_ACL;
		} else if (injuryType.equalsIgnoreCase(ACL_INJURY_PHASE3)) {
			TREATMENT_PLAN_ACL.add("Leg Press");
			TREATMENT_PLAN_ACL.add("Resistance Hamstring Curls");
			TREATMENT_PLAN_ACL.add("Calf Raise Machine");
			injuryArrayList = TREATMENT_PLAN_ACL;
		}
		return injuryArrayList;
	}

	// generate a treatment plan based on the injury type
	public String generateTreatmentPlan(Patient_Notes patientNotes) {
		String selectedTreatmentPlanID = null;

		if (patientNotes.getInjury().equalsIgnoreCase(LOWER_BACK_INJURY)) {
			TREATMENT_PLAN_LOWER_BACK = new ArrayList<String>();
			setRom_min_val(40);
			setRom_mid_val1(45);
			setRom_mid_val2(55);
			setRom_max_val(60);
			selectedTreatmentPlanID = determineSpecificTreatment(patientNotes);
		} else if (patientNotes.getInjury().equalsIgnoreCase(SHOULDER_INJURY)) {
			TREATMENT_PLAN_SHOULDER = new ArrayList<String>();
			setRom_min_val(0);
			setRom_mid_val1(60);
			setRom_mid_val2(120);
			setRom_max_val(180);
			selectedTreatmentPlanID = determineSpecificTreatment(patientNotes);
		} else if (patientNotes.getInjury().equalsIgnoreCase(ACL_INJURY)) {
			TREATMENT_PLAN_ACL = new ArrayList<String>();
			setRom_min_val(0);
			setRom_mid_val1(30);
			setRom_mid_val2(60);
			setRom_max_val(90);
			selectedTreatmentPlanID = determineSpecificTreatment(patientNotes);
		}

		return selectedTreatmentPlanID;
	}

	// pick a specific treatment plan based off the patients objective notes
	public String determineSpecificTreatment(Patient_Notes patientNotes) {
		String injuryType = patientNotes.getInjury() + "_";
		ArrayList<String> treatmentExercises = null;

		// lets determine the patients pain level
		if ((patientNotes.getPain() >= 7) && (patientNotes.getPain() <= 10)
				&& (patientNotes.getStrength() >= 0)
				&& (patientNotes.getStrength() <= 3)
				&& (patientNotes.getRangeOfMotion() >= getRom_min_val())
				&& (patientNotes.getRangeOfMotion() <= getRom_mid_val1())) {

			// set an initial treatment plan
			setPlanID(injuryType + "1");

			// lets call the method to set the exercises
			treatmentExercises = setRehabilitationExercises(getPlanID());
			setTreatmentType(PHASE1);
			setTreatmentDescription(treatmentExercises);
			pickExerciseRepsAndTime(patientNotes);
			Log.d(TREATMENT_TAG, "1");
		} else if ((patientNotes.getPain() >= 3)
				&& (patientNotes.getPain() <= 6)
				&& (patientNotes.getStrength() >= 4)
				&& (patientNotes.getStrength() <= 7)
				&& (patientNotes.getRangeOfMotion() > getRom_mid_val1())
				&& (patientNotes.getRangeOfMotion() <= getRom_mid_val2())) {

			// set an initial treatment plan
			setPlanID(injuryType + "2");

			// lets call the method to set the exercises
			treatmentExercises = setRehabilitationExercises(getPlanID());
			setTreatmentType(PHASE2);
			setTreatmentDescription(treatmentExercises);
			pickExerciseRepsAndTime(patientNotes);
			Log.d(TREATMENT_TAG, "2");
		} else if ((patientNotes.getPain() >= 0)
				&& (patientNotes.getPain() <= 2)
				&& (patientNotes.getStrength() >= 8)
				&& (patientNotes.getStrength() <= 10)
				&& (patientNotes.getRangeOfMotion() > getRom_mid_val2())
				&& (patientNotes.getRangeOfMotion() <= getRom_max_val())) {

			// set an initial treatment plan
			setPlanID(injuryType + "3");

			// lets call the method to set the exercises
			treatmentExercises = setRehabilitationExercises(getPlanID());
			setTreatmentType(PHASE3);
			setTreatmentDescription(treatmentExercises);
			pickExerciseRepsAndTime(patientNotes);
			Log.d(TREATMENT_TAG, "3");
		} else {
			// lets start the patient off slowly with Phase I
			// set an initial treatment plan
			setPlanID(injuryType + "1");

			// lets call the method to set the exercises
			treatmentExercises = setRehabilitationExercises(getPlanID());
			setTreatmentType(PHASE1);
			setTreatmentDescription(treatmentExercises);
			pickExerciseRepsAndTime(patientNotes);
			Log.d(TREATMENT_TAG, "1");
		}

		return getPlanID();
	}

	// pick exercise repetitions and min/max exercise time
	public void pickExerciseRepsAndTime(Patient_Notes patientNotes) {
		String injuryName = patientNotes.getInjury();
		String phaseOfTreatment = getTreatmentType();
		int val = 0;

		if (injuryName.equalsIgnoreCase(LOWER_BACK_INJURY)
				&& (phaseOfTreatment.equalsIgnoreCase(PHASE1)))
			val = 1;
		else if (injuryName.equalsIgnoreCase(LOWER_BACK_INJURY)
				&& (phaseOfTreatment.equalsIgnoreCase(PHASE2)))
			val = 2;
		else if (injuryName.equalsIgnoreCase(LOWER_BACK_INJURY)
				&& (phaseOfTreatment.equalsIgnoreCase(PHASE3)))
			val = 3;
		else if (injuryName.equalsIgnoreCase(SHOULDER_INJURY)
				&& (phaseOfTreatment.equalsIgnoreCase(PHASE1)))
			val = 4;
		else if (injuryName.equalsIgnoreCase(SHOULDER_INJURY)
				&& (phaseOfTreatment.equalsIgnoreCase(PHASE2)))
			val = 5;
		else if (injuryName.equalsIgnoreCase(SHOULDER_INJURY)
				&& (phaseOfTreatment.equalsIgnoreCase(PHASE3)))
			val = 6;
		else if (injuryName.equalsIgnoreCase(ACL_INJURY)
				&& (phaseOfTreatment.equalsIgnoreCase(PHASE1)))
			val = 7;
		else if (injuryName.equalsIgnoreCase(ACL_INJURY)
				&& (phaseOfTreatment.equalsIgnoreCase(PHASE2)))
			val = 8;
		else if (injuryName.equalsIgnoreCase(ACL_INJURY)
				&& (phaseOfTreatment.equalsIgnoreCase(PHASE3)))
			val = 9;

		switch (val) {
		case 1:
			setExerciseReps(5);
			setExerciseMinTime(10);
			setExerciseMaxTime(20);
			break;
		case 2:
			setExerciseReps(5);
			setExerciseMinTime(10);
			setExerciseMaxTime(30);
			break;
		case 3:
			setExerciseReps(10);
			setExerciseMinTime(10);
			setExerciseMaxTime(30);
			break;
		case 4:
			setExerciseReps(5);
			setExerciseMinTime(15);
			setExerciseMaxTime(25);
			break;
		case 5:
			setExerciseReps(5);
			setExerciseMinTime(20);
			setExerciseMaxTime(30);
			break;
		case 6:
			setExerciseReps(10);
			setExerciseMinTime(20);
			setExerciseMaxTime(30);
			break;
		case 7:
			setExerciseReps(20);
			setExerciseMinTime(10);
			setExerciseMaxTime(20);
			break;
		case 8:
			setExerciseReps(15);
			setExerciseMinTime(20);
			setExerciseMaxTime(30);
			break;
		case 9:
			setExerciseReps(20);
			setExerciseMinTime(10);
			setExerciseMaxTime(20);
			break;
		default:
			setExerciseReps(5);
			setExerciseMinTime(10);
			setExerciseMaxTime(20);
			break;
		}
	}
}
