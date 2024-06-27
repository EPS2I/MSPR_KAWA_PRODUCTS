/*
package com.mspr_kawa.products.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mspr_kawa.products.main.model.Details;
import com.mspr_kawa.products.main.model.Product;
import com.mspr_kawa.products.main.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductIntegrationTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Autowired
    private ObjectMapper objectMapper;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        logger.debug("Running testGetAllProducts");

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());

        logger.info("Finished testGetAllProducts");
    }

  */
/*  @Test
    public void getProductByIdIntegrationTest() throws Exception {
        logger.debug("Running getProductByIdIntegrationTest");

        UUID id = UUID.randomUUID();
        Details details = new Details();
        Date currentDate = new Date();
        Product product = new Product(id, "Cappuccino", 10, details, currentDate);
        when(productService.getProductById(id)).thenReturn(product);

        mockMvc.perform(get("/products/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":\"" + id + "\",\"name\":\"Cappuccino\",\"stock\":10,\"details\":null,\"createdAt\":\"" + dateFormat.format(currentDate) + "\"}"));

        logger.info("Finished getProductByIdIntegrationTest");
    }*//*


  */
/*  @Test
    public void createProductIntegrationTest() throws Exception {
        logger.debug("Running createProductIntegrationTest");

        UUID id = UUID.randomUUID();
        Details details = new Details();
        Date currentDate = new Date();
        Product product = new Product(id, "Latte", 15, details, currentDate);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Latte\",\"stock\":15,\"details\":null}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":\"" + id + "\",\"name\":\"Latte\",\"stock\":15,\"details\":null,\"createdAt\":\"" + dateFormat.format(currentDate) + "\"}"));

        logger.info("Finished createProductIntegrationTest");
    }*//*


    @Test
    public void deleteProductIntegrationTest() throws Exception {
        logger.debug("Running deleteProductIntegrationTest");

        UUID id = UUID.randomUUID();

        when(productService.deleteProduct(id)).thenReturn(true);
        mockMvc.perform(delete("/products/" + id))
                .andExpect(status().isNoContent());
        logger.info("Successfully deleted product with id: " + id);

        when(productService.deleteProduct(id)).thenReturn(false);
        mockMvc.perform(delete("/products/" + id))
                .andExpect(status().isNotFound());
        logger.info("Product with id " + id + " not found for deletion");
    }

   */
/* @Test
    public void updateProductIntegrationTest() throws Exception {
        logger.debug("Running updateProductIntegrationTest");

        UUID id = UUID.randomUUID();
        Details details = new Details();
        Date currentDate = new Date();
        Product product = new Product(id, "Mocha", 20, details, currentDate);
        when(productService.updateProduct(any(UUID.class), any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Mocha\",\"stock\":20,\"details\":null}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":\"" + id + "\",\"name\":\"Mocha\",\"stock\":20,\"details\":null,\"createdAt\":\"" + dateFormat.format(currentDate) + "\"}"));

        logger.info("Finished updateProductIntegrationTest");
    }*//*

}
*/
