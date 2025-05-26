package com.capaciti.morse.service;

import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link MorseCodeService} interface.
 * Delegates encoding, decoding, and audio generation to {@link MorseService}.
 */
@Service
public class MorseCodeServiceImpl implements MorseCodeService {

    private final MorseService morseService;

    public MorseCodeServiceImpl(MorseService morseService) {
        this.morseService = morseService;
    }

    @Override
    public String encode(String input) {
        return morseService.encode(input);
    }

    @Override
    public String decode(String morseCode) {
        return morseService.decode(morseCode);
    }

    @Override
    public byte[] generateMorseAudio(String text) {
        return morseService.generateMorseAudio(text);
    }
}
