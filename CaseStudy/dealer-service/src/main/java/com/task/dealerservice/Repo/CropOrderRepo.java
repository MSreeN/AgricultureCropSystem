package com.task.dealerservice.Repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.task.dealerservice.Models.CropOrder;

public interface CropOrderRepo extends MongoRepository<CropOrder, String> {

    List<CropOrder> findByDealerEmail(String dealerEmail);
    
}
