package com.ty.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class EncountersDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String bedNumber;
	private String ward;
	@CreationTimestamp
	LocalDateTime entered_date;
	@UpdateTimestamp
	LocalDateTime updated_date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="branch_id")
	private BranchDto branchdto;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "encountersdto",cascade = CascadeType.ALL)
	private List<MedOrdersDto> medorders;
	
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private PersonDto person;
	
	public LocalDateTime getEntered_date() {
		return entered_date;
	}
	public void setEntered_date(LocalDateTime entered_date) {
		this.entered_date = entered_date;
	}
	public LocalDateTime getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(LocalDateTime updated_date) {
		this.updated_date = updated_date;
	}
	public List<MedOrdersDto> getMedorders() {
		return medorders;
	}
	public void setMedorders(List<MedOrdersDto> medorders) {
		this.medorders = medorders;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	
	public BranchDto getBranchdto() {
		return branchdto;
	}
	public void setBranchdto(BranchDto branchdto) {
		this.branchdto = branchdto;
	}
	public PersonDto getPerson() {
		return person;
	}
	public void setPerson(PersonDto person) {
		this.person = person;
	}
}
