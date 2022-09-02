package com.task.cropservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "crops")
public class Crop {
    @Id
	private String cropId;
	private String cropName;
	private String cropType;
	private float cropPrice;
	private String location;
	private String farmerEmail;
	
	public String getCropId() {
		return cropId;
	}
	public void setCropId(String cropId) {
		this.cropId = cropId;
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	public String getCropType() {
		return cropType;
	}
	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	public float getCropPrice() {
		return cropPrice;
	}
	
	public void setCropPrice(float cropPrice) {
		this.cropPrice = cropPrice;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	public String getFarmerEmail() {
		return farmerEmail;
	}
	public void setFarmerEmail(String farmerEmail) {
		this.farmerEmail = farmerEmail;
	}
	public Crop(String cropId, String cropName, String cropType, float cropPrice, String location,
			String farmerEmail) {
		super();
		this.cropId = cropId;
		this.cropName = cropName;
		this.cropType = cropType;
		this.cropPrice = cropPrice;
		this.location = location;
		this.farmerEmail = farmerEmail;
	}
	public Crop() {
		super();
	}
}
