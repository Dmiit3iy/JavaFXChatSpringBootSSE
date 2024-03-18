package org.dmiit3iy.сontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.MessageEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.dmiit3iy.dto.MsgEvent;
import org.dmiit3iy.model.Message;
import org.dmiit3iy.model.User;
import org.dmiit3iy.retrofit.MessageRepository;

import java.io.IOException;
import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class ChatController implements ControllerData<User> {

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public TextArea chatTextArea;

    public TextArea messageTextArea;
    public ListView onLineUsersListView;
    public Label loginLabel;
    private String message;
    private User user;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    MessageRepository messageRepository = new MessageRepository();

    @FXML
    public void initData(User user) {
        this.user = user;
        loginLabel.setText(user.getLogin());

        executorService.execute(() -> {
            try {
                while (true) {
                    System.out.println("Initialize event source");

                    String url = "http://localhost:8080/sse/chat/" + user.getId();
                    String url2 = "http://localhost:8080/user/online";

                    EventSource.Builder builder = new EventSource.Builder(new EventHandler() {
                        @Override
                        public void onOpen() throws Exception {
                        }

                        @Override
                        public void onClosed() throws Exception {
                        }

                        @Override
                        public void onMessage(String s, MessageEvent messageEvent) throws Exception {

                            Platform.runLater(() -> {
                               System.out.println(messageEvent.getData());

                                try {
                                    MsgEvent msgEvent = objectMapper.readValue(messageEvent.getData(), new TypeReference<MsgEvent>() {
                                    });
                                    Message msg = msgEvent.getMessage();

                                    chatTextArea.appendText("@ "+msg.getUser().getLogin());
                                    chatTextArea.appendText("\n");
                                    chatTextArea.appendText(msg.getLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                                    chatTextArea.appendText("\n");
                                    chatTextArea.appendText(msg.getMessage());
                                    chatTextArea.appendText("\n");
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }

                        @Override
                        public void onComment(String s) throws Exception {
                        }

                        @Override
                        public void onError(Throwable throwable) {
                        }
                    }, URI.create(url));

                    EventSource.Builder builder2 = new EventSource.Builder(new EventHandler() {
                        @Override
                        public void onOpen() throws Exception {

                        }

                        @Override
                        public void onClosed() throws Exception {
                        }

                        @Override
                        public void onMessage(String s, MessageEvent messageEvent) throws Exception {

                            Platform.runLater(() -> {
                                System.out.println(messageEvent.getData());

//                                try {
//

//                                    ArrayList<User> userArrayList = objectMapper.readValue(event.getMessage(), new TypeReference<ArrayList<User>>() {
//                                    });
//
//                                    emittersList = (ArrayList<String>) userArrayList.stream().map(x -> x.getLogin()).collect(Collectors.toList());
//
//                                    ObservableList<String> userList = FXCollections.observableList(emittersList);
//                                    onLineUsersListView.setItems(userList);
//                                } catch (JsonProcessingException e) {
//                                    throw new RuntimeException(e);
//                                }
                            });
                        }

                        @Override
                        public void onComment(String s) throws Exception {
                        }

                        @Override
                        public void onError(Throwable throwable) {
                        }
                    }, URI.create(url2));


                    try (
                            EventSource eventSource = builder.build();
                            EventSource eventSource2 = builder2.build()) {

                        eventSource.start();
                        eventSource2.start();

                        TimeUnit.SECONDS.sleep(60);
                    }
                }
            } catch (InterruptedException ignored) {
            }
        });


    }

    public void sendButton(ActionEvent actionEvent) throws IOException {
        message = messageTextArea.getText();
        messageRepository.post(String.valueOf(user.getId()), message);
        messageTextArea.clear();
    }

    public void getAllMsgButton(ActionEvent actionEvent) throws IOException {
        chatTextArea.clear();
        List<Message> msgArrayList = messageRepository.getAll();
        msgArrayList.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getLocalDateTime().compareTo(o2.getLocalDateTime());
            }
        });
        for (Message m : msgArrayList
        ) {
            chatTextArea.appendText("@ "+m.getUser().getLogin());
            chatTextArea.appendText("\n");
            chatTextArea.appendText(m.getLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            chatTextArea.appendText("\n");
            chatTextArea.appendText(m.getMessage());
            chatTextArea.appendText("\n");
        }
    }


}
