package com.capaciti.morse;

import com.capaciti.morse.service.MorseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MorseServiceTest {

    @Autowired
    private MorseService morseService;

    @Test
    void testEncode() {
        String input = "SOS";
        String encoded = morseService.encode(input);
        assertEquals("... --- ...", encoded);
    }

    @Test
    void testDecode() {
        String morseCode = "... --- ...";
        String decoded = morseService.decode(morseCode);
        assertEquals("SOS", decoded);
    }
}
