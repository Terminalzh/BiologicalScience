package com.neutech.mammalia.exception;

public class NotEnoughAuthority extends Exception{
    public NotEnoughAuthority() {
    }

    public NotEnoughAuthority(String message) {
        super(message);
    }
}
