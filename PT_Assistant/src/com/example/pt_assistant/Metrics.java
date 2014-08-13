package com.example.pt_assistant;
import java.io.Serializable;

public class Metrics implements Serializable {

	private String specificMetric;
	
	public String getSpecificMetric() {
		return specificMetric;
	}

	public void setSpecificMetric(String specificMetric) {
		this.specificMetric = specificMetric;
	}

	private static final long serialVersionUID = -2813029214068905410L;
	
}
