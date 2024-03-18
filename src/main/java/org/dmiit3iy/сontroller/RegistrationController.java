package org.dmiit3iy.сontroller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.dmiit3iy.App;
import org.dmiit3iy.model.User;
import org.dmiit3iy.retrofit.UserRepository;

import java.io.IOException;

public class RegistrationController {
    public PasswordField passwordField;
    public TextField logintextField;
    private String login;
    private String password;
    UserRepository userRepository = new UserRepository();

    public void RegistrationButton(ActionEvent actionEvent) {
        login = logintextField.getText();
        password= passwordField.getText();
        User user = new User(login,password);
        try {
            userRepository.post(user);
            App.showMessage("Success", "the user has been successfully registered", Alert.AlertType.INFORMATION);
            App.closeWindow(actionEvent);

        } catch (IOException e) {
            App.showMessage("Warning", "User is not added", Alert.AlertType.WARNING);
        }
    }
}
