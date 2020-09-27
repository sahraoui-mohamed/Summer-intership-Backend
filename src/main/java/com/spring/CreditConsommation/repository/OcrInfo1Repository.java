package com.spring.CreditConsommation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.CreditConsommation.model.Ocrinfo1;

public interface OcrInfo1Repository extends MongoRepository<Ocrinfo1,String> {
	List<Ocrinfo1> findByNomContaining(String nom);

}
