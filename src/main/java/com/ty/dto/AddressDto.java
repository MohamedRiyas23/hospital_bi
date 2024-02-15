package com.ty.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AddressDto {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String street;
	private String area;
	private String city;
	private String state;
	private long pincode;
	@OneToOne(mappedBy = "address")
	private BranchDto branch;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public BranchDto getBranch() {
		return branch;
	}
	public void setBranch(BranchDto branch) {
		this.branch = branch;
	}
	
	
}
