package com.task.cropservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.cropservice.models.Crop;
import com.task.cropservice.repo.CropRepo;

@Service
public class CropService {
    
    @Autowired
    public CropRepo cropRepo;

    public List<Crop> getAllCrop(){
        return cropRepo.findAll();
    }

    public Optional<Crop> getCropById(String cropId){
        return cropRepo.findById(cropId);
    }

    public Crop addCrop(Crop crop){
        return cropRepo.save(crop);
    }

    public String updateCrop(Crop crop, String cropId) {
        Optional<Crop> cropOptional = cropRepo.findById(cropId);
        if(cropOptional.isPresent()){
            Crop cropD = cropOptional.get();
            cropD.setCropId(crop.getCropId());
            cropD.setCropName(crop.getCropName());
            cropD.setCropPrice(crop.getCropPrice());
            cropD.setCropType(crop.getCropType());
            cropD.setLocation(crop.getLocation());
            this.cropRepo.save(cropD);
            return "New crop details updated";
        }
        else{
            return "Details of crop not updated";
        }
    }

    public String deleteCrop(String cropId){
        cropRepo.deleteById(cropId);
        return "crop with ID "+cropId+ " is deleted";
    }
    

    public List<Crop> getFarmer(String farmerEmail){
        return cropRepo.findByFarmerEmail(farmerEmail);
    }

    public List<Crop> findByCropName(String cropName){
        return cropRepo.findByCropName(cropName);
    }
}
