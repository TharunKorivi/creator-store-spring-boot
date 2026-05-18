package com.tharunkorivi.creatorstore.repositories;

import com.tharunkorivi.creatorstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
