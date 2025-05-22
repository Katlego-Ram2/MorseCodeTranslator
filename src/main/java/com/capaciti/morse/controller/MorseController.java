package com.capaciti.morse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capaciti.morse.service.MorseCodeService;

/**
 * MorseController provides REST endpoints for encoding plain text
 * to Morse code and decoding Morse code to plain text.
 */
@RestController
@RequestMapping("/api/morse")
public class MorseController {

    private final MorseCodeService morseCodeService;

    /**
     * Constructor that injects the MorseCodeService dependency.
     *
     * @param morseCodeService the service used for Morse code operations.
     */
    public MorseController(MorseCodeService morseCodeService) {
        this.morseCodeService = morseCodeService;
    }

    /**
     * REST endpoint to encode plain text into Morse code.
     *
     * @param text the plain text to be encoded.
     * @return the encoded Morse code string.
     */
    @GetMapping("/encode")
    public String encode(@RequestParam String text) {
        return morseCodeService.encode(text);  // Calls encode method of MorseCodeService
    }

    /**
     * REST endpoint to decode Morse code into plain text.
     *
     * @param code the Morse code string to decode.
     * @return the decoded plain text string.
     */
    @GetMapping("/decode")
    public String decode(@RequestParam String code) {
        return morseCodeService.decode(code);  // Calls decode method of MorseCodeService
    }
}
