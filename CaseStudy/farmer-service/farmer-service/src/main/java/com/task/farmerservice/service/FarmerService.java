package com.task.farmerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.task.farmerservice.models.Farmer;
import com.task.farmerservice.repo.FarmerRepo;

@Service
public class FarmerService {
    @Autowired
    public FarmerRepo farmerRepo;
    @Autowired
    public RestTemplate restTemplate;

    public List<Farmer> getAllFarmers(){
        return farmerRepo.findAll();
    }

    public Farmer getFarmerByEmail(String email){
        return farmerRepo.findByFarmerEmail(email);
    }

    public Farmer login(String email, String password){
        return farmerRepo.findByFarmerEmailAndFarmerPassword(email, password);
    }

    public String addFarmer(Farmer farmer){
        farmerRepo.save(farmer);
        return "Farmer added successfully";
    }

public String updateFarmer(Farmer farmer, String farmerEmail){
    Farmer farmerData = farmerRepo.findByFarmerEmail(farmerEmail);
    Optional<Farmer> farmerr = Optional.of(farmerData);
    if( farmerr.isPresent()){
        Farmer newFarmer = farmerr.get();
        newFarmer.setFarmerName(farmer.getFarmerName());
        newFarmer.setFarmerGender(farmer.getFarmerGender());
        newFarmer.setFarmerAddress(farmer.getFarmerAddress());
        newFarmer.setFarmerEmail(farmer.getFarmerEmail());
        newFarmer.setFarmerPhone(farmer.getFarmerPhone());
        newFarmer.setFarmerPassword(farmer.getFarmerPassword());
        farmerRepo.save(newFarmer);
        return "Farmer with name "+newFarmer.getFarmerName()+ " is successfully updated";
    }
    else{
        return "Farmer cannot be updated";
    }
}
public String deleteFarmer(String farmerEmail){
    // farmerRepo.deleteByFarmerEmail(farmerEmail);
    // return "Farmer with mailId " +farmerEmail+ " was deleted successfully";
    Farmer farmer = farmerRepo.findByFarmerEmail(farmerEmail);
    Optional<Farmer> farm = Optional.of(farmer);
    if (farm.isPresent()) {
        farmerRepo.deleteByFarmerEmail(farmerEmail);
        return "Farmer with mailId " +farmerEmail+ " was deleted successfully";
    }
    else{
        return "NO farmer with mailId" +farmerEmail+ " found";
    }
}







}
