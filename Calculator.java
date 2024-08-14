import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel buttonPanel;
    private String currentOperator;
    private double firstOperand;
    private boolean isOperatorClicked;

    // Constructor
    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create display
        display = new JTextField();
        display.setFont(new Font("Helvetica Neue", Font.PLAIN, 50));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setPreferredSize(new Dimension(400, 100));
        add(display, BorderLayout.NORTH);

        // Create buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 0, 0)); // No margin between buttons
        buttonPanel.setBackground(Color.DARK_GRAY);
        String[] buttons = {
            "C", "±", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", "="
        };
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
            button.setFocusPainted(false);
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);

        // Initialize variables
        currentOperator = "";
        firstOperand = 0;
        isOperatorClicked = false;

        // Make the frame visible
        setVisible(true);
    }

    // Action listener for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789".contains(command)) {
            if (isOperatorClicked) {
                display.setText("");
                isOperatorClicked = false;
            }
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            firstOperand = 0;
            currentOperator = "";
        } else if (command.equals("±")) {
            double value = Double.parseDouble(display.getText());
            value *= -1;
            display.setText(String.valueOf(value));
        } else if (command.equals("%")) {
            double value = Double.parseDouble(display.getText());
            value /= 100;
            display.setText(String.valueOf(value));
        } else if ("÷×-+".contains(command)) {
            firstOperand = Double.parseDouble(display.getText());
            currentOperator = command;
            isOperatorClicked = true;
        } else if (command.equals("=")) {
            double secondOperand = Double.parseDouble(display.getText());
            double result = 0;
            switch (currentOperator) {
                case "÷":
                    result = firstOperand / secondOperand;
                    break;
                case "×":
                    result = firstOperand * secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "+":
                    result = firstOperand + secondOperand;
                    break;
            }
            display.setText(String.valueOf(result));
            currentOperator = "";
        } else if (command.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }
    }

    // Main method to launch the calculator
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
