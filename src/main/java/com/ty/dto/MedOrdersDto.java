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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class MedOrdersDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int quantity;
	private String invoiceCode;
	private String paymentMethod;
	@CreationTimestamp
	LocalDateTime Billed_date;
	@UpdateTimestamp
	LocalDateTime updated_date;
	@ManyToOne
	@JoinColumn(name="Encounter_id")
	private EncountersDto encountersdto;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable (joinColumns = @JoinColumn(name = "my_medorders_id"),inverseJoinColumns = @JoinColumn(name = "my_items_id"))
	private List<ItemsDto> items;
	
	
	public List<ItemsDto> getItems() {
		return items;
	}
	public void setItems(List<ItemsDto> items) {
		this.items = items;
	}
	public LocalDateTime getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(LocalDateTime updated_date) {
		this.updated_date = updated_date;
	}
	public LocalDateTime getBilled_date() {
		return Billed_date;
	}
	public void setBilled_date(LocalDateTime billed_date) {
		Billed_date = billed_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public EncountersDto getEncountersdto() {
		return encountersdto;
	}
	public void setEncountersdto(EncountersDto encountersdto) {
		this.encountersdto = encountersdto;
	}
}
