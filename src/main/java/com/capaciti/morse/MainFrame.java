package com.capaciti.morse;

import com.capaciti.morse.service.MorseCodeService;
import com.capaciti.morse.service.MorseCodeServiceImpl;
import com.capaciti.morse.service.MorseService;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * MainFrame class represents the primary GUI window for the Morse Code Encoder/Decoder application.
 * It provides functionalities to encode/decode Morse code, play it as sound, and copy/paste to/from clipboard.
 */
public class MainFrame extends JFrame {

    // Declare components of the UI
    private final MorseCodeService morseCodeService; // Service for encoding/decoding Morse code
    private final JTextField textInputField; // Input field for entering text or Morse code
    private final JTextArea resultTextArea; // Output area displaying encoded/decoded results
    private final JButton encodeButton; // Button to trigger encoding
    private final JButton decodeButton; // Button to trigger decoding
    private final JButton copyButton; // Button to copy the result to clipboard
    private final JButton pasteButton; // Button to paste content from clipboard
    private final JButton playButton; // Button to play the Morse code as sound

    /**
     * Constructor for the MainFrame.
     * Initializes the frame properties and calls methods to setup the UI and actions.
     *
     * @param morseCodeService - The service for handling Morse code operations (encoding/decoding).
     */
    public MainFrame(MorseCodeService morseCodeService) {
        this.morseCodeService = morseCodeService; // Initialize the Morse code service

        // Set up the frame properties
        setTitle("Morse Code Encoder / Decoder");
        setSize(580, 480); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        setLocationRelativeTo(null); // Center the window on screen
        setResizable(false); // Disable resizing of the window

        // Initialize the UI components
        textInputField = new JTextField(30); // Text input field for entering text/Morse code
        resultTextArea = new JTextArea(6, 30); // Text area to display results
        encodeButton = new JButton("Encode"); // Button for encoding text to Morse code
        decodeButton = new JButton("Decode"); // Button for decoding Morse code to text
        copyButton = new JButton("Copy"); // Button for copying result to clipboard
        pasteButton = new JButton("Paste"); // Button for pasting clipboard content
        playButton = new JButton("Play"); // Button for playing Morse code sound

        // Set up the UI layout and button actions
        setupUI();
        setupActions();
    }

