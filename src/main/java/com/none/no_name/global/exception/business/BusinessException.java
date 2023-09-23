package com.none.no_name.global.exception.business;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {

	private final String errorCode;
	private final HttpStatus httpStatus;

	protected BusinessException(String errorCode, HttpStatus httpStatus, String message) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;

	}
}
