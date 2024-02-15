package com.ty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.ItemsDto;
import com.ty.dto.MedOrdersDto;

public class ItemsDao {
	static EntityManagerFactory entityManagerFactory=null;
	static EntityManager entityManager=null;
	static EntityTransaction entityTransaction=null;
	static {
		entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	static Scanner sc=new Scanner(System.in);
	static ItemsDto items=null;
	static List<ItemsDto> itemslist;
	static List<MedOrdersDto> medorderslist=new ArrayList<MedOrdersDto>();
	public static ItemsDto saveItems() {
		System.out.println("Enter how many items you want to add: ");
		int count=sc.nextInt();
		for(int i=0;i<count;i++) {
			items=new ItemsDto();
			itemslist=new ArrayList<ItemsDto>();
			System.out.println("Enter the item name: ");
			sc.nextLine();
			String name=sc.nextLine();
			System.out.println("Enter the item type: ");
			String type=sc.next();
			System.out.println("Enter the item price: ");
			double price=sc.nextDouble();
			System.out.println("Enter the item expiry date: ");
			String expiry=sc.next();
			
			items.setName(name);
			items.setItemType(type);
			items.setPrice(price);
			items.setExpiryDate(expiry);
			
			itemslist.add(items);
			entityTransaction.begin();
			entityManager.persist(items);
			entityTransaction.commit();
			System.out.println("************Item got added***********");
		}
		System.out.println("Enter in how many medorders you want to add this items: ");
		int count2=sc.nextInt();
		Query q=entityManager.createQuery("select s from MedOrdersDto s");
		List<MedOrdersDto> medorders=q.getResultList();
		for(MedOrdersDto m:medorders) {
			System.out.println(m.getId()+"."+m.getInvoiceCode());
		}
		for(int i=0;i<count2;i++) {
			
			System.out.println("Enter the medical order id that you want to add the items: ");
			int mid=sc.nextInt();
			MedOrdersDto medorderss=entityManager.find(MedOrdersDto.class, mid);
			if(medorderss!=null) {
				medorderslist.add(medorderss);
				medorderss.setItems(itemslist);
				entityTransaction.begin();
				entityManager.merge(medorderss);
				entityTransaction.commit();
				System.out.println("=================Items added in the medorder=================");
			}
			else {
				System.out.println("=======Medical order not found=========");
			}
		}
		items.setMedorders(medorderslist);
		entityTransaction.begin();
		entityManager.merge(items);
		entityTransaction.commit();
		System.out.println("============Medical orders added to the items");
		return null;
	}
	
	public static ItemsDto findItems() {
		Query q=entityManager.createQuery("select s from ItemsDto s");
		List<ItemsDto> items=q.getResultList();
		for(ItemsDto i:items) {
			System.out.println("Item id: "+i.getId()+", Item name: "+i.getName());
		}
		System.out.println("Enter the Item id you want to get details: ");
		int id=sc.nextInt();
		ItemsDto item=entityManager.find(ItemsDto.class, id);
		if(item!=null) {
			System.out.println("****************Item details******************");
			System.out.println("Item name: "+item.getName());
			System.out.println("Item Type: "+item.getItemType());
			System.out.println("Item price: "+item.getPrice());
			System.out.println("Item Expiry: "+item.getExpiryDate());
			System.out.println("***********************************************");
			List<MedOrdersDto> medorders=item.getMedorders();
			for(MedOrdersDto m:medorders) {
				System.out.println("*******************Medical order*******************");
				System.out.println("Medorders Id: "+m.getId());
				System.out.println("Medorders quantity: "+m.getQuantity());
				System.out.println("Invoice code: "+m.getInvoiceCode());
				System.out.println("Payment method: "+m.getPaymentMethod());
				System.out.println("Billed date: "+m.getBilled_date());
				System.out.println("Updated date: "+m.getUpdated_date());
				System.out.println("***************************************************");
			}
		}
		else {
			System.out.println("*************Item not found*********************");
		}
		return null;
	}
	
	public static ItemsDto removeItems() {
		Query q=entityManager.createQuery("select s from ItemsDto s");
		List<ItemsDto> items=q.getResultList();
		for(ItemsDto i:items) {
			System.out.println("Item id: "+i.getId()+", Item name: "+i.getName());
		}
		System.out.println("Enter the Item id you want to remove: ");
		int id=sc.nextInt();
		ItemsDto item=entityManager.find(ItemsDto.class, id);
		if(item!=null) {
			entityTransaction.begin();
			entityManager.remove(item);
			entityTransaction.commit();
			System.out.println("----------------Item got removed----------------");
		}
		else {
			System.out.println("----------------Item not found------------------");
		}
		
		return null;
	}
	
	public static void items() {
		System.out.println("\n1.Save Items\n2.Find Items\n3.Remove Items\n4.Exit\n\nEnter choice: ");
		int choice=sc.nextInt();
		boolean loop=true;
		while(loop) {
			switch(choice) {
			case 1:{
				saveItems();
				break;
			}
			case 2:{
				findItems();
				break;
			}
			case 3:{
				removeItems();
				break;
			}
			case 4:{
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
