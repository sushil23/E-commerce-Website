package com.sushil.ecommerceproject;

import com.sushil.ecommerceproject.models.Category;
import com.sushil.ecommerceproject.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void checkFindAllCategoriesWorkingFine() {
        List<Category> categories = categoryRepository.findAll();
        for (Category category: categories) {
            System.out.println(category.getName() + ": " + category.getDescription());
        }
    }
}
