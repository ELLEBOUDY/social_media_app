package com.socialmedia.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MenuController {

    @FXML private VBox contentArea;
    @FXML private Button newsFeedButton;
    @FXML private Button profileButton;
    @FXML private Button notificationsButton;
    @FXML private Button friendRequestsButton;
    @FXML private Button searchButton;
    @FXML private Button logoutButton;
    @FXML private Button friendsButton;

    private List<Button> menuButtons;

    @FXML
    public void initialize() {
        menuButtons = Arrays.asList(newsFeedButton, profileButton, notificationsButton, friendRequestsButton, searchButton,friendsButton);

        renderView("newsfeed.fxml");
        setHighlight(newsFeedButton);
    }

    private void setHighlight(Button selectedButton) {
        for (Button btn : menuButtons) {
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #666; -fx-font-weight: normal; -fx-padding: 12; -fx-background-radius: 12; -fx-cursor: hand;");
        }

        selectedButton.setStyle("-fx-background-color: #6e2da115; -fx-text-fill: #6e2da1; -fx-font-weight: bold; -fx-padding: 12; -fx-background-radius: 12; -fx-cursor: hand;");
    }

    private void renderView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/" + fxmlFile));
            Node view = loader.load();
            contentArea.getChildren().setAll(view);
            VBox.setVgrow(view, javafx.scene.layout.Priority.ALWAYS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToNewsFeed() {
        renderView("newsfeed.fxml");
        setHighlight(newsFeedButton);
    }

    @FXML
    private void goToProfile() {
        renderView("profile.fxml");
        setHighlight(profileButton);
    }

    @FXML
    private void goToNotifications() {
        renderView("notification.fxml");
        setHighlight(notificationsButton);
    }

    @FXML
    private void goToFriendRequests() {
        renderView("friend_request.fxml");
        setHighlight(friendRequestsButton);
    }

    @FXML
    private void goToSearch() {
        renderView("search.fxml");
        setHighlight(searchButton);
    }

    @FXML
    private void goToFriends() {
        renderView("friends.fxml"); // تأكد من اسم الملف
        setHighlight(friendsButton);
    }

    @FXML
    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));
            javafx.stage.Stage stage = (javafx.stage.Stage) logoutButton.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(loader.load(), 600, 650));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}