package com.rookies5.myspringbootlab.exception;

public class ErrorObject {

    private String errorCode;
    private String message;

    public ErrorObject(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}