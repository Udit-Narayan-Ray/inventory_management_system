package com.inventory.exceptions;

import com.inventory.dto.ResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

public class Generic_Exception_Handling extends  RuntimeException{

    public Generic_Exception_Handling() {
    }

    public Generic_Exception_Handling(String exceptionMessage) {
        super(exceptionMessage);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<ResponseDto> handlingException(Generic_Exception_Handling exception){
        return  new ResponseEntity<>(
                new ResponseDto(exception.getCause(),exception.getMessage(),new Date())
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
