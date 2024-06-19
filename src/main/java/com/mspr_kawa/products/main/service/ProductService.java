package com.mspr_kawa.products.main.service;
import com.mspr_kawa.products.main.model.Product;
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
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }
    public Product getProductById(UUID id) {
        Optional<Product> optionalProducts = productsRepository.findById(id);
        return optionalProducts.orElse(null);
    }
    public Product createProduct(Product product) {
        product.setId(null);
        return productsRepository.save(product);
    }
    public Product updateProduct(UUID id, Product product) {
        Optional<Product> optionalProducts = productsRepository.findById(id);
        if (optionalProducts.isPresent()) {
            Product existingProduct = optionalProducts.get();
            if (existingProduct.getId().equals(product.getId())){
                return productsRepository.save(product);
            }
        }
        return null;
    }
    public boolean deleteProduct(UUID id) {
        Optional<Product> optionalProduct= productsRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productsRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
