package com.task.cropservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.task.cropservice.models.Crop;
import com.task.cropservice.service.CropService;



@SpringBootTest(classes = {ControllerTest.class})
public class ControllerTest {
    
    @Mock
	CropService cropService;
	
	@InjectMocks
	CropController cropController;
	
	List<Crop> mockCrop;
	
	@Test
	public void getAllCropTest()
	{
		mockCrop = new ArrayList<Crop>();
		mockCrop.add(new Crop("001","Apple","Fruit",120.0f,"Raipur","002"));
		mockCrop.add(new Crop("002","Orange","Fruit",80.0f,"Nagpur","001"));
		
		when(cropService.getAllCrop()).thenReturn(mockCrop);
		ResponseEntity<List<Crop>> res = cropController.getAllCrop();
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(2, res.getBody().size());
	}
	
	@Test
	public void getCropByIdTest()
	{
		Crop mockCrop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		String CropId="003";
		
		when(cropService.getCropById(CropId)).thenReturn(Optional.of(mockCrop));
		ResponseEntity<Optional<Crop>> res = cropController.getCrop(CropId);
		assertEquals(CropId, mockCrop.getCropId());
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(CropId, res.getBody().get().getCropId());
	}
	
	@Test
	public void AddCropTest()
	{
		Crop mockCrop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		
		when(cropService.addCrop(mockCrop)).thenReturn(mockCrop);
		ResponseEntity<Crop> res= cropController.AddCrop(mockCrop);
		
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(mockCrop, res.getBody());
	}
	
	@Test
	public void updateCropTest()
	{
		Crop mockCrop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		String CropId="003";
		
		when(cropService.updateCrop(mockCrop, CropId)).thenReturn(CropId);
		
		ResponseEntity<String> res=cropController.updateCrop(mockCrop, CropId);
		assertEquals(CropId, mockCrop.getCropId());
		assertEquals(HttpStatus.OK, res.getStatusCode());
	}
	
	@Test
	public void deleteCropTest()
	{
		Crop mockCrop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		String CropId="003";
		
		ResponseEntity<String> res= cropController.deleteCrop(CropId);
		assertEquals(CropId, mockCrop.getCropId());
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		
	}
	
}

