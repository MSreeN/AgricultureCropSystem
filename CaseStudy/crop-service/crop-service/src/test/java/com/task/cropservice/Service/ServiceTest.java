package com.task.cropservice.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.cropservice.models.Crop;
import com.task.cropservice.repo.CropRepo;
import com.task.cropservice.service.CropService;

@SpringBootTest(classes = {ServiceTest.class})
public class ServiceTest {
    @Mock
	CropRepo cropRepo;
	
	@InjectMocks
	CropService cropService;
	
	public List<Crop> mockProduct;
	
	@Test
	public void getAllCropTest()
	{
		List<Crop> mockCrop = new ArrayList<Crop>();
		mockCrop.add(new Crop("001","Apple","Fruit",120.0f,"Raipur","002"));
		mockCrop.add(new Crop("002","Orange","Fruit",80.0f,"Nagpur","001"));
		
		
		
		when(cropRepo.findAll()).thenReturn(mockCrop);
		assertEquals(2, cropService.getAllCrop().size());
	}
	

	@Test
	public void getCropTest()
	{
		Crop mockCrop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		String CropId="003";
		
		when(cropRepo.findById(CropId)).thenReturn(Optional.of(mockCrop));
		assertEquals(CropId, cropService.getCropById(CropId).get().getCropId());
	}
	
	@Test
	public void AddCropTest()
	{
		Crop mockCrop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		
		when(cropRepo.save(mockCrop)).thenReturn(mockCrop);
		assertEquals(mockCrop, cropService.addCrop(mockCrop));
	}
	
	@Test
	public void updateCropTest()
	{
		Crop mockCrop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		String CropId = "003";
		
		when(cropRepo.findById(CropId)).thenReturn(Optional.of(mockCrop));
		assertEquals("New crop details updated", cropService.updateCrop(mockCrop, CropId));
	}
	
	@Test
	public void deleteCropTest()
	{
		Crop mockCrop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		String CropId = "003";
		
		when(cropRepo.findById(CropId)).thenReturn(Optional.of(mockCrop));
		assertEquals("crop with ID "+CropId+" is deleted", cropService.deleteCrop(CropId));
		
	}
}
