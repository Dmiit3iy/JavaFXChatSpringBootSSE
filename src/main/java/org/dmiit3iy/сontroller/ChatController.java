package org.dmiit3iy.сontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.dmiit3iy.model.User;

public class ChatController implements ControllerData<User>{
    public TextArea chatTextArea;
    public TextArea messageTextArea;
    public ListView onLineUsersListView;
    public Label loginLabel;
    private User user;

    @FXML
    public void initData(User user) {
        this.user = user;
        loginLabel.setText(user.getLogin());
    }

    public void sendButton(ActionEvent actionEvent) {
    }

    public void getAllMsgButton(ActionEvent actionEvent) {
    }
}
