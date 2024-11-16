package com.nikhil.grocery.repository;

import com.nikhil.grocery.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
