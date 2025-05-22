package com.capaciti.morse.service;

import org.springframework.stereotype.Service;

@Service
public class MorseCodeServiceImpl implements MorseCodeService {

    private final MorseService morseService;

    // Constructor injection for MorseService
    public MorseCodeServiceImpl(MorseService morseService) {
        this.morseService = morseService;
    }

    @Override
    public String encode(String input) {
        return morseService.encode(input);  // Delegate to MorseService for encoding
    }

    @Override
    public String decode(String morseCode) {
        return morseService.decode(morseCode);  // Delegate to MorseService for decoding
    }
}
