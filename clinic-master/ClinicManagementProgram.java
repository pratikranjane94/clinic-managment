/*
*@file_Name: ClinicManagementProgram.java
*@Author: Shwetali
*@Date: 07-09-2016
*@purpose:  Clinic Management program for Patients and Doctors and storing data inside json file.
*/

package com.bridgelabz.clinic;
import com.bridgelabz.clinic.Doctor;
import com.bridgelabz.clinic.Patient;
import com.bridgelabz.clinic.ArrangeAppointment;
import com.bridgelabz.clinic.JSONDoctorsData;
import com.bridgelabz.clinic.JSONPatientsData;
import com.bridgelabz.clinic.Utility;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ClinicManagementProgram{
	public static void main(String[] args){
		Utility u = new Utility();
		int patientId = 1;
		ArrangeAppointment appointment = new ArrangeAppointment();
		JSONDoctorsData doctorData = new JSONDoctorsData();
		JSONPatientsData patientData = new JSONPatientsData();
		
		ArrayList<Doctor> doctors = new ArrayList();
		ArrayList<Patient> patients = new ArrayList();
		
		//reading doctors data from json file
		for(int i=0;i<doctorData.getList();i++){
			Doctor doctor = doctorData.readDoctFile(i);
			doctors.add(doctor);
			
		}
		
		//taking the appointments for patients inside file
		for(int i=0;i<patientData.getList();i++){
			Patient patient = patientData.readPatientFile(i);
			appointment.takeAppoinment(patient);
			patients.add(patient);
			patientId = i+1;
		}
		
		//Updating file
		for(int i=0;i<patients.size();i++){
			patientData.writeIntoFile(patients.get(i));
		}

		//Display Options to perform operations.
		boolean check = true;
		while(check){
			System.out.println("\n\nWelcome, Please choose Your Option");
			System.out.println("1.Doctors List\n2.Patients List\n3.Search Doctor\n4.Search Patient");
			System.out.println("5.Take Appoinment\n6.Show All Appointments\n7.Popular Doctor \n8.Exit");
			int choice = u.inputInteger();			
			switch(choice){
				case 1:{
					System.out.println("List Of Available Doctors:");
					new Doctor().listOfDoctors(doctors);
					break;
				}
				case 2:{
					System.out.println("List of Patients in Clinic:");
					new Patient().listOfPatients(patients);
					break;
				}
				
				case 3:{
					System.out.println("Search Doctor By:");
					new Doctor().searchDoctor(doctors);
					break;
				}
				case 4:{
					System.out.println("Search Patient By:");
					new Patient().searchPatient(patients);
					break;
				}
				case 5:{
					//taking patient's info and appointment
					System.out.print("Please Enter Patient's Name: ");
					String name = u.inputString();
					System.out.print("Please Enter Phone number: ");
					String number = u.inputString();
					boolean checkNumber = Pattern.matches("[789]{1}\\d{9}",number);
					if(!checkNumber){
						System.out.println("Number is incorrect");
						break;
						
					}
					patientId++;
					System.out.print("Please Enter Age: ");
					int age = u.inputInteger();
					System.out.print("Please enter Doctors id: ");
					int docId = u.inputInteger();
					Patient newPatient = new Patient(name,patientId,number,age,docId);
					patients.add(newPatient);
					int size = patients.size();
					appointment.takeAppoinment(newPatient);
					patientData.writeIntoFile(patients.get(size-1));//data write into file
					break;
				}
				case 6:{
					appointment.showAppointments();
					break;
				}
		
				case 7:{
					new Doctor().popularDoctor(doctors);
					break;
				}
				case 8:{
					check = false;
					break;
				}
				default:{
					System.out.println("Wrong Choice!!");
					break;
				}
			}//end of switch
		}//end of while
		
	}
}