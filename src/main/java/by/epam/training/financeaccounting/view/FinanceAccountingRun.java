package by.epam.training.financeaccounting.view;

import by.epam.training.financeaccounting.controller.Controller;
import by.epam.training.financeaccounting.controller.ControllerInterface;

import java.util.Scanner;

public class FinanceAccountingRun {
    Scanner scan;
    ControllerInterface contr;


    public void run() {
        scan = new Scanner(System.in);
        contr = new Controller();
        String name;
        String pass;

        System.out.println("Enter name.");
        name = scan.next();

        System.out.println("Enter password.");
        pass = scan.next();

        if (contr.signIn(name, pass)) {
            System.out.println("User found");
        } else
            System.out.println("User not found!");
    }
}
