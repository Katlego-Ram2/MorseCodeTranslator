package com.capaciti.morse;

import com.capaciti.morse.service.MorseService;
import com.capaciti.morse.service.MorseCodeService;
import com.capaciti.morse.service.MorseCodeServiceImpl;
import com.capaciti.morse.controller.MorseController;  // Assuming this is the correct service class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JTextField textInputField;
    private JTextArea resultTextArea;
    private JButton encodeButton;
    private JButton decodeButton;

    private final MorseController morseController;  // Fixed variable name

    public MainFrame(MorseController morseController) {
        this.morseController = morseController;

        // Set up the main frame
        setTitle("Morse Code Encoder/Decoder");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set up components
        textInputField = new JTextField(20);
        resultTextArea = new JTextArea(5, 20);
        encodeButton = new JButton("Encode");
        decodeButton = new JButton("Decode");

        // Set up the text area to be read-only and scrollable
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        // Set up font and padding
        Font font = new Font("Arial", Font.PLAIN, 14);
        textInputField.setFont(font);
        resultTextArea.setFont(font);
        encodeButton.setFont(font);
        decodeButton.setFont(font);

        // Set up button colors
        encodeButton.setBackground(new Color(63, 81, 181));
        encodeButton.setForeground(Color.WHITE);
        decodeButton.setBackground(new Color(233, 30, 99));
        decodeButton.setForeground(Color.WHITE);

        // Set up the layout
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(new JLabel("Enter Text to Encode/Decode:"));
        inputPanel.add(textInputField);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);
        add(buttonPanel, BorderLayout.CENTER);

        add(scrollPane, BorderLayout.SOUTH);

        // Action listeners for encoding and decoding buttons
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textInputField.getText().trim();
                if (!inputText.isEmpty()) {
                    String encodedText = morseController.encode(inputText);
                    resultTextArea.setText("Encoded Morse Code:\n" + encodedText);
                } else {
                    resultTextArea.setText("Please enter text to encode.");
                }
            }
        });

        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textInputField.getText().trim();
                if (!inputText.isEmpty()) {
                    String decodedText = morseController.decode(inputText);
                    resultTextArea.setText("Decoded Text:\n" + decodedText);
                } else {
                    resultTextArea.setText("Please enter Morse code to decode.");
                }
            }
        });

        // Tooltips for buttons
        encodeButton.setToolTipText("Click to encode text into Morse code");
        decodeButton.setToolTipText("Click to decode Morse code into text");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MorseService morseService = new MorseService();
            MorseCodeService morseCodeService = new MorseCodeServiceImpl(morseService);
            MorseController controller = new MorseController(morseCodeService);
            MainFrame frame = new MainFrame(controller);
            frame.setVisible(true);
        });
    }


}
