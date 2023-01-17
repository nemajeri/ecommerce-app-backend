package com.server.ecommerceapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyExistsException extends Exception{

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
