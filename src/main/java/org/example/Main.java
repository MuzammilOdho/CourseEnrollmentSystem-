package org.example;

import org.example.config.AppConfig;
import org.example.ui.ConsoleController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var controller = context.getBean(ConsoleController.class);
        controller.start();

    }
}