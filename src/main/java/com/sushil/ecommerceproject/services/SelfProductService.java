package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.dtos.ProductRequestDto;
import com.sushil.ecommerceproject.models.Category;
import com.sushil.ecommerceproject.models.Product;
import com.sushil.ecommerceproject.repositories.CategoryRepository;
import com.sushil.ecommerceproject.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Optional<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return Optional.of(products);
    }

    @Override
    public Optional<Product> getASingleProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Optional<Product> addANewProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if (!categoryOptional.isEmpty()) {
            product.setCategory(categoryOptional.get());
        }

        Product savedProduct = productRepository.save(product);
        if (savedProduct == null) {
            return Optional.empty();
        }

        return Optional.of(savedProduct);
    }

    @Override
    public Optional<Product> updateAProduct(Long productId, Product product) {
        product.setId(productId);
        Product updatedProduct = productRepository.save(product);
        if (updatedProduct == null) {
            return Optional.empty();
        }

        return Optional.of(updatedProduct);
    }

    @Override
    public Optional<Long> deleteAProduct(Long productId) {
        return productRepository.removeProductById(productId);
    }
}
