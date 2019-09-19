package com.company;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.util.HashMap;

public class ConversionListener implements DocumentListener {
    private UnitConverter converter = null;
    JComboBox<String> unitSelector;
    JLabel[] outputLabels;

    public ConversionListener(String mode, JComboBox<String> unitSelector, JLabel[] outputLabels) {
        converter = new UnitConverter(mode);
        this.unitSelector = unitSelector;
        this.outputLabels = outputLabels;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String inputText = "";
        try {
            inputText = e.getDocument().getText(0, e.getDocument().getLength());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        HashMap<String, Double> convertResults = converter.convert(unitSelector.getSelectedItem().toString(),
                Double.parseDouble(inputText));

        for (int i = 0; i < outputLabels.length; i++) {
            Double currentConversion = convertResults.get(outputLabels[i].getClientProperty("Name"));
            if (currentConversion.toString().length() > 5) {
                String formattedConversion = String.format("%.5E", currentConversion);
                outputLabels[i].setText(formattedConversion);
            } else {
                outputLabels[i].setText(currentConversion.toString());
            }
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}