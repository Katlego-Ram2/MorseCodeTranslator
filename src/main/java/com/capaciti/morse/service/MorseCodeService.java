package com.capaciti.morse.service;

/**
 * Interface defining the contract for Morse code operations.
 * Provides methods to encode plain text into Morse code and decode Morse code into plain text.
 */
public interface MorseCodeService {

    /**
     * Encodes the given input string into Morse code.
     *
     * @param input The plain text to be encoded.
     * @return A string representing the encoded Morse code.
     */
    String encode(String input);

    /**
     * Decodes the given Morse code string into plain text.
     *
     * @param morseCode The Morse code string to be decoded.
     * @return The decoded plain text.
     */
    String decode(String morseCode);
}
