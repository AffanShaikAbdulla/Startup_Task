package com.shipping.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shipping.dto.OrderStatusResponse;
import com.shipping.dto.ShippingResponseDTO;
import com.shipping.dto.StatusHistoryEntry;
import com.shipping.dto.StatusUpdateRequest;
import com.shipping.entities.Order;
import com.shipping.exception.OrderNotFoundException;
import com.shipping.repoitory.OrderRepository;
import com.shipping.service.ShippingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private ShippingService shippingService;

	@Autowired
	private OrderRepository orderRepository;

	// GET endpoint to fetch shipping cost for a specific order
	@GetMapping("/{orderId}/shipping-cost")
	public ResponseEntity<ShippingResponseDTO> getShippingCost(@PathVariable Long orderId) {
		ShippingResponseDTO response = shippingService.caluclateShipping(orderId);
		return ResponseEntity.ok(response);
	}

	// POST endpoint to create/save an order
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order savedOrder = orderRepository.save(order);
		return ResponseEntity.ok(savedOrder);
	}

	// GET endpoint to fetch order status and history
	@GetMapping("/{orderId}/status")
	public OrderStatusResponse getStatus(@PathVariable Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

		return new OrderStatusResponse(order.getStatus(),
				order.getHistory().stream()
						.map(h -> new StatusHistoryEntry(h.getFromStatus(), h.getToStatus(), h.getTimestamp()))
						.collect(Collectors.toList()));
	}

	// PUT endpoint to update order status
	@PutMapping("/{orderId}/status")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Long orderId, @Valid @RequestBody StatusUpdateRequest request) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

		order.changeStatus(request.getNewStatus(), "System");
		orderRepository.save(order);
	}
}
