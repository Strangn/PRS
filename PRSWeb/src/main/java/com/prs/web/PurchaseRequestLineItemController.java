package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prs.business.purchaserequestlineitem.PurchaseRequestLineItem;
import com.prs.business.purchaserequestlineitem.PurchaseRequestLineItemRepository;
import com.prs.util.JsonResponse;

@CrossOrigin
@Controller
@RequestMapping("/PurchaseRequestLineItem")
public class PurchaseRequestLineItemController {
	
	@Autowired
	private PurchaseRequestLineItemRepository purchaserequestlineitemRepository;
	
	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllPurchaseRequestLineItem() {
		try {	
			return JsonResponse.getInstance(purchaserequestlineitemRepository.findAll());
		}
		catch (Exception prli) {
			return JsonResponse.getErrorInstance("PurchaseRequestLineItem list failure:"+prli.getMessage(), prli);
		}
	}
	
	@GetMapping("/Get/{id}")
	public @ResponseBody JsonResponse getPurchaseRequestLineItem(@PathVariable int id) {
		try {
			Optional<PurchaseRequestLineItem> PurchaseRequestLineItem = purchaserequestlineitemRepository.findById(id);
			if (PurchaseRequestLineItem.isPresent())
				return JsonResponse.getInstance(PurchaseRequestLineItem.get());
			else
				return JsonResponse.getErrorInstance("PurchaseRequestLineItem not found for id: "+id, null);
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Error getting purchaserequestlineitem:  "+e.getMessage(), null);
		}
	}

	

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addNewPurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem purchaserequestlineitem) {
		return savePurchaseRequestLineItem(purchaserequestlineitem);
	}
	@PostMapping("/Change")
	public @ResponseBody JsonResponse updatePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem purchaserequestlineitem) {
		return savePurchaseRequestLineItem(purchaserequestlineitem);
	}

	private @ResponseBody JsonResponse savePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem purchaserequestlineitem) {
		try {
			purchaserequestlineitemRepository.save(purchaserequestlineitem);
			return JsonResponse.getInstance(purchaserequestlineitem);
		} catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem purchaserequestlineitem) {
		try {
			purchaserequestlineitemRepository.delete(purchaserequestlineitem);
			return JsonResponse.getInstance(purchaserequestlineitem);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
	
}