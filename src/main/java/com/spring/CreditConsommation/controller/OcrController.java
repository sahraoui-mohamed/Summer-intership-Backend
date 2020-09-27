package com.spring.CreditConsommation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.CreditConsommation.model.Ocr;
import com.spring.CreditConsommation.repository.OcrRepository;

@CrossOrigin(origins = {"http://192.168.1.4:4200" , "http://localhost:4200" , "http://127.0.0.1:4200" , "http://192.168.210.113:4200"})
@RestController
@RequestMapping("/test")
public class OcrController {
	@Autowired
	OcrRepository ocrRepository;
	
	@GetMapping("/informations")
	public ResponseEntity<List<Ocr>> getAllInformations(@RequestParam(required = false) String nom ){
		try {
		    List<Ocr> informations = new ArrayList<Ocr>();

		    if (nom == null)
		    	ocrRepository.findAll().forEach(informations::add);
		    else
		    	ocrRepository.findByNomContaining(nom).forEach(informations::add);

		    if (informations.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(informations, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}
	
	
	
	@GetMapping("/informations/{id}")
	public ResponseEntity<Ocr> getInformationById(@PathVariable("id") String id){
		Optional<Ocr> informationData = ocrRepository.findById(id);

		  if (informationData.isPresent()) {
		    return new ResponseEntity<>(informationData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	}
	
	@PostMapping("/informations")
	public ResponseEntity<Ocr> createInformation(@RequestBody Ocr information) {
		try {
			Ocr _information = ocrRepository.save(new Ocr(information.getNom(), information.getPrenom(), information.getEmail() , information.getDatelieu() , information.getAdress() , information.getVille() , information.getCodepostale() , information.getPays() , information.getRib() , information.getMontantcredit() , information.getProfession() , information.getDepuis() , information.getTelephone() , information.getRevenues() , information.getAutres() , information.getImmobilier(), information.getCreditencour()));
		    return new ResponseEntity<>(_information, HttpStatus.CREATED);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    
	}

	@PutMapping("/informations/{id}")
	public ResponseEntity<Ocr> updateInfomation(@PathVariable("id") String id, @RequestBody Ocr tuto) {
		Optional<Ocr> informationData = ocrRepository.findById(id);

		  if (informationData.isPresent()) {
			  Ocr _information = informationData.get();
			  _information.setNom(tuto.getNom());
			  _information.setPrenom(tuto.getPrenom());
			  _information.setEmail(tuto.getEmail());
			  _information.setDatelieu(tuto.getDatelieu());
			  _information.setAdress(tuto.getAdress());
			  _information.setVille(tuto.getVille());
			  _information.setCodepostale(tuto.getCodepostale());
			  _information.setPays(tuto.getPays());
			  _information.setRib(tuto.getRib());
			  _information.setMontantcredit(tuto.getMontantcredit());
			  _information.setProfession(tuto.getPays());
			  _information.setDepuis(tuto.getRib());
			  _information.setTelephone(tuto.getMontantcredit());
			  _information.setRevenues(tuto.getRevenues());
			  _information.setAutres(tuto.getAutres());
			  _information.setImmobilier(tuto.getImmobilier());
			  _information.setCreditencour(tuto.getCreditencour());
		    return new ResponseEntity<>(ocrRepository.save(_information), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	    
	}

	@DeleteMapping("/informations/{id}")
	public ResponseEntity<HttpStatus> deleteInformation(@PathVariable("id") String id) {
		try {
			ocrRepository.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    
	}

	@DeleteMapping("/informations")
	public ResponseEntity<HttpStatus> deleteAllInformations() {
		try {
			ocrRepository.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    
	}


}
