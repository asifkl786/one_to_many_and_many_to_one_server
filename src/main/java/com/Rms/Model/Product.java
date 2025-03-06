package com.Rms.Model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
}
