package com.example.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataWasNotFoundException extends Exception {

    public DataWasNotFoundException(String message) {
        super(message + ": data was not found");

        log.error("Error: data was not found");
    }
}


