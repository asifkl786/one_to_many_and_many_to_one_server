package com.Rms.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Rms.DTO.CategoryDTO;
import com.Rms.Service.CategoryService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	// Build Create REST API
	@PostMapping("/create")
	private ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
		logger.info("Received request to create Category: {}", categoryDTO.getName());
		CategoryDTO categoryDto = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(categoryDto,HttpStatus.CREATED);
	}
	
	// Build Get CategoryById REST API
	@GetMapping("/{id}")
	private ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
		logger.info("Received Request to get category With Id : {}", id);
	CategoryDTO category = categoryService.getCategoryById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	// Build Get All Category REST API
	@GetMapping("/getAllCategory")
	private ResponseEntity<List<CategoryDTO>> getAllCategory(){
		logger.info("Received Request to Fetch All Category ");
		List<CategoryDTO> category = categoryService.getAllCategory();
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
	
	// Build Update Category REST API
	@PutMapping("/update/{id}")
	private ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
		logger.info("Received Request to Update Category With Id : {} " , id);
		CategoryDTO category = categoryService.updateCategory(id, categoryDTO);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	// Build Delete Category REST API
	@DeleteMapping("delete/{id}")
	private ResponseEntity<String> deleteCategory(@PathVariable Long id){
		logger.info("Received Request to Delete Category With id : {} ",id);
		categoryService.deleteCategory(id);
		return new ResponseEntity<>("Delete Category Succesfully", HttpStatus.OK);
		
	}

}
