package com.shipping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipping.dto.ShippingResponseDTO;
import com.shipping.entities.Order;
import com.shipping.repoitory.OrderRepository;
import com.shipping.service.ShippingService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private ShippingService shippingService;
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{orderId}/shipping-cost")
	public ResponseEntity<ShippingResponseDTO> getShippingCost(@PathVariable Long orderId) {
		ShippingResponseDTO response = shippingService.caluclateShipping(orderId);
		return ResponseEntity.ok(response);
	}

	// new POST endpoint to create/Save an order
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order saveOrder = orderRepository.save(order);
		return ResponseEntity.ok(saveOrder);

	}

}
