package com.task.dealerservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dealerservice.Models.CropOrder;
import com.task.dealerservice.Models.Dealer;
import com.task.dealerservice.Services.DealerService;

@RestController
@CrossOrigin
@RequestMapping("/dealer")
public class DealerController {
    
    @Autowired
    public DealerService dealerService;


     @GetMapping("/list")
     public ResponseEntity<List<Dealer>> getAllDealers(){
        try{
            List<Dealer> dealers = dealerService.getAllDealers();
            return new ResponseEntity<List<Dealer>>(dealers, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     }

     @GetMapping("/list/{email}")
     public ResponseEntity<Dealer> getDealerByEmail(@PathVariable("email") String email){
        try{
            return new ResponseEntity<Dealer>(dealerService.getDealerByEmail(email), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     }

     @PostMapping("/login")
     public ResponseEntity<Dealer> getLogin(@RequestBody Dealer dealer){
        try {
            Dealer dealer1 = null;
            String dealerEmail = dealer.getDealerEmail();
            String dealerPassword = dealer.getDealerPassword();
            if (dealerEmail != null && dealerPassword != null) {
                dealer1 = dealerService.getLogin(dealerEmail, dealerPassword);
            }
            return new ResponseEntity<Dealer>(dealer1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
     }

     @PostMapping("/list/add")
     public ResponseEntity<Dealer> addDealer(@RequestBody Dealer dealer)
     {
         try {
             return new ResponseEntity<Dealer>(dealerService.addDealer(dealer),HttpStatus.CREATED);
         }
         catch(Exception e)
         {
             return new ResponseEntity<>(HttpStatus.CONFLICT);
         }
         
     }

     @PostMapping("/order/create")
     public ResponseEntity<CropOrder> createOrder(@RequestBody CropOrder order)
     {
         try {
             return new ResponseEntity<>(dealerService.addCropOrder(order),HttpStatus.CREATED);
         }
         catch(Exception e)
         {
             return new ResponseEntity<>(HttpStatus.CONFLICT);
         }
         
     }

     @GetMapping("/order/{email}")
     public ResponseEntity<List<CropOrder>> getOrderbyDealer(@PathVariable("email") String dealerEmail)
     {
         try {
             return new ResponseEntity<>(dealerService.getCropOrderByDealer(dealerEmail),HttpStatus.OK);
         }
         catch(Exception e)
         {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
 
     }

     @PutMapping("/updateDealer/{email}")
     public ResponseEntity<Dealer> updateDealer(@RequestBody Dealer dealer ,@PathVariable("email") String dealerEmail)
     {
         try 
         {
             Dealer existingDealer = dealerService.getDealerByEmail(dealerEmail);
             existingDealer.setDealerName(dealer.getDealerName()); 
             existingDealer.setDealerPhone(dealer.getDealerPhone());
             existingDealer.setDealerAddress(dealer.getDealerAddress());
             existingDealer.setDealerEmail(dealer.getDealerEmail());
             existingDealer.setDealerPassword(dealer.getDealerPassword());
             existingDealer.setDealerGender(dealer.getDealerGender());
             Dealer updated_dealer = dealerService.updateDealer(existingDealer);
             return new ResponseEntity<>(updated_dealer,HttpStatus.OK);
         }
         catch(Exception e)
         {
             return new ResponseEntity<>(HttpStatus.CONFLICT);
         }
     }

     @DeleteMapping("/list/{id}")
     public ResponseEntity<String> deleteDealer(@PathVariable("id") String dealerId)
     {
         try {
             dealerService.deleteDealer(dealerId);
         }
         catch(Exception e)
         {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
         }
         return new ResponseEntity<>(dealerId+" was deleted",HttpStatus.OK);
         
         
     }


}
