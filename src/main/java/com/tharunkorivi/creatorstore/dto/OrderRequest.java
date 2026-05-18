package com.tharunkorivi.creatorstore.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "Customer name is required")
    @NotBlank(message = "Customer name can't be blank")
    private String customerName;

    @NotNull(message = "Customer email is required")
    @NotBlank(message = "Customer email can't be blank")
    @Email(message = "Email is invalid")
    private String customerEmail;

    @NotEmpty(message = "Order must have at least 1 item")
    private List<OrderItemRequest> orderItems;
}
