package com.clinic.dto;

public class Clinic {

	private int clinicID;
	private String clinicName;

	public int getClinicId() {
		return clinicID;
	}

	public void setClinicId(int clinicID) {
		this.clinicID = clinicID;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	@Override
	public String toString() {
		return "Clinic [clinicID=" + clinicID + ", clinicName=" + clinicName + "]";
	}

}
