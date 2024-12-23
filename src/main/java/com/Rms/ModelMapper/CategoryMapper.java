package com.Rms.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

import com.Rms.DTO.CategoryDTO;
import com.Rms.DTO.ProductDTO;
import com.Rms.Model.Category;
import com.Rms.Model.Product;



public class CategoryMapper {
	
	
	// Convert to Entity
	public static Category toEntity(CategoryDTO categoryDTO) {
		 if (categoryDTO == null) {
	            return null;
	        }
		Category category = new Category();
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		//category.setProducts(categoryDTO.getProducts()); set nahi hui thi
		
		// Convert List<ProductDTO> to List<Product>
		  if (categoryDTO.getProducts() != null) {
		        List<Product> products = categoryDTO.getProducts()
		        		.stream()
		        		.map(ProductMapper::toEntity)
		                .collect(Collectors.toList());
		        category.setProducts(products);
		     // Set the category in each product
	            products.forEach(product -> product.setCategory(category));
		    }

		    return category;
	
	}
	
	// Convert to DTO
	public static CategoryDTO mapToDTO(Category category) {
		 if (category == null) {
	            return null;
	        }
	    CategoryDTO dto = new CategoryDTO();
	    dto.setId(category.getId());
	    dto.setName(category.getName());
	   // dto.setProducts(category.getProducts()); set nahi ho rahi thi

	 // Convert List<Product> to List<ProductDTO>
	    if (category.getProducts() != null) {
	        List<ProductDTO> productDTOs = category.getProducts()
	        		.stream()
	                .map(ProductMapper::mapToDTO)
	                .collect(Collectors.toList());
	        dto.setProducts(productDTOs);
	    }

	    return dto;
	}

}
