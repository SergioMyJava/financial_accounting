package by.epam.training.financeaccounting.view;

import by.epam.training.financeaccounting.controller.Controller;

import java.util.Scanner;

public class ConsoleView implements View {
    Scanner scan;
    Controller control = null;

    public boolean entry(Controller controller) {
        if(control == null){
            this.control = controller;
        }
        scan = new Scanner(System.in);
        System.out.println("Do you have an account, or do you want to have one? 1. I have account. 2. I want to register an account.");
        String answer = scan.nextLine();
        if ("1".equals(answer)) {
            return checkNamePassword();

        } else if ("2".equals(answer)) {
            registration();
            return entry(controller);
        } else {
            System.out.println("You entered an invalid number.Try it again.");
            entry(controller);
        }
        return false;
    }

    private boolean checkNamePassword() {
        String name;
        String pass;

        System.out.println("Enter name.");
        name = scan.nextLine();

        System.out.println("Enter password.");
        pass = scan.nextLine();

        if (control.signIn(name, pass)) {
            System.out.println("User found!");
            System.out.println("Current state of finance : ");
            return true;
        } else {
            userNotFound();
            return false;
        }
    }

    public void displayUser(String[] arr) {
        int k = 1;
        for (int i = 0; i < arr.length; i++) {
            System.out.println((k++) + "." + " " + arr[i]);
        }
    }

    public void userNotFound() {
        System.out.println("User not found!Try it again.");
    }

    public void registration() {
        String name;
        String password;
        System.out.println("Enter your name.");
        name = scan.nextLine();

        System.out.println("Enter your password.");
        password = scan.nextLine();

        if (control.registerNewUser(name, password)) {
            System.out.println("Registration completed successfully.");
        } else {
            System.out.println("For some reason, something went wrong try again or contact support..");
            registration();
        }
    }

}
