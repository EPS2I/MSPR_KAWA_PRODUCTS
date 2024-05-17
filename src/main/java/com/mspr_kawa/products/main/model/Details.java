package com.mspr_kawa.products.main.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details {

    @Id
    @UuidGenerator
    private UUID id;
    private String price;
    private String description;
    private String color;
}
