package com.prs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.aspectj.apache.bcel.util.Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.product.Product;
import com.prs.business.product.ProductRepository;
import com.prs.business.vendor.Vendor;
import com.prs.business.vendor.VendorRepository;

@RunWith(SpringRunner.class)
public class PrsProductTest extends PrsWebApplicationTests  {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void testProductCrudFunctions()  {
		//get all vendors
		Iterable<Product> products = productRepository.findAll();
		assertNotNull(products);
		
		//add a user
		Product p1 = new Product("vendor", "partNumber", "name", "price", "unit", "photoPath", true, true);
		assertNotNull(productRepository.save(p1));
		int id = p1.getId();

		//get user and validate productname is correct
		Optional<Product> v2 = productName
				Repository.findById(id);
		assertEquals(v2.get().getProduct(),"ProductName");
		
		//update the product
		p2.get().setProduct("newProduct");
		assertNotNull(productRepository.save(p2.get()));
		
		// remove the product
		productRepository.delete(p2.get());
		assertThat(!(productRepository.findById(id)).isPresent());
	}
	

	
}
