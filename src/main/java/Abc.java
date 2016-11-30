import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import com.clinic.daoImp.ClinicDaoImp;
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
			//clinicDaoImp.dropTable();	
			ArrayList<Doctor> clinicList = clinicDaoImp.getClinicDetailsByPatientId(1003);
			System.out.println("clinics"+clinicList.toString());
			int clinicId=scanner.nextInt();
			clinicDaoImp.getDrByClinicId(clinicId);
		
	}
}
