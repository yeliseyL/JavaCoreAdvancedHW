package HW8.Client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField messageField;
    @FXML
    public Button sendBtn;
    @FXML
    public TextArea chatArea;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public HBox authPanel;
    @FXML
    public HBox chatPanel;

    private final int PORT = 8806;
    private final String IP_ADDRESS = "localhost";
    private final String CHAT_TITLE_EMPTY = "Chat";

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    private Boolean authenticated;
    private String nickname;

    private Stage stage;

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        chatPanel.setVisible(authenticated);
        chatPanel.setManaged(authenticated);

        if(!authenticated) {
            nickname = "";
        }

        setTtile(nickname);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            stage = (Stage)chatArea.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                if (socket != null && !socket.isClosed()) {
                    try {
                        out.writeUTF("/end");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        setAuthenticated(false);
    }

    private void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    // auth cycle
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/authok ")) {
                            nickname = str.split("\\s")[1];
                            setAuthenticated(true);
                            break;
                        }
                        chatArea.appendText(str);
                        System.out.println(str);
                    }
                    // work cycle
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            setAuthenticated(false);
                            break;
                        }
                        chatArea.appendText(str);
                        System.out.println(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
        try {
            out.writeUTF(messageField.getText() + "\n");
            messageField.requestFocus();
            messageField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            connect();
        }

        try {
            out.writeUTF(String.format("/auth %s %s", loginField.getText().trim(), passwordField.getText().trim()));
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTtile(String nickname) {
        Platform.runLater(() -> {
            stage.setTitle(CHAT_TITLE_EMPTY + " : " + nickname);
        });
    }
}
