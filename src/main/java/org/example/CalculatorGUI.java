package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorGUI {

    private Calculator calculator = new Calculator();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

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

        // Add components to the frame
        frame.add(label1);
        frame.add(num1Field);
        frame.add(label2);
        frame.add(num2Field);
        frame.add(label3);
        frame.add(operatorField);
        frame.add(resultLabel);
        frame.add(resultValue);
        frame.add(new JLabel()); // Empty placeholder
        frame.add(calculateButton);

        // Make the frame visible
        frame.setVisible(true);
    }
}

