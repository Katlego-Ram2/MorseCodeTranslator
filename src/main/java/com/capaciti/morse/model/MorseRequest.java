package com.capaciti.morse.model;

/**
 * MorseRequest is a simple model class that represents the input
 * message to be encoded to or decoded from Morse code.
 */
public class MorseRequest {

    /**
     * The message to encode or decode.
     */
    private String message;

    /**
     * Default constructor.
     */
    public MorseRequest() {
    }

    /**
     * Constructs a MorseRequest with the specified message.
     *
     * @param message The input message (plain text or Morse code).
     */
    public MorseRequest(String message) {
        this.message = message;
    }

    /**
     * Returns the message to be processed.
     *
     * @return The message string.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message to be processed.
     *
     * @param message The message string to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
