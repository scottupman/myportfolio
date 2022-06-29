package com.independentstudy.financeportfolio.exceptions;

public class NotEnoughBuyingPowerException extends Exception
{
    public NotEnoughBuyingPowerException(String errorMessage)
    {
        super(errorMessage);
    }
}
