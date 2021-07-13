package com.zup.comics.services.exceptions;

public class MarvelClientException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MarvelClientException(String msg) {
		super(msg);
	}

}
