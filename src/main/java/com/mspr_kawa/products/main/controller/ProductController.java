package com.mspr_kawa.products.main.controller;
import com.mspr_kawa.products.main.model.Products;
import com.mspr_kawa.products.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint pour récupérer une liste de produits
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Endpoint pour récupérer un produit par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable UUID id) {
        Products product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour créer un nouveau produit
    @PostMapping("/products")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("name is required");
        }
        if (product.getStock() <= 0) {
            throw new IllegalArgumentException("Stock must be greater than zero");
        }
        Products createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour un produit existant
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable UUID id, @RequestBody Products product) {
        Products updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}