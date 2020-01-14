package by.epam.training.financeaccounting.view;

import by.epam.training.financeaccounting.controller.Controller;

import java.util.Scanner;

public class ConsoleView implements View {
    Scanner scan;
    Controller control = null;

    public boolean entry(Controller controller) {
        if (control == null) {
            this.control = controller;
        }
        scan = new Scanner(System.in);
        System.out.println("Do you have an account, or do you want to have one? 1. I have account. 2. I want to register an account. 3. Escape .");
        String answer = scan.nextLine();
        if ("1".equals(answer)) {
            return checkNamePassword();

        } else if ("2".equals(answer)) {
            registration();
            return entry(controller);

        } else if ("3".equals(answer)) {
            System.out.println("Good bye!");
            scan.close();
            return false;
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
            tryAgain();
            return false;
        }
    }

    public void displayUser(String[] arr) {
        int k = 1;
        for (int i = 0; i < arr.length; i++) {
            System.out.println((k++) + "." + " " + arr[i]);
        }
    }

    public void tryAgain() {
        System.out.println("Try it again.Something went wrong, check the entered data wrong.");
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
        }
    }

    public int changeIncomeOrConsumption() {
        System.out.println("Do you want to change: 1. Income . 2. Consumption . 3. Escape .");
        int chois = scan.nextInt();

        switch (chois) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                scan.close();
                return 3;
        }
        return 0;
    }

    public int whatChangesMake() {
        System.out.println("Want to add a new position, change an existing one? 1. Change existing. " +
                "2. Add a new position. 3. Delete position .");
        int userChoice = checkEnteredNumber();
        return userChoice;
    }

    public int selectCategory() {
        System.out.println("Choose in which category you want to make changes.");
        return checkEnteredNumber();
    }

    public int enterChange() {
        System.out.println("Enter amount.The entered amount will be added to the existing.");
        return checkEnteredNumber();
    }

    public String addCategory() {
        scan = new Scanner(System.in);
        System.out.println("Enter new name of category.The new category should not contain ':'.");
        String newCategoryName ="";
        if(scan.hasNext()) {
            newCategoryName = scan.nextLine();
        }
        if (newCategoryName.contains(":")) {
            System.out.println("You disobeyed recommendations.");
            addCategory();
        }

        System.out.println("Enter a value for the category.Value is greater than zero.");
        int newAmound = checkEnteredNumber();
        if (newAmound < 0) {
            System.out.println("Value must be a greater than zero.");
            addCategory();
        }

        return newCategoryName + ":" + newAmound;
    }

    public int checkEnteredNumber() {
        int enteredNumber;
        if (scan.hasNextInt()) {
            enteredNumber = scan.nextInt();
        } else {
            System.out.println("Entered not a number.");
            return -1;
        }
        return enteredNumber;
    }
}
