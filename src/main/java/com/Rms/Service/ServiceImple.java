package com.Rms.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rms.DTO.CategoryDTO;
import com.Rms.Exception.ResourceNotFoundException;
import com.Rms.Model.Category;
import com.Rms.Model.Product;
import com.Rms.ModelMapper.CategoryMapper;
import com.Rms.Repository.CategoryRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ServiceImple implements CategoryService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceImple.class);
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	// Create Category 
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		logger.info("Creating Category: {}", categoryDTO);
	Category category = CategoryMapper.toEntity(categoryDTO);
	Category savedCategory = categoryRepository.save(category);
		return CategoryMapper.mapToDTO(savedCategory);
	}

	// Get Category By id
	@Override
	public CategoryDTO getCategoryById(Long id) {
		logger.info("Get Category With Id : {} ", id);
	       Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Category is not exists with given id :"  + id));
		return CategoryMapper.mapToDTO(category);
	}

	// Get All Category
	@Override
	public List<CategoryDTO> getAllCategory() {
		logger.info("get all category");
		List<Category> categorys = categoryRepository.findAll();
		return categorys.stream().map(CategoryMapper::mapToDTO).collect(Collectors.toList());
	}

	
	// Update Category
	@Override
	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
		logger.info("Update Category With id : {} ", id);
		 Category category = categoryRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Category is not exists with given id :"  + id));
		 category.setId(categoryDTO.getId());
		 category.setName(categoryDTO.getName());
		 Category savedCategory = categoryRepository.save(category);
		return CategoryMapper.mapToDTO(savedCategory);
	}

	// Delete Category
	@Override
	public void deleteCategory(Long id) {
		logger.info("Delete Category With id: {} ", id);
		Category category = categoryRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Category is not exists with given id :"  + id));
		categoryRepository.deleteById(id);
		
	}

	@Override
	public List<CategoryDTO> searchCategory(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
