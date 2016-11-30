/*
*@file_Name: JSONDoctorsData.java
*@Author: Shwetali
*@Date: 09-09-2016
*@purpose:  Reading Doctors data form json file.
*/

package com.bridgelabz.clinic;

import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.bridgelabz.clinic.Doctor;

public class JSONDoctorsData {
	JSONParser parser = new JSONParser();
	File file = new File("DoctorsData.json");
	Doctor doctor1 = new Doctor();
	
	//getting List count
	public int getList(){
		try{
			Object object = parser.parse(new FileReader(file));
			JSONObject doctObj = (JSONObject) object;//json object created
			JSONArray doctorsArray = (JSONArray) doctObj.get("Doctors");
			return doctorsArray.size();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return 1;
	}
	
	//reading data from file using parsing method
	public Doctor readDoctFile(int y){
		try{
			Object object = parser.parse(new FileReader(file));
			JSONObject doctObj = (JSONObject) object;//json object created
			JSONArray doctorsArray = (JSONArray) doctObj.get("Doctors");
			
				JSONObject doctor = (JSONObject) doctorsArray.get(y);
				Object obj = doctor.get("Name");
				String name = (String)obj;
				
				obj = doctor.get("ID");
				int idInfo = Integer.parseInt(String.valueOf(obj));
				
				obj = doctor.get("Specialization");
				String specialization = (String)obj;
				
				obj = doctor.get("Availability");
				String availability = (String)obj;
				
				doctor1 = new Doctor(name,idInfo,specialization,availability);
				return doctor1;
		}
		catch(Exception e){
			System.out.println("Doctors data");
		}
		return doctor1;
	}
}
