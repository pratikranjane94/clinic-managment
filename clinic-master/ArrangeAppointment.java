/*
*@file_Name: ArrangeAppointment.java
*@Author: Shwetali
*@Date: 07-09-2016
*@purpose:  Arranging appointments of patients for clinic management.
*/

package com.bridgelabz.clinic;
import com.bridgelabz.clinic.UnOrderedNodeCreation;
import com.bridgelabz.clinic.Patient;

public class ArrangeAppointment{
	String[] docsNames = {"Dr. Joshi","Dr. Patel","Dr. Naved","Dr. Desai","Dr. Jeet"};
	int id;
	UnOrderedNodeCreation[] table = new UnOrderedNodeCreation[5];
	UnOrderedNodeCreation head,temp,entry;

	//table of Doctors
	public void ArrangeAppointment(){
		for (int i = 0; i <5; i++)
			table[i] = null;
	}

	//Arranging the appointments according to doctors availability
	public void takeAppoinment(Patient patient){
		try{
			this.id = patient.doctId;
			int num = id-1;
			int count = 1;
			if(table[num] == null){
				table[num] = new UnOrderedNodeCreation();
				table[num].setData(docsNames[num]);
				head = table[num];
				//System.out.println(table[num].getData());
			}
			head = table[num];
			temp = head;

			//Checking for total appointments
			while(temp.getNext() != null){
				temp = temp.getNext();
				count++;
			}	

			//if appointments for perticular doctor are 5 then no further appointments
			if(count<=5){
				head = table[num];
				temp = head;
				entry = new UnOrderedNodeCreation();
				entry.setData(patient);
				System.out.println("Appointment successfull for "+docsNames[num]);
				while(temp.getNext() != null){
					temp = temp.getNext();
				}
					temp.setNext(entry);
			}

			else{
				System.out.println("Sorry Todays Appointment for "+docsNames[num]+" is not available ");
			}
				//new Patient().printPatient((Patient)temp.getNext().getData());
		}
		catch(ArrayIndexOutOfBoundsException ae){
			System.out.println("Doctor's ID is Wrong!!");
		}

	}//end of takeApppointment

	//showing total appointments in clinic
	public void showAppointments(){
		for(int i=0;i<5;i++){
			System.out.print("\n"+docsNames[i]+": ");
			temp = table[i];
			int total = 0;
			if(temp == null)
				System.out.println(" \nTotal Patients: "+total);
			
			else{
				temp = temp.getNext();
				while(temp != null){
						total++;
						System.out.print(((Patient)temp.getData()).name+", ");
						temp = temp.getNext();
				}
				System.out.println("\nTotal patients: "+total);
			}
		}
	}//end of showTable

}