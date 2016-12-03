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
	public void createPatientClinicTable();
	public void createAppointmentTable();
	
	public void insertClinicDetails(Clinic clinic);
	public void insertDoctorDetails(Doctor doctor);
	public void insertPatientDetails(Patient patient);
	public void insertDrClinicDetails(Doctor doctor);
	public void insertPatientClinicDetails(Patient patient);
	
	public ArrayList<Doctor> getDoctorDetailsByClinicId(int clinicId);
	public ArrayList<Clinic> getClinicDetailsByPatientId(int patientId);
	public ArrayList<Doctor> getDoctorByAvailability(String availability, int clinicId);
	public Doctor getDoctorDetailsByID(int drId,String availability,int clinicId);
	public int isAppointmentAvailable(int drId,String currentDate);
	public boolean isAppointmentAlreadyTaken(int drId,int patientId,String currentDate);
	public int checkTimeForAppointment(int drId);
	public void takeAppointment(int drId,int clinicId,int patientId,int time,String date);
	public Doctor showAppointment(int drId,int patientId);
	public String getNextDayForAppointment(int drId);
	
	public boolean isExist(String tableName);
	public void dropTable();
}