    /**
     * Sets up the user interface (UI) elements such as text fields, buttons, and panels.
     * It also applies specific styling for better user experience.
     */
    private void setupUI() {
        Font font = new Font("Verdana", Font.PLAIN, 15); // Set a common font style for the UI components

        // Configure text input field
        textInputField.setFont(font);
        textInputField.setMaximumSize(textInputField.getPreferredSize());
        textInputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), // Border color
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Internal padding
        ));

        // Configure result text area (non-editable)
        resultTextArea.setEditable(false);
        resultTextArea.setFont(font);
        resultTextArea.setLineWrap(true); // Enable word wrapping
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)), // Border color
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Internal padding
        ));

        // Apply button styles (color, text style)
        setupButtonStyle(encodeButton, new Color(33, 150, 243));
        setupButtonStyle(decodeButton, new Color(76, 175, 80));
        setupButtonStyle(copyButton, new Color(255, 152, 0));
        setupButtonStyle(pasteButton, new Color(255, 235, 59));
        setupButtonStyle(playButton, new Color(121, 85, 72)); // Brown color for the play button

        // Tooltips for buttons to give users information about their functionality
        encodeButton.setToolTipText("Encode plain text to Morse code");
        decodeButton.setToolTipText("Decode Morse code to plain text");
        copyButton.setToolTipText("Copy output text to clipboard");
        pasteButton.setToolTipText("Paste text from clipboard");
        playButton.setToolTipText("Play Morse code as sound");

        // Set up the main layout panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // Set padding
        mainPanel.setBackground(Color.WHITE); // Set background color

        // Add input panel, action buttons, and result text area to the main panel
        mainPanel.add(createInputPanel(font));
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(createButtonPanel());
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(new JScrollPane(resultTextArea));

        // Add the main panel to the frame
        add(mainPanel);
    }

    /**
     * Helper method to set up the style for buttons (background color, font, etc.).
     *
     * @param button - The button to style.
     * @param background - The background color of the button.
     */
    private void setupButtonStyle(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); // Remove focus border
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set hand cursor when hovering
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding inside the button
        button.setFont(new Font("Verdana", Font.BOLD, 13)); // Set bold font style
    }

    /**
     * Creates a panel that contains the input field and a label.
     *
     * @param font - The font used for the label.
     * @return A panel containing the input field and label.
     */
    private JPanel createInputPanel(Font font) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use vertical layout
        panel.setBackground(Color.WHITE);

        // Create and set up the input label
        JLabel inputLabel = new JLabel("Enter text or Morse code:");
        inputLabel.setFont(font);
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label

        // Add components to the panel
        panel.add(inputLabel);
        panel.add(Box.createVerticalStrut(10)); // Add space between the label and input field
        panel.add(textInputField);

        return panel;
    }

    /**
     * Creates a panel that contains action buttons (Encode, Decode, Copy, Paste, Play).
     *
     * @return A panel containing the buttons.
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        panel.setBackground(Color.WHITE);

        // Add buttons to the panel
        panel.add(encodeButton);
        panel.add(decodeButton);
        panel.add(copyButton);
        panel.add(pasteButton);
        panel.add(playButton);

        return panel;
    }

    /**
     * Sets up the action listeners for the buttons.
     * Each button performs a specific task, like encoding, decoding, or copying/pasting content.
     */
    private void setupActions() {
        // Encode button action: Convert text to Morse code
        encodeButton.addActionListener(e -> {
            String input = textInputField.getText().trim(); // Get user input
            resultTextArea.setText(
                    input.isEmpty() ? "" : morseCodeService.encode(input) // Encode using service
            );
        });

        // Decode button action: Convert Morse code to plain text
        decodeButton.addActionListener(e -> {
            String input = textInputField.getText().trim(); // Get Morse code input
            resultTextArea.setText(
                    input.isEmpty() ? "" : morseCodeService.decode(input) // Decode using service
            );
        });

        // Copy button action: Copy result to clipboard
        copyButton.addActionListener(e -> {
            String result = resultTextArea.getText();
            if (!result.isEmpty()) {
                Toolkit.getDefaultToolkit().getSystemClipboard()
                        .setContents(new java.awt.datatransfer.StringSelection(result), null); // Copy result to clipboard
                JOptionPane.showMessageDialog(this, "Copied to clipboard!"); // Show success message
            } else {
                JOptionPane.showMessageDialog(this, "Nothing to copy."); // Show message if no content to copy
            }
        });

        // Paste button action: Paste content from clipboard into input field
        pasteButton.addActionListener(e -> {
            try {
                String clipboardText = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard()
                        .getData(DataFlavor.stringFlavor); // Get clipboard content
                textInputField.setText(clipboardText); // Paste the content
            } catch (UnsupportedFlavorException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to paste from clipboard."); // Error handling
            }
        });

        // Play button action: Play Morse code as audio
        playButton.addActionListener(e -> {
            String morseCode = resultTextArea.getText().trim();
            if (!morseCode.isEmpty()) {
                new Thread(() -> playMorseCode(morseCode)).start(); // Play Morse code sound in a new thread
            } else {
                JOptionPane.showMessageDialog(this, "Nothing to play."); // Show message if no content to play
            }
        });
    }

    /**
     * Plays the given Morse code as sound (beeps for dots and dashes).
     *
     * @param morse - The Morse code string to play.
     */
    private void playMorseCode(String morse) {
        for (char c : morse.toCharArray()) {
            try {
                switch (c) {
                    case '.': beep(800, 150); break; // Dot (short beep)
                    case '-': beep(800, 400); break; // Dash (long beep)
                    case ' ': Thread.sleep(200); break; // Space between letters
                    case '/': Thread.sleep(600); break; // Space between words
                }
                Thread.sleep(100); // Small pause between symbols
            } catch (InterruptedException ignored) {
            }
        }
    }

    /**
     * Generates a beep sound at a specified frequency and duration.
     *
     * @param freq - The frequency of the beep in Hz.
     * @param duration - The duration of the beep in milliseconds.
     */
    private void beep(int freq, int duration) {
        float sampleRate = 44100; // Sample rate for audio
        byte[] buf = new byte[(int) (sampleRate * duration / 1000)]; // Create an empty buffer for the audio
        for (int i = 0; i < buf.length; i++) {
            double angle = 2.0 * Math.PI * i / (sampleRate / freq); // Calculate the sine wave
            buf[i] = (byte) (Math.sin(angle) * 127); // Apply sine wave for the beep sound
        }

        // Create an audio format and play the sound
        AudioFormat af = new AudioFormat(sampleRate, 8, 1, true, false);
        try (SourceDataLine sdl = AudioSystem.getSourceDataLine(af)) {
            sdl.open(af);
            sdl.start();
            sdl.write(buf, 0, buf.length); // Write the audio data to the line
            sdl.drain(); // Ensure the audio finishes playing
            sdl.stop(); // Stop the audio line
        } catch (LineUnavailableException ignored) {
        }
    }

    /**
     * Main method to launch the application.
     * Initializes the Morse service and displays the main frame.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MorseService morseService = new MorseService(); // Create the Morse service
            MorseCodeService service = new MorseCodeServiceImpl(morseService); // Create the Morse code service
            new MainFrame(service).setVisible(true); // Display the main frame
        });
    }
}
