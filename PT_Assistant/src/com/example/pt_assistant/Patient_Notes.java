package com.example.pt_assistant;

import java.io.Serializable;

public class Patient_Notes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Subjective Section
	private String pastDiagnosis;
	private String medications;
	private String other_PatientHistory;
	private String goals;
	private String reasons;

	// Objective Section
	private int pain;
	private int strength;
	private int rangeOfMotion;
	private int palpation;
	private int jointMobilization;
	private int special_test;

	// Assessment Section
	private String patient_diagnosis;
	private String injury;

	// Plan Section
	private String additional_plan_notes;

	public String getPastDiagnosis() {
		return pastDiagnosis;
	}

	public void setPastDiagnosis(String pastDiagnosis) {
		this.pastDiagnosis = pastDiagnosis;
	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
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

	public int getPalpation() {
		return palpation;
	}

	public void setPalpation(int palpation) {
		this.palpation = palpation;
	}

	public int getJointMobilization() {
		return jointMobilization;
	}

	public void setJointMobilization(int jointMobilization) {
		this.jointMobilization = jointMobilization;
	}

	public String getPatient_diagnosis() {
		return patient_diagnosis;
	}

	public void setPatient_diagnosis(String patient_diagnosis) {
		this.patient_diagnosis = patient_diagnosis;
	}

	public String getAdditionalPlanNotes() {
		return additional_plan_notes;
	}

	public void setAdditionalPlanNotes(String additional_plan_notes) {
		this.additional_plan_notes = additional_plan_notes;
	}

	public void setInjury(String injury) {
		this.injury = injury;
	}

	public String getInjury() {
		return injury;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getGoals() {
		return goals;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public String getReasons() {
		return reasons;
	}

	public void setSpecialTest(int special_test) {
		this.special_test = special_test;
	}

	public int getSpecialTest() {
		return special_test;
	}
}
