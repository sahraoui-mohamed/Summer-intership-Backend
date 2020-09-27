package com.spring.CreditConsommation.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.CreditConsommation.model.Conseiller;

@Repository
public interface ConseillerRepository extends MongoRepository<Conseiller,String> {
	public Conseiller findByEmailContaining(String email);
	public Conseiller findByMdpContaining(String mdp);
	public Conseiller findByEmailAndMdpContaining(String email,String mdp);


}
