package com.shipping.dto;

import java.util.Collections;
import java.util.List;

import com.shipping.entities.Order;
import com.shipping.entities.Order.Status;

public class OrderStatusResponse {
	private Order.Status currentStatus;
	private List<StatusHistoryEntry> history;

	public OrderStatusResponse(Status currentStatus, List<StatusHistoryEntry> history) {
		super();
		this.currentStatus = currentStatus;
		this.history = history;
	}

//getters(No setter for Immunity) 
	public Order.Status getCurrentStatus() {
		return currentStatus;

	}
	public List<StatusHistoryEntry> getHistory(){
		return Collections.unmodifiableList(history);
		
	}

}
