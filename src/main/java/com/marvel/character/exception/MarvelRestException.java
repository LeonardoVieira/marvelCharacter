package com.marvel.character.exception;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class MarvelRestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5245788182520949473L;
	private final HttpResponse response;

	public MarvelRestException(HttpResponse response) throws IOException {
		super(EntityUtils.toString(response.getEntity()));
		this.response = response;
	}
}
