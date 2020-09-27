package com.spring.CreditConsommation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "ocr")
public class Ocr {
	@Id
	private String id;
	private String nom;
	private String prenom;
	private String email;
	private String datelieu;
	private String adress;
	private String ville;
	private Integer codepostale;
	private String pays;
	private String rib;
	private String montantcredit;
	private String profession;
	private String depuis;
	private String telephone;
	private Double Revenues;
	private Double Autres;
	private Double immobilier;
	private Double creditencour;
	
	
	
	public Ocr() {
		
	}


	


	public Ocr(String nom, String prenom, String email, String datelieu, String adress, String ville,
			Integer codepostale, String pays, String rib, String montantcredit, String profession, String depuis,
			String telephone, Double revenues, Double autres, Double immobilier, Double creditencour) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.datelieu = datelieu;
		this.adress = adress;
		this.ville = ville;
		this.codepostale = codepostale;
		this.pays = pays;
		this.rib = rib;
		this.montantcredit = montantcredit;
		this.profession = profession;
		this.depuis = depuis;
		this.telephone = telephone;
		Revenues = revenues;
		Autres = autres;
		this.immobilier = immobilier;
		this.creditencour = creditencour;
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





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getDatelieu() {
		return datelieu;
	}





	public void setDatelieu(String datelieu) {
		this.datelieu = datelieu;
	}





	public String getAdress() {
		return adress;
	}





	public void setAdress(String adress) {
		this.adress = adress;
	}





	public String getVille() {
		return ville;
	}





	public void setVille(String ville) {
		this.ville = ville;
	}





	public Integer getCodepostale() {
		return codepostale;
	}





	public void setCodepostale(Integer codepostale) {
		this.codepostale = codepostale;
	}





	public String getPays() {
		return pays;
	}





	public void setPays(String pays) {
		this.pays = pays;
	}





	public String getRib() {
		return rib;
	}





	public void setRib(String rib) {
		this.rib = rib;
	}





	public String getMontantcredit() {
		return montantcredit;
	}





	public void setMontantcredit(String montantcredit) {
		this.montantcredit = montantcredit;
	}





	public String getProfession() {
		return profession;
	}





	public void setProfession(String profession) {
		this.profession = profession;
	}





	public String getDepuis() {
		return depuis;
	}





	public void setDepuis(String depuis) {
		this.depuis = depuis;
	}





	public String getTelephone() {
		return telephone;
	}





	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}





	public Double getRevenues() {
		return Revenues;
	}





	public void setRevenues(Double revenues) {
		Revenues = revenues;
	}





	public Double getAutres() {
		return Autres;
	}





	public void setAutres(Double autres) {
		Autres = autres;
	}





	public Double getImmobilier() {
		return immobilier;
	}





	public void setImmobilier(Double immobilier) {
		this.immobilier = immobilier;
	}





	public Double getCreditencour() {
		return creditencour;
	}





	public void setCreditencour(Double creditencour) {
		this.creditencour = creditencour;
	}
	
	
	
}
