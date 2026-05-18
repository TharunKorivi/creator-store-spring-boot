package com.tharunkorivi.creatorstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Product name can't be blank")
    @NotNull(message = "Product name is required")
    private String name;

    private String description;

    @Column(nullable = false)
    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.0" , inclusive = false,message = "Product price must be greater than 0.0")
    private BigDecimal price;


    @Min(value = 0 , message = "Product quantity must be greater than 0")
    @Column(name = "stock_quantity",nullable = false)
    private Integer stockQuantity;

    private String category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

}
