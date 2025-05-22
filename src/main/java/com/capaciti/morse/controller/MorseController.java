package com.capaciti.morse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capaciti.morse.service.MorseCodeService;  // Correct package import

@RestController
@RequestMapping("/api/morse")
public class MorseController {

    private final MorseCodeService morseCodeService;

    // Constructor injection for MorseCodeService
    public MorseController(MorseCodeService morseCodeService) {
        this.morseCodeService = morseCodeService;
    }

    // Endpoint to encode text into Morse code
    @GetMapping("/encode")
    public String encode(@RequestParam String text) {
        return morseCodeService.encode(text);  // Calls encode method of MorseCodeService
    }

    // Endpoint to decode Morse code into text
    @GetMapping("/decode")
    public String decode(@RequestParam String code) {
        return morseCodeService.decode(code);  // Calls decode method of MorseCodeService
    }
}
