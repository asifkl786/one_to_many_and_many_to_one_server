package com.Rms.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDTO {
	
    private Long id;
    private String name;
    private List<ProductDTO> products;
    
}
