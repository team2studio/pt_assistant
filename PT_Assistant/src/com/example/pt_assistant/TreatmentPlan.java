package com.example.pt_assistant;

public class TreatmentPlan {
	private int planID;
	private String treatmentDescription;
	private String treatmentType;
	
	public int getPlanID() {
		return planID;
	}
	public void setPlanID(int planID) {
		this.planID = planID;
	}
	public String getTreatmentDescription() {
		return treatmentDescription;
	}
	public void setTreatmentDescription(String treatmentDescription) {
		this.treatmentDescription = treatmentDescription;
	}
	public String getTreatmentType() {
		return treatmentType;
	}
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}
	
	//generate treatment plan
	public int generateTreatmentPlan(Patient patient, Patient_Notes patientNotes){
		//no implementation
		return 0;
	}
}
