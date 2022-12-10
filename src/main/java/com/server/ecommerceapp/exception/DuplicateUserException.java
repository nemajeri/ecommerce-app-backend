package com.server.ecommerceapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateUserException extends Exception
{
    public DuplicateUserException()
    {
        super("Cannot register or login user because user already exists");
    }
}
