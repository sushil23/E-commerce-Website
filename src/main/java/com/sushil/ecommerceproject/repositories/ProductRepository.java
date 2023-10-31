package com.sushil.ecommerceproject.repositories;

import com.sushil.ecommerceproject.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByCategoryName(String categoryName);

    @Transactional
    Optional<Long> removeProductById(Long id);
}
