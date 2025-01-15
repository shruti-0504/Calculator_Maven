package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calGUI {



        private Calculator calculator = new Calculator();
        private boolean isDarkMode = false; // Keeps track of the current mode

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new calGUI().createAndShowGUI());
        }

        private void createAndShowGUI() {
            // Create the main frame
            JFrame frame = new JFrame("Simple Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(6, 2, 10, 10));

            // Create input fields and labels
            JLabel label1 = new JLabel("First Number:");
            JTextField num1Field = new JTextField();
            JLabel label2 = new JLabel("Second Number:");
            JTextField num2Field = new JTextField();
            JLabel label3 = new JLabel("Operator (+, -, *, /):");
            JTextField operatorField = new JTextField();
            JLabel resultLabel = new JLabel("Result: ");
            JLabel resultValue = new JLabel("");

            // Create calculate button
            JButton calculateButton = new JButton("Calculate");
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double num1 = Double.parseDouble(num1Field.getText());
                        double num2 = Double.parseDouble(num2Field.getText());
                        char operator = operatorField.getText().charAt(0);

                        double result;
                        switch (operator) {
                            case '+':
                                result = calculator.add(num1, num2);
                                break;
                            case '-':
                                result = calculator.subtract(num1, num2);
                                break;
                            case '*':
                                result = calculator.multiply(num1, num2);
                                break;
                            case '/':
                                result = calculator.divide(num1, num2);
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid operator!");
                        }
                        resultValue.setText(String.valueOf(result));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Calculation Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Create a toggle button for Dark Mode/Light Mode
            JToggleButton modeToggle = new JToggleButton("Switch to Dark Mode");
            modeToggle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isDarkMode = !isDarkMode;
                    updateTheme(frame, label1, num1Field, label2, num2Field, label3, operatorField, resultLabel, resultValue, calculateButton, modeToggle);
                }
            });

            // Add components to the frame
            frame.add(label1);
            frame.add(num1Field);
            frame.add(label2);
            frame.add(num2Field);
            frame.add(label3);
            frame.add(operatorField);
            frame.add(resultLabel);
            frame.add(resultValue);
            frame.add(modeToggle);
            frame.add(calculateButton);

            // Set initial theme
            updateTheme(frame, label1, num1Field, label2, num2Field, label3, operatorField, resultLabel, resultValue, calculateButton, modeToggle);

            // Make the frame visible
            frame.setVisible(true);
        }

        private void updateTheme(Component... components) {
            Color backgroundColor = isDarkMode ? Color.BLACK : Color.WHITE;
            Color foregroundColor = isDarkMode ? Color.WHITE : Color.BLACK;

            for (Component component : components) {
                if (component instanceof JFrame) {
                    ((JFrame) component).getContentPane().setBackground(backgroundColor);
                } else if (component instanceof JLabel || component instanceof JTextField || component instanceof JButton || component instanceof JToggleButton) {
                    component.setBackground(backgroundColor);
                    component.setForeground(foregroundColor);

                    if (component instanceof JToggleButton) {
                        ((JToggleButton) component).setText(isDarkMode ? "Switch to Light Mode" : "Switch to Dark Mode");
                    }
                }
            }
        }
    }


