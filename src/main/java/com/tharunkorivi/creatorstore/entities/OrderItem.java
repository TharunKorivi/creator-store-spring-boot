package com.tharunkorivi.creatorstore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Min(value = 1 , message = "Order Item quantity must be at least 1")
    private Integer quantity;

    @Column(nullable = false,name = "price_at_purchase")
    private BigDecimal priceAtPurchase;

    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false )
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

}
