package com.shipping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(Long orderId) {
		super("Order Not found Exception" + orderId);

	}

	// for more
	public OrderNotFoundException(Long orderId, Throwable cause) {
		super("Order not found with ID: " + orderId, cause);
	}

}
