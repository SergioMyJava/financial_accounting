package by.epam.training.financeaccounting.view;

import by.epam.training.financeaccounting.controller.Controller;

public interface View {
    boolean entry(Controller contr);
    void displayUser(String[] arr);
    void userNotFound();
    int changeIncomeOrConsumption();
    int whatChangesMake();
    int selectCategory();
    int enterChange();
    String addCategory();
}
