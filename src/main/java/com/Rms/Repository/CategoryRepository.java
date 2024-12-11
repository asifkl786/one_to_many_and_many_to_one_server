package com.Rms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rms.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
