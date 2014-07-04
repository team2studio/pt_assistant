package com.example.pt_assistant;

import java.util.ArrayList;

public class Patient_Notes {
	
	//Subjective Section
	private String pastDiagnosis;
	private ArrayList<String> medications;
	private String other_PatientHistory;
	
	//Objective Section
	private int pain;
	private int strength;
	private int rangeOfMotion;
	private int palpatation;
	private int jointMobilization;
	
	//Assessment Section
	private StringBuilder patient_diagnosis;
	
	//Plan Section
	private StringBuilder patient_special_plan_notes;
	
	
	public String getPastDiagnosis() {
		return pastDiagnosis;
	}

	public void setPastDiagnosis(String pastDiagnosis) {
		this.pastDiagnosis = pastDiagnosis;
	}

	public ArrayList<String> getMedications() {
		return medications;
	}

	public void setMedications(ArrayList<String> medications) {
		this.medications = medications;
	}

	public String getOther_PatientHistory() {
		return other_PatientHistory;
	}

	public void setOther_PatientHistory(String other_PatientHistory) {
		this.other_PatientHistory = other_PatientHistory;
	}

	public int getPain() {
		return pain;
	}

	public void setPain(int pain) {
		this.pain = pain;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getRangeOfMotion() {
		return rangeOfMotion;
	}

	public void setRangeOfMotion(int rangeOfMotion) {
		this.rangeOfMotion = rangeOfMotion;
	}

	public int getPalpatation() {
		return palpatation;
	}

	public void setPalpatation(int palpatation) {
		this.palpatation = palpatation;
	}

	public int getJointMobilization() {
		return jointMobilization;
	}

	public void setJointMobilization(int jointMobilization) {
		this.jointMobilization = jointMobilization;
	}

	public StringBuilder getPatient_diagnosis() {
		return patient_diagnosis;
	}

	public void setPatient_diagnosis(StringBuilder patient_diagnosis) {
		this.patient_diagnosis = patient_diagnosis;
	}

	public StringBuilder getPatient_special_plan_notes() {
		return patient_special_plan_notes;
	}

	public void setPatient_special_plan_notes(
			StringBuilder patient_special_plan_notes) {
		this.patient_special_plan_notes = patient_special_plan_notes;
	}
		
}
