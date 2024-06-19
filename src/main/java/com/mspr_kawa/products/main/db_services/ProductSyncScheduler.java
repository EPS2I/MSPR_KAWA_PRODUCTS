package com.mspr_kawa.products.main.db_services;

import com.mspr_kawa.products.main.model.Product;
import com.mspr_kawa.products.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSyncScheduler {

    private final ProductSyncService productSyncService;
    private final ProductService productService;

    @Autowired
    public ProductSyncScheduler(ProductSyncService productSyncService, ProductService productService) {
        this.productSyncService = productSyncService;
        this.productService = productService;
    }

    @Scheduled(fixedRate = 3000)  // Sync every 3 seconds
    public void syncProducts() {
        List<Product> products = this.fetchProductsFromSQLite();
        productSyncService.syncProductsToMainDb(products);
    }

    private List<Product> fetchProductsFromSQLite() {
        return this.productService.getAllProducts();
    }
}
