package com.task.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.dealerservice.Models.CropOrder;
import com.task.dealerservice.Models.Dealer;
import com.task.dealerservice.Repo.CropOrderRepo;
import com.task.dealerservice.Repo.DealerRepo;
import com.task.dealerservice.Services.DealerService;

@SpringBootTest(classes = {ServiceTest.class})
public class ServiceTest {
    @Mock
	DealerRepo dealerRepo;
	
	@Mock
	CropOrderRepo orderRepo;
	
	@InjectMocks
	DealerService dealerService;
	
	public List<Dealer> mockDealer;
	public List<CropOrder> mockOrder;
	
	@Test
	void allDealerTest()
	{
		mockDealer = new ArrayList<>();
		mockDealer.add(new Dealer("001","Raju kumar","9992231523","Noida","raju@gmail.com","Raju@123","male"));
		mockDealer.add(new Dealer("002","Raj kumar","8762231521","Kolkata","raj@gmail.com","Raj@123","male"));
		
		
		
		when(dealerRepo.findAll()).thenReturn(mockDealer);
		assertEquals(2, dealerService.getAllDealers().size());
	}
	
	@Test
	void getDealerbyemailTest()
	{
		Dealer mockDealer = new Dealer("003","Raju kumar","9992231523","Noida","raju@gmail.com","Raju@123","male");
		String dealerEmail="raju@gmail.com";
		
		when(dealerRepo.findByDealerEmail(dealerEmail)).thenReturn(mockDealer);
		assertEquals(dealerEmail, dealerService.getDealerByEmail(dealerEmail).getDealerEmail());
	}
	
	@Test
	void AddDealer()
	{
		Dealer dealer = new Dealer("003","Raju kumar","9992231523","Noida","raju@gmail.com","Raju@123","male");
		
		when(dealerRepo.save(dealer)).thenReturn(dealer);
		assertEquals(dealer, dealerService.addDealer(dealer));
	}
	
	@Test
	void updateDealer()
	{
		Dealer dealer = new Dealer("003","Raju kumar","9992231523","Noida","raju@gmail.com","Raju@123","male");
		
		when(dealerRepo.save(dealer)).thenReturn(dealer);
		assertEquals(dealer, dealerService.updateDealer(dealer));
	}
	
	@Test
	void deleteDealer()
	{
		Dealer dealer = new Dealer("003","Raju kumar","9992231523","Noida","raju@gmail.com","Raju@123","male");
		String dealerId="003";
		
		when(dealerRepo.findById(dealerId)).thenReturn(Optional.of(dealer));
		assertEquals("Dealer was successfully deleted", dealerService.deleteDealer(dealerId));
		
	}
	
	@Test
	void Ordercreate()
	{
		CropOrder order =new CropOrder("003","raju@gmail.com","Apple",60,3,180, "sree@gmail.com");
		when(orderRepo.save(order)).thenReturn(order);
		assertEquals(order, dealerService.addCropOrder(order));
	}
	
	@Test
	void getOrderById() 
	{
		mockOrder = new ArrayList<>();
		mockOrder.add(new CropOrder("002","raju@gmail.com","Banana",20,9,180, "sree@gmail.com"));
		mockOrder.add(new CropOrder("003","raju@gmail.com","Apple",60,3,180, "sree@gmail.com"));
		String dealerEmail="raju@gmail.com";
		
		when(orderRepo.findByDealerEmail(dealerEmail)).thenReturn(mockOrder);
		assertEquals(2, dealerService.getCropOrderByDealer(dealerEmail).size());
		
	}
	
	@Test
	void getLogin()
	{
		Dealer dealer = new Dealer("003","Raju kumar","9992231523","Noida","raju@gmail.com","Raju@123","male");
		String dealerEmail = "raju@gmail.com";
		String dealerPassword = "Raj@123";
		
		when(dealerRepo.findByDealerEmailAndDealerPassword(dealerEmail, dealerPassword)).thenReturn(dealer);
		assertEquals(dealer.getDealerEmail(),dealerEmail);
		assertEquals(dealer,dealerService.getLogin(dealerEmail, dealerPassword));
	}
}
