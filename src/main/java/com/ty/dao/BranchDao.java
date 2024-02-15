package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.AddressDto;
import com.ty.dto.BranchDto;
import com.ty.dto.HospitalDto;

public class BranchDao {
	static EntityManagerFactory entityManagerFactory=null;
	static EntityManager entityManager=null;
	static EntityTransaction entityTransaction=null;
	static {
		entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	static Scanner sc=new Scanner(System.in);
	static BranchDto branchdto=null;
	static AddressDto addressdto=null;
	
	public static BranchDto saveBranch() {
		System.out.println("Enter how branches you want to add: ");
		int count=sc.nextInt();
		for(int i=0;i<count;i++) {
			branchdto=new BranchDto();
			addressdto=new AddressDto();
			
			System.out.println("Enter the branch name: ");
			sc.nextLine();
			String name=sc.nextLine();
			System.out.println("Enter the branch phone number: ");
			long phone=sc.nextLong();
			System.out.println("Enter the branch email: ");
			String email=sc.next();
			
			branchdto.setBranchName(name);
			branchdto.setPhoneNumber(phone);
			branchdto.setEmail(email);
			
			System.out.println("Enter the street: ");
			//sc.nextLine();
			String street=sc.next();
			System.out.println("Enter the Area: ");
			//sc.nextLine();
			String area=sc.next();
			System.out.println("Enter the city: ");
			//sc.nextLine();
			String city=sc.next();
			System.out.println("Enter the state: ");
			//sc.nextLine();
			String state=sc.next();
			System.out.println("Enter the pincode: ");
			long pin=sc.nextLong();
			
			addressdto.setStreet(street);
			addressdto.setArea(area);
			addressdto.setCity(city);
			addressdto.setState(state);
			addressdto.setPincode(pin);
			
			branchdto.setAddress(addressdto);
			Query q=entityManager.createQuery("select s from HospitalDto s");
			List<HospitalDto> hospitals=q.getResultList();
			for(HospitalDto h:hospitals) {
				System.out.println(h.getId()+"."+h.getName());
			}
			System.out.println("Enter the hospital id that you want to add these baranches: ");
			int hid=sc.nextInt();
			HospitalDto hospital=entityManager.find(HospitalDto.class, hid);
			
				List<BranchDto> branchess=new ArrayList<BranchDto>();
				branchess.add(branchdto);
				hospital.setBranches(branchess);
				branchdto.setHospitaldto(hospital);
				entityTransaction.begin();
				entityManager.merge(hospital);
				entityManager.persist(branchdto);
				entityTransaction.commit();
				System.out.println("****************branch added!!!!!!****************");		
		}
		return null;
	}
	
	public static BranchDto findBranch() {
		Query q=entityManager.createQuery("select s from BranchDto s");
		List<BranchDto> branches=q.getResultList();
		for(BranchDto b:branches) {
			System.out.println(b.getBranchId()+"."+b.getBranchName());
		}
		System.out.println("Enter the branch id that you wnat to get details: ");
		int bid=sc.nextInt();
		BranchDto branch=entityManager.find(BranchDto.class, bid);
		HospitalDto hospital=branch.getHospitaldto();
		System.out.println("-----------Hospital-------------------");
		System.out.println("Hospital name: "+hospital.getName());
		System.out.println("-----------Branch details-------------");
		System.out.println("Branch Name: "+branch.getBranchName());
		System.out.println("Branch Email: "+branch.getEmail());
		System.out.println("Branch phone: "+branch.getPhoneNumber());
		AddressDto address=branch.getAddress();
		System.out.println("Address: "+address.getStreet()+" Street,"+address.getArea()+","+address.getCity()+","+address.getState()+"-"+address.getPincode());
		System.out.println("--------------------------------------");
		
		return null;
	}
	
	public static BranchDto updateBranchName() {
		Query q=entityManager.createQuery("select s from BranchDto s");
		List<BranchDto> branches=q.getResultList();
		for(BranchDto b:branches) {
			System.out.println(b.getBranchId()+"."+b.getBranchName());
		}
		System.out.println("Enter the branch id that you want to update name: ");
		int bid=sc.nextInt();
		BranchDto branch=entityManager.find(BranchDto.class, bid);
		System.out.println("Enter the name that you want to update: ");
		String name=sc.next();
		branch.setBranchName(name);
		entityTransaction.begin();
		entityManager.merge(branch);
		entityTransaction.commit();
		System.out.println("********************Name got updated********************");
		
		return null;
	}
	
	public static BranchDto updateBranchEmail() {
		Query q=entityManager.createQuery("select s from BranchDto s");
		List<BranchDto> branches=q.getResultList();
		for(BranchDto b:branches) {
			System.out.println(b.getBranchId()+"."+b.getBranchName()+"->"+b.getEmail());
		}
		System.out.println("Enter the branch id that you want to update email: ");
		int bid=sc.nextInt();
		BranchDto branch=entityManager.find(BranchDto.class, bid);
		System.out.println("Enter the email that you want to update: ");
		String email=sc.next();
		branch.setEmail(email);
		entityTransaction.begin();
		entityManager.merge(branch);
		entityTransaction.commit();
		System.out.println("********************Email got updated********************");
		
		return null;
	}
	
	public static BranchDto updatePhoneNumber() {
		Query q=entityManager.createQuery("select s from BranchDto s");
		List<BranchDto> branches=q.getResultList();
		for(BranchDto b:branches) {
			System.out.println(b.getBranchId()+"."+b.getBranchName()+"->"+b.getPhoneNumber());
		}
		System.out.println("Enter the branch id that you want to update email: ");
		int bid=sc.nextInt();
		BranchDto branch=entityManager.find(BranchDto.class, bid);
		System.out.println("Enter the phone number that you want to update: ");
		long phone=sc.nextLong();
		branch.setPhoneNumber(phone);
		entityTransaction.begin();
		entityManager.merge(branch);
		entityTransaction.commit();
		System.out.println("********************Phone Number got updated********************");
		
		return null;
	}
	
	public static void update() {
		System.out.println("\n1.update name\n2.update email\n3.update phone_number\n4.Exit\n\nEnter the choice: ");
		int choice=sc.nextInt();
		boolean again=true;
		while(again) {
			switch(choice) {
			case 1:{
				updateBranchName();
				break;
			}
			case 2:{
				updateBranchEmail();
				break;
			}
			case 3:{
				updatePhoneNumber();
				break;
			}
			case 4:{
				again=false;
				break;
			}
			default:{
				System.out.println("Invalid input!!!");
				break;
			}
			}
		}
	}
	
	public static BranchDto remove() {
		Query q=entityManager.createQuery("select s from BranchDto s");
		List<BranchDto> branches=q.getResultList();
		for(BranchDto b:branches) {
			System.out.println(b.getBranchId()+"."+b.getBranchName());
		}
		System.out.println("Enter the branch id that you want to remove: ");
		int bid=sc.nextInt();
		BranchDto branch=entityManager.find(BranchDto.class, bid);
	
		entityTransaction.begin();
		entityManager.remove(branch);
		entityTransaction.commit();
		
		System.out.println("**************Branch got removed******************");
		return null;
	}
	
	public static void branches() {
		System.out.println("\n1.Save Branch\n2.Find Branch\n3.Update Branch\n4.Delete Branch\n5.Exit\n\nEnter the choice: ");
		int choice=sc.nextInt();
		boolean loop=true;
		while(loop) {
			switch(choice) {
			case 1:{
				saveBranch();
				break;
			}
			case 2:{
				findBranch();
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
				System.out.println("***************invalid input****************");
			}
			}
		}
	}

}
