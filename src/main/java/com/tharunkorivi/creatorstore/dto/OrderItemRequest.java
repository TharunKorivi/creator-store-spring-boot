package com.tharunkorivi.creatorstore.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderItemRequest {

    @NotNull(message = "Product id is required")
    private Long productId;

    @NotNull(message = "Product quantity is required")
    @Min(value = 1 , message = "Product quantity must be at least 1")
    private Integer quantity;
}
