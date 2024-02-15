package com.ty.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class HospitalDto {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String hospWebsite;
	private String location;
	@OneToMany(mappedBy = "hospitaldto",cascade = CascadeType.ALL)
	private List<BranchDto> branches;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHospWebsite() {
		return hospWebsite;
	}
	public void setHospWebsite(String hospWebsite) {
		this.hospWebsite = hospWebsite;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<BranchDto> getBranches() {
		return branches;
	}
	public void setBranches(List<BranchDto> branches) {
		this.branches = branches;
	}
	
	
}
