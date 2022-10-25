package com.group7.controller;

public class InsufficientFundsException extends Exception{
    
    public InsufficientFundsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public InsufficientFundsException(String errorMessage) {
        super(errorMessage);
    }

}
