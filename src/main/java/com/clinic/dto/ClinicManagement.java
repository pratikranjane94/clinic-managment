package com.clinic.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

		Doctor doctorModel = new Doctor();

		String availability = null;
		int clinicId = 0;
		int patientId = 0;
		int time = 0;

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
				patientId = scanner.nextInt();

				// ----GETTING LIST OF CLINIC DETAILS BY GIVING PATIENT ID-----
				clinicList = clinicDaoImp.getClinicDetailsByPatientId(patientId);

				if (clinicList.isEmpty() || clinicList.equals(null)) {
					System.out.println("No data found for Patient Id:" + patientId);
					break;
				}

				// displaying list of clinics retrieved by patient id
				ArrayList<Integer> clinicIdList = new ArrayList<Integer>();
				for (Clinic clinic : clinicList) {
					// adding all clinic id in list for validation while taking
					// input
					clinicIdList.add(clinic.getClinicId());

					System.out.println("Clinic Id:" + clinic.getClinicId());
					System.out.println("Clinic Name:" + clinic.getClinicName());
				}

				// ---------END OF GETTING CLINIC DETAILS BY PATIENT ID------

				System.out.println("Enter clinic id:");
				clinicId = scanner.nextInt();
				while (!clinicIdList.contains(clinicId)) {
					System.out.println("You have entered wrong Clinic Id, Please Enter again:");
					clinicId = scanner.nextInt();
				}

				// ------GETTING LIST OF DOCTORS BY CLINIC ID------
				drListByClinicID = clinicDaoImp.getDoctorDetailsByClinicId(clinicId);
				if (!clinicIdList.equals(null)) {
					for (Doctor doctor : drListByClinicID) {
						System.out.println("Dr ID:" + doctor.getDrID());
						System.out.println("Dr Name:" + doctor.getDrName());
						System.out.println("Dr Specialization:" + doctor.getSpecialization());
						System.out.println("Dr Availabilty:" + doctor.getAvailability());
						System.out.println("Clinic Name" + doctor.getClinic().getClinicName());
						System.out.println("****************************************");
					}
				} else {
					System.out.println("Doctor details are not present");
				}

				// ------END OF GETTING DOCTORS BY CLINIC ID-----

				System.out.println("What time you want appointment Morning or Evening");
				availability = scanner.next();
				drListByAvailabilty = clinicDaoImp.getDoctorByAvailability(availability, clinicId);
				System.out.println("Checking:" + drListByAvailabilty.toString());
				try {
					if (drListByAvailabilty.equals(null) || drListByAvailabilty.isEmpty()) {
						System.out.println("No doctors are present for " + availability);
						break;
					} else {
						for (Doctor doctor : drListByAvailabilty) {
							System.out.println("Dr ID:" + doctor.getDrID());
							System.out.println("Dr Name:" + doctor.getDrName());
							System.out.println("Dr Specialization:" + doctor.getSpecialization());
							System.out.println("Dr Availabilty:" + doctor.getAvailability());
							System.out.println("Clinic Name" + doctor.getClinic().getClinicName());
							System.out.println("****************************************");
						}
					}
				} catch (NullPointerException e) {
					System.out.println("ignore");
				}

				System.out.println("Enter Doctor's id whose appointment you want to take");
				int drId = scanner.nextInt();
				doctorModel = clinicDaoImp.getDoctorDetailsByID(drId, availability, clinicId);

				//checking whether appointment is already taken 
				if (clinicDaoImp.isAppointmentAlreadyTaken(drId, patientId)) {
					Doctor doctor = new Doctor();
					System.out.println("You already have taken an appointment");
					
					//showing appointment details
					doctor = clinicDaoImp.showAppointment(drId, patientId);
					
					System.out.println(
							"Clinic Id\t Clinic Name\t Patient Id\t Dr Id\t Dr Name\t specialization\t Time\t Date");
					System.out.println(doctor.getClinic().getClinicId() + "\t" + doctor.getClinic().getClinicName()
							+ "\t" + doctor.getPatient().getPatientId() + "\t" + doctor.getDrID() + "\t"
							+ doctor.getDrName() + "\t" + doctor.getSpecialization() + "\t"
							+ doctor.getAppointment().getTime() + "\t" + doctor.getAppointment().getDate());
					break;
				}

				//checking appointment is available or not
				int noOfAppointments = clinicDaoImp.isAppointmentAvailable(drId);
				if (noOfAppointments == 2) {
					System.out.println("All appointments are full")	;
					String date = clinicDaoImp.getNextDayForAppointment(drId);
					System.out.println("Yo can get appointment for : "+date);
					System.out.println("Do you want appointment for : "+date);
				} else {
					time = clinicDaoImp.checkTimeForAppointment(drId);
					time = time + 1;
					System.out.println("Time Available for Appointment is : " + time);
				}
				System.out.println("Do you want to take appointment press YES or NO");
				String ans = scanner.next();
				if (ans.equalsIgnoreCase("no"))
					break;
				String date = clinicUtilities.getDate();
				clinicDaoImp.takeAppointment(drId, clinicId, patientId, time, date);
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
