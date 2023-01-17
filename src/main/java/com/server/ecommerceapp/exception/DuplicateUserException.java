package com.server.ecommerceapp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(code = CONFLICT)
public class DuplicateUserException extends Exception
{
    public DuplicateUserException()
    {
        super("Cannot register or login user because user already exists");
    }
}
