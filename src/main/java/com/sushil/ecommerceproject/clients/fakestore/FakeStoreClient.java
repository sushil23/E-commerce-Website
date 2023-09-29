package com.sushil.ecommerceproject.clients.fakestore;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Optional<FakeStoreProductResponseDto> getASingleProduct(long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> fakeStoreProductResponseDtoResponseEntity = restTemplate.getForEntity(
            "https://fakestoreapi.com/products/{productId}",
            FakeStoreProductResponseDto.class,
            productId
        );

        FakeStoreProductResponseDto fakeStoreProductResponseDto = fakeStoreProductResponseDtoResponseEntity.getBody();
        if (fakeStoreProductResponseDto == null) {
            return Optional.empty();
        }

        return Optional.of(fakeStoreProductResponseDto);
    }

    public Optional<List<FakeStoreProductResponseDto>> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto[]> fakeProductsResponseEntity = restTemplate.getForEntity(
            "https://fakestoreapi.com/products",
            FakeStoreProductResponseDto[].class
        );

        FakeStoreProductResponseDto[] fakeProductsResponse = fakeProductsResponseEntity.getBody();
        if (fakeProductsResponse == null) {
            return Optional.empty();
        }

        return Optional.of(Arrays.asList(fakeProductsResponse));
    }
}
