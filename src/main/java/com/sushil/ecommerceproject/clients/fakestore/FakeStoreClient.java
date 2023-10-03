package com.sushil.ecommerceproject.clients.fakestore;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
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

    public Optional<FakeStoreProductResponseDto> addANewProduct(FakeStoreProductRequestDto fakeStoreProductRequestDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> fakeStoreProductResponseDtoResponseEntity = restTemplate.postForEntity(
            "https://fakestoreapi.com/products",
             fakeStoreProductRequestDto,
             FakeStoreProductResponseDto.class
        );

        FakeStoreProductResponseDto fakeStoreProductResponseDto = fakeStoreProductResponseDtoResponseEntity.getBody();
        if (fakeStoreProductResponseDto == null) {
            return Optional.empty();
        }

        return Optional.of(fakeStoreProductResponseDto);
    }

    public Optional<FakeStoreProductResponseDto> updateAProduct(Long productId, FakeStoreProductRequestDto fakeStoreProductRequestDto) {
        ResponseEntity<FakeStoreProductResponseDto> fakeStoreProdResOptional = requestForEntity(
            HttpMethod.PUT,
            "https://fakestoreapi.com/products/{productId}",
            fakeStoreProductRequestDto,
            FakeStoreProductResponseDto.class,
            productId
        );

        FakeStoreProductResponseDto fakeStoreProductResponseDto = fakeStoreProdResOptional.getBody();
        if (fakeStoreProductResponseDto == null) {
            return Optional.empty();
        }

        return Optional.of(fakeStoreProductResponseDto);
    }

    public Optional<String[]> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> categoriesResponse = restTemplate.getForEntity(
            "https://fakestoreapi.com/products/categories",
            String[].class
        );

        String[] categories = categoriesResponse.getBody();
        if (categories == null) {
            return Optional.empty();
        }

        return Optional.of(categories);
    }

    public Optional<List<FakeStoreProductResponseDto>> getAllProductsInACategory(String categoryName) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto[]> fakeStoreProdListResponse = restTemplate.getForEntity(
            "https://fakestoreapi.com/products/category/{categoryName}",
            FakeStoreProductResponseDto[].class,
            categoryName
        );

        FakeStoreProductResponseDto[] productsResponse = fakeStoreProdListResponse.getBody();
        if (productsResponse == null) {
            return Optional.empty();
        }

        List<FakeStoreProductResponseDto> products = new ArrayList<>();

//        for (FakeStoreProductResponseDto prodResponse: productsResponse) {
//            products.add(prodResponse);
//        }

        products.addAll(Arrays.asList(productsResponse));

        return Optional.of(products);
    }

    public Optional<FakeStoreProductResponseDto> deleteAProduct(Long productId) {
        ResponseEntity<FakeStoreProductResponseDto> fakeStoreProdResponse = requestForEntity(
            HttpMethod.DELETE,
        "https://fakestoreapi.com/products/{productId}",
            null,
            FakeStoreProductResponseDto.class,
            productId
        );

        FakeStoreProductResponseDto fakeStoreProductResponseDto = fakeStoreProdResponse.getBody();
        if (fakeStoreProductResponseDto == null) {
            return Optional.empty();
        }

        return Optional.of(fakeStoreProductResponseDto);
    }
}
