package com.independentstudy.financeportfolio.exceptions;

public class NotEnoughQuantityException extends Exception
{
    public NotEnoughQuantityException(String errorMessage) {
        super(errorMessage);
    }
}
