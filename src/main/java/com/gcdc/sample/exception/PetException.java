package com.gcdc.sample.exception;

public class PetException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PetException() {
		super();
	}

	public PetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PetException(String message, Throwable cause) {
		super(message, cause);
	}

	public PetException(String message) {
		super(message);
	}

	public PetException(Throwable cause) {
		super(cause);
	}
}
