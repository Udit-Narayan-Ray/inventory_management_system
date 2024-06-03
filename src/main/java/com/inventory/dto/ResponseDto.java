package com.inventory.dto;

import java.util.Date;

public class ResponseDto {
    private Throwable cause;
    private String message;
    private Date date;

    public ResponseDto() {
    }

    public ResponseDto(Throwable cause, String message, Date date) {
        this.cause = cause;
        this.message = message;
        this.date = date;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "Reason = '" + cause + '\'' +
                ", message = '" + message + '\'' +
                ", date = " + date +
                '}';
    }
}
