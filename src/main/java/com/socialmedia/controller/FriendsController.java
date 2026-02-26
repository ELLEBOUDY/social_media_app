package com.socialmedia.controller;

import com.socialmedia.dao.FriendDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.sql.SQLException;
import java.util.List;

public class FriendsController {

    @FXML private ListView<String> friendsListView;
    private FriendDAO friendDAO = new FriendDAO();

    private int loggedInUserId = 1;

    @FXML
    public void initialize() {
        loadFriends();
    }

    private void loadFriends() {
        try {
            List<String> names = friendDAO.getFriendNames(loggedInUserId);
            friendsListView.getItems().setAll(names);

            if (names.isEmpty()) {
                System.out.println("No friends found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}