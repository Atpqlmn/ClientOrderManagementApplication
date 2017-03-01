package com.application.management.order.client.error;

public class JsonError {

    private String errorMessage;

    public JsonError() {
    }

    public JsonError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
