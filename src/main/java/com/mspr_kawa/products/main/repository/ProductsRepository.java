package com.mspr_kawa.products.main.repository;

import com.mspr_kawa.products.main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductsRepository  extends JpaRepository<Product,UUID> {
}
