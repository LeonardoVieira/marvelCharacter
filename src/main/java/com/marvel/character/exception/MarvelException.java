/**
 * 
 */
package com.marvel.character.exception;

import org.springframework.http.HttpStatus;

/**
 * Project exception class
 * 
 * @author Leonardo Vieira
 * @since 01-2017
 * @version 1.0
 */
public class MarvelException extends Exception {

	private static final long serialVersionUID = 6528511075388915845L;

	private String errorMessage;

	private HttpStatus httpStatus;

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public MarvelException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public MarvelException(String errorMessage, HttpStatus httpStatus) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public MarvelException() {
		super();
	}
}