package com.ty.dao;

import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.MedOrdersDto;

public class Demo {

	public static void main(String[] args) {
		Query q= Persistence.createEntityManagerFactory("vikas").createEntityManager().createQuery("select s from MedOrdersDto s");
		List<MedOrdersDto> medorders=q.getResultList();
		System.out.println(medorders);
	}
	
}
