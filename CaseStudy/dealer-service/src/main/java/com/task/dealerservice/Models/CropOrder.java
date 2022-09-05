package com.task.dealerservice.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CropOrder {
    
    @Id
    private String orderId;
    private String dealerEmail;
    private String cropName;
    private float cropPrice;
    private int corpQnt;
    private float totalAmount;
    private String farmerEmail;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public CropOrder(String orderId, String dealerEmail, String cropName, float cropPrice, int corpQnt,
            float totalAmount, String farmerEmail) {
        this.orderId = orderId;
        this.dealerEmail = dealerEmail;
        this.cropName = cropName;
        this.cropPrice = cropPrice;
        this.corpQnt = corpQnt;
        this.totalAmount = totalAmount;
        this.farmerEmail = farmerEmail;
    }
    public CropOrder() {
    }
    public String getDealerEmail() {
        return dealerEmail;
    }
    public void setDealerEmail(String dealerEmail) {
        this.dealerEmail = dealerEmail;
    }
    public String getCropName() {
        return cropName;
    }
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }
    public float getCropPrice() {
        return cropPrice;
    }
    public void setCropPrice(float cropPrice) {
        this.cropPrice = cropPrice;
    }
    public int getCorpQnt() {
        return corpQnt;
    }
    public void setCorpQnt(int corpQnt) {
        this.corpQnt = corpQnt;
    }
    public float getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getFarmerEmail() {
        return farmerEmail;
    }
    public void setFarmerEmail(String farmerEmail) {
        this.farmerEmail = farmerEmail;
    }


}
