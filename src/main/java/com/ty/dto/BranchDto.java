package com.ty.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
@Entity
public class BranchDto {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int branchId;
	private String branchName;
	private long phoneNumber;
	private String email;
	@ManyToOne 
	@JoinColumn(name="hospital_id")
	private HospitalDto hospitaldto;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private AddressDto address;
	@OneToMany (cascade = CascadeType.ALL,mappedBy = "branchdto")
	private List<EncountersDto> encounterdto;
	
	public List<EncountersDto> getEncounterdto() {
		return encounterdto;
	}
	public void setEncounterdto(List<EncountersDto> encounterdto) {
		this.encounterdto = encounterdto;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public HospitalDto getHospitaldto() {
		return hospitaldto;
	}
	public void setHospitaldto(HospitalDto hospitaldto) {
		this.hospitaldto = hospitaldto;
	}
	public AddressDto getAddress() {
		return address;
	}
	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	
}
