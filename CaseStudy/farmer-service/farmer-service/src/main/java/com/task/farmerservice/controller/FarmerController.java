package com.task.farmerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.task.farmerservice.models.Farmer;
import com.task.farmerservice.repo.FarmerRepo;
import com.task.farmerservice.service.FarmerService;

@RestController
@CrossOrigin
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired
    public FarmerService farmerService;
    @Autowired
    public RestTemplate restTemplate;
    @Autowired
    public FarmerRepo  farmerRepo;

    @GetMapping("/list")
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        try{
            return new ResponseEntity<>(farmerService.getAllFarmers(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/farmerCrop/{email}")
    public ResponseEntity<Farmer> getFarmerCrop(@PathVariable("email")String email){
        try{
            Farmer farmer = this.farmerService.getFarmerByEmail(email);
            @SuppressWarnings("rawtypes")
            List cropList = this.restTemplate.getForObject("http://crop-service/crop/cropByFarmerEmail/"+farmer.getFarmerEmail(), List.class);
            farmer.setCropsList(cropList);
            return new ResponseEntity<>(farmer, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("farmerDetails/{farmerEmail}")
    public ResponseEntity<Farmer> getFarmerDetails(@PathVariable("farmerEmail") String farmerEmail){
        try {
            return new ResponseEntity<>(farmerService.getFarmerByEmail(farmerEmail), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFarmer(@RequestBody Farmer farmer){
        try{
            return new ResponseEntity<>(farmerService.addFarmer(farmer), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/updateFarmer/{email}")
    public ResponseEntity<String> updateFarmer(@RequestBody Farmer farmer, @PathVariable("email") String farmerEmail){
        try{
            return new ResponseEntity<>(farmerService.updateFarmer(farmer, farmerEmail), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteFarmer/{email}")
    public ResponseEntity<String> deleteFarmer(@PathVariable("email") String email){
        try{
            return new ResponseEntity<>(farmerService.deleteFarmer(email), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/farmerLogin")
    public ResponseEntity<Farmer> getLogin(@RequestBody Farmer farmer){
        String farmerEmail = farmer.getFarmerEmail();
        String farmerPassword = farmer.getFarmerPassword();
        try{
            Farmer farmerObj = null;
            if(farmerEmail != null && farmerPassword != null){
                farmerObj = farmerService.login(farmerEmail, farmerPassword);
            }
            return new ResponseEntity<>(farmerObj, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    


}
