package com.task.dealerservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.dealerservice.Models.CropOrder;
import com.task.dealerservice.Models.Dealer;
import com.task.dealerservice.Repo.CropOrderRepo;
import com.task.dealerservice.Repo.DealerRepo;

@Service
public class DealerService {
    
    @Autowired
    public DealerRepo dealerRepo;

    @Autowired
    public CropOrderRepo cropOrderRepo;

    public List<Dealer> getAllDealers() {
        return dealerRepo.findAll();
    }

    public Dealer getDealerByEmail(String email){
        return dealerRepo.findByDealerEmail(email);
    }

    public Dealer addDealer(Dealer dealer){
        dealerRepo.save(dealer);
        return dealer;
    }

    public Dealer updateDealer(Dealer dealer){
        return dealerRepo.save(dealer);
    }

    public String deleteDealer(String dealerId){
        dealerRepo.deleteById(dealerId);
        return "Dealer was successfully deleted";
    }

    public CropOrder addCropOrder(CropOrder cropOrder){
        return cropOrderRepo.save(cropOrder);
    }

    public Dealer getLogin(String dealerEmail, String dealerPassword){
        return dealerRepo.findByDealerEmailAndDealerPassword(dealerEmail, dealerPassword);
    }

    public List<CropOrder> getCropOrderByDealer(String dealerEmail){
        return cropOrderRepo.findByDealerEmail(dealerEmail);
    }

}
