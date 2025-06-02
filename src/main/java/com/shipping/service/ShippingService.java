package com.shipping.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shipping.dto.ShippingResponseDTO;
import com.shipping.entities.Order;
import com.shipping.repoitory.OrderRepository;

@Service
public class ShippingService {

	@Autowired
	private OrderRepository orderRepository;
	// injecting values from applicationn.properties
	 @Value("${shipping.domestic.rate}")
	    private BigDecimal domesticRate;

	    @Value("${shipping.international.rate}")
	    private BigDecimal internationalRate;

	    @Value("${shipping.domestic.currency}")
	    private String domesticCurrency;

	    @Value("${shipping.international.currency}")
	    private String internationalCurrency;

	public ShippingResponseDTO caluclateShipping(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		if (order.getShippingAddress() == null) {
			throw new RuntimeException("Shipping address missing");
		}

		String country = order.getShippingAddress().getCountry();
		boolean isDomestic = "India".equalsIgnoreCase(country);

		return new ShippingResponseDTO(isDomestic ? new BigDecimal("200") : new BigDecimal("15"),
				isDomestic ? "INR" : "USD");
		//

	}

}
