package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.EncountersDto;
import com.ty.dto.MedOrdersDto;

public class MedOrdersDao {
	static EntityManagerFactory entityManagerFactory=null;
	static EntityManager entityManager=null;
	static EntityTransaction entityTransaction=null;
	static {
		entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	static Scanner sc=new Scanner(System.in);
	static MedOrdersDto medorders=null;
	static List<MedOrdersDto> medorderss=new ArrayList<MedOrdersDto>();
	public static MedOrdersDto saveMedorders() {
		System.out.println("Enter how many medical orders you want to add: ");
		int count=sc.nextInt();
		for(int i=0;i<count;i++) {
			medorders=new MedOrdersDto();
			System.out.println("Enter the quantity: ");
			int q=sc.nextInt();
			System.out.println("Enter the Invoice code: ");
			String invoice=sc.next();
			System.out.println("Enter the payment method: ");
			String pay=sc.next();
			
			medorders.setQuantity(q);
			medorders.setInvoiceCode(invoice);
			medorders.setPaymentMethod(pay);
			
			medorderss.add(medorders);
			entityTransaction.begin();
			entityManager.persist(medorders);
			entityTransaction.commit();
			System.out.println("---------------Medical Order added----------------");
			
			Query q1=entityManager.createQuery("select s from EncountersDto s");
			List<EncountersDto> encounters=q1.getResultList();
			for(EncountersDto e:encounters) {
				System.out.println(e.getId()+"."+"->"+e.getBedNumber());
			}
			System.out.println("Enter the encounter id that you want to add these medical orders: ");
			int eid=sc.nextInt();
			EncountersDto encounter=entityManager.find(EncountersDto.class, eid);
			encounter.setMedorders(medorderss);
			medorders.setEncountersdto(encounter);
			entityTransaction.begin();
			entityManager.merge(medorders);
			entityManager.merge(encounter);
			
			entityTransaction.commit();
			System.out.println("*******************Medorders added******************");
		}
		
		
		return null;
	}
	
	public static MedOrdersDto findMedorders() {
		Query q=entityManager.createQuery("select s from MedOrdersDto s");
		List<MedOrdersDto> medorders=q.getResultList();
		for(MedOrdersDto m:medorders) {
			System.out.println(m.getId()+","+m.getInvoiceCode());
		}
		System.out.println("Enter the medical order id that you to get details: ");
		int mid=sc.nextInt();
		MedOrdersDto medorderss=entityManager.find(MedOrdersDto.class, mid);
		if(medorderss!=null) {
			System.out.println("*********************Medical orders*******************");
			System.out.println("Medical order quantity: "+medorderss.getQuantity());
			System.out.println("Medical order quantity: "+medorderss.getInvoiceCode());
			System.out.println("Medical order payment method: "+medorderss.getPaymentMethod());
			System.out.println("Medical order billed date: "+medorderss.getBilled_date());
			System.out.println("Medical order updated date: "+medorderss.getUpdated_date());
			System.out.println("*******************************************************");
			System.out.println("**********************Encounters***********************");
			EncountersDto encounters=medorderss.getEncountersdto();
			System.out.println("Encounter id"+encounters.getId());
			System.out.println("Encounter bed Number"+encounters.getBedNumber());
			System.out.println("Encounter ward"+encounters.getWard());
			System.out.println("*******************************************************");
		}
		else {
			System.out.println("*************Medical order not found*******************");
		}
		
		
		return null;
	}
	
	public static MedOrdersDto updateQuantity() {
		Query q=entityManager.createQuery("select s from MedOrdersDto s");
		List<MedOrdersDto> medorders=q.getResultList();
		for(MedOrdersDto m:medorders) {
			System.out.println(m.getId()+"."+((m.getEncountersdto().getBranchdto()).getBranchName())+"->"+(m.getEncountersdto().getBedNumber()));
		}
		System.out.println("Enter the medical order id that you want to update quantity: ");
		int mid=sc.nextInt();
		MedOrdersDto medorderss=entityManager.find(MedOrdersDto.class, mid);
		if(medorderss!=null) {
			System.out.println("Enter the new quantity: ");
			int quantity=sc.nextInt();
			medorderss.setQuantity(quantity);
			entityTransaction.begin();
			entityManager.merge(medorderss);
			entityTransaction.commit();
			System.out.println("================Quantity got updated===================");
		}
		else {
			System.out.println("================Medical order not found================");
		}
		return null;
	}
	public static MedOrdersDto updatePayment() {
		Query q=entityManager.createQuery("select s from MedOrdersDto s");
		List<MedOrdersDto> medorders=q.getResultList();
		for(MedOrdersDto m:medorders) {
			System.out.println(m.getId()+"."+m.getInvoiceCode());
		}
		System.out.println("Enter the medical order id that you want to update payment method: ");
		int mid=sc.nextInt();
		MedOrdersDto medorderss=entityManager.find(MedOrdersDto.class, mid);
		if(medorderss!=null) {
			System.out.println("Enter the new quantity: ");
			String payment=sc.next();
			medorderss.setPaymentMethod(payment);
			entityTransaction.begin();
			entityManager.merge(medorderss);
			entityTransaction.commit();
			System.out.println("================payment method got updated===================");
		}
		else {
			System.out.println("================Medical order not found================");
		}
		return null;
	}
	
	public static MedOrdersDto removeMedorder() {
		Query q=entityManager.createQuery("select s from MedOrdersDto s");
		List<MedOrdersDto> medorders=q.getResultList();
		for(MedOrdersDto m:medorders) {
			System.out.println(m.getId()+","+m.getInvoiceCode());
		}
		System.out.println("Enter the medical order id that you want to remove: ");
		int mid=sc.nextInt();
		MedOrdersDto medorderss=entityManager.find(MedOrdersDto.class, mid);
		if(medorderss!=null) {
			entityTransaction.begin();
			entityManager.remove(medorderss);
			entityTransaction.commit();
			System.out.println("**********************Medical order removed********************");
		}
		else {
			System.out.println("================Medical order not found================");
		}
		
		return null;
	}
	
	public static void medorders() {
		System.out.println("\n1.Save MedOrders\n2.Find MedOrder\n3.Update quantity\n4.Update payment\n5.Remove MedOrder\n6.Exit\n\nEnter choice: ");
		int choice=sc.nextInt();
		boolean loop=true;
		while(loop) {
			switch(choice) {
			case 1:{
				saveMedorders();
				break;
			}
			case 2:{
				findMedorders();
				break;
			}
			case 3:{
				updateQuantity();
				break;
			}
			case 4:{
				updatePayment();
				break;
			}
			case 5:{
				removeMedorder();
				break;
			}
			case 6:{
				loop=false;
				break;
			}
			default:{
				System.out.println("Invalid choice");
				break;
			}
			}
		}
	}
	
}
