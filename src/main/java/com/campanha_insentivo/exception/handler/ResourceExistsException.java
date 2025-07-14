package com.campanha_insentivo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ResourceExistsException(String ex) {
		super(ex);
	}

}
