package com.capaciti.morse;

import com.capaciti.morse.service.MorseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MorseServiceTest {

    @Autowired
    private MorseService morseService;

    private static final String VALID_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

    /**
     * Generates a random string consisting only of valid Morse characters
     * (letters, digits, and space).
     */
    private String generateRandomValidString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = VALID_CHARS.charAt(random.nextInt(VALID_CHARS.length()));
            sb.append(c);
        }
        return sb.toString();
    }

    @Test
    void testEncodeDecodeRoundTripWithRandomInputs() {
        for (int i = 0; i < 10; i++) {  // test 10 random strings
            String input = generateRandomValidString(20);
            String encoded = morseService.encode(input);
            assertNotNull(encoded, "Encoded result should not be null");
            assertFalse(encoded.contains("?"), "Encoded result should not contain '?' for valid chars");

            String decoded = morseService.decode(encoded);

            // Trim input before comparison since decode() trims output
            assertEquals(input.toUpperCase().trim(), decoded,
                    "Decoded output should match original input (uppercased & trimmed)");
        }
    }

    @Test
    void testEncodeProducesQuestionMarkForInvalidChars() {
        String input = "HELLO!@#"; // contains invalid chars
        String encoded = morseService.encode(input);
        assertTrue(encoded.contains("?"), "Encoding should produce '?' for invalid characters");
    }

    @Test
    void testDecodeProducesQuestionMarkForInvalidMorse() {
        // Construct invalid Morse dynamically by adding invalid symbol '......'
        String validEncoded = morseService.encode("SOS");
        String invalidMorse = validEncoded + " ...... ";
        String decoded = morseService.decode(invalidMorse);
        assertTrue(decoded.contains("?"), "Decoding should produce '?' for unknown Morse sequences");
    }

    @Test
    void testGenerateMorseAudioProducesValidWav() {
        String text = generateRandomValidString(10);
        byte[] audioBytes = morseService.generateMorseAudio(text);

        assertNotNull(audioBytes, "Audio bytes should not be null");
        assertTrue(audioBytes.length > 44, "Audio byte array should be larger than WAV header");

        // WAV header should start with "RIFF"
        String header = new String(audioBytes, 0, 4);
        assertEquals("RIFF", header);
    }
}
