package com.Rms.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Rms.DTO.ProductDTO;
import com.Rms.Exception.ResourceNotFoundException;
import com.Rms.Model.Category;
import com.Rms.Model.Product;
import com.Rms.ModelMapper.ProductMapper;
import com.Rms.Repository.CategoryRepository;
import com.Rms.Repository.ProductRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProductServiceImple implements ProductService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImple.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
    private CategoryRepository categoryRepository;

	
	// Create product 
	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		logger.info("{} :: Product Creating..... ",productDTO.getName());
		// Fetch the Category
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + productDTO.getCategoryId()));
        
        // Map DTO to Product entity
		Product product = ProductMapper.toEntity(productDTO);
		Product savedProduct = productRepository.save(product);
		logger.info("{}:: Product Created Successfully",savedProduct.getName());
		return ProductMapper.mapToDTO(savedProduct);
	}

	// Get Product By  Id
	@Override
	public ProductDTO getProductById(Long id) {
		logger.info("Geting Product with id.... :: {} ", id);
		Product product = productRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		logger.info("{} :: Product Found Successfully",product.getName());
		return ProductMapper.mapToDTO(product);
	}

	
	// Get All Product
	@Override
	public List<ProductDTO> getAllProduct() {
		logger.info("Get All Product....");
		List<Product> products = productRepository.findAll();
		logger.info("{} Products Successfully Found",products.size());
		return products.stream().map(ProductMapper::mapToDTO).collect(Collectors.toList());
	}
	
	
	// Update Product
	@Override
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		logger.info("Updating Product with id.... :: {}", id);
		Product product = productRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		
		// Save product to  database
		Product savedProduct = productRepository.save(product);
		logger.info("{} :: Product Update Successfully",savedProduct.getName());
		return ProductMapper.mapToDTO(savedProduct);
	}
    
	
	// Delete Product
	@Override
	public void deleteProduct(Long id) {
		logger.info("Delete Product With Id : {} ", id);
		Product product = productRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		productRepository.delete(product);
		logger.info("{} Product Deleted Successfully",product.getName());
		
	}


	// Search Product By Name
	@Override
	public List<ProductDTO> searchProductByName(String name) {
		logger.info("Search Product With Name : {} ", name);
		List<Product> products = productRepository.searchProductByName(name);
		logger.info("{} Products Successfully found");
				if (products.isEmpty()) {
		            throw new ResourceNotFoundException("No product found in with Name: " + name);
		        }
				//.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		return products.stream().map(ProductMapper::mapToDTO).collect(Collectors.toList());
	}
	

	// Search Product By Price
	@Override
	public List<ProductDTO> searchProductByPrice(Double price) {
		logger.info("Search Product By Price : {}", price);
		List<Product> products = productRepository.findByPriceLessThanEqual(price);
		logger.info("{} Products Successfully Found", products.size());
			if (products.isEmpty()) {
	            throw new ResourceNotFoundException("No product found in with Price: " + price);
	        }
			//.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		return products.stream().map(ProductMapper::mapToDTO).collect(Collectors.toList());
	}
   
}
