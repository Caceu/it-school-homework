//package com.company;

public class NoExceptionsException extends Exception{
    public NoExceptionsException(String message) {
        super("There is no exception. " + message);
    }
}
