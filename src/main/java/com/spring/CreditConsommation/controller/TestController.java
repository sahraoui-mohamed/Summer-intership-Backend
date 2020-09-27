package com.spring.CreditConsommation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.CreditConsommation.model.Credit;
import com.spring.CreditConsommation.repository.CreditRepository;



@CrossOrigin(origins = {"http://192.168.1.4:4200" , "http://localhost:4200" , "http://127.0.0.1:4200" , "http://192.168.210.113:4200"})
@RestController
@RequestMapping("/api")
public class TestController {
	@Autowired
	CreditRepository creditRepository;

	@GetMapping("/credits")
	public ResponseEntity<List<Credit>> getAllCredits(@RequestParam(required = false) Double montantAchat) {
		try {
		    List<Credit> credits = new ArrayList<Credit>();

		    if (montantAchat == null)
		    	creditRepository.findAll().forEach(credits::add);
		    else
		    	creditRepository.findByMontantAchatContaining(montantAchat).forEach(credits::add);

		    if (credits.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(credits, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    
	}

	@GetMapping("/credits/{id}")
	public ResponseEntity<Credit> getCreditById(@PathVariable("id") String id) {
		Optional<Credit> creditData = creditRepository.findById(id);

		  if (creditData.isPresent()) {
		    return new ResponseEntity<>(creditData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	    
	}

	@PostMapping("/credits")
	public ResponseEntity<Credit> createCredit(@RequestBody Credit credit) {
		try {
		    Credit _credit = creditRepository.save(new Credit(credit.getMontantAchat(), credit.getApport(), credit.getMontantaFinancier(),credit.getProduit(),credit.getDureeRembourssement(),credit.getMensualitePaye()));
		    return new ResponseEntity<>(_credit, HttpStatus.CREATED);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    
	}

	@PutMapping("/credits/{id}")
	public ResponseEntity<Credit> updateCredit(@PathVariable("id") String id, @RequestBody Credit tutorial) {
		Optional<Credit> creditData = creditRepository.findById(id);

		  if (creditData.isPresent()) {
			  Credit _credit = creditData.get();
			  _credit.setMontantAchat(tutorial.getMontantAchat());
			  _credit.setApport(tutorial.getApport());
			  _credit.setMontantaFinancier(tutorial.getMontantaFinancier());
			  _credit.setProduit(tutorial.getProduit());
			  _credit.setDureeRembourssement(tutorial.getDureeRembourssement());
			  _credit.setMensualitePaye(tutorial.getMensualitePaye());
		    return new ResponseEntity<>(creditRepository.save(_credit), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	    
	}

	@DeleteMapping("/credits/{id}")
	public ResponseEntity<HttpStatus> deleteCredit(@PathVariable("id") String id) {
		try {
			creditRepository.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    
	}

	@DeleteMapping("/credits")
	public ResponseEntity<HttpStatus> deleteAllCredits() {
		try {
			creditRepository.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	    
	}

	

}
