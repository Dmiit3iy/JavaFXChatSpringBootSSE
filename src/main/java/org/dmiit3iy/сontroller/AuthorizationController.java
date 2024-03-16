package org.dmiit3iy.сontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.dmiit3iy.App;
import org.dmiit3iy.model.User;
import org.dmiit3iy.retrofit.UserRepository;

import java.io.IOException;
import java.net.ConnectException;

public class AuthorizationController {
    public PasswordField passwordFieldPassword;
    public TextField textAreaLogin;
    private String login;
    private String password;
    private UserRepository userRepository;

    @FXML
    public void buttonLogin(ActionEvent actionEvent) {
        login = textAreaLogin.getText();
        password = passwordFieldPassword.getText();
        userRepository = new UserRepository();
        User user = null;
        try {
            user = userRepository.get(login, password);
            if (user != null) {
                App.openWindow("chat.fxml", "Chat window", user);
                App.closeWindow(actionEvent);
            } else {
                App.showMessage("Mistake", "Incorrect login or password", Alert.AlertType.ERROR);
            }
        } catch (ConnectException e) {
            App.showMessage("Warning", "There is no connection to the server", Alert.AlertType.WARNING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void buttonRegistration(ActionEvent actionEvent) throws IOException {
        App.openWindowAndWait("registration.fxml", "Registration", null);
    }
}
