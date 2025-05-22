package com.capaciti.morse.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service  // Makes this class a Spring Bean
public class MorseService {

    private static final Map<Character, String> morseMap = new HashMap<>();
    private static final Map<String, Character> reverseMap = new HashMap<>();

    static {
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

        morseMap.put(' ', "/");

        // Reverse mapping for decoding
        for (Map.Entry<Character, String> entry : morseMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
    }

    public String encode(String input) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toUpperCase().toCharArray()) {
            result.append(morseMap.getOrDefault(ch, "?")).append(" ");
        }
        return result.toString().trim();
    }

    public String decode(String morseCode) {
        StringBuilder result = new StringBuilder();
        String[] words = morseCode.split(" / ");
        for (String word : words) {
            for (String symbol : word.split(" ")) {
                result.append(reverseMap.getOrDefault(symbol, '?'));
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
}
