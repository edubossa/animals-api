package com.animals.controller;

import com.animals.exceptions.NotFoundException;
import com.animals.model.Error;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class AnimalsControllerAdvice {

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new Error(BAD_REQUEST.value(), "Invalid Fields!", ex.getBindingResult().getAllErrors());
    }

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public Error notFoundException(NotFoundException ex) {
        return Error.of(NOT_FOUND.value(), ex.getMessage());
    }

}
