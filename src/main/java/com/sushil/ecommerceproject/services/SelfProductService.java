package com.sushil.ecommerceproject.services;

import com.sushil.ecommerceproject.models.Product;
import com.sushil.ecommerceproject.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Optional<List<Product>> getAllProducts() {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getASingleProduct(Long productId) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> addANewProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        if (savedProduct == null) {
            return Optional.empty();
        }

        return Optional.of(savedProduct);
    }

    @Override
    public Optional<Product> updateAProduct(Long productId, Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteAProduct(Long productId) {
        return Optional.empty();
    }
}
