package com.sushil.ecommerceproject;

import com.sushil.ecommerceproject.models.Category;
import com.sushil.ecommerceproject.models.Product;
import com.sushil.ecommerceproject.repositories.CategoryRepository;
import com.sushil.ecommerceproject.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    void checkSaveWorkingFine() {
        Product product = new Product();
        Category category = new Category();

        category.setName("Electronics");
        category.setDescription("kjdk ddg");

        product.setName("abc");
        product.setBrandName("LG");
        product.setDescription("Tv");
        product.setPrice(600);
        product.setImageUrl("ddd.com");
        product.setCategory(category);

        productRepository.save(product);
    }

    @Test
    void checkFindAllProdsWorkingFine() {
        List<Product> products = productRepository.findAll();
        for (Product prod : products) {
            System.out.println(prod.getName());
        }
    }

    @Test
    void checkFindProdByIdWorkingFine() {
        Optional<Product> productOptional = productRepository.findById(1L);
        if (productOptional.isEmpty()) {
            System.out.println("Not found");
        } else {
            System.out.println(productOptional.get().getName());
        }
    }

    @Test
    void checkFindProdByCatId() {
        List<Product> products = productRepository.findByCategoryId(2L);
        for (Product product: products) {
            System.out.println(product.getName());
        }
    }

    @Test
    @Transactional
    void checkRemoveProdByIdWorkingFine() {
        Long productId = productRepository.removeProductById(2L);
        System.out.println("Removed product " + productId);
    }
}
