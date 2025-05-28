package com.capaciti.morse.service;

import org.springframework.stereotype.Service;

import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

/**
 * MorseService class provides the functionality for encoding and decoding Morse code.
 * It also includes audio generation for Morse code.
 */
@Service
public class MorseService {

    private static final Map<Character, String> morseMap = new HashMap<>();
    private static final Map<String, Character> reverseMap = new HashMap<>();

    static {
        // Letters
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

        // Numbers
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

        // Space as slash
        morseMap.put(' ', "/");

        // Reverse map
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

    /**
     * Generates Morse code audio (WAV format) for a given text.
     *
     * @param text the plain text to convert to Morse code audio
     * @return byte array representing WAV audio
     */
    public byte[] generateMorseAudio(String text) {
        String morse = encode(text);
        float sampleRate = 44100;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            for (char c : morse.toCharArray()) {
                switch (c) {
                    case '.':
                        addTone(out, sampleRate, 800, 0.1); // dot = 100 ms
                        addSilence(out, sampleRate, 0.1);
                        break;
                    case '-':
                        addTone(out, sampleRate, 800, 0.3); // dash = 300 ms
                        addSilence(out, sampleRate, 0.1);
                        break;
                    case ' ':
                        addSilence(out, sampleRate, 0.3); // inter-letter gap
                        break;
                    case '/':
                        addSilence(out, sampleRate, 0.7); // inter-word gap
                        break;
                }
            }

            byte[] audioData = out.toByteArray();
            AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
            ByteArrayInputStream bais = new ByteArrayInputStream(audioData);
            AudioInputStream audioStream = new AudioInputStream(bais, format, audioData.length);

            ByteArrayOutputStream wavOut = new ByteArrayOutputStream();
            AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, wavOut);

            return wavOut.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private void addTone(ByteArrayOutputStream out, float sampleRate, int freq, double durationSeconds) {
        int samples = (int)(durationSeconds * sampleRate);
        for (int i = 0; i < samples; i++) {
            double angle = 2.0 * Math.PI * freq * i / sampleRate;
            out.write((byte)(Math.sin(angle) * 127));  // 8-bit PCM, -128 to 127
        }
    }

    private void addSilence(ByteArrayOutputStream out, float sampleRate, double durationSeconds) {
        int samples = (int)(durationSeconds * sampleRate);
        for (int i = 0; i < samples; i++) {
            out.write(0);
        }
    }
}
