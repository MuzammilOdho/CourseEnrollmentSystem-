package org.example.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputHandler {

    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
