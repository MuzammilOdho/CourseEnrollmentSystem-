package org.example.service.impl;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.exception.AuthenticationException;
import org.example.exception.DatabaseException;
import org.example.exception.UserAlreadyExistException;
import org.example.exception.UserNotFoundException;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User authenticate(String username, String password) {
        try {
            User user = userDao.findByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (UserNotFoundException e) {

            throw new AuthenticationException("Invalid username or password");
        }
        return null;
    }

    @Override
    public void register(User user) {
        User existing = null;
        try {
            existing = userDao.findByUsername(user.getUsername());

        } catch (UserNotFoundException e) {
            userDao.save(user.getUsername(), user.getPassword(), user.getRole().toString());
        }
        if (existing != null) {
            throw new UserAlreadyExistException("Username already exists: " + user.getUsername());
        }
    }
}
