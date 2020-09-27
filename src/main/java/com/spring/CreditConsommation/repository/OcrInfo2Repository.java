package com.spring.CreditConsommation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.spring.CreditConsommation.model.Ocrinfo2;

public interface OcrInfo2Repository extends MongoRepository<Ocrinfo2,String>{
	List<Ocrinfo2> findByAdresseContaining(String adresse);

}
