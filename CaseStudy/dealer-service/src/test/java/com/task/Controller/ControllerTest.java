package com.task.Controller;

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

import com.task.dealerservice.Controller.DealerController;
import com.task.dealerservice.Models.CropOrder;
import com.task.dealerservice.Models.Dealer;
import com.task.dealerservice.Services.DealerService;

@SpringBootTest(classes = {ControllerTest.class})
public class ControllerTest {
    @Mock
	DealerService dealerService;
	
	@InjectMocks
	DealerController dealerController;
	
	List<Dealer> mockDealer;
	Dealer dealer;
	
	List<CropOrder> MockOrder;
	List<CropOrder> mockOrder;
	CropOrder order;
	
	
	
	@Test
	void getAllDealerTest()
	{
		mockDealer = new ArrayList<Dealer>();
		mockDealer.add(new Dealer("001","Raju kumar","9992231523","Noida","raju@gmail.com","Raju@123","male"));
		mockDealer.add(new Dealer("002","Raj kumar","8762231521","Kolkata","raj@gmail.com","Raj@123","male"));
		
		when(dealerService.getAllDealers()).thenReturn(mockDealer);
		ResponseEntity<List<Dealer>> res=dealerController.getAllDealers();
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(2,res.getBody().size());
	}
	
	@Test
	void getDealerByEmailTest()
	{
		dealer = new Dealer("003","Raju kumar","9992231523","Noida","raju@gmail.com","Raju@123","male");
		String dealerEmail="raju@gmail.com";
		
		when(dealerService.getDealerByEmail(dealerEmail)).thenReturn(dealer);
	
		ResponseEntity<Dealer> res= dealerController.getDealerByEmail(dealerEmail);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(dealer, res.getBody());
		
	}
	
	@Test
	void AddDealer()
	{
		dealer = new Dealer("003","Raju kumar","9992231523","Noida","raju@gmail.com","Raju@123","male");
		
		when(dealerService.addDealer(dealer)).thenReturn(dealer);
		ResponseEntity<Dealer> res=dealerController.addDealer(dealer);
		
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(dealer, res.getBody());
	}
	
	@Test
	void updateDealer()
	{
		dealer = new Dealer("003","Ram kumar","9992231523","Gujrat","ram@gmail.com","Ram@123","male");
		String dealerEmail="ram@gmail.com";
		
		when(dealerService.getDealerByEmail(dealerEmail)).thenReturn(dealer);
		when(dealerService.updateDealer(dealer)).thenReturn(dealer);
		assertEquals(dealerEmail, dealer.getDealerEmail());
		ResponseEntity<Dealer> res=dealerController.updateDealer(dealer, dealerEmail);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals("003", res.getBody().getDealerId());
		assertEquals("ram@gmail.com", res.getBody().getDealerEmail());
		assertEquals("Ram kumar", res.getBody().getDealerName());
		assertEquals("9992231523", res.getBody().getDealerPhone());
		assertEquals("Gujrat", res.getBody().getDealerAddress());
		assertEquals("Ram@123", res.getBody().getDealerPassword());
		assertEquals("male", res.getBody().getDealerGender());

	}
	
	
	@Test
	void deleteDealer()
	{
		dealer = new Dealer("003","Ram kumar","9992231523","Gujrat","ram@gmail.com","Ram@123","male");
		String dealerId="003";
		
		ResponseEntity<String> res=dealerController.deleteDealer(dealerId);
		assertEquals(dealerId, dealer.getDealerId());
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
	}
	
	@Test
	void getLoginTest()
	{
		dealer = new Dealer("003","Ram kumar","9992231523","Gujrat","ram@gmail.com","Ram@123","male");
		
		String dealerEmail = dealer.getDealerEmail();
		String dealerPassword = dealer.getDealerPassword();
		
		when(dealerService.getLogin(dealerEmail, dealerPassword)).thenReturn(dealer);
		ResponseEntity<Dealer> res = dealerController.getLogin(dealer);
		assertEquals(HttpStatus.OK, res.getStatusCode());
	}
	
	@Test
	void OrderCreateTest()
	{
		CropOrder order =new CropOrder("003","raju@gmail.com","Apple",60,3,180, "sree@gmail.com");
		
		when(dealerService.addCropOrder(order)).thenReturn(order);
		ResponseEntity<CropOrder> res=dealerController.createOrder(order);
		
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(order, res.getBody());
		
	}
	
	@Test
	void getOrderById()
	{
		mockOrder = new ArrayList<>();
		mockOrder.add(new CropOrder("002","raju@gmail.com","Banana",20,9,180,"sree@gmail.com"));
		mockOrder.add(new CropOrder("003","raju@gmail.com","Apple",60,3,180,"sree@gmail.com"));
		String dealerEmail = "raju@gmail.com";
		when(dealerService.getCropOrderByDealer(dealerEmail)).thenReturn(mockOrder);
		ResponseEntity<List<CropOrder>> res=dealerController.getOrderbyDealer(dealerEmail);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(2,res.getBody().size());
		
	}
}
