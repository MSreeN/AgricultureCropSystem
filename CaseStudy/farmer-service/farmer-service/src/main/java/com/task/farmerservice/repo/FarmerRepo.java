package com.task.farmerservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.task.farmerservice.models.Farmer;

public interface FarmerRepo extends MongoRepository<Farmer, String> {

  

    Farmer findByFarmerEmail(String email);

    Farmer findByFarmerEmailAndFarmerPassword(String email, String password);

    void deleteByFarmerEmail(String farmerEmail);
    
}
