package com.shipping.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
	private LocalDateTime timeStamp;
	private int status;
	private String error;
	private String message;
	// Optional : Add error code for Client handling
	private String errorCode;

	// Static factory method for convenience
	public static ErrorResponse of(HttpStatus status, String message, String errorCode) {
		return new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, errorCode);

	}

}
