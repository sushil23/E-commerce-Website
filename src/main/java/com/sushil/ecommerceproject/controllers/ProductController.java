package com.sushil.ecommerceproject.controllers;

import com.sushil.ecommerceproject.dtos.ProductRequestDto;
import com.sushil.ecommerceproject.dtos.ProductResponseDto;
import com.sushil.ecommerceproject.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getAllProducts() {
        return "Get all prods";
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getASingleProduct(@PathVariable("productId") Long productId) {
        return productService.getASingleProduct(productId);
    }

    @PostMapping()
    public String addANewProduct(@RequestBody ProductRequestDto productRequestDto) {
        return "";
    }

    @PutMapping()
    public String updateAProduct(@RequestBody ProductRequestDto productRequestDto) {
        return "";
    }

    @DeleteMapping("/{productId}")
    public String deleteAProduct(@PathVariable("productId") Long productId) {
        return "Delete " + productId;
    }
}
