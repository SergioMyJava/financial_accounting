package by.epam.training.financeaccounting.view;

import by.epam.training.financeaccounting.controller.Controller;

public interface View {
    boolean entry(Controller contr);
    void displayIncomeConsumption(String[] arr);
    void tryAgain();
    int changeIncomeOrConsumption();
    int whatChangesMake();
    int selectCategory();
    int enterChange();
    String addCategory();
    int specialEntrance();
    void displayToString(String string);
    String blackList();
    void dataIsAmpty();
}
