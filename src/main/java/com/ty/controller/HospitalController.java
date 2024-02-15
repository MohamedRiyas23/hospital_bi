package com.ty.controller;

import java.util.Scanner;

import com.ty.dao.AddressDao;
import com.ty.dao.BranchDao;
import com.ty.dao.EncounterDao;
import com.ty.dao.HospitalDao;
import com.ty.dao.ItemsDao;
import com.ty.dao.MedOrdersDao;
import com.ty.dao.PersonDao;

public class HospitalController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n1.Hospitals\n2.Branches\n3.Address\n4.Encounters\n5.Medical Orders\n6.Items\n7.Persons\n8.Exit\n\nEnter choice: ");
		int choice = sc.nextInt();
		boolean loop = true;
		while(loop) {
			switch(choice) {
			case 1:{
				HospitalDao.hospitals();
				break;
			}
			case 2:{
				BranchDao.branches();
				break;
			}
			case 3:{
				AddressDao.operationsAddress();
				break;
			}
			case 4:{
				EncounterDao.encounters();
				break;
			}
			case 5:{
				MedOrdersDao.medorders();
				break;
			}
			case 6:{
				ItemsDao.items();
				break;
			}
			case 7:{
				PersonDao.persons();
				break;
			}
			case 8:{
				loop=false;
				break;
			}
			default:{
				System.out.println("Invalid choice!!");
			}
			}
		}
		
	}

}
