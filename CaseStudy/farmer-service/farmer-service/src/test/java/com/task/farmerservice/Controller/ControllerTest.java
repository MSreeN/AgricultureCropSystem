package com.task.farmerservice.Controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.task.farmerservice.controller.FarmerController;
import com.task.farmerservice.models.Crop;
import com.task.farmerservice.models.Farmer;
import com.task.farmerservice.service.FarmerService;

@SpringBootTest(classes = { ControllerTest.class})
public class ControllerTest {
    
    @Mock
    FarmerService farmerService;

    @InjectMocks
    FarmerController farmerController;

    @Mock
    RestTemplate restTemplate;
    List<Farmer> mockFarmer;
    List<Crop> mockCrop;
    
    @Test
    void getAllFarmerTest(){
        mockCrop = new ArrayList<Crop>();
        mockFarmer = new ArrayList<Farmer>();
        mockFarmer.add( new Farmer("3", "raj", "M", "9621487562", "raj@gmail.com", "raj", "bengaluru", mockCrop));
        mockCrop.add(new Crop("2", "grapes", "fruits", 45455, "bengaluru", "raj@gmail.com"));
        when(farmerService.getAllFarmers()).thenReturn(mockFarmer);
        ResponseEntity<List<Farmer>> response = farmerController.getAllFarmers();
        assertEquals(HttpStatus.OK , response.getStatusCode());
    }

    @Test
    void getFarmerDetails(){
        mockCrop = new ArrayList<Crop>();
        Farmer farmer =  new Farmer("3", "raj", "M", "9621487562", "raj@gmail.com", "raj", "bengaluru", mockCrop);
        mockCrop.add(new Crop("2", "grapes", "fruits", 45455, "bengaluru", "raj@gmail.com"));
        when(farmerService.getFarmerByEmail(farmer.getFarmerEmail())).thenReturn(farmer);
        ResponseEntity<Farmer> responseEntity = farmerController.getFarmerDetails(farmer.getFarmerEmail());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
	void AddFarmerTest()
	{
		mockCrop = new ArrayList<Crop>();
		 Farmer farmer = new Farmer("001", "Ravi kumar","Male","9911223312","ravi@gmail.com","Ravi@123","Patna",mockCrop);
		 mockCrop.add(new Crop("001","Apple","Fruit",120.0f,"Raipur","002"));
		 mockCrop.add(new Crop("002","Orange","Fruit",80.0f,"Nagpur","001"));
		 
		 when(farmerService.addFarmer(farmer)).thenReturn("Farmer added successfully");
		 ResponseEntity<String> responseEntity=farmerController.addFarmer(farmer);
		 
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		 assertEquals("Farmer added successfully", responseEntity.getBody());
	}


    @Test
	void deleteFarmerTest()
	{
		mockCrop = new ArrayList<Crop>();
		 Farmer farmer = new Farmer("001", "Ravi kumar","Male","9911223312","ravi@gmail.com","Ravi@123","Patna",mockCrop);
		 mockCrop.add(new Crop("001","Apple","Fruit",120.0f,"Raipur","002"));
		 mockCrop.add(new Crop("002","Orange","Fruit",80.0f,"Nagpur","001"));
		 String farmerEmail="ravi@gmail.com";
		 
		 ResponseEntity<String> responseEntity = farmerController.deleteFarmer(farmerEmail);
		 assertEquals(farmerEmail, farmer.getFarmerEmail());
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

    @Test
	void getFarmerCrop()
	{
		mockCrop = new ArrayList<Crop>();
		 Farmer farmer = new Farmer("001", "Ravi kumar","Male","9911223312","ravi@gmail.com","Ravi@123","Patna",mockCrop);
		 mockCrop.add(new Crop("001","Apple","Fruit",120.0f,"Raipur","002"));
		 mockCrop.add(new Crop("002","Orange","Fruit",80.0f,"Nagpur","001"));
		 String farmerEmail = "ravi@gmail.com";
		 
		 
		 when(farmerService.getFarmerByEmail(farmerEmail)).thenReturn(farmer);
		 when(restTemplate.getForObject("http://crop-service/crop/cropByFarmerEmail/"+farmer.getFarmerEmail(), List.class)).thenReturn(mockCrop);	
		 
		 ResponseEntity<Farmer> responseEntity= farmerController.getFarmerCrop(farmerEmail);
		 assertEquals(mockCrop, responseEntity.getBody().getCropsList());
		 
		 
	}

    @Test
	void updateFarmerTest()
	{
		mockCrop = new ArrayList<Crop>();
		 Farmer farmer = new Farmer("001", "Ravi kumar","Male","9911223312","ravi@gmail.com","Ravi@123","Patna",mockCrop);
		 mockCrop.add(new Crop("001","Apple","Fruit",120.0f,"Raipur","002"));
		 mockCrop.add(new Crop("002","Orange","Fruit",80.0f,"Nagpur","001"));
		String farmerEmail="ravi@gmail.com";
		
		when(farmerService.getFarmerByEmail(farmerEmail)).thenReturn(farmer);
		when(farmerService.updateFarmer(farmer, farmer.getFarmerEmail())).thenReturn("Farmer with name "+farmer.getFarmerName()+ " is successfully updated");
		assertEquals(farmerEmail, farmer.getFarmerEmail());
		ResponseEntity<String> responseEntity = farmerController.updateFarmer(farmer, farmerEmail);
        Farmer farmer1 = farmerService.getFarmerByEmail(farmerEmail);
		assertAll(
		() -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
		() -> assertEquals("001", farmer1.getFarmerId()),
		() -> assertEquals("ravi@gmail.com", farmer1.getFarmerEmail()),
		() -> assertEquals("Ravi kumar", farmer1.getFarmerName()),
		() -> assertEquals("9911223312", farmer1.getFarmerPhone()),
		() ->assertEquals("Patna", farmer1.getFarmerAddress()),
		() -> assertEquals("Ravi@123", farmer1.getFarmerPassword()),
		() -> assertEquals("Male", farmer1.getFarmerGender())
        );
	}

    @Test
	void getLogin()
	{
		mockCrop = new ArrayList<Crop>();
		 Farmer farmer = new Farmer("001", "Ravi kumar","Male","9911223312","ravi@gmail.com","Ravi@123","Patna",mockCrop);
		 mockCrop.add(new Crop("001","Apple","Fruit",120.0f,"Raipur","002"));
		 mockCrop.add(new Crop("002","Orange","Fruit",80.0f,"Nagpur","001"));
		String farmerEmail = "ravi@gmail.com";
		String farmerPassword = "Ravi@123";
		
		when(farmerService.login(farmerEmail, farmerPassword)).thenReturn(farmer);
		ResponseEntity<Farmer> responseEntity = farmerController.getLogin(farmer);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
