package com.spring.CreditConsommation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "credit")
public class Credit {
	@Id
	private String id;
    private Double montantAchat;
    private Double apport;
    private Double montantaFinancier;
    private String produit;
    private Double dureeRembourssement;
    private Double mensualitePaye;
    
    public Credit(){
    }
    
    

	public Credit(Double montantAchat, Double apport, Double montantaFinancier, String produit,
			Double dureeRembourssement, Double mensualitePaye) {
		this.montantAchat = montantAchat;
		this.apport = apport;
		this.montantaFinancier = montantaFinancier;
		this.produit = produit;
		this.dureeRembourssement = dureeRembourssement;
		this.mensualitePaye = mensualitePaye;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public double getMontantAchat() {
		return montantAchat;
	}

	public void setMontantAchat(double montantAchat) {
		this.montantAchat = montantAchat;
	}

	public double getApport() {
		return apport;
	}

	public void setApport(double apport) {
		this.apport = apport;
	}

	public double getMontantaFinancier() {
		return montantaFinancier;
	}

	public void setMontantaFinancier(double montantaFinancier) {
		this.montantaFinancier = montantaFinancier;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public Double getDureeRembourssement() {
		return dureeRembourssement;
	}

	public void setDureeRembourssement(double i) {
		this.dureeRembourssement = i;
	}

	public double getMensualitePaye() {
		return mensualitePaye;
	}

	public void setMensualitePaye(double mensualitePaye) {
		this.mensualitePaye = mensualitePaye;
	}
    
    

}
