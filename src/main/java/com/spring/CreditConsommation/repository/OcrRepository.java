package com.spring.CreditConsommation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.CreditConsommation.model.Ocr;

@Repository
public interface OcrRepository extends MongoRepository<Ocr,String> {
	List<Ocr> findByNomContaining(String nom);

}
