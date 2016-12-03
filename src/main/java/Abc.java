import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.clinic.daoImp.ClinicDaoImp;

public class Abc {
	public static void main(String args[]) throws ParseException {

		// ClinicUtilities clinicUtilities=new ClinicUtilities();

		Scanner scanner = new Scanner(System.in);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(dateFormat.format(new Date()));

		String currentDate = dateFormat.format(new Date());

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(currentDate));
		c.add(Calendar.DATE, 1);

		String tommorowDate = dateFormat.format(c.getTime());

		scanner.close();
	}
}
