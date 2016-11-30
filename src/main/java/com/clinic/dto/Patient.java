package com.clinic.dto;

import java.util.ArrayList;

public class Patient {
	private int patientId;
	private String patientName;
	private String gender;
	private String mobileNo;
	private int clinicId;
	private ArrayList<Integer> clinicIdList;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public ArrayList<Integer> getClinicIdList() {
		return clinicIdList;
	}

	public void setClinicIdList(ArrayList<Integer> clinicIdList) {
		this.clinicIdList = clinicIdList;
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", gender=" + gender + ", mobileNo="
				+ mobileNo + ", clinicIdList=" + clinicIdList + "]";
	}

}
