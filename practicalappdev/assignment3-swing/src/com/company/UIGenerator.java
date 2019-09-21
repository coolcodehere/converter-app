package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class UIGenerator {

    public UIGenerator() {
        JFrame primaryFrame = new JFrame();
        primaryFrame.setTitle("Weight and Distance Converter");

        JPanel distancePanel = createPanel("Distance");
        JPanel weightPanel = createPanel("Weight");

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.setBounds(10, 10, 250, 280);
        tabbedPane.add("Distance", distancePanel);
        tabbedPane.add("Weight", weightPanel);

        primaryFrame.add(tabbedPane);
        primaryFrame.setSize(283, 340);
        primaryFrame.setLayout(null);
        primaryFrame.setVisible(true);
    }

    /*
    createPanel

    Arguments
    mode: A string that can either be "Distance" or "Weight" - anything else throws a

    Generates UI panels within tabbed panes for weight and distance.
    */
    private JPanel createPanel(String mode) {
        String[] labels = null;

        if (mode.equals("Distance")) {
            labels = new String[] {"Centimeters", "Meters", "Kilometers", "Inches", "Feet", "Miles"};
        } else if (mode.equals("Weight")) {
            labels = new String[] {"Grams", "Kilograms", "Ounces", "Pounds", "Stone", "Earths"};
        } else {
            throw new IllegalArgumentException();
        }

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JLabel[] outputLabels = new JLabel[6];
        JComboBox<String> inputUnitSelector = new JComboBox<>();

        JTextField inputField = new JTextField();
        inputUnitSelector.addActionListener(e -> inputField.setText(""));

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String inputText = inputField.getText();
                char newChar = e.getKeyChar();

                if ((!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.') || inputText.length() > 16) {
                    e.consume();
                } else if (e.getKeyChar() == '.' && (inputText.indexOf('.') != -1 || inputText.equals(""))) {
                    e.consume();
                }
            }
        });

        inputField.setPreferredSize(new Dimension(20, 150));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setFont(new Font("Dialog", Font.PLAIN, 14));

        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 1;
        inputConstraints.insets = new Insets(0, 120, 20, 0);
        inputConstraints.ipadx = 150;
        inputConstraints.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(inputField, inputConstraints);

        GridBagConstraints unitSelectorConstraints = new GridBagConstraints();
        unitSelectorConstraints.gridx = 0;
        unitSelectorConstraints.gridy = 0;
        unitSelectorConstraints.insets = new Insets(0, 120, 5, 0);
        unitSelectorConstraints.ipadx = 40;
        unitSelectorConstraints.anchor = GridBagConstraints.NORTHWEST;

        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.anchor = GridBagConstraints.NORTHWEST;
        titleConstraints.insets = new Insets(0, 110, 0, 0);
        titleConstraints.gridx = 0;

        GridBagConstraints outputConstraints = new GridBagConstraints();
        Dimension labelSizes = new Dimension(200, 23);
        outputConstraints.anchor = GridBagConstraints.NORTHWEST;
        outputConstraints.insets = new Insets(0, -110, 0, 0);
        outputConstraints.gridx = 1;

        for (int i = 0; i < outputLabels.length; i++) {
            titleConstraints.gridy = i + 3;
            outputConstraints.gridy = i + 3;
            inputUnitSelector.addItem(labels[i]);
            JLabel outputTitle = new JLabel(labels[i]);
            outputTitle.setMaximumSize(labelSizes);
            outputTitle.setMinimumSize(labelSizes);
            mainPanel.add(outputTitle, titleConstraints);

            JLabel output = new JLabel();
            output.setMaximumSize(labelSizes);
            output.setMinimumSize(labelSizes);
            output.putClientProperty("Name", labels[i]);
            output.setFont(new Font("Dialog", Font.PLAIN, 16));
            output.setText("0.0");
            output.putClientProperty("Name", labels[i]);
            outputLabels[i] = output;
            mainPanel.add(output, outputConstraints);
        }

        inputField.getDocument().addDocumentListener(new InputListener(mode, inputUnitSelector, outputLabels));
        mainPanel.add(inputUnitSelector, unitSelectorConstraints);
        inputUnitSelector.setSelectedIndex(0);
        return mainPanel;
    }
}