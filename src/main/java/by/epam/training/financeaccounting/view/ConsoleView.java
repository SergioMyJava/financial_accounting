package by.epam.training.financeaccounting.view;

import by.epam.training.financeaccounting.controller.Controller;

import java.util.Scanner;

public class ConsoleView implements View {
    private Scanner scan;
    private Controller control = null;

    public boolean entry(Controller controller) {
        if (control == null) {
            this.control = controller;
        }
        scan = new Scanner(System.in);
        System.out.println("Do you have an account, or do you want to have one? 1. I have account. 2. I want to register an account. 3. Escape .");
        int answer = checkEnteredNumber();
        switch (answer) {
            case 1:
                return checkNamePassword();
            case 2:
                registration();
                return entry(controller);
            case 3:
                System.out.println("Good bye!");
                scan.close();
                return false;
            case -1:
                entry(controller);
        }
        return false;
    }

    private boolean checkNamePassword() {
        String name;
        String password;
        scan = new Scanner(System.in);
        name = enteredName();
        password = enteredPass();

        if (control.signIn(name, password)) {
            System.out.println("Current state of finance : ");
            return true;
        } else {
            tryAgain();
            return false;
        }
    }

    public void displayIncomeConsumption(String[] arr) {
        int k = 1;
        for (int i = 0; i < arr.length; i++) {
            System.out.println((k++) + "." + " " + arr[i]);
        }
    }

    public void tryAgain() {
        System.out.println("Try it again.Something went wrong, check the entered data wrong.");
    }

    private void registration() {
        String name;
        String password;
        scan = new Scanner(System.in);
        name = enteredName();
        password = enteredPass();

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
        return checkEnteredNumber();
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
        String newCategoryName = "";
        if (scan.hasNext()) {
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

    private int checkEnteredNumber() {
        int enteredNumber;
        if (scan.hasNextInt()) {
            enteredNumber = scan.nextInt();
        } else {
            System.out.println("You entered an invalid number.Try it again.");
            return -1;
        }
        return enteredNumber;
    }

    public int specialEntrance() {
        System.out.println("Log out all users or block user. 1. Log out. 2. Block user. 3. Escape.");
        int number = checkEnteredNumber();
        switch (number) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return -1;
        }
        return -1;
    }

    public void displayToString(String string){
        System.out.println(string);
    }

    public String blackList(){
        scan = new Scanner(System.in);
        return enteredName();
    }

    private String enteredName(){
        System.out.println("Enter name.");
        return scan.nextLine();
    }

    private String enteredPass(){
        System.out.println("Enter password.");
        return scan.nextLine();
    }

    public void dataIsAmpty(){
        System.out.println("File empty nothing to read.");
    }
}
