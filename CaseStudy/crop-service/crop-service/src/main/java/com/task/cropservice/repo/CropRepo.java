package com.task.cropservice.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.task.cropservice.models.Crop;

public interface CropRepo extends MongoRepository<Crop, String> {

    List<Crop> findByFarmerEmail(String farmerEmail);

    List<Crop> findByCropName(String cropName);
    
}
