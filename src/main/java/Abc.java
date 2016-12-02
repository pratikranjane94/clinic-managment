import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import com.clinic.daoImp.ClinicDaoImp;
import com.clinic.dto.Clinic;
import com.clinic.dto.Doctor;
import com.clinic.model.ClinicUtilities;

public class Abc {
	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {

		// ClinicUtilities clinicUtilities=new ClinicUtilities();
		ClinicDaoImp clinicDaoImp = new ClinicDaoImp();
		/*
		 * clinicDaoImp.dropTable(); System.exit(0);
		 */
		clinicDaoImp.createAppointmentTable();
		System.exit(0);

		Scanner scanner = new Scanner(System.in);

		System.out.println("Insert Your patient id:");
		int patientId = scanner.nextInt();

		ArrayList<Clinic> clinicList = clinicDaoImp.getClinicDetailsByPatientId(patientId);
		for (Clinic clinic : clinicList) {
			System.out.println("Clinic Id:" + clinic.getClinicId());
			System.out.println("Clinic Name:" + clinic.getClinicName());
		}

		System.out.println("Enter clinic id:");
		int clinicId = scanner.nextInt();

		// clinicDaoImp.dropTable();
		ArrayList<Doctor> drListByClinicID = clinicDaoImp.getDoctorDetailsByClinicId(clinicId);
		for (Doctor doctor : drListByClinicID) {
			System.out.println("Dr ID:" + doctor.getDrID());
			System.out.println("Dr Name:" + doctor.getDrName());
			System.out.println("Dr Specialization:" + doctor.getSpecialization());
			System.out.println("Dr Availabilty:" + doctor.getAvailability());
			System.out.println("Clinic Name" + doctor.getClinic().getClinicName());
			System.out.println("****************************************");
		}

		String availability = scanner.next();
		ArrayList<Doctor> drListByAvailabilty = clinicDaoImp.getDoctorByAvailability(availability, clinicId);
		if (!drListByAvailabilty.equals(null)) {
			for (Doctor doctor : drListByAvailabilty) {
				System.out.println("Dr ID:" + doctor.getDrID());
				System.out.println("Dr Name:" + doctor.getDrName());
				System.out.println("Dr Specialization:" + doctor.getSpecialization());
				System.out.println("Dr Availabilty:" + doctor.getAvailability());
				System.out.println("Clinic Name" + doctor.getClinic().getClinicName());
				System.out.println("****************************************");
			}
		}
		else{
			System.out.println("No doctors are present for "+availability);
		}

		scanner.close();
	}
}
