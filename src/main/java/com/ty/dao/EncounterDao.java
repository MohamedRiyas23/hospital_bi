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

public class EncounterDao {
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	static EntityTransaction entityTransaction = null;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	static Scanner sc = new Scanner(System.in);
	static EncountersDto encountersdto = null;
	static List<EncountersDto> encounters = new ArrayList<EncountersDto>();

	public static EncountersDto saveEncounters() {
		System.out.println("Enter how many encounters you want to add: ");
		int count = sc.nextInt();
		for (int i = 0; i < count; i++) {
			encountersdto = new EncountersDto();
			System.out.println("Enter the Bed number: ");
			String bed = sc.next();
			System.out.println("Enter the ward type: ");
			String ward = sc.next();

			encountersdto.setBedNumber(bed);
			encountersdto.setWard(ward);

			encounters.add(encountersdto);
			entityTransaction.begin();
			entityManager.persist(encountersdto);
			entityTransaction.commit();
			System.out.println("-------------Encounter added-------------");

			Query q = entityManager.createQuery("select s from BranchDto s");
			List<BranchDto> branches = q.getResultList();
			for (BranchDto b : branches) {
				System.out.println(b.getBranchId() + "." + b.getBranchName());
			}
			System.out.println("Enter the branch id that you want to add these encounters: ");
			int bid = sc.nextInt();
			BranchDto branch = entityManager.find(BranchDto.class, bid);
			branch.setEncounterdto(encounters);
			encountersdto.setBranchdto(branch);
			entityTransaction.begin();
			entityManager.merge(branch);
			entityManager.merge(encountersdto);
			entityTransaction.commit();
			System.out.println("******************Encounters added to branch*******************");
		}
		return null;
	}

	public static EncountersDto updateBed() {
		Query q = entityManager.createQuery("select s from EncountersDto s");
		List<EncountersDto> encounters = q.getResultList();
		for (EncountersDto e : encounters) {
			System.out.println(e.getId() + "." + (e.getBranchdto().getBranchName()) + "->" + e.getBedNumber());
		}
		System.out.println("Enter the encounter id id that you want to update bed number: ");
		int eid = sc.nextInt();
		EncountersDto encounter = entityManager.find(EncountersDto.class, eid);
		if (encounter != null) {
			System.out.println("Enter the bed number that you want to update: ");
			String bed = sc.next();
			encounter.setBedNumber(bed);
			entityTransaction.begin();
			entityManager.merge(encounter);
			entityTransaction.commit();
			System.out.println("***************Encounter bed number got updated***************");
		} else {
			System.out.println("***************Encounter not found***************");
		}

		return null;
	}

	public static EncountersDto updateWard() {
		Query q = entityManager.createQuery("select s from EncountersDto s");
		List<EncountersDto> encounters = q.getResultList();
		for (EncountersDto e : encounters) {
			System.out.println(e.getId() + "." + (e.getBranchdto().getBranchName()) + "->" + e.getWard());
		}
		System.out.println("Enter the encounter id id that you want to update ward: ");
		int eid = sc.nextInt();
		EncountersDto encounter = entityManager.find(EncountersDto.class, eid);
		if (encounter != null) {
			System.out.println("Enter the ward that you want to update: ");
			String ward = sc.next();
			encounter.setWard(ward);
			entityTransaction.begin();
			entityManager.merge(encounter);
			entityTransaction.commit();
			System.out.println("***************Encounter ward got updated***************");
		} else {
			System.out.println("***************Encounter not found***************");
		}

		return null;
	}

	public static EncountersDto findEncounter() {
		Query q = entityManager.createQuery("select s from EncountersDto s");
		List<EncountersDto> encounters = q.getResultList();
		for (EncountersDto e : encounters) {
			System.out.println(e.getId() + "." + (e.getBranchdto().getBranchName()));
		}
		System.out.println("Enter the encounter id that you want to get details: ");
		int eid = sc.nextInt();
		EncountersDto encounter = entityManager.find(EncountersDto.class, eid);
		if (encounter != null) {
			System.out.println("Encounter bed number: " + encounter.getBedNumber());
			System.out.println("Encounter Ward Type: " + encounter.getWard());
			System.out.println("Encounter entered date: " + encounter.getEntered_date());
			System.out.println("Encounter modified date: " + encounter.getUpdated_date());
			System.out.println("Branch that belongs to: " + (encounter.getBranchdto().getBranchName()));
		} else {
			System.out.println("***************Encounter not found***************");
		}

		return null;
	}

	public static EncountersDto removeEncounter() {

		Query q = entityManager.createQuery("select s from EncountersDto s");
		List<EncountersDto> encounters = q.getResultList();
		for (EncountersDto e : encounters) {
			System.out.println(e.getId() + "." + (e.getBranchdto().getBranchName()));
		}
		System.out.println("Enter the encounter id that you want to remove: ");
		int eid = sc.nextInt();
		EncountersDto encounter = entityManager.find(EncountersDto.class, eid);
		if (encounter != null) {
			entityTransaction.begin();
			entityManager.remove(encounter);
			entityTransaction.commit();
			System.out.println("***************Encounter Removed***************");
		} else {
			System.out.println("***************Encounter not found***************");
		}

		return null;
	}

	public static void update() {
		System.out.println("\n1.Bed Number\n2.Ward\n3.Exit\n\nEnter choice: ");
		int choice = sc.nextInt();
		boolean loop = true;
		while (loop) {
			switch (choice) {
			case 1: {
				updateBed();
				break;
			}
			case 2: {
				updateWard();
				break;
			}
			case 3: {
				loop = false;
				break;
			}
			default: {
				System.out.println("Invalid input");
			}
			}
		}
	}

	public static void encounters() {
		System.out.println(
				"\n1.Save Encounters\n2.Find Encounters\n3.Update Encounters\n4.Remove Encounters\n5.Exit\n\nEnter choice: ");
		int choice = sc.nextInt();
		boolean loop = true;
		while (loop) {
			switch (choice) {
			case 1: {
				saveEncounters();
				break;
			}
			case 2: {
				findEncounter();
				break;
			}
			case 3: {
				update();
				break;
			}
			case 4: {
				removeEncounter();
				break;
			}
			case 5: {
				loop = false;
				break;
			}
			default: {
				System.out.println("Invalid choice");
				break;
			}
			}
		}
	}
}
