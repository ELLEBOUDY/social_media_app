package com.socialmedia.controller;

import com.socialmedia.util.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import com.socialmedia.dao.UserDAO;

public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleRegister() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (name == null || name.trim().isEmpty()) {
            messageLabel.setText("Name cannot be empty!");
            return;
        }
        if (email == null || email.trim().isEmpty()) {
            messageLabel.setText("Email cannot be empty!");
            return;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            messageLabel.setText("Invalid email format!");
            return;
        }
        if (password == null || password.trim().isEmpty()) {
            messageLabel.setText("Password cannot be empty!");
            return;
        }

        try {
            String hashedPassword = PasswordUtil.hashPassword(password);
            userDAO.registerUser(name, email, hashedPassword);
            messageLabel.setText("Registration successful! You can now login.");
            nameField.clear();
            emailField.clear();
            passwordField.clear();
        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load(), 400, 300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}