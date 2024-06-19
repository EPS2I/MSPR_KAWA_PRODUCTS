package com.mspr_kawa.products.main.db_services;

import com.mspr_kawa.products.main.model.Products;
import com.mspr_kawa.products.main.repository.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ApplicationStartUp {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartUp.class);

    private final ProductSyncService productSyncService;
    private final ProductsRepository productRepository;

    public ApplicationStartUp(ProductSyncService productSyncService, ProductsRepository productRepository) {
        this.productSyncService = productSyncService;
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent() throws IOException {
        logger.info("Application started, fetching products from main db...");
        try {
            List<Products> productList = productSyncService.fetchProductsFromMainDb();
            productRepository.saveAll(productList);
            logger.info("Fetched and saved {} products from main db.", productList.size());
        } catch (Exception e) {
            logger.error("Error fetching products from main db", e);
        }
    }
}
