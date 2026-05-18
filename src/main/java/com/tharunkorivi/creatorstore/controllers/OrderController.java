package com.tharunkorivi.creatorstore.controllers;


import com.tharunkorivi.creatorstore.dto.OrderRequest;
import com.tharunkorivi.creatorstore.entities.Order;
import com.tharunkorivi.creatorstore.services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

}
