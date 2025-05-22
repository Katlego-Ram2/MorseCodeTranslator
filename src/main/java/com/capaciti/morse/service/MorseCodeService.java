package com.capaciti.morse.service;

public interface MorseCodeService {
    String encode(String input);
    String decode(String morseCode);
}