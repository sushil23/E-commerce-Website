package com.sushil.ecommerceproject.controllers;

import com.sushil.ecommerceproject.dtos.ProductRequestDto;
import com.sushil.ecommerceproject.dtos.ProductResponseDto;
import com.sushil.ecommerceproject.exceptions.NotFoundException;
import com.sushil.ecommerceproject.models.Product;
import com.sushil.ecommerceproject.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private ProductResponseDto getProductResponseDtoFromProduct(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory().getName());
        productResponseDto.setImage(product.getImageUrl());

        return productResponseDto;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() throws NotFoundException {
        Optional<List<Product>> productsOptional = productService.getAllProducts();
        if (productsOptional.isEmpty()) {
            throw new NotFoundException("Product List not found");
        }

        List<Product> products = productsOptional.get();
        ArrayList<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product: products) {
            productResponseDtos.add(getProductResponseDtoFromProduct(product));
        }

        return new ResponseEntity<>(productResponseDtos, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getASingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        Optional<Product> productOptional = productService.getASingleProduct(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        Product product = productOptional.get();

        return getProductResponseDtoFromProduct(product);
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
