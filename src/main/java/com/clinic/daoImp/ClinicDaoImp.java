package com.clinic.daoImp;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import com.clinic.dao.ClinicDao;
import com.clinic.dto.Appointment;
import com.clinic.dto.Clinic;
import com.clinic.dto.Doctor;
import com.clinic.dto.Patient;

public class ClinicDaoImp implements ClinicDao {

	Connection connection;

	public ClinicDaoImp() {
		MysqlDataSource ds = new MysqlDataSource();
		try {
			ds.setServerName("localhost");
			ds.setPortNumber(3306);
			ds.setDatabaseName("clinic_management");
			ds.setUser("root");
			ds.setPassword("root");
			connection = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createClinicTable() {
		try {
			PreparedStatement createClinic = connection.prepareStatement("CREATE TABLE clinic (clinic_id INT NOT NULL,"
					+ "clinic_name varchar(100) DEFAULT NULL, " + "PRIMARY KEY (clinic_id))");
			createClinic.executeUpdate();
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Present! ");
		} catch (SQLException e) {
			System.out.println("Error in creating table");
		}

	}

	public void createDoctorTable() {
		try {
			PreparedStatement createDoctor = connection
					.prepareStatement("CREATE TABLE doctor(dr_id INT NOT NULL,dr_name VARCHAR(100) NULL,"
							+ "specialization VARCHAR(100) NULL," + "PRIMARY KEY (dr_id))");
			createDoctor.executeUpdate();
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Present! ");
		} catch (SQLException e) {
			System.out.println("Error in creating table");
		}
	}

	public void createPatientTable() {

		try {
			PreparedStatement createPatient = connection.prepareStatement(
					"CREATE TABLE patient (patient_id INT NOT NULL," + "patient_name VARCHAR(100) NULL,"
							+ "gender VARCHAR(100) NULL," + "mobile_no VARCHAR(100) NULL,PRIMARY KEY (patient_id))");
			createPatient.executeUpdate();
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Present! ");
		} catch (SQLException e) {
			System.out.println("Error in creating table");
		}

	}

	public void createDrClinicTable() {

		try {
			PreparedStatement create_dr_clinic = connection.prepareStatement(
					"CREATE TABLE dr_clinic (dr_id INT NULL,clinic_id INT NULL,availability VARCHAR(100) NULL)");
			create_dr_clinic.executeUpdate();

		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Present! ");
		} catch (SQLException e) {
			System.out.println("Error in creating table");
		}
	}

	public void createPatientClinicTable() {
		/*
		 * try { connection = Database.getConnection(); } catch (Exception e) {
		 * System.out.println("Exception in opening connenction"); }
		 */
		try {
			PreparedStatement create_patient_clinic = connection.prepareStatement(
					"CREATE TABLE patient_clinic(patient_id INT NOT NULL," + "clinic_id INT DEFAULT NULL,"
							+ "CONSTRAINT clinic_id2 FOREIGN KEY (clinic_id) REFERENCES clinic (clinic_id),"
							+ "CONSTRAINT patient_id2 FOREIGN KEY (patient_id) REFERENCES patient (patient_id))");
			create_patient_clinic.executeUpdate();
		} catch (MySQLSyntaxErrorException e) {
			System.out.println("Table Present! ");
		} catch (SQLException e) {
			System.out.println("Error in creating table");
		}

	}

	public void insertDoctorDetails(Doctor doctor) {

		if (!isExist("doctor")) {
			createDoctorTable();
		}

		try {

			PreparedStatement insert = connection
					.prepareStatement("insert into doctor(dr_id, dr_name, specialization) values(?,?,?)");
			insert.setInt(1, doctor.getDrID());
			insert.setString(2, doctor.getDrName());
			insert.setString(3, doctor.getSpecialization());
			insert.executeUpdate();
			System.out.println("Doctor details are stored");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*****************************************************");

	}

	public void insertClinicDetails(Clinic clinic) {
		System.out.println("Inserting");

		if (!isExist("clinic")) {
			createClinicTable();
		}

		try {

			PreparedStatement insert = connection
					.prepareStatement("insert into clinic(clinic_id,clinic_name) values(?,?)");
			insert.setInt(1, clinic.getClinicId());
			insert.setString(2, clinic.getClinicName());
			insert.executeUpdate();
			System.out.println("Clinic details are stored");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*****************************************************");
	}

	public void insertPatientDetails(Patient patient) {

		if (!isExist("patients")) {
			createPatientTable();
		}

		try {
			PreparedStatement insertPatient = connection
					.prepareStatement("insert into patient(patient_id,patient_name,gender,mobile_no) values(?,?,?,?)");
			insertPatient.setInt(1, patient.getPatientId());
			insertPatient.setString(2, patient.getPatientName());
			insertPatient.setString(3, patient.getGender());
			insertPatient.setString(4, patient.getMobileNo());
			insertPatient.executeUpdate();
			System.out.println("Patients details are stored");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*****************************************************");
	}

	public void insertDrClinicDetails(Doctor doctor) {

		if (!isExist("dr_clinic")) {
			createDrClinicTable();
		}

		for (int j = 0; j < doctor.getClinicIdList().size(); j++) {

			try {
				PreparedStatement insert = connection
						.prepareStatement("insert into dr_clinic(dr_id, clinic_id,availability) values(?,?,?)");
				insert.setInt(1, doctor.getDrID());
				insert.setInt(2, Integer.parseInt(String.valueOf(doctor.getClinicIdList().get(j))));
				insert.setString(3, doctor.getAvalibilityList().get(j));
				insert.executeUpdate();
				System.out.println("Doctor clinic mapping data is stored");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void insertPatientClinicDetails(Patient patient) {

		if (!isExist("patients_clinic")) {
			createPatientClinicTable();
		}

		for (int i = 0; i < patient.getClinicIdList().size(); i++) {
			try {
				PreparedStatement insertPatientClinic = connection
						.prepareStatement("insert into patient_clinic(patient_id,clinic_id) values(?,?)");
				insertPatientClinic.setInt(1, patient.getPatientId());
				insertPatientClinic.setInt(2, Integer.parseInt(String.valueOf(patient.getClinicIdList().get(i))));
				insertPatientClinic.executeUpdate();
				System.out.println("Clinic paient data is stored");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean isExist(String tableName) {
		try {
			DatabaseMetaData metadata = null;
			metadata = connection.getMetaData();

			ResultSet resultSet;
			resultSet = metadata.getTables(null, null, tableName, null);
			if (resultSet.next()) {
				System.out.println("Exist");
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void dropTable() {
		try {
			PreparedStatement drop1 = connection.prepareStatement("DROP TABLE dr_clinic");
			PreparedStatement drop11 = connection.prepareStatement("DROP TABLE patient_clinic");
			PreparedStatement drop2 = connection.prepareStatement("DROP TABLE doctor");
			PreparedStatement drop4 = connection.prepareStatement("DROP TABLE patient");
			PreparedStatement drop3 = connection.prepareStatement("DROP TABLE clinic");
			drop1.executeUpdate();
			drop11.executeUpdate();
			drop2.executeUpdate();
			drop3.executeUpdate();
			drop4.executeUpdate();

		} catch (Exception e) {
			System.out.println("Exception while droping");
		}
	}

	public ArrayList<Doctor> getDoctorDetailsByClinicId(int clinicId) {
		ArrayList<Doctor> clinicIdList = new ArrayList<Doctor>();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"select * from doctor natural join dr_clinic natural join clinic where clinic_id=?");
			ps.setInt(1, clinicId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Clinic clinic = new Clinic();
				Doctor doctor = new Doctor();
				Patient patient = new Patient();
				clinic.setClinicId(rs.getInt("clinic_id"));
				clinic.setClinicName(rs.getString("clinic_name"));
				doctor.setDrID(rs.getInt("dr_id"));
				doctor.setDrName(rs.getString("dr_name"));
				doctor.setSpecialization(rs.getString("specialization"));
				doctor.setAvailability(rs.getString("availability" + ""));
				doctor.setClinic(clinic);
				doctor.setPatient(patient);
				clinicIdList.add(doctor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clinicIdList;

	}

	public ArrayList<Clinic> getClinicDetailsByPatientId(int patientId) {
		ArrayList<Clinic> clinicList = new ArrayList<Clinic>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from clinic natural join patient_clinic where patient_id=?");
			ps.setInt(1, patientId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Clinic clinic = new Clinic();
				clinic.setClinicId(rs.getInt("clinic_id"));
				clinic.setClinicName(rs.getString("clinic_name"));
				clinicList.add(clinic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clinicList;
	}

	public ArrayList<Doctor> getDoctorByAvailability(String availability, int clinicId) {
		ArrayList<Doctor> doctorList = new ArrayList<Doctor>();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"select * from doctor natural join dr_clinic natural join clinic where availability=? and clinic_id=?");
			ps.setString(1, availability);
			ps.setInt(2, clinicId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Doctor doctor = new Doctor();
				Clinic clinic = new Clinic();

				doctor.setDrID(rs.getInt("dr_id"));
				doctor.setDrName(rs.getString("dr_name"));
				doctor.setSpecialization(rs.getString("specialization"));
				doctor.setAvailability(rs.getString("availability"));

				clinic.setClinicId(rs.getInt("clinic_id"));
				clinic.setClinicName(rs.getString("clinic_name"));
				doctor.setClinic(clinic);

				doctorList.add(doctor);
			}
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctorList;
	}

	public Doctor getDoctorDetailsByID(int drId, String availability, int clinicId) {
		Doctor doctor = null;
		Clinic clinic = null;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select * from doctor natural join dr_clinic natural join clinic where availability=? and clinic_id=? and dr_id=?");
			ps.setString(1, availability);
			ps.setInt(2, clinicId);
			ps.setInt(3, drId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				doctor = new Doctor();
				clinic = new Clinic();

				clinic.setClinicId(rs.getInt("clinic_id"));
				clinic.setClinicName(rs.getString("clinic_name"));

				doctor.setDrID(rs.getInt("dr_id"));
				doctor.setDrName(rs.getString("dr_name"));
				doctor.setSpecialization(rs.getString("specialization"));
				doctor.setClinic(clinic);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctor;
	}

	public void createAppointmentTable() {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"CREATE TABLE appointment (clinic_id INT NULL,dr_id INT NULL,patient_id INT NULL,time INT NULL,date VARCHAR(45) NULL,"
							+ "CONSTRAINT clinic_idA FOREIGN KEY (clinic_id) REFERENCES clinic (`clinic_id`),"
							+ "CONSTRAINT dr_idA FOREIGN KEY (dr_id) REFERENCES doctor (dr_id),"
							+ "CONSTRAINT patient_idA FOREIGN KEY (patient_id) REFERENCES patient (patient_id),PRIMARY KEY (no))");
			ps.executeUpdate();
		} catch (MySQLSyntaxErrorException e) {
			e.printStackTrace();
			System.out.println("Table Present! ");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error in creating table");
		}
	}

	public int isAppointmentAvailable(int drId,String currentDate) {
		int noOfAppointment = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT count(dr_id) as count FROM appointment where dr_id=? and date=?");
			ps.setInt(1, drId);
			ps.setString(2, currentDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				noOfAppointment = rs.getInt(1);
			}

			System.out.println("Size:" + noOfAppointment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noOfAppointment;
	}

	public boolean isAppointmentAlreadyTaken(int drId, int patientId,String currentDate) {
		int noOfAppointment = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT count(dr_id) as count FROM appointment where dr_id=? and patient_id=? and date=?");
			ps.setInt(1, drId);
			ps.setInt(2, patientId);
			ps.setString(3, currentDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				noOfAppointment = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (noOfAppointment > 0)
			return true;
		else
			return false;
	}

	public int checkTimeForAppointment(int drId) {
		int time = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT time FROM appointment where dr_id=? ORDER BY time DESC LIMIT 1");
			ps.setInt(1, drId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				time = rs.getInt(1);
			}
			if (time == 0)
				time = 8;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public void takeAppointment(int drId, int clinicId, int patientId, int time, String date) {

		if (!isExist("appointment")) {
			createAppointmentTable();
		}

		try {
			PreparedStatement insert = connection.prepareStatement(
					"insert into appointment(clinic_id,dr_id,patient_id,time,date) values(?,?,?,?,?)");
			insert.setInt(1, clinicId);
			insert.setInt(2, drId);
			insert.setInt(3, patientId);
			insert.setInt(4, time);
			insert.setString(5, date);
			insert.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Doctor showAppointment(int drId, int patientId) {
		Doctor doctor = new Doctor();
		Appointment appointment = new Appointment();
		Clinic clinic = new Clinic();
		Patient patient = new Patient();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM doctor natural join appointment natural join clinic where dr_id=? and patient_id=?");
			ps.setInt(1, drId);
			ps.setInt(2, patientId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				doctor.setDrID(rs.getInt("dr_id"));
				doctor.setDrName(rs.getString("dr_name"));
				doctor.setSpecialization(rs.getString("specialization"));
				appointment.setDate(rs.getString("date"));
				appointment.setTime(rs.getInt("time"));
				clinic.setClinicId(rs.getInt("clinic_id"));
				clinic.setClinicName(rs.getString("clinic_name"));
				patient.setPatientId(rs.getInt("patient_id"));
				doctor.setClinic(clinic);
				doctor.setAppointment(appointment);
				doctor.setPatient(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doctor;
	}
	
	public String getNextDayForAppointment(int drId) {
		String date=null;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT date FROM appointment where dr_id=? ORDER BY time DESC LIMIT 1");
			ps.setInt(1, drId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				date=rs.getString("date");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}

}
