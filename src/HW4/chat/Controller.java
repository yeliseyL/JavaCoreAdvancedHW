package HW4.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public TextField messageField;
    @FXML
    public Button sendBtn;
    @FXML
    public TextArea chatArea;

    public void sendMessage(ActionEvent actionEvent) {
        chatArea.appendText(messageField.getText() + "\n");
        messageField.clear();
    }
}
