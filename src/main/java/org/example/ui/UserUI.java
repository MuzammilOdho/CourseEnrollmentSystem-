package org.example.ui;

import org.example.entity.User;
import org.example.exception.AuthenticationException;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUI {


    private final UserService userService;
    private final InputHandler inputHandler;

    @Autowired
    public UserUI(UserService userService, InputHandler inputHandler) {
        this.userService = userService;
        this.inputHandler = inputHandler;
    }

    public User login() {
        try {
            String username = inputHandler.readString("Username: ");
            String password = inputHandler.readString("Password: ");

            User user = userService.authenticate(username, password);

            if (user == null) {
//            System.out.println("Invalid credentials! Please try again.");
                return null;
            }

            System.out.println("Login successful as " + user.getRole());
            return user;

        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again later.");
            return null;
        }
    }
}
