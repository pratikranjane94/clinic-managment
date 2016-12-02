package com.clinic.dto;

import java.util.ArrayList;
import java.util.Scanner;

import com.clinic.dao.ClinicDao;
import com.clinic.daoImp.ClinicDaoImp;
import com.clinic.model.ClinicUtilities;

public class ClinicManagement {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ClinicUtilities clinicUtilities = new ClinicUtilities();
		ClinicDao clinicDaoImp = new ClinicDaoImp();

		ArrayList<Clinic> clinicList;
		ArrayList<Doctor> drListByClinicID;
		ArrayList<Doctor> drListByAvailabilty;
		
		Doctor doctorModel=new Doctor();
		
		String availability=null;
		int clinicId=0;

		while (true) {
			System.out.println("----------------Welcome To Clinic Management----------------");
			System.out.println("Select Your what you want to do:");
			System.out.println("1.Add Clinic database");
			System.out.println("2.Take Appoinment");
			System.out.println("Exit");

			System.out.println("Enter Your Choice here ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				// call add Doctor function from Add all details class
				System.out.println("Enter doctor's details file name");
				// String clinicFileName = scanner.next();
				String clinicFileName = "clinic.json";

				ArrayList<Clinic> clinicObjList = new ArrayList<Clinic>();
				ArrayList<Doctor> doctorObjList = new ArrayList<Doctor>();
				ArrayList<Patient> patientObjList = new ArrayList<Patient>();

				clinicObjList = clinicUtilities.readClinicJson(clinicFileName);

				for (Clinic clinic : clinicObjList) {
					clinicDaoImp.insertClinicDetails(clinic);
				}
				doctorObjList = clinicUtilities.readDrJson(clinicFileName);
				for (Doctor doctor : doctorObjList) {
					clinicDaoImp.insertDoctorDetails(doctor);
				}
				patientObjList = clinicUtilities.readPatientJson(clinicFileName);
				for (Patient patient : patientObjList) {
					clinicDaoImp.insertPatientDetails(patient);
				}
				for (Patient patient : patientObjList) {
					clinicDaoImp.insertPatientClinicDetails(patient);
				}
				for (Doctor doctor : doctorObjList) {
					clinicDaoImp.insertDrClinicDetails(doctor);
				}

				break;
			}
			case 2: {
				// call add patient function to add all patient
				System.out.println("Insert Your patient id:");
				int patientId = scanner.nextInt();

				clinicList = clinicDaoImp.getClinicDetailsByPatientId(patientId);
				for (Clinic clinic : clinicList) {
					System.out.println("Clinic Id:" + clinic.getClinicId());
					System.out.println("Clinic Name:" + clinic.getClinicName());
				}

				System.out.println("Enter clinic id:");
				clinicId = scanner.nextInt();

				// clinicDaoImp.dropTable();
				drListByClinicID = clinicDaoImp.getDoctorDetailsByClinicId(clinicId);
				for (Doctor doctor : drListByClinicID) {
					System.out.println("Dr ID:" + doctor.getDrID());
					System.out.println("Dr Name:" + doctor.getDrName());
					System.out.println("Dr Specialization:" + doctor.getSpecialization());
					System.out.println("Dr Availabilty:" + doctor.getAvailability());
					System.out.println("Clinic Name" + doctor.getClinic().getClinicName());
					System.out.println("****************************************");
				}

				System.out.println("What time you want appointment Morning or Evening");
				availability = scanner.next();
				drListByAvailabilty = clinicDaoImp.getDoctorByAvailability(availability, clinicId);
				try {
					if (!drListByAvailabilty.equals(null)) {
						for (Doctor doctor : drListByAvailabilty) {
							System.out.println("Dr ID:" + doctor.getDrID());
							System.out.println("Dr Name:" + doctor.getDrName());
							System.out.println("Dr Specialization:" + doctor.getSpecialization());
							System.out.println("Dr Availabilty:" + doctor.getAvailability());
							System.out.println("Clinic Name" + doctor.getClinic().getClinicName());
							System.out.println("****************************************");
						}
					} else {
						System.out.println("No doctors are present for " + availability);
					}
				} catch (NullPointerException e) {
					System.out.println("ignore");
				}
				break;
			}
			case 3: {
				System.out.println("Enter Doctor's id whose appointment you want to take");
				int drId=scanner.nextInt();
				doctorModel=clinicDaoImp.getDoctorDetailsByID(drId, availability, clinicId);
				
				break;
			}
			case 4: {
				System.out.println("Exit");
				break;
			}
			default: {
				System.out.println("Wrong Choice!!");
				break;
			}
			}
		}
	}
}
