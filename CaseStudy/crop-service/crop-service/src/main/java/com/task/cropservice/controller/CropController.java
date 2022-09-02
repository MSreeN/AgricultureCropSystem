package com.task.cropservice.controller;

import java.util.List;
import java.util.Optional;



import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.task.cropservice.models.Crop;
import com.task.cropservice.service.CropService;

@RestController
@RequestMapping("/crop")
public class CropController {
    
    @Autowired
    public CropService cropService;

    @GetMapping("/list")
    public ResponseEntity<List<Crop>> getAllCrop(){
        try {
             return new ResponseEntity<>(cropService.getAllCrop(), HttpStatus.OK);
//            return  ResponseEntity.ok(cropService.getAllCrop());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cropById/{id}")
    public ResponseEntity<Optional<Crop>> getCrop(@PathVariable("id") String cropId){
        try {
            // return new ResponseEntity<>(cropService.getCropById(cropId),HttpStatus.FOUND);
            return new ResponseEntity<>(cropService.getCropById(cropId),HttpStatus.FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND );
        }
    }

    @GetMapping("/cropByFarmerEmail/{femail}")
    public ResponseEntity<List<Crop>> getFarmerByEmail(@PathVariable("femail") String femail){
        try {
            return new ResponseEntity<>(cropService.getFarmer(femail),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/list/add")
    public ResponseEntity<Crop> AddCrop(@RequestBody Crop crop){
        try {
            return new ResponseEntity<>(cropService.addCrop(crop),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    
    @PutMapping("/updateCrop/{id}")
    public ResponseEntity<String> updateCrop(@RequestBody Crop crop ,@PathVariable("id") String cropId){
        try {
            return new ResponseEntity<>(cropService.updateCrop(crop, cropId),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteCrop/{id}")
    public ResponseEntity<String> deleteCrop(@PathVariable("id") String cropId){
        try {
            return new ResponseEntity<>(cropService.deleteCrop(cropId),HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cropByName/{cropName}")
    public ResponseEntity<List<Crop>> getCropByName(@PathVariable("cropName") String cropName){
        try{
            return new ResponseEntity<>(cropService.findByCropName(cropName), HttpStatus.FOUND);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
