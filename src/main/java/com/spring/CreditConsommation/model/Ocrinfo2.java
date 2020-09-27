package com.spring.CreditConsommation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "ocrinfo2")
public class Ocrinfo2 {
	@Id
	private String id;
	private String adresse;
	private String code;
	private String ville;
	
	public Ocrinfo2(String adresse, String code, String ville) {
		super();
		this.adresse = adresse;
		this.code = code;
		this.ville = ville;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	

}
