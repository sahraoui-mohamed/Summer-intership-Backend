package com.spring.CreditConsommation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.spring.CreditConsommation.model.Credit;
//@RepositoryRestResource(collectionResourceRel = "creditdata", path = "creditdata")
@Repository
public interface CreditRepository extends MongoRepository<Credit,String> {
	List<Credit> findByMontantAchatContaining(Double montantAchat);

}
