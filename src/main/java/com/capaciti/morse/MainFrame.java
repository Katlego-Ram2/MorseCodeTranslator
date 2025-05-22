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

public class MainFrame extends JFrame {

    private final MorseCodeService morseCodeService;
    private final JTextField textInputField;
    private final JTextArea resultTextArea;
    private final JButton encodeButton;
    private final JButton decodeButton;
    private final JButton copyButton;
    private final JButton pasteButton;
    private final JButton playButton;

    public MainFrame(MorseCodeService morseCodeService) {
        this.morseCodeService = morseCodeService;

        setTitle("Morse Code Encoder / Decoder");
        setSize(580, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        textInputField = new JTextField(30);
        resultTextArea = new JTextArea(6, 30);
        encodeButton = new JButton("Encode");
        decodeButton = new JButton("Decode");
        copyButton = new JButton("Copy");
        pasteButton = new JButton("Paste");
        playButton = new JButton("Play");

        setupUI();
        setupActions();
    }

    private void setupUI() {
        Font font = new Font("Verdana", Font.PLAIN, 15);

        textInputField.setFont(font);
        textInputField.setMaximumSize(textInputField.getPreferredSize());
        textInputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        resultTextArea.setEditable(false);
        resultTextArea.setFont(font);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        setupButtonStyle(encodeButton, new Color(33, 150, 243));
        setupButtonStyle(decodeButton, new Color(76, 175, 80));
        setupButtonStyle(copyButton, new Color(255, 152, 0));
        setupButtonStyle(pasteButton, new Color(255, 235, 59));
        setupButtonStyle(playButton, new Color(121, 85, 72)); // Brown

        encodeButton.setToolTipText("Encode plain text to Morse code");
        decodeButton.setToolTipText("Decode Morse code to plain text");
        copyButton.setToolTipText("Copy output text to clipboard");
        pasteButton.setToolTipText("Paste text from clipboard");
        playButton.setToolTipText("Play Morse code as sound");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(createInputPanel(font));
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(createButtonPanel());
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(new JScrollPane(resultTextArea));

        add(mainPanel);
    }

    private void setupButtonStyle(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Verdana", Font.BOLD, 13));
    }

    private JPanel createInputPanel(Font font) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel inputLabel = new JLabel("Enter text or Morse code:");
        inputLabel.setFont(font);
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(inputLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(textInputField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        panel.setBackground(Color.WHITE);
        panel.add(encodeButton);
        panel.add(decodeButton);
        panel.add(copyButton);
        panel.add(pasteButton);
        panel.add(playButton);
        return panel;
    }

    private void setupActions() {
        encodeButton.addActionListener(e -> {
            String input = textInputField.getText().trim();
            resultTextArea.setText(
                    input.isEmpty() ? "" : morseCodeService.encode(input)
            );
        });

        decodeButton.addActionListener(e -> {
            String input = textInputField.getText().trim();
            resultTextArea.setText(
                    input.isEmpty() ? "" : morseCodeService.decode(input)
            );
        });

        copyButton.addActionListener(e -> {
            String result = resultTextArea.getText();
            if (!result.isEmpty()) {
                Toolkit.getDefaultToolkit().getSystemClipboard()
                        .setContents(new java.awt.datatransfer.StringSelection(result), null);
                JOptionPane.showMessageDialog(this, "Copied to clipboard!");
            } else {
                JOptionPane.showMessageDialog(this, "Nothing to copy.");
            }
        });

        pasteButton.addActionListener(e -> {
            try {
                String clipboardText = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard()
                        .getData(DataFlavor.stringFlavor);
                textInputField.setText(clipboardText);
            } catch (UnsupportedFlavorException | IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to paste from clipboard.");
            }
        });

        playButton.addActionListener(e -> {
            String morseCode = resultTextArea.getText().trim();
            if (!morseCode.isEmpty()) {
                new Thread(() -> playMorseCode(morseCode)).start(); // Background thread
            } else {
                JOptionPane.showMessageDialog(this, "Nothing to play.");
            }
        });
    }

    // Play Morse code sound
    private void playMorseCode(String morse) {
        for (char c : morse.toCharArray()) {
            try {
                switch (c) {
                    case '.': beep(800, 150); break; // dot
                    case '-': beep(800, 400); break; // dash
                    case ' ': Thread.sleep(200); break; // space between letters
                    case '/': Thread.sleep(600); break; // space between words
                }
                Thread.sleep(100); // small pause between symbols
            } catch (InterruptedException ignored) {
            }
        }
    }

    // Generate beep sound
    private void beep(int freq, int duration) {
        float sampleRate = 44100;
        byte[] buf = new byte[(int) (sampleRate * duration / 1000)];
        for (int i = 0; i < buf.length; i++) {
            double angle = 2.0 * Math.PI * i / (sampleRate / freq);
            buf[i] = (byte) (Math.sin(angle) * 127);
        }

        AudioFormat af = new AudioFormat(sampleRate, 8, 1, true, false);
        try (SourceDataLine sdl = AudioSystem.getSourceDataLine(af)) {
            sdl.open(af);
            sdl.start();
            sdl.write(buf, 0, buf.length);
            sdl.drain();
            sdl.stop();
        } catch (LineUnavailableException ignored) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MorseService morseService = new MorseService();
            MorseCodeService service = new MorseCodeServiceImpl(morseService);
            new MainFrame(service).setVisible(true);
        });
    }
}
