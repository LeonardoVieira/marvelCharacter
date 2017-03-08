/**
 * 
 */
package com.marvel.character.exception.model;

/**
 * Error Entity 
 * 
 * Used in the return of the rest methods
 * 
 * @author Leonardo Vieira
 * @since 01-2017
 * @version 1.0
 */
public class Error {

	private int httpStatus;

	private String message;

	/**
	 * @return the httpStatus
	 */
	public int getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}