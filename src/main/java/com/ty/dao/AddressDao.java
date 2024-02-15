package com.ty.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.AddressDto;
import com.ty.dto.HospitalDto;

public class AddressDao {
	static EntityManagerFactory entityManagerFactory=null;
	static EntityManager entityManager=null;
	static EntityTransaction entityTransaction=null;
	static {
		entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	static Scanner sc=new Scanner(System.in);
	
	public static AddressDto updateStreet() {
		Query q=entityManager.createQuery("select s from AddressDto s");
		List<AddressDto> Addresses=q.getResultList();
		for(AddressDto a:Addresses) {
			System.out.println(a.getId()+"."+a.getBranch().getBranchName()+"->old street: "+a.getStreet());
		}
		System.out.println("Enter the Address id that you want to update Street: ");
		int aid=sc.nextInt();
		AddressDto address=entityManager.find(AddressDto.class, aid);
		System.out.println("Enter the street you want to update: ");
		String street=sc.next();
		if(address!=null) {
			address.setStreet(street);
			entityTransaction.begin();
			entityManager.merge(address);
			entityTransaction.commit();
			
			System.out.println("****************street got update****************");
		}
		else {
			System.out.println("----------------Address not found-----------------");
		}
		
		return null;
	}
	
	public static AddressDto findaddress() {
		Query q=entityManager.createQuery("select s from AddressDto s");
		List<AddressDto> Addresses=q.getResultList();
		for(AddressDto a:Addresses) {
			System.out.println(a.getId()+"."+a.getBranch().getBranchName());
		}
		System.out.println("Enter the Address id that you want to get details: ");
		int aid=sc.nextInt();
		AddressDto address=entityManager.find(AddressDto.class, aid);
		
		if(address!=null) {
			System.out.println("***************Address details*****************");
			
			System.out.println("Street: "+address.getStreet());
			System.out.println("Area: "+address.getArea());
			System.out.println("City: "+address.getCity());
			System.out.println("State: "+address.getState());
			System.out.println("Pincode: "+address.getPincode());
			
			System.out.println("********************************");
		}
		else {
			System.out.println("----------------Address not found-----------------");
		}
		
		return null;
	}
	
	public static AddressDto remove() {
		Query q=entityManager.createQuery("select s from AddressDto s");
		List<AddressDto> Addresses=q.getResultList();
		for(AddressDto a:Addresses) {
			System.out.println(a.getId()+"."+a.getBranch().getBranchName());
		}
		System.out.println("Enter the Address id that you want to remove: ");
		int aid=sc.nextInt();
		AddressDto address=entityManager.find(AddressDto.class, aid);
		if(address!=null) {
			entityTransaction.begin();
			entityManager.remove(address);
			entityTransaction.commit();
			System.out.println("********************Address removed successfully*****************");
		}
		else {
			System.out.println("-------------------Address not found----------------------");
		}
		
		
		return null;
	}
	
	public static void operationsAddress() {
		System.out.println("\n1.Update Address\n2.Find Address\n3.Delete Address\n4.exit\n\nEnter the choice: ");
		int choice=sc.nextInt();
		boolean loop=true;
		while(loop) {
			switch(choice) {
			case 1:{
				updateStreet();
				break;
			}
			case 2:{
				findaddress();
				break;
			}
			case 3:{
				remove();
				break;
			}
			case 4:{
				loop=false;
				break;
			}
			default:{
				System.out.println("---------invalid input----------");
				break;
			}
			}
		}
	}
}
