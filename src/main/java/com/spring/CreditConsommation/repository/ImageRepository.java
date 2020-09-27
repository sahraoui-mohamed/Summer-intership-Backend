package com.spring.CreditConsommation.repository;



import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.CreditConsommation.model.ImageModel;

@Repository
public interface ImageRepository extends MongoRepository<ImageModel,String> {
	public ArrayList<ImageModel> findByName(String name);

}
