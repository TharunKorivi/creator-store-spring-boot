package com.tharunkorivi.creatorstore.services;

import com.tharunkorivi.creatorstore.dto.OrderItemRequest;
import com.tharunkorivi.creatorstore.dto.OrderRequest;
import com.tharunkorivi.creatorstore.entities.Order;
import com.tharunkorivi.creatorstore.entities.OrderItem;
import com.tharunkorivi.creatorstore.entities.Product;
import com.tharunkorivi.creatorstore.repositories.OrderRepository;
import com.tharunkorivi.creatorstore.repositories.ProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    ProductRepository productRepository;
    OrderRepository orderRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest)  {
        Order order = new Order();
        List<OrderItem>  orderItems = new ArrayList<>();

        BigDecimal totalPrice = BigDecimal.ZERO;

        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());


        for(OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id : " + itemRequest.getProductId()));

            if(product.getStockQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Product is out of stock");
            }

            BigDecimal orderPrice = product.getPrice().
                    multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalPrice = totalPrice.add(orderPrice);

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .priceAtPurchase(product.getPrice())
                    .quantity(itemRequest.getQuantity())
                    .build();
            product.setStockQuantity(product.getStockQuantity() - itemRequest.getQuantity());
            orderItems.add(orderItem);
            productRepository.save(product);

        }

        order.setStatus("CONFIRMED");
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }


    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id : " + id));
    }
}
