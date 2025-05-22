package com.capaciti.morse.service;

import org.springframework.stereotype.Service;
import java.util.*;

/**
 * MorseService class provides the functionality for encoding and decoding Morse code.
 * It maintains mappings for encoding characters to Morse code and decoding Morse code back to characters.
 */
@Service  // Marks this class as a Spring service (bean)
public class MorseService {

    // Map for encoding characters to Morse code
    private static final Map<Character, String> morseMap = new HashMap<>();
    // Map for decoding Morse code back to characters
    private static final Map<String, Character> reverseMap = new HashMap<>();

    // Static block to populate the morseMap and reverseMap with character-Morse code pairs
    static {
        // Initialize the morseMap for letters and digits
        morseMap.put('A', ".-");
        morseMap.put('B', "-...");
        morseMap.put('C', "-.-.");
        morseMap.put('D', "-..");
        morseMap.put('E', ".");
        morseMap.put('F', "..-.");
        morseMap.put('G', "--.");
        morseMap.put('H', "....");
        morseMap.put('I', "..");
        morseMap.put('J', ".---");
        morseMap.put('K', "-.-");
        morseMap.put('L', ".-..");
        morseMap.put('M', "--");
        morseMap.put('N', "-.");
        morseMap.put('O', "---");
        morseMap.put('P', ".--.");
        morseMap.put('Q', "--.-");
        morseMap.put('R', ".-.");
        morseMap.put('S', "...");
        morseMap.put('T', "-");
        morseMap.put('U', "..-");
        morseMap.put('V', "...-");
        morseMap.put('W', ".--");
        morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--");
        morseMap.put('Z', "--..");

        morseMap.put('0', "-----");
        morseMap.put('1', ".----");
        morseMap.put('2', "..---");
        morseMap.put('3', "...--");
        morseMap.put('4', "....-");
        morseMap.put('5', ".....");
        morseMap.put('6', "-....");
        morseMap.put('7', "--...");
        morseMap.put('8', "---..");
        morseMap.put('9', "----.");

        morseMap.put(' ', "/");  // Space is represented as a forward slash

        // Initialize reverseMap for decoding Morse code into characters
        for (Map.Entry<Character, String> entry : morseMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Encodes a string of text into Morse code.
     * The input text is converted to uppercase, and each character is mapped to its corresponding Morse code.
     *
     * @param input The input text to encode.
     * @return A string representing the input text in Morse code.
     */
    public String encode(String input) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toUpperCase().toCharArray()) {
            result.append(morseMap.getOrDefault(ch, "?")).append(" "); // Use "?" for unmapped characters
        }
        return result.toString().trim();
    }

    /**
     * Decodes a Morse code string into plain text.
     * The input Morse code is split into words (delimited by " / ") and symbols (separated by space).
     *
     * @param morseCode The Morse code to decode.
     * @return A string representing the decoded plain text.
     */
    public String decode(String morseCode) {
        StringBuilder result = new StringBuilder();
        String[] words = morseCode.split(" / "); // Split words by " / "
        for (String word : words) {
            for (String symbol : word.split(" ")) { // Split symbols by spaces
                result.append(reverseMap.getOrDefault(symbol, '?')); // Use "?" for unknown symbols
            }
            result.append(" "); // Add space between words
        }
        return result.toString().trim();
    }
}
