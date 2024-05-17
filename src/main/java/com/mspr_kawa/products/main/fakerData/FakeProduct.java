package com.mspr_kawa.products.main.fakerData;

import com.github.javafaker.Faker;
import com.mspr_kawa.products.main.model.Products;
import com.mspr_kawa.products.main.model.Details;
import com.mspr_kawa.products.main.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FakeProduct {


    private final ProductsRepository productsRepository;

    private final Faker faker;

    @Autowired
    public FakeProduct(Faker faker, ProductsRepository productsRepository) {
        this.faker = faker;
        this.productsRepository = productsRepository;
        this.initDB();
    }

    public void initDB(){
        for (int i = 0; i < 100; i++) {
            this.productsRepository.save(this.generateFakeProduct());
        }
    }
    public Products generateFakeProduct() {
        Products products = new Products();
        products.setName(faker.commerce().productName());
        products.setStock(faker.random().nextInt(1, 1000));


        Details details = new Details();
        details.setPrice(faker.commerce().price());
        details.setDescription(faker.lorem().sentence());
        details.setColor(faker.color().name());


        return products;
    }


}
