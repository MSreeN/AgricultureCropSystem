package com.task.cropservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.cropservice.models.Crop;
import com.task.cropservice.service.CropService;

// @ComponentScan(basePackages = "io.cropProject")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {ControllerMokitoMVC.class})
class ControllerMokitoMVC {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CropService cropService;
	
	@InjectMocks
	CropController cropController;
	
	List<Crop> mockCrop;
	Crop crop;
	
	@BeforeEach
	public void setUp()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(cropController).build();
		
	}
	
	@Test
	void getAllCropTest() throws Exception
	{
	
		mockCrop = new ArrayList<Crop>();
		mockCrop.add(new Crop("001","Apple","Fruit",120.0f,"Raipur","002"));
		mockCrop.add(new Crop("002","Orange","Fruit",80.0f,"Nagpur","001"));
		
		when(cropService.getAllCrop()).thenReturn(mockCrop);
		
		this.mockMvc.perform(get("/crop/list")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	void getCropTest() throws Exception
	{
		crop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","raju@gmail.com");
		String cropId="003";
		
		when(cropService.getCropById(cropId)).thenReturn(Optional.of(crop));
		assertEquals(cropId, crop.getCropId());
		this.mockMvc.perform(get("/crop/list/{id}",cropId))
					.andExpect(status().isFound())
					.andExpect(MockMvcResultMatchers.jsonPath(".cropId").value("003"))
					.andExpect(MockMvcResultMatchers.jsonPath(".cropName").value("Carrot"))
					.andExpect(MockMvcResultMatchers.jsonPath(".cropType").value("Vegetable"))
					.andExpect(MockMvcResultMatchers.jsonPath(".cropPrice").value(50.0))
					.andExpect(MockMvcResultMatchers.jsonPath(".location").value("Patna"))
					.andExpect(MockMvcResultMatchers.jsonPath(".farmerEmail").value("raju@gmail.com"))
					.andDo(print());
		
		
	}
	
	@Test
	void AddCropTest() throws Exception
	{
		crop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		
		when(cropService.addCrop(crop)).thenReturn(crop);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(crop);
		
		this.mockMvc.perform(post("/crop/list/add")
								.content(jsonbody)
								.contentType(MediaType.APPLICATION_JSON)
							)
				.andExpect(status().isCreated())
				.andDo(print());
		
				
	}
	
	@Test
	void updateCropTest() throws Exception
	{
		crop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		String cropid="003";
		
		when(cropService.updateCrop(crop, cropid)).thenReturn(cropid);
		assertEquals(cropid, crop.getCropId());
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(crop);
		
		this.mockMvc.perform(put("/crop/list/{id}",cropid)
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON)
				)
			.andExpect(status().isOk())
			.andDo(print());
		
	}
	
	@Test
	void deleteCropTest() throws Exception
	{
		crop = new Crop("003","Carrot","Vegetable",50.0f,"Patna","002");
		String cropid="003";
		
		when(cropService.deleteCrop(cropid)).thenReturn(cropid);
		assertEquals(cropid, crop.getCropId());
		this.mockMvc.perform(delete("/crop/list/{id}",cropid))
					.andExpect(status().isOk());
		
	}
}
