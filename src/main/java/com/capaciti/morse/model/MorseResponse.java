package com.capaciti.morse.model;

public class MorseResponse {
    private String result;

    public MorseResponse() {
    }

    public MorseResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
