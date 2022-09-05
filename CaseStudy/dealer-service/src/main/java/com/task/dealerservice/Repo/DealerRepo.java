package com.task.dealerservice.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.task.dealerservice.Models.Dealer;

public interface DealerRepo extends MongoRepository<Dealer, String>{

    

    Dealer findByDealerEmail(String email);

    Dealer findByDealerEmailAndDealerPassword(String dealerEmail, String dealerPassword);
    
}
