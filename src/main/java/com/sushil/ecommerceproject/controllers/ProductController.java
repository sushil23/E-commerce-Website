package com.sushil.ecommerceproject.controllers;

import com.sushil.ecommerceproject.dtos.ProductRequestDto;
import com.sushil.ecommerceproject.dtos.ProductResponseDto;
import com.sushil.ecommerceproject.exceptions.NoCategoryProvidedException;
import com.sushil.ecommerceproject.exceptions.NotFoundException;
import com.sushil.ecommerceproject.models.Category;
import com.sushil.ecommerceproject.models.Product;
import com.sushil.ecommerceproject.services.ProductService;
import com.sushil.ecommerceproject.services.SelfProductService;
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
    private final ProductService productService;

    public ProductController(SelfProductService productService) {
        this.productService = productService;
    }

     static ProductResponseDto getProductResponseDtoFromProduct(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setBrandName(product.getBrandName());
        productResponseDto.setCategory(product.getCategory().getName());
        productResponseDto.setImage(product.getImageUrl());

        return productResponseDto;
    }

     static Product getProductFromProductRequestDto(ProductRequestDto productRequestDto) {
        Product product = new Product();

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setBrandName(productRequestDto.getBrandName());
        Category category = new Category();
        category.setName(productRequestDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productRequestDto.getImage());

        return product;
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

        return new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
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
    public ResponseEntity<ProductResponseDto> addANewProduct(@RequestBody ProductRequestDto productRequestDto) throws NotFoundException, NoCategoryProvidedException {
        Product product = getProductFromProductRequestDto(productRequestDto);
        String categoryName = productRequestDto.getCategory();
        if (categoryName == null) {
            throw new NoCategoryProvidedException("Please provide a category");
        }

        Optional<Product> productOptional = productService.addANewProduct(product);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not added");
        }

        Product createdProduct = productOptional.get();
        return new ResponseEntity<>(getProductResponseDtoFromProduct(createdProduct), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateAProduct(@PathVariable("productId") Long productId, @RequestBody ProductRequestDto productRequestDto) throws NotFoundException {
        Product product = getProductFromProductRequestDto(productRequestDto);
        Optional<Product> productOptional = productService.updateAProduct(productId, product);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not updated");
        }

        return new ResponseEntity<>(getProductResponseDtoFromProduct(productOptional.get()), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Long> deleteAProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        Optional<Long> productOptional = productService.deleteAProduct(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }
}
