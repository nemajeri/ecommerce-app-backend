package com.server.ecommerceapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserNotFoundException extends Exception
{
    public UserNotFoundException(String userName)
    {
        super("User with" + userName + "not found");
    }
}
