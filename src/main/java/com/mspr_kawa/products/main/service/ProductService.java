package com.mspr_kawa.products.main.service;
import com.mspr_kawa.products.main.model.Products;
import com.mspr_kawa.products.main.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    // Méthode pour récupérer une liste de clients
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }
    public Products getProductById(UUID id) {
        Optional<Products> optionalProducts = productsRepository.findById(id);
        return optionalProducts.orElse(null);
    }
    public  Products createProduct(Products product) {
        product.setId(null);
        return productsRepository.save(product);
    }
    public Products updateProduct(UUID id, Products products) {
        Optional<Products> optionalProducts = productsRepository.findById(id);
        if (optionalProducts.isPresent()) {
            Products existingProduct = optionalProducts.get();
            if (existingProduct.getId().equals(products.getId())){
                return productsRepository.save(products);
            }
        }
        return null;
    }
    public boolean deleteProduct(UUID id) {
        Optional<Products> optionalProduct= productsRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productsRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
