package com.inventory.exceptions;

import com.inventory.dto.ErrorResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class Generic_Exception_Handling extends  RuntimeException{

    public Generic_Exception_Handling() {
    }

    public Generic_Exception_Handling(String exceptionMessage) {
        super(exceptionMessage);
    }

    @ExceptionHandler({ExpiredJwtException.class, Generic_Exception_Handling.class, JwtException.class})
    public ResponseEntity<ErrorResponseDto> handlingException(Generic_Exception_Handling exception){
        return  new ResponseEntity<>(
                new ErrorResponseDto(exception.getCause(),exception.getMessage(),new Date())
                , HttpStatus.BAD_REQUEST);
    }
}
