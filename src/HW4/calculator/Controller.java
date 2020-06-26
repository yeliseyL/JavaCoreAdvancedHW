package HW4.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public TextField inputField;
    @FXML
    public Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot,
            btnPlus, btnDiv, btnMult, btnMinus, btnComp, btnDelete, btnClear, btnModule, btnSqrt;

    public void printDigit(ActionEvent actionEvent) {
        inputField.appendText(((Button)actionEvent.getSource()).getText());
    }

    public void compute(ActionEvent actionEvent) {
        String expression = inputField.getText();
        inputField.setText(String.valueOf(computeExpression(expression)));
    }

    private Double computeExpression(String expression) {
        String[] operands;
        Double result = 0.0;
        if (expression.contains("+")) {
            operands = expression.split("[+]");
            result = Double.parseDouble(operands[0]) + Double.parseDouble(operands[1]);
        }
        if (expression.contains("-")) {
            operands = expression.split("[-]");
            result = Double.parseDouble(operands[0]) - Double.parseDouble(operands[1]);
        }
        if (expression.contains("*")) {
            operands = expression.split("[*]");
            result = Double.parseDouble(operands[0]) * Double.parseDouble(operands[1]);
        }
        if (expression.contains("/")) {
            operands = expression.split("[/]");
            result = Double.parseDouble(operands[0]) / Double.parseDouble(operands[1]);
        }
        if (expression.contains("%")) {
            operands = expression.split("[%]");
            result = (double) (Integer.parseInt(operands[0]) % Integer.parseInt(operands[1]));
        }
        return result;
    }

    public void deleteLast(ActionEvent actionEvent) {
        inputField.setText(inputField.getText(0, inputField.getLength()-1));
    }

    public void clearText(ActionEvent actionEvent) {
        inputField.clear();
    }

    public void printPercent(ActionEvent actionEvent) {
        inputField.appendText("%");
    }

    public void compSqrt(ActionEvent actionEvent) {
        double operand = Double.parseDouble(inputField.getText());
        inputField.setText(String.valueOf(Math.sqrt(operand)));
    }

}
