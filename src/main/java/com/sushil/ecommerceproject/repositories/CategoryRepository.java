package com.sushil.ecommerceproject.repositories;

import com.sushil.ecommerceproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save (Category category);

    List<Category> findAll();
}
