package com.shipping.repoitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shipping.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@EntityGraph(attributePaths = { "shippingAddress" })
	Optional<Order> findById(Long id); // Standard name
}
