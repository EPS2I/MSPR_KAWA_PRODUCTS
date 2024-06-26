package com.mspr_kawa.products.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mspr_kawa.products.main.model.Details;
import com.mspr_kawa.products.main.model.Product;
import com.mspr_kawa.products.main.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductFunctionalTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductFunctionalTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProductsFunctionalTest() throws Exception {
        logger.info("Starting getAllProductsFunctionalTest");
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
        logger.info("Finished getAllProductsFunctionalTest");
    }

    @Test
    public void getProductByIdFunctionalTest() throws Exception {
        logger.info("Starting getProductByIdFunctionalTest");
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Cappuccino", 10, new Details(), new Date());

        when(productService.getProductById(id)).thenReturn(product);

        mockMvc.perform(get("/products/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":\"" + id + "\",\"name\":\"Cappuccino\",\"stock\":10}"));
        logger.info("Finished getProductByIdFunctionalTest");
    }

    @Test
    public void createProductFunctionalTest() throws Exception {
        logger.info("Starting createProductFunctionalTest");
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Latte", 15, new Details(), new Date());

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        String productJson = objectMapper.writeValueAsString(product);
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":\"" + id + "\",\"name\":\"Latte\",\"stock\":15}"));
        logger.info("Finished createProductFunctionalTest");
    }

    @Test
    public void deleteProductFunctionalTest() throws Exception {
        logger.info("Starting deleteProductFunctionalTest");
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

    @Test
    public void updateProductFunctionalTest() throws Exception {
        logger.info("Starting updateProductFunctionalTest");
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Mocha", 20, new Details(), new Date());

        when(productService.updateProduct(any(UUID.class), any(Product.class))).thenReturn(product);

        String updatedProductJson = objectMapper.writeValueAsString(product);
        mockMvc.perform(put("/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":\"" + id + "\",\"name\":\"Mocha\",\"stock\":20}"));
        logger.info("Finished updateProductFunctionalTest");
    }
}
