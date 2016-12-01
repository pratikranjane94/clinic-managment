import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import com.clinic.daoImp.ClinicDaoImp;
import com.clinic.dto.Clinic;
import com.clinic.dto.Doctor;

public class Abc {
	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
		/*
		 * ClinicUtilities clinicUtilities=new ClinicUtilities(); String
		 * clinicFileName="clinic.json"; for (int i = 0; i <
		 * clinicUtilities.getSize(clinicFileName,"Patients"); i++) { Patient
		 * patient=clinicUtilities.readPatientJson(clinicFileName, i);
		 * System.out.println("paient"+patient.toString()); }
		 */
		
			ClinicDaoImp clinicDaoImp = new ClinicDaoImp();
			Scanner scanner=new Scanner(System.in);
			
			System.out.println("Insert Your patient id:");
			int patientId=scanner.nextInt();
			
			ArrayList<Clinic> clinicList=clinicDaoImp.getClinicDetailsByPatientId(patientId);
			for (Clinic clinic : clinicList) {
				System.out.println("Clinic Id:"+clinic.getClinicId());
				System.out.println("Clinic Name:"+clinic.getClinicName());
			}
			
			System.out.println("Enter clinic id:");
			int clinicId=scanner.nextInt();
			
			//clinicDaoImp.dropTable();	
			ArrayList<Doctor> drList = clinicDaoImp.getDoctorDetailsByClinicId(clinicId);
			for (Doctor doctor : drList) {
				System.out.println("dr name:"+doctor.getDrName());
				System.out.println("dr specialization:"+doctor.getSpecialization());
				System.out.println("dr availabilty:"+doctor.getAvailability());
				System.out.println("clinic name"+doctor.getClinic().getClinicName());
				System.out.println("****************************************");
			}
			int clinicId=scanner.nextInt();
			clinicDaoImp.getDrByClinicId(clinicId);
		
	}
}
