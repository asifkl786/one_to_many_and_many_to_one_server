package com.Rms.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Rms.Model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
	
	
	// This is custom search method JPQL QUERY
    @Query("SELECT p FROM Product p WHERE " +
           "p.name LIKE CONCAT('%', :query, '%')" )
          //  "Or p.id LIKE CONCAT('%', :query, '%')" )
           // "Or p.price LIKE CONCAT('%', :query, '%')")
    List<Product> searchProduct(String query);
    
    // Find by product name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Find by product name (case-insensitive)
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> searchProductByName(@Param("name") String name);

    // Find by price less than or equal to a given value
   // @Query("SELECT p FROM Product p WHERE p.price LIKE %:price%")
   // List<Product> findByPriceLessThanEqual(@Param("price") Double price);
    
    // Find by price less than or equal to a given value
    @Query("SELECT p FROM Product p WHERE p.price <= :price")
    List<Product> findByPriceLessThanEqual(@Param("price") Double price);


   
}
