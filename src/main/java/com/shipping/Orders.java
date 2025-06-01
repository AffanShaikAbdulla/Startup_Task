package com.shipping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
   @JoinColumn(name = "shipping_address_Id")
	private Address shippingAddress;

}
