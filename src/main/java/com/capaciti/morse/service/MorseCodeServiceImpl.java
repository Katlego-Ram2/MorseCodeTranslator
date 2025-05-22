package com.capaciti.morse.service;

import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link MorseCodeService} interface.
 * <p>
 * This class delegates the actual logic for encoding and decoding
 * Morse code to the {@link MorseService} component.
 * </p>
 * <p>
 * Annotated with {@code @Service} to register it as a Spring-managed bean.
 * </p>
 */
@Service
public class MorseCodeServiceImpl implements MorseCodeService {

    private final MorseService morseService;

    /**
     * Constructor that injects the {@link MorseService} dependency.
     *
     * @param morseService the service that contains the encoding and decoding logic
     */
    public MorseCodeServiceImpl(MorseService morseService) {
        this.morseService = morseService;
    }

    /**
     * Encodes the given plain text into Morse code by delegating to {@link MorseService}.
     *
     * @param input the plain text to encode
     * @return the encoded Morse code string
     */
    @Override
    public String encode(String input) {
        return morseService.encode(input);
    }

    /**
     * Decodes the given Morse code into plain text by delegating to {@link MorseService}.
     *
     * @param morseCode the Morse code string to decode
     * @return the decoded plain text string
     */
    @Override
    public String decode(String morseCode) {
        return morseService.decode(morseCode);
    }
}
