package com.spring.CreditConsommation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "ocrinfo1")
public class Ocrinfo1 {
	@Id
	private String id;
	private String nom;
	private String prenom;
	private String cin;
	private String date;
	public Ocrinfo1(String nom, String prenom, String cin, String date) {
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	

}
