package com.example.pt_assistant;

public class Injury {

	private int injury;
	private String injuryName;
	private String injuryDescription;
	
	public int getInjury() {
		return injury;
	}
	public void setInjury(int injury) {
		this.injury = injury;
	}
	public String getInjuryName() {
		return injuryName;
	}
	public void setInjuryName(String injuryName) {
		this.injuryName = injuryName;
	}
	public String getInjuryDescription() {
		return injuryDescription;
	}
	public void setInjuryDescription(String injuryDescription) {
		this.injuryDescription = injuryDescription;
	}
	
	public String getSpecificInjuryHelp(int injuryID){
		String commonInjury1 = null;
		//NO IMPLEMENTATION - MAKE IT FAIL
		if (injuryID == 5){
			commonInjury1 = "Kneck Injury";
		}
		return commonInjury1;
	}
	
}
