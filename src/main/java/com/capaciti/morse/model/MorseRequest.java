package com.capaciti.morseCode.model;

public class MorseRequest {
    private String message;

    public MorseRequest() {
    }

    public MorseRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
