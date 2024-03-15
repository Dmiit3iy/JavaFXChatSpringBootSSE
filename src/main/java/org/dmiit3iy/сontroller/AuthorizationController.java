package org.dmiit3iy.сontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.dmiit3iy.App;

import java.io.IOException;

public class AuthorizationController {
    public void buttonLogin(ActionEvent actionEvent) {
    }

    @FXML
    public void buttonRegistration(ActionEvent actionEvent) throws IOException {
        App.openWindowAndWait("registration.fxml", "Registration", null);
    }
}
