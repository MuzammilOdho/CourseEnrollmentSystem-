package org.example.service.impl;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.service.UserService;

import java.util.Optional;

public class UserServiceImpl  implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User authenticate(String username, String password) {
      User user = userDao.findByUsername(username);

      if (user.getPassword().equals(password)) {
          return user;
      }
        return null;
    }

    @Override
    public void register(String username, String password, String role) {

        User user = userDao.findByUsername(username);
        if(user != null) {
            userDao.save(new User(username,password,role));
        }
    }
}
