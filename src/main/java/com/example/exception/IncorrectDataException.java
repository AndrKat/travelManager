package com.example.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncorrectDataException extends Exception {

    public IncorrectDataException(String message) {
        super(message + ": data is not correct");

        log.error("Error: data is not correct");
    }
}
