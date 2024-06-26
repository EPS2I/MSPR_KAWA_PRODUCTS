package com.mspr_kawa.products.main.controller;

import com.mspr_kawa.products.main.model.Product;
import com.mspr_kawa.products.main.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductUnitairesTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductUnitairesTest.class);

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getAllProductsTest() {
        Product product1 = new Product(UUID.randomUUID(), "Cappuccino", 10, null, new Date());
        Product product2 = new Product(UUID.randomUUID(), "Latte", 5, null, new Date());
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
        logger.info("Finished getAllProductsTest.");
    }

    @Test
    public void getProductByIdTest() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Cappuccino", 10, null, new Date());

        when(productService.getProductById(id)).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).getProductById(id);
        logger.info("Finished getProductByIdTest.");
    }


    @Test
    public void updateProductTest() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Cappuccino", 10, null, new Date());

        when(productService.updateProduct(id, product)).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(id, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).updateProduct(id, product);
        logger.info("Finished updateProductTest.");
    }


    @Test
    public void deleteProductTest() {
        UUID id = UUID.randomUUID();

        when(productService.deleteProduct(id)).thenReturn(true);

        ResponseEntity<Void> response = productController.deleteProduct(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(id);
        logger.info("Finished deleteProductTest.");
    }


    @Test
    public void createProductTest() {
        Product product = new Product(UUID.randomUUID(), "Cappuccino", 10, null, new Date());

        when(productService.createProduct(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).createProduct(product);
        logger.info("Finished createProductTest.");
    }


    @Test
    void testCreateProduct_MissingName() {
        Product product = new Product();
        product.setStock(10);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productController.createProduct(product);
        });

        assertEquals("name is required", exception.getMessage());
    }


    @Test
    void testGetProductByIdNotFound() {
        UUID productId = UUID.randomUUID();
        when(productService.getProductById(productId)).thenReturn(null);

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void testUpdateProductNotFound() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        when(productService.updateProduct(productId, product)).thenReturn(null);

        ResponseEntity<Product> response = productController.updateProduct(productId, product);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteProductNotFound() {
        UUID productId = UUID.randomUUID();
        when(productService.deleteProduct(productId)).thenReturn(false);

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
