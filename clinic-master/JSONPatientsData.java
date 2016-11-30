/*
*@file_Name: JSONPatientsData.java
*@Author: Shwetali
*@Date: 09-09-2016
*@purpose:  Reading and Writing Patients data to json file and updating file.
*/ 

package com.bridgelabz.clinic;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.bridgelabz.clinic.Patient;

public class JSONPatientsData {
	JSONParser parser = new JSONParser();
	File file = new File("PatientsData.json");
	Patient patient1 = new Patient();
	JSONObject patients = new JSONObject();
	JSONArray patientsArray = new JSONArray();
	int id=1;
	
	//getting List count
	public int getList(){
		try{
			Object object = parser.parse(new FileReader(file));
			JSONObject patientObj = (JSONObject) object;//json object created
			JSONArray patientsArray = (JSONArray) patientObj.get("Patients");
			return patientsArray.size();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return 1;
	}
	
	//reading data from file using parsing method
	public Patient readPatientFile(int y){
		try{
			Object object = parser.parse(new FileReader(file));
			JSONObject patientObj = (JSONObject) object;//json object created
			JSONArray patientsArray = (JSONArray) patientObj.get("Patients");
				
				JSONObject patient = (JSONObject) patientsArray.get(y);
				Object obj = patient.get("Name");
				String name = (String)obj;
				
				obj = patient.get("ID");
				int idInfo = Integer.parseInt(String.valueOf(obj));
				
				obj = patient.get("MobileNo");
				String number = (String)obj;
				
				obj = patient.get("Age");
				int age = Integer.parseInt(String.valueOf(obj));
				
				obj = patient.get("DoctID");
				int doctId = Integer.parseInt(String.valueOf(obj));
						
				patient1 = new Patient(name,idInfo,number,age,doctId);
				return patient1;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return patient1;
	}
	
	//writing patients data into file
	public void writeIntoFile(Patient patientinfo){
		JSONObject patient = new JSONObject();
		patientsArray.add(patient);
		patient.put("Name",patientinfo.name);
		patient.put("ID",new Integer(id));
		patient.put("MobileNo",patientinfo.mobileNo);
		patient.put("Age",new Integer(patientinfo.age));
		patient.put("DoctID",new Integer(patientinfo.doctId));
		id++;
		patients.put("Patients",patientsArray);
		
		try {
			FileWriter writeFile = new FileWriter(file);
			writeFile.write(patients.toJSONString());
			writeFile.flush();
			writeFile.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
}
