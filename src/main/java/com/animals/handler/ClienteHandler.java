package com.animals.handler;

import com.animals.model.Error;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ClienteHandler {

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @ExceptionHandler(CpfDuplicadoHandlerException.class)
    public Error cpfDuplicadoHandlerException(CpfDuplicadoHandlerException ex) {
        return Error.of(CONFLICT.value(),ex.getMessage());
    }

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @ExceptionHandler(UnexpectedTypeException.class)
    public Error unexpectedTypeException(UnexpectedTypeException ex) {
        return Error.of(BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new Error(BAD_REQUEST.value(), "Um ou mais campos estão invalidos!", ex.getBindingResult().getAllErrors());
    }

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @ExceptionHandler(DataInvalidaHandlerException.class)
    public Error dataInvalidaHandlerException(DataInvalidaHandlerException ex) {
        return Error.of(BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseStatus(NO_CONTENT)
    @ResponseBody
    @ExceptionHandler(ClienteNaoLocalizadoHandlerException.class)
    public Error clienteNaoLocalizadoHandlerException(ClienteNaoLocalizadoHandlerException ex) {
        return Error.of(NO_CONTENT.value(), ex.getMessage());
    }

}
