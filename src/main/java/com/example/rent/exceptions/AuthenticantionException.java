package com.example.rent.exceptions;

public class AuthenticantionException extends RuntimeException {

	private static final long serialVersionUID = -4449817210208048457L;

	public AuthenticantionException() { }

	public AuthenticantionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}