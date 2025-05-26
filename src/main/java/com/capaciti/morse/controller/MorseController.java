package com.capaciti.morse.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capaciti.morse.service.MorseCodeService;

/**
 * MorseController provides REST endpoints for encoding, decoding,
 * and generating sound for Morse code.
 */
@RestController
@RequestMapping("/api/morse")
public class MorseController {

    private final MorseCodeService morseCodeService;

    public MorseController(MorseCodeService morseCodeService) {
        this.morseCodeService = morseCodeService;
    }

    @GetMapping("/encode")
    public String encode(@RequestParam String text) {
        return morseCodeService.encode(text);
    }

    @GetMapping("/decode")
    public String decode(@RequestParam String code) {
        return morseCodeService.decode(code);
    }

    @GetMapping("/sound")
    public ResponseEntity<byte[]> playMorseSound(@RequestParam String text) {
        byte[] audioData = morseCodeService.generateMorseAudio(text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(audioData.length);
        headers.setContentDispositionFormData("attachment", "morse_code.wav");

        return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
    }
}
