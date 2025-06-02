package com.shipping.dto;

import java.math.BigDecimal;

import lombok.Getter;
@Getter
public class ShippingResponseDTO {
	private BigDecimal amount;
	private String currency;
	private String currentSymbol;

	public ShippingResponseDTO(BigDecimal amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public ShippingResponseDTO(BigDecimal amount, String currency, String currentSymbol) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.currentSymbol = currentSymbol;
	}

}
