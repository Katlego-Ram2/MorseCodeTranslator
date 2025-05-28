package com.capaciti.morse.model;

/**
 * MorseResponse is a simple model class used to encapsulate the result
 * of Morse code operations (either encoding or decoding).
 */
public class MorseResponse {

    /**
     * The resulting Morse code or decoded plain text.
     */
    private String result;

    /**
     * Default constructor.
     */
    public MorseResponse() {
    }

    /**
     * Constructs a MorseResponse with the given result string.
     *
     * @param result The Morse code or plain text result.
     */
    public MorseResponse(String result) {
        this.result = result;
    }

    /**
     * Returns the result of the Morse code operation.
     *
     * @return The result string.
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the result of the Morse code operation.
     *
     * @param result The result string to set.
     */
    public void setResult(String result) {
        this.result = result;
    }
}
