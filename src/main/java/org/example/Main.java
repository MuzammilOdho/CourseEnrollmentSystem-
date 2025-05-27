package org.example;

import org.example.config.AppConfig;
import org.example.dao.UserDao;
import org.example.entity.Role;
import org.example.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//      UserDao userDao  = (UserDao) context.getBean("userDao");
//        userDao.save(new User(2 , "ali" , "6346" , Role.ADMIN));

    }
}