package com.Rms.Service;

import java.util.List;

import com.Rms.DTO.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	CategoryDTO getCategoryById(Long id);
	List<CategoryDTO> getAllCategory();
	CategoryDTO updateCategory(Long id , CategoryDTO categoryDTO);
	void deleteCategory(Long id);
	List<CategoryDTO> searchCategory(String query);
	

}
