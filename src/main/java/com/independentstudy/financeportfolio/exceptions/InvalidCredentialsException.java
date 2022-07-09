package com.independentstudy.financeportfolio.exceptions;

public class InvalidCredentialsException extends Exception
{
    public InvalidCredentialsException(String errorMessage)
    {
        super(errorMessage);
    }
}
