package org.example.service;

import org.example.entity.User;

public interface UserService {
    User authenticate(String username, String password);
    void register(String username, String password , String role);
}
