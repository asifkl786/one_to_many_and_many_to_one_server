package com.Rms.Service;

import java.util.List;

import com.Rms.DTO.ProductDTO;

public interface ProductService {
	
	ProductDTO createProduct(ProductDTO productDTO);
	ProductDTO getProductById(Long id);
	List<ProductDTO> getAllProduct();
	ProductDTO updateProduct(Long id, ProductDTO productDTO);
	void deleteProduct(Long id);
    List<ProductDTO> searchProductByName(String name);
    List<ProductDTO> searchProductByPrice(Double price);
    
}
