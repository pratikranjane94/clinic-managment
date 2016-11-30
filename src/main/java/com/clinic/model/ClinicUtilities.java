package com.clinic.model;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clinic.dto.Clinic;
import com.clinic.dto.Doctor;
import com.clinic.dto.Patient;

public class ClinicUtilities {

	public Clinic readClinicJson(String clinicFileName, int i) {
		Clinic clinic = new Clinic();
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(clinicFileName));
			JSONObject doctObj = (JSONObject) object;
			JSONArray doctorsArray = (JSONArray) doctObj.get("Clinic");
			JSONObject doctorJson = (JSONObject) doctorsArray.get(i);
			String clinicName = (String) doctorJson.get("ClinicName");

			int clinicID = Integer.parseInt(String.valueOf(doctorJson.get("ClinicID")));

			clinic.setClinicId(clinicID);
			clinic.setClinicName(clinicName);

		} catch (Exception e) {
			System.out.println("Exception");
		}
		return clinic;
	}

	@SuppressWarnings("unchecked")
	public Doctor readDrJson(String clinicFileName, int i) {
		Doctor doctor = new Doctor();
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(clinicFileName));
			JSONObject doctObj = (JSONObject) object;
			JSONArray doctorsArray = (JSONArray) doctObj.get("Doctors");
			JSONObject doctorJson = (JSONObject) doctorsArray.get(i);
			String drName = (String) doctorJson.get("DrName");

			int drID = Integer.parseInt(String.valueOf(doctorJson.get("DoctorID")));

			String specialization = (String) doctorJson.get("Specialization");

			String availability = (String) doctorJson.get("Availability");

			ArrayList<Integer> clinicIdList = (ArrayList<Integer>) doctorJson.get("ClinicID");

			doctor.setDrID(drID);
			doctor.setDrName(drName);
			doctor.setSpecialization(specialization);
			doctor.setAvailability(availability);
			doctor.setClinicIdList(clinicIdList);
		} catch (Exception e) {
			System.out.println("Exception");
		}

		return doctor;
	}

	@SuppressWarnings("unchecked")
	public Patient readPatientJson(String clinicFileName, int i) {
		Patient patient = new Patient();
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(clinicFileName));
			JSONObject doctObj = (JSONObject) object;
			JSONArray doctorsArray = (JSONArray) doctObj.get("Patients");
			JSONObject doctorJson = (JSONObject) doctorsArray.get(i);

			String patientName = (String) doctorJson.get("PatientName");

			int patientId = Integer.parseInt(String.valueOf(doctorJson.get("PatientID")));

			String mobileNo = (String) doctorJson.get("MobileNo");

			String gender = (String) doctorJson.get("Gender");

			ArrayList<Integer> clinicIdList = (ArrayList<Integer>) doctorJson.get("ClinicID");

			patient.setPatientId(patientId);
			patient.setPatientName(patientName);
			patient.setMobileNo(mobileNo);
			patient.setGender(gender);
			patient.setClinicIdList(clinicIdList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patient;
	}

	public int getSize(String clinicFileName, String arrayName) {
		try {
			JSONParser parser = new JSONParser();
			Object object = parser.parse(new FileReader(clinicFileName));
			JSONObject doctObj = (JSONObject) object;
			JSONArray doctorsArray = (JSONArray) doctObj.get(arrayName);
			return doctorsArray.size();
		} catch (Exception e) {
			System.out.println(e);
		}
		return 1;
	}
}
