package com.vbhoomidi.exception;

/**
 * Created by vikramreddy on 7/10/2017.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
