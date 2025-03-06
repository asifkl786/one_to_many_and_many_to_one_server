package com.Rms.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

	private Long id;
	
	@NotBlank(message = "Product name is required")
    private String name;
    
    @NotNull(message = "Price is required")
    private Double price;
    
    @NotNull(message = "Category ID is required")
    private Long categoryId; // Reference to the parent category
    
}
