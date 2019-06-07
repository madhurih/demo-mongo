package com.stackroute.muzixservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MusicControllerAdvice
{
    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<String> exceptionHandler(TrackAlreadyExistsException e)
    {
        return new ResponseEntity<String>("Error occurred" +e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(TrackNotFoundException e)
    {
        return new ResponseEntity<String>("Error occurred" +e.getMessage(), HttpStatus.CONFLICT);
    }
}