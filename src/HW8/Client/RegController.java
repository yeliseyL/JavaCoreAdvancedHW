package HW8.Client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegController {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField nicknameField;
    @FXML
    public Button signUp;
    @FXML
    public Button cancel;
    @FXML
    public TextArea messageArea;

    public void tryToSignup(ActionEvent actionEvent) {
        controller.tryToReg(loginField.getText().trim(),
                passwordField.getText().trim(),
                nicknameField.getText().trim());
    }

    public void cancel(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            ((Stage) loginField.getScene().getWindow()).close();
        });
    }

    public void addSystemMessage(String message) {
        messageArea.appendText(message + "\n");
    }
}