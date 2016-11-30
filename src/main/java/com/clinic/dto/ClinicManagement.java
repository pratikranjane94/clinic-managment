package com.clinic.dto;

import java.util.Scanner;

import com.clinic.dao.ClinicDao;
import com.clinic.daoImp.ClinicDaoImp;
import com.clinic.model.ClinicUtilities;

public class ClinicManagement {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ClinicUtilities clinicUtilities = new ClinicUtilities();
		ClinicDao doctorDao = new ClinicDaoImp();

		System.out.println("----------------Welcome To Clinic Management----------------");
		System.out.println("Select Your what you want to do:");
		System.out.println("1.Add Clinic database");
		System.out.println("2.Print clinic");
		System.out.println("3.Print doctor");
		System.out.println("4.Print Patients");
		System.out.println("5.Take Appoinment");
		System.out.println("Enter Your Choice here ");
		//int choice=scanner.nextInt();
		switch (1) {
		case 1: {
			// call add Doctor function from Add all details class
			System.out.println("Enter doctor's details file name");
			//String clinicFileName = scanner.next();
			String clinicFileName = "clinic.json";

			for (int i = 0; i < clinicUtilities.getSize(clinicFileName,"Clinic"); i++) {
				doctorDao.insertClinicDetails(clinicUtilities.readClinicJson(clinicFileName, i));
			}
			for (int i = 0; i < clinicUtilities.getSize(clinicFileName,"Doctors"); i++) {
				doctorDao.insertDoctorDetails(clinicUtilities.readDrJson(clinicFileName, i));
			}
			for (int i = 0; i < clinicUtilities.getSize(clinicFileName,"Patients"); i++) {
				doctorDao.insertPatientDetails(clinicUtilities.readPatientJson(clinicFileName, i));
			}

			break;
		}
		case 2: {
			// call add patient function to add all patient

			break;
		}
		case 3: {

			// displays all doctor details

			break;
		}
		case 4: {
			// displays all patient details

			break;
		}
		case 5: {
			// Schedule the appointment for the available doctors

			break;
		}
		case 6: {
			// Display the list of Appointments for the day

			break;
		}

		}
		scanner.close();
	}
}
