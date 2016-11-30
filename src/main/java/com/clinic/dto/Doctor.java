package com.clinic.dto;

import java.util.ArrayList;

public class Doctor {
	private int drID;
	private String drName;
	private String specialization;
	private String availability;
	private Clinic clinic;
	private Patient patient;
	private ArrayList<Integer> clinicIdList;

	public int getDrID() {
		return drID;
	}

	public void setDrID(int drID) {
		this.drID = drID;
	}

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public ArrayList<Integer> getClinicIdList() {
		return clinicIdList;
	}

	public void setClinicIdList(ArrayList<Integer> clinicIdList) {
		this.clinicIdList = clinicIdList;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "Doctor [drID=" + drID + ", drName=" + drName + ", specialization=" + specialization + ", availability="
				+ availability + ", clinic=" + clinic + ", patient=" + patient + ", clinicIdList=" + clinicIdList + "]";
	}

	

}
