package com.clinic.dao;

import java.util.ArrayList;

import com.clinic.dto.Clinic;
import com.clinic.dto.Doctor;
import com.clinic.dto.Patient;

public interface ClinicDao {
	public void createClinicTable();
	public void createDoctorTable();
	public void createPatientTable();
	public void createDrClinicTable();
	public void createPatientClinic();
	public void insertClinicDetails(Clinic clinic);
	public void insertDoctorDetails(Doctor doctor);
	public void insertPatientDetails(Patient patient);
	public ArrayList<Doctor> getDoctorDetailsByClinicId(int clinicId);
	public ArrayList<Clinic> getClinicDetailsByPatientId(int patientId);
	public void getDrByClinicId(int clinicId);
	public void dropTable();
	public boolean isExist(String tableName);
}
