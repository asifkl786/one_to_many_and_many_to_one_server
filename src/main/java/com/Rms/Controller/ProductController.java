package com.Rms.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Rms.DTO.ProductDTO;
import com.Rms.Service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
@Validated
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	
	// Create Product REST API
	@PostMapping("/create")
	private ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
		logger.info("Recived Request to create Product :: {} ", productDTO.getName());
		ProductDTO product = productService.createProduct(productDTO);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	// Get All Product REST API
	@GetMapping("/getAll")
	private ResponseEntity<List<ProductDTO>> getAllProduct(){
		logger.info("Recieved Request to get all Product ");
		List<ProductDTO> product = productService.getAllProduct();
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	// Get Product By Id REST API
	@GetMapping("getProduct/{id}")
	private ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
		logger.info("Reciaved Request to getProduct with id : {} ", id);
		ProductDTO product = productService.getProductById(id);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	// Build Delete REST API
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<String> deleteProduct(@PathVariable Long id){
		logger.info("Recieved Request to delete Product with id : {} ", id);
		productService.deleteProduct(id);
		return new ResponseEntity<>("Delete Product  Successfully ", HttpStatus.OK);			
	}
	
	// Build update product REST API
	@PutMapping("/update/{id}")
	private ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
		logger.info("Reciaved Request to Update Product with id :: {} ",id);
		ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
		return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
	}
	
	// Build Search Product By Name REST API
	@GetMapping("/search/by-name")
	private ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name){
		logger.info("Reciaved Request for Search Product with Name : {} ", name);
		List<ProductDTO> products = productService.searchProductByName(name);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	// Build Search Product By Price REST API
	@GetMapping("/search/by-price")
	private ResponseEntity<List<ProductDTO>> searchProductByPrice(@RequestParam Double price){
		logger.info("Recieaved Request for Search Product With Price : {} ", price);
		List<ProductDTO> products = productService.searchProductByPrice(price);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String testEndpoint() {
	    return "Test endpoint is working!";
	}
	

}
