package com.task.farmerservice.Service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.task.farmerservice.models.Crop;
import com.task.farmerservice.models.Farmer;
import com.task.farmerservice.repo.FarmerRepo;
import com.task.farmerservice.service.FarmerService;

@SpringBootTest(classes = {ServiceTest.class})
public class ServiceTest {
    
    @Mock
    FarmerRepo farmerRepo;
    @InjectMocks
    FarmerService  farmerService;

    public List<Farmer> mockFarmer;
    public List<Crop> mockCrop;


    @Test
    void getFarmerDetailsTest(){
        mockCrop = new ArrayList<Crop>();
        Farmer farmer = new Farmer("1", "sample", "F", "9587463258", "sample@gmail", "sample", "mumbai", mockCrop);
        mockCrop.add(new Crop("2", "onions", "vegetable", 454545, "mumbai", "sample@gmail.com"));
        String farmerEmail = "sample@gmail.com";
        when(farmerRepo.findByFarmerEmail(farmerEmail)).thenReturn(farmer);
        assertEquals(farmer, farmerService.getFarmerByEmail("sample@gmail.com"));
    }

    @Test
    void getAllFarmerTest(){
        mockCrop = new ArrayList<Crop>();
        mockFarmer = new ArrayList<Farmer>();
        mockFarmer.add( new Farmer("3", "raj", "M", "9621487562", "raj@gmail.com", "raj", "bengaluru", mockCrop));
        mockCrop.add(new Crop("2", "grapes", "fruits", 45455, "bengaluru", "raj@gmail.com"));
        when(farmerRepo.findAll()).thenReturn(mockFarmer);
        assertEquals(mockFarmer, farmerService.getAllFarmers());
    }

    @Test
    void addFarmerTest(){
        mockCrop = new ArrayList<Crop>();
        Farmer farmer = new Farmer("1", "sample", "F", "9587463258", "sample@gmail", "sample", "mumbai", mockCrop);
        mockCrop.add(new Crop("2", "onions", "vegetable", 454545, "mumbai", "sample@gmail.com"));
        when(farmerRepo.save(farmer)).thenReturn(farmer);
        assertEquals("Farmer added successfully", farmerService.addFarmer(farmer));

    }

    @Test
    void updateFarmerTest(){
        mockCrop = new ArrayList<Crop>();
        Farmer farmer = new Farmer("1", "sample", "F", "9587463258", "sample@gmail", "sample", "mumbai", mockCrop);
        mockCrop.add(new Crop("2", "onions", "vegetable", 454545, "mumbai", "sample@gmail.com"));
        when(farmerRepo.findByFarmerEmail(farmer.getFarmerEmail())).thenReturn(farmer);
        when(farmerRepo.save(farmer)).thenReturn(farmer);
        assertEquals("Farmer with name "+farmer.getFarmerName()+ " is successfully updated", farmerService.updateFarmer(farmer, farmer.getFarmerEmail()));
    }

    @Test
    void deleteFarmerTest(){
        mockCrop = new ArrayList<Crop>();
        Farmer farmer = new Farmer("1", "sample", "F", "9587463258", "sample@gmail", "sample", "mumbai", mockCrop);
        mockCrop.add(new Crop("2", "onions", "vegetable", 454545, "mumbai", "sample@gmail.com"));
        when(farmerRepo.findByFarmerEmail(farmer.getFarmerEmail())).thenReturn(farmer);
        // when(farmerRepo.deleteByFarmerEmail(farmer.getFarmerEmail())).thenReturn(Optional.of(farmer));
        assertEquals("Farmer with mailId " +farmer.getFarmerEmail() + " was deleted successfully", farmerService.deleteFarmer(farmer.getFarmerEmail()));

    }

    @Test
    void getLoginTest(){
        mockCrop = new ArrayList<Crop>();
        Farmer farmer = new Farmer("1", "sample", "F", "9587463258", "sample@gmail", "sample", "mumbai", mockCrop);
        mockCrop.add(new Crop("2", "onions", "vegetable", 454545, "mumbai", "sample@gmail.com"));
        when(farmerRepo.findByFarmerEmailAndFarmerPassword(farmer.getFarmerEmail(), farmer.getFarmerPassword())).thenReturn(farmer);
        assertAll(
            () -> assertEquals(farmer.getFarmerEmail(), farmerService.login(farmer.getFarmerEmail(), farmer.getFarmerPassword()).getFarmerEmail()),
            () -> assertEquals(farmer.getFarmerPassword(), farmerService.login(farmer.getFarmerEmail(), farmer.getFarmerPassword()).getFarmerPassword())

        );
    }
}
