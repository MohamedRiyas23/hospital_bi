package com.ty.dao;



import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.BranchDto;
import com.ty.dto.HospitalDto;
public class HospitalDao {
	static EntityManagerFactory entityManagerFactory=null;
	static EntityManager entityManager=null;
	static EntityTransaction entityTransaction=null;
	static {
		entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	static Scanner sc=new Scanner(System.in);
	static HospitalDto hospitaldto=null;
	public static HospitalDto saveHospitals() {
		System.out.println("Enter the number of hospitals you want to add: ");
		int count=sc.nextInt();
		for(int i=0;i<count;i++) {
			hospitaldto=new HospitalDto();
			System.out.println("Enter hospital name: ");
			//sc.nextLine();
			String name=sc.next();
			System.out.println("Enter the hospital website: ");
			String web=sc.next();
			System.out.println("Enter the hospital location: ");
			//sc.nextLine();
			String loc=sc.next();
			
			hospitaldto.setName(name);
			hospitaldto.setHospWebsite(web);
			hospitaldto.setLocation(loc);
//			hospitaldto.setBranches(branchess);
			
			entityTransaction.begin();
			entityManager.persist(hospitaldto);
			entityTransaction.commit();
			System.out.println("************Hospital added***********");
		}
		return null;
	}
	
	public static HospitalDto findHospital() {
		Query q=entityManager.createQuery("select s from HospitalDto s");
		List<HospitalDto> hospitals=q.getResultList();
		for(HospitalDto h:hospitals) {
			System.out.println(h.getId()+"."+h.getName());
		}
		System.out.println("Enter the Hospital id that you want deatils: ");
		int hid=sc.nextInt();
		HospitalDto hospital=entityManager.find(HospitalDto.class, hid);
		System.out.println("------------Hospital details--------------");
		System.out.println("Hospital Name: "+hospital.getName());
		System.out.println("Hospital Website: "+hospital.getHospWebsite());
		System.out.println("Hospital Location: "+hospital.getLocation());
		List<BranchDto> branches=hospital.getBranches();
		int i=1;
		System.out.println("----------Branch names------------");
		System.out.println("**************************************");
		for(BranchDto b:branches) {
			
			System.out.println("Branch "+i+": "+b.getBranchName());
			i++;
		}	
		System.out.println("**************************************");
		return null;
	}
	
	public static HospitalDto updateName() {
		Query q=entityManager.createQuery("select s from HospitalDto s");
		List<HospitalDto> hospitals=q.getResultList();
		for(HospitalDto h:hospitals) {
			System.out.println(h.getId()+"."+h.getName());
		}
		System.out.println("Enter the Hospital id that you want to update name: ");
		int hid=sc.nextInt();
		HospitalDto hospital=entityManager.find(HospitalDto.class, hid);
		System.out.println("Enter the name you want to update: ");
		String name=sc.next();
		if(hospital!=null) {
			hospital.setName(name);
			entityTransaction.begin();
			entityManager.merge(hospital);
			entityTransaction.commit();
			
			System.out.println("****************Name got update****************");
		}
		else {
			System.out.println("----------------Hospital not found-----------------");
		}
		
		return null;
	}
	
	public static HospitalDto updateWeb() {
		Query q=entityManager.createQuery("select s from HospitalDto s");
		List<HospitalDto> hospitals=q.getResultList();
		for(HospitalDto h:hospitals) {
			System.out.println(h.getId()+"."+h.getName()+"->old website: "+h.getHospWebsite());
		}
		System.out.println("Enter the Hospital id that you want to update website: ");
		int hid=sc.nextInt();
		HospitalDto hospital=entityManager.find(HospitalDto.class, hid);
		System.out.println("Enter the website you want to update: ");
		String website=sc.next();
		if(hospital!=null) {
			hospital.setHospWebsite(website);
			entityTransaction.begin();
			entityManager.merge(hospital);
			entityTransaction.commit();
			
			System.out.println("****************Website got update****************");
		}
		else {
			System.out.println("----------------Hospital not found-----------------");
		}
		
		return null;
	}
	
	public static HospitalDto updateLocation() {
		Query q=entityManager.createQuery("select s from HospitalDto s");
		List<HospitalDto> hospitals=q.getResultList();
		for(HospitalDto h:hospitals) {
			System.out.println(h.getId()+"."+h.getName()+"->old location: "+h.getLocation());
		}
		System.out.println("Enter the Hospital id that you want to update location: ");
		int hid=sc.nextInt();
		HospitalDto hospital=entityManager.find(HospitalDto.class, hid);
		System.out.println("Enter the location you want to update: ");
		String loc=sc.next();
		if(hospital!=null) {
			hospital.setLocation(loc);
			entityTransaction.begin();
			entityManager.merge(hospital);
			entityTransaction.commit();
			
			System.out.println("****************Location got update****************");
		}
		else {
			System.out.println("----------------Hospital not found-----------------");
		}
		
		return null;
	}
	
	public static HospitalDto remove() {
		Query q=entityManager.createQuery("select s from HospitalDto s");
		List<HospitalDto> hospitals=q.getResultList();
		for(HospitalDto h:hospitals) {
			System.out.println(h.getId()+"."+h.getName());
		}
		System.out.println("Enter the Hospital id that you want to remove: ");
		int hid=sc.nextInt();
		HospitalDto hospital=entityManager.find(HospitalDto.class, hid);
		if(hospital!=null) {
			
			entityTransaction.begin();
			entityManager.remove(hospital);
			entityTransaction.commit();
			
			System.out.println("****************Hospital got removed****************");
		}
		else {
			System.out.println("----------------Hospital not found-----------------");
		}
		
		return null;
	}
	
	public static void update() {
		System.out.println("\n1.Name\n2.Website\n3.Location\n4.Exit\n\nEnter the choice: ");
		int choice=sc.nextInt();
		boolean loop=true;
		while(loop) {
			switch(choice) {
			case 1:{
				updateName();
				break;
				}
			case 2:{
				updateWeb();
				break;
			}
			case 3:{
				updateLocation();
				break;
			}
			case 4:{
				loop=false;
				break;
			}
			default:{
				System.out.println("---------------invalid input--------------");
				break;
			}
			}
		}
	}
	
	public static void hospitals() {
		System.out.println("\n1.Save Hospital\n2.Find Hospital\n3.update Hospital\n4.delete Hospital\n5.exit\n\nEnter the choice: ");
		int choice=sc.nextInt();
		boolean loop=true;
		while(loop) {
			switch(choice) {
			case 1:{
				saveHospitals();
				break;
			}
			case 2:{
				findHospital();
				break;
			}
			case 3:{
				update();
				break;
			}
			case 4:{
				remove();
				break;
			}
			case 5:{
				loop=false;
				break;
			}
			default:{
				System.out.println("--------------invalid input-----------------");
			}
			}
		}
	}
}
