package com.clinic.model;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.clinic.dto.Clinic;
import com.clinic.dto.Doctor;
import com.clinic.dto.Patient;

public class ClinicUtilities {

	public ArrayList<Clinic> readClinicJson(String clinicFileName) {
		ArrayList<Clinic> clinicObjList = new ArrayList<Clinic>();
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(clinicFileName));
			JSONObject doctObj = (JSONObject) object;
			JSONArray doctorsArray = (JSONArray) doctObj.get("Clinic");
			for (int i = 0; i < getSize(clinicFileName, "Clinic"); i++) {
				Clinic clinic = new Clinic();
				JSONObject doctorJson = (JSONObject) doctorsArray.get(i);
				String clinicName = (String) doctorJson.get("ClinicName");

				int clinicID = Integer.parseInt(String.valueOf(doctorJson.get("ClinicID")));

				clinic.setClinicId(clinicID);
				clinic.setClinicName(clinicName);
				clinicObjList.add(clinic);
			}
		} catch (Exception e) {
			System.out.println("Exception");
		}
		return clinicObjList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Doctor> readDrJson(String clinicFileName) {
		ArrayList<Doctor> doctorObjList = new ArrayList<Doctor>();

		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(clinicFileName));
			JSONObject doctObj = (JSONObject) object;
			JSONArray doctorsArray = (JSONArray) doctObj.get("Doctors");
			for (int i = 0; i < getSize(clinicFileName, "Doctors"); i++) {
				Doctor doctor = new Doctor();
				JSONObject doctorJson = (JSONObject) doctorsArray.get(i);
				String drName = (String) doctorJson.get("Name");

				int drID = Integer.parseInt(String.valueOf(doctorJson.get("DoctID")));

				String specialization = (String) doctorJson.get("Specialization");

				JSONArray clinics = (JSONArray) doctorJson.get("Clinic");
				ArrayList<Integer> clinicIdList = new ArrayList<Integer>();
				ArrayList<String> availibilityList = new ArrayList<String>();
				for (int j = 0; j < clinics.size(); j++) {
					JSONObject clinicsObject = (JSONObject) clinics.get(j);
					clinicIdList.add(Integer.parseInt((String) clinicsObject.get("ClinicID")));
					availibilityList.add((String) clinicsObject.get("Availability"));

				}

				doctor.setDrID(drID);
				doctor.setDrName(drName);
				doctor.setSpecialization(specialization);
				doctor.setAvalibilityList(availibilityList);
				doctor.setClinicIdList(clinicIdList);
				doctorObjList.add(doctor);
			}
		} catch (Exception e) {
			System.out.println("Exception");
		}

		return doctorObjList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Patient> readPatientJson(String clinicFileName) {
		ArrayList<Patient> patientObjList = new ArrayList<Patient>();

		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(clinicFileName));
			JSONObject doctObj = (JSONObject) object;
			JSONArray doctorsArray = (JSONArray) doctObj.get("Patients");
			for (int i = 0; i < getSize(clinicFileName, "Patients"); i++) {
				Patient patient = new Patient();
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
				patientObjList.add(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientObjList;
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
	
	public String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(new Date());

		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(currentDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.DATE, 1);
		String tommorowDate = dateFormat.format(calendar.getTime());
		return tommorowDate;
	}
}
