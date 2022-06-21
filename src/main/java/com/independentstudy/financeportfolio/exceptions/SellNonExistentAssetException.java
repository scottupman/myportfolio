package com.independentstudy.financeportfolio.exceptions;

public class SellNonExistentAssetException extends Exception
{
    public SellNonExistentAssetException(String errorMessage)
    {
        super(errorMessage);
    }

}
