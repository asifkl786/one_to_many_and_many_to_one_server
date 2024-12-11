package com.Rms.ModelMapper;

import com.Rms.DTO.ProductDTO;
import com.Rms.Model.Category;
import com.Rms.Model.Product;



public class ProductMapper {
	
	// Convert to Entity
		public static Product toEntity(ProductDTO productDTO) {
			 if (productDTO == null) {
		            return null;
		        }
		    Product product = new Product();
		    product.setId(productDTO.getId());
		    product.setName(productDTO.getName());
		    product.setPrice(productDTO.getPrice());

		    // Since this is many-to-one, you need to set the parent category manually
		    if (productDTO.getCategoryId() != null) {
		        Category category = new Category();
		        category.setId(productDTO.getCategoryId());
		     // Associate product with category (bi-directional relationship)
		        product.setCategory(category);
		        category.getProducts().add(product); // Ensure category knows about this product
		    }

		    return product;
		}
		
		
		// Convert to DTO
		public static ProductDTO mapToDTO(Product product) {
			 if (product == null) {
			        return null;
			    }
		    ProductDTO dto = new ProductDTO();
		    dto.setId(product.getId());
		    dto.setName(product.getName());
		    dto.setPrice(product.getPrice());
		    dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
		    return dto;
		}

}
