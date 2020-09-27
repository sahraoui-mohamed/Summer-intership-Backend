package com.spring.CreditConsommation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.CreditConsommation.model.Conseiller;
import com.spring.CreditConsommation.repository.ConseillerRepository;



@CrossOrigin(origins = {"http://192.168.1.4:4200" , "http://localhost:4200" , "http://127.0.0.1:4200" , "http://192.168.210.113:4200"})
@RestController
@RequestMapping("/")
public class ConseillerController {
	@Autowired
	ConseillerRepository conseillerRepository;

	
	@PostMapping("register")
	public Conseiller createConseiller(@RequestBody Conseiller conseiller) throws Exception{
			Conseiller conseiller2 = conseillerRepository.findByEmailContaining(conseiller.getEmail());
			if (conseiller2 != null) {
				throw new Exception("Email is already exist");
			}
			else {
			Conseiller _conseiller = conseillerRepository.save(new Conseiller(conseiller.getNom(),conseiller.getPrenom(),conseiller.getAdresse(),conseiller.getTelephone(),conseiller.getEmail(),conseiller.getCin(),conseiller.getMdp()));
		    return _conseiller;}
	    
	}
	
	@PostMapping("registerr")
	public ResponseEntity<Conseiller> createCredit(@RequestBody Conseiller conseiller) {
		try {
			
			Conseiller _conseiller1 = conseillerRepository.findByEmailContaining(conseiller.getEmail());
			if (_conseiller1 != null) {
				Conseiller _conseiller2 = conseillerRepository.save(new Conseiller(conseiller.getNom(),conseiller.getPrenom(),conseiller.getAdresse(),conseiller.getTelephone(),conseiller.getEmail(),conseiller.getCin(),conseiller.getMdp(),"error"));
				return new ResponseEntity<>(_conseiller2, HttpStatus.CREATED);
			}
			Conseiller _conseiller = conseillerRepository.save(new Conseiller(conseiller.getNom(),conseiller.getPrenom(),conseiller.getAdresse(),conseiller.getTelephone(),conseiller.getEmail(),conseiller.getCin(),conseiller.getMdp()));
			return new ResponseEntity<>(_conseiller, HttpStatus.CREATED);
		  } catch (Exception e) {
			  	Conseiller _conseiller2 = conseillerRepository.save(new Conseiller(conseiller.getNom(),conseiller.getPrenom(),conseiller.getAdresse(),conseiller.getTelephone(),conseiller.getEmail(),conseiller.getCin(),conseiller.getMdp(),"error"));
				return new ResponseEntity<>(_conseiller2, HttpStatus.CREATED);
		  }
	    
	}
	
	@PostMapping("login")
	public Conseiller loginConseiller(@RequestBody Conseiller conseiller) throws Exception{
			String email = conseiller.getEmail();
			System.out.println(email);
			String mdp = conseiller.getMdp();
			System.out.println(mdp);
			Conseiller _conseiller = conseillerRepository.findByEmailContaining(email);
			if (email != null && mdp != null) {
				throw new Exception("Bad credentials");
			}
			if(_conseiller == null) {
				throw new Exception("Badd credentials");
			}
			return _conseiller;
			

	}
	@PostMapping("loginn")
	public ResponseEntity<Conseiller> loginCredit(@RequestBody Conseiller conseiller) {

			
			Conseiller _conseiller1 = conseillerRepository.findByEmailAndMdpContaining(conseiller.getEmail(), conseiller.getMdp());
			if (_conseiller1 == null) {
				Conseiller _conseiller2 = conseillerRepository.save(new Conseiller(null,null,null,null,null,null,null,"error"));
				return new ResponseEntity<>(_conseiller2, HttpStatus.CREATED);
			}
			Conseiller _conseiller = conseillerRepository.save(new Conseiller(null,null,null,null,null,null,null));
			return new ResponseEntity<>(_conseiller, HttpStatus.CREATED);
		  
	    
	}

}
