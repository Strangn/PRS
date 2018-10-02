package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestRepository;

@Controller 
@RequestMapping("/PurchaseRequest")
public class PurchaseRequestController {
	
	@Autowired 
	private PurchaseRequestRepository purchaserequestRepository;

	@GetMapping("/List")
	public @ResponseBody Iterable<PurchaseRequest> getAllProducts()  {
		Iterable<PurchaseRequest> purchaserequests = purchaserequestRepository.findAll();
		return purchaserequests;
	}
	
	@GetMapping("/Get")
	public @ResponseBody Optional<PurchaseRequest> getProduct(@RequestParam int id)  {
		Optional<PurchaseRequest> purchaserequest = purchaserequestRepository.findById(id);
		return purchaserequest;
		
	} 
	@PostMapping("/Add")
	public @ResponseBody PurchaseRequest addPurchaseRequest(@RequestBody PurchaseRequest purchaserequest)  {
		return purchaserequestRepository.save(purchaserequest);
	}
	@PostMapping("/Change")
	public @ResponseBody PurchaseRequest updatePurchaseRequest(@RequestBody PurchaseRequest purchaserequest)  {
		return purchaserequestRepository.save(purchaserequest);
	}
	@PostMapping("/Remove")
	public @ResponseBody String removePurchaseRequest(@RequestBody PurchaseRequest purchaserequest)  {
		purchaserequestRepository.delete(purchaserequest);
		return "user deleted";
	}
}
