package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.BranchDto;
import com.ty.dto.EncountersDto;
import com.ty.dto.PersonDto;

public class PersonDao {
	static EntityManagerFactory entityManagerFactory=null;
	static EntityManager entityManager=null;
	static EntityTransaction entityTransaction=null;
	static {
		entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	static Scanner sc=new Scanner(System.in);
	static PersonDto persondto=null;
	static List<EncountersDto> encountersL;
	
	
	public static PersonDto savePerson() {
		System.out.println("Enter how many persons you want to add: ");
		int countp=sc.nextInt();
		for(int i=0;i<countp;i++) {
			persondto=new PersonDto();
			System.out.println("Enter the person name: ");
			sc.nextLine();
			String name=sc.nextLine();
			System.out.println("Enter the person age: ");
			int age=sc.nextInt();
			System.out.println("Enter the phone number: ");
			long phone=sc.nextLong();
			System.out.println("Enter the person gender: ");
			String gender=sc.next();
			
			persondto.setName(name);
			persondto.setAge(age);
			persondto.setPhoneNumber(phone);
			persondto.setGender(gender);
			entityTransaction.begin();
			entityManager.persist(persondto);
			entityTransaction.commit();
			System.out.println("============person got added==============");
			System.out.println("\nIn how many encounters you want to add this person: ");
			int counte=sc.nextInt();
			for(int j=0;j<counte;j++) {
				Query q=entityManager.createQuery("select s from EncountersDto s");
				List<EncountersDto> encounters=q.getResultList();
				for(EncountersDto e:encounters) {
					BranchDto b=e.getBranchdto();
					System.out.println(e.getId()+"."+b.getBranchName());
				}
				System.out.println("Enter the encounter id that you want to add this person: ");
				int eid=sc.nextInt();
				EncountersDto encounter=entityManager.find(EncountersDto.class, eid);
				List<EncountersDto> encountersL=new ArrayList<EncountersDto>();
				if(encounter!=null) {
					encounter.setPerson(persondto);
					encountersL.add(encounter);
					entityTransaction.begin();
					entityManager.merge(encounter);
					entityTransaction.commit();
				}
				else {
					System.out.println("Encounter not found");
				}
			}
			persondto.setEncounters(encountersL);
			
			entityTransaction.begin();
			entityManager.merge(persondto);
			entityTransaction.commit();
			System.out.println("=====================Persons added to the encounters=====================");	
		}
		
		return null;
	}
	
	public static PersonDto findPerson() {
		Query q=entityManager.createQuery("select s from PersonDto s");
		List<PersonDto> persons=q.getResultList();
		for(PersonDto p:persons) {
			System.out.println(p.getId()+"."+p.getName());
		}
		System.out.println("Enter the person id that you want to get details: ");
		int pid=sc.nextInt();
		PersonDto person=entityManager.find(PersonDto.class, pid);
		System.out.println("******************Person details************************");
		System.out.println(""+person.getName());
		System.out.println(""+person.getAge());
		System.out.println(""+person.getPhoneNumber());
		System.out.println(""+person.getGender());
		System.out.println("********************************************************");
		List<EncountersDto> encounters=person.getEncounters();
		System.out.println("*********************Encounters*************************");
		for(EncountersDto e:encounters) {
			System.out.println("Encounter id: "+e.getId()+", Bed number: "+e.getBedNumber());
		}
		System.out.println("*********************************************************");
		return null;
	}
	
	public static PersonDto update() {
		Query q=entityManager.createQuery("select s from PersonDto s");
		List<PersonDto> persons=q.getResultList();
		for(PersonDto p:persons) {
			System.out.println(p.getId()+"."+p.getName());
		}
		System.out.println("Enter the person id that you want to update: ");
		int pid=sc.nextInt();
		PersonDto person=entityManager.find(PersonDto.class, pid);
		System.out.println("\n1.Name\n2.Age\n3.Phone number\n\nEnter the choice: ");
		int choice=sc.nextInt();
		if(choice==1) {
			if(person!=null) {
				System.out.println("Enter the name that you want to update: ");
				sc.nextLine();
				String name=sc.nextLine();
				person.setName(name);
				entityTransaction.begin();
				entityManager.merge(person);
				entityTransaction.commit();
				System.out.println("==============Name got updated==============");
			}
			else {
				System.out.println("===========person not found==========");
			}
		}
		else if(choice==2) {
			if(person!=null) {
				System.out.println("Enter the age that you want to update: ");
				int age=sc.nextInt();
				
				person.setAge(age);
				entityTransaction.begin();
				entityManager.merge(person);
				entityTransaction.commit();
				System.out.println("==============age got updated==============");
			}
			else {
				System.out.println("===========person not found==========");
			}
		}
		else if(choice==3) {
			if(person!=null) {
				System.out.println("Enter the phone number that you want to update: ");
				
				long ph=sc.nextLong();
				person.setPhoneNumber(ph);
				entityTransaction.begin();
				entityManager.merge(person);
				entityTransaction.commit();
				System.out.println("==============phone number got updated==============");
			}
			else {
				System.out.println("===========person not found==========");
			}
		}
		else {
			System.out.println("================Invalid choice====================");
		}
		
		
		return null;
	}
	
	public static PersonDto removePerson() {
		Query q=entityManager.createQuery("select s from PersonDto s");
		List<PersonDto> persons=q.getResultList();
		for(PersonDto p:persons) {
			System.out.println(p.getId()+"."+p.getName());
		}
		System.out.println("Enter the person id that you want to remove: ");
		int pid=sc.nextInt();
		PersonDto person=entityManager.find(PersonDto.class, pid);
		if(person!=null) {
			entityTransaction.begin();
			entityManager.remove(person);
			entityTransaction.commit();
			System.out.println("=============person got removed!==============");
		}
		else {
			System.out.println("=============person not found!==============");
		}
		
		return null;
	}
	
	public static void persons() {
		System.out.println("\n1.Save Person\n2.Find Person\n3.Update Person\n4.Remove Person\n5.Exit\n\nEnter choice: ");
		int choice=sc.nextInt();
		boolean loop=true;
		while(loop) {
			switch(choice) {
			case 1:{
				savePerson();
				break;
			}
			case 2:{
				findPerson();
				break;
			}
			case 3:{
				update();
				break;
			}
			case 4:{
				removePerson();
				break;
			}
			case 5:{
				loop=false;
				break;
			}
			default :{
				System.out.println("Invalid choice");
				break;
			}
			}
		}
	}
}
