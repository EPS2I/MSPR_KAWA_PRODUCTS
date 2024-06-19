package com.mspr_kawa.products.main.db_services;

import com.mspr_kawa.products.main.model.Product;
import com.mspr_kawa.products.main.security.KeycloakConfigCustom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class ProductSyncService {

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${app.config-url.api-db}")
    private String apiDbUrl;

    private final RestTemplate restTemplate;

    private final KeycloakConfigCustom keycloakConfigCustom;

    public ProductSyncService(RestTemplate restTemplate, KeycloakConfigCustom keycloakConfigCustom) {
        this.restTemplate = restTemplate;
        this.keycloakConfigCustom = keycloakConfigCustom;
    }

    public List<Product> fetchProductsFromMainDb() throws IOException {
        String apiBaseUrl = "http://" + this.apiDbUrl + "/products";
        String accessToken = keycloakConfigCustom.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Product[]> response = restTemplate.exchange(apiBaseUrl, HttpMethod.GET, entity, Product[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return List.of(response.getBody());
        } else {
            throw new RuntimeException("Failed to get data from API BDD: " + response.getStatusCode());
        }
    }

    public void syncProductsToMainDb(List<Product> products) {
        String apiBaseUrl = "http://" + this.apiDbUrl + "/products";
        for (Product product : products) {
            restTemplate.postForEntity(apiBaseUrl, product, Product.class);
        }
    }
}
