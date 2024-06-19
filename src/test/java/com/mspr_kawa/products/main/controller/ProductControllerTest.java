package com.mspr_kawa.products.main.controller;

import com.mspr_kawa.products.main.controller.ProductController;
import com.mspr_kawa.products.main.model.Products;
import com.mspr_kawa.products.main.service.ProductService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Products product = new Products();
        product.setName("Cappuccino");
        product.setStock(10);

        Products createdProduct = new Products();
        when(productService.createProduct(product)).thenReturn(createdProduct);

        ResponseEntity<Products> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdProduct, response.getBody());
    }

    @Test
    void testCreateProduct_MissingName() {
        Products product = new Products();
        product.setStock(10);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productController.createProduct(product);
        });

        assertEquals("name is required", exception.getMessage());
    }

    @Test
    void testCreateProduct_MissingStock() {
        Products product = new Products();
        product.setName("Cappuccino");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productController.createProduct(product);
        });

        assertEquals("Stock must be greater than zero", exception.getMessage());
    }

    @Test
    void getAllProductsTest() {
        Products product1 = new Products();
        Products product2 = new Products();
        List<Products> productList = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(productList);

        ResponseEntity<List<Products>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
        assertEquals(product1, response.getBody().get(0));
        assertEquals(product2, response.getBody().get(1));
    }
    @Test
    void getProductByIdTest() {
        UUID productId = UUID.randomUUID();
        Products  product =new Products();
        when(productService.getProductById(productId)).thenReturn(product);
        ResponseEntity<Products> response = productController.getProductById(productId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());

    }
    @Test
    void testGetCustomerByIdNotFound() {
        UUID productId = UUID.randomUUID();
        when(productService.getProductById(productId)).thenReturn(null);
        ResponseEntity<Products> response = productController.getProductById(productId);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }

    @Test
    void testUpdateProduct() {
        UUID productId = UUID.randomUUID();
        Products product = new Products();
        Products updatedProduct = new Products();
        when(productService.updateProduct(productId, product)).thenReturn(updatedProduct);

        ResponseEntity<Products> response = productController.updateProduct(productId, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }
    @Test
    void testUpdateProductNotFound() {
        UUID productId = UUID.randomUUID();
        Products product = new Products();
        when(productService.updateProduct(productId, product)).thenReturn(null);

        ResponseEntity<Products> response = productController.updateProduct(productId, product);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void testDeleteProductNotFound() {
        UUID productId = UUID.randomUUID();
        when(productService.deleteProduct(productId)).thenReturn(false);

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void testDeleteProduct() {
        UUID productId = UUID.randomUUID();
        when(productService.deleteProduct(productId)).thenReturn(true);

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}