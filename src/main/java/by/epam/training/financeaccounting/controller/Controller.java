package by.epam.training.financeaccounting.controller;


import by.epam.training.financeaccounting.dao.UserBean;
import by.epam.training.financeaccounting.service.ServiceInterfase;
import by.epam.training.financeaccounting.view.View;

import java.util.LinkedList;
import java.util.List;

public class Controller {
    private List internalResources;
    private ServiceInterfase service;
    private View view;
    private boolean end = false;

    private void setEnd() {
        this.end = true;
    }

    public void run(View view, ServiceInterfase service) {
        this.service = service;
        this.view = view;
        entryController(this);
        while (!end) {
            int choisNumber = view.changeIncomeOrConsumption();
            if (choisNumber == 1) {
                workWithIncome();
            }
            if (choisNumber == 2) {
                workWithConsumption();
            }
            if (choisNumber == 3) {
                setEnd();
                service.saveToData();
            }

        }
    }

    private void entryController(Controller controller) {
        if (view.entry(controller)) {
            displayUserState(view);
        } else {
            run(view, service);
        }
    }

    public boolean signIn(String name, String pass) {
        administrationCheck(name, pass);
        checkFillingData();

        if (checkBlackList(name)) {
            return false;
        }

        if (service.getUserFromData(name, pass) != null) {
            return true;
        } else {
            return false;
        }
    }

    private void displayUserState(View view) {
        UserBean user = service.getUser();
        view.displayIncomeConsumption(user.getIncome());
        System.out.println();
        view.displayIncomeConsumption(user.getConsumption());
    }

    public boolean registerNewUser(String name, String password) {
        return service.addNewUser(name, password);
    }

    private void workWithIncome() {
        makeChanges(1);
    }

    private void workWithConsumption() {
        makeChanges(2);
    }

    private void makeChanges(int flag) {
        UserBean forChange = service.getUser();
        int userChoice = view.whatChangesMake();
        String[] arrForChange = new String[]{};
        if (flag == 1) {
            arrForChange = forChange.getIncome();
        }

        if (flag == 2) {
            arrForChange = forChange.getConsumption();
        }

        if (userChoice == 1) {
            view.displayIncomeConsumption(arrForChange);
            int category = view.selectCategory();
            if (checkCategory(category, arrForChange)) {
                int addAmount = view.enterChange();
                String[] newArr = service.addChangeToIncomeOrConsumption(addAmount, category, arrForChange);
                saveChange(flag, newArr, forChange);
            } else {
                view.tryAgain();
            }
        }

        if (userChoice == 2) {
            String newCategory = view.addCategory();
            String[] newArr = new String[arrForChange.length + 1];
            for (int i = 0; i < arrForChange.length; i++) {
                newArr[i] = arrForChange[i];
            }
            newArr[newArr.length - 1] = newCategory;
            saveChange(flag, newArr, forChange);
        }
        if (userChoice == 3) {
            view.displayIncomeConsumption(arrForChange);
            int category = view.selectCategory();
            if (checkCategory(category, arrForChange)) {
                arrForChange = deleteCategory(arrForChange, category);
                saveChange(flag, arrForChange, forChange);
            }
        }
        if (userChoice == -1) {
            workWithIncome();
        }
    }

    private void saveChange(int flag, String[] newArr, UserBean forChange) {
        if (flag == 1) {
            forChange.setIncome(newArr);
            view.displayIncomeConsumption(forChange.getIncome());
        }
        if (flag == 2) {
            forChange.setConsumption(newArr);
            view.displayIncomeConsumption(forChange.getConsumption());
        }
    }

    private String[] deleteCategory(String[] arr, int category) {
        String[] forReturn = new String[arr.length - 1];

        if (category == arr.length) {
            for (int i = 0; i < forReturn.length; i++) {
                forReturn[i] = arr[i];
            }
            return forReturn;
        } else {
            for (int i = 0; i < arr.length - 1; i++) {
                if (i >= category - 1) {
                    forReturn[i] = arr[i + 1];
                } else {
                    forReturn[i] = arr[i];
                }
            }
            return forReturn;
        }
    }

    private boolean checkCategory(int category, String[] arrForCheck) {
        return (category <= arrForCheck.length && category > 0);
    }

    private void administrationCheck(String name, String pass) {
        if (name.equals("admin") && pass.equals("1111")) {
            special();
        }
    }

    private void special() {
        boolean end = false;
        while (!end) {
            int adminChoice = view.specialEntrance();
            switch (adminChoice) {
                case 1:
                    displayAllUser();
                    break;
                case 2:
                    blackList();
                    break;
                case -1:
                    end = true;
            }
        }
    }

    private void displayAllUser() {
        UserBean[] users = service.getingAllUsers();
        for (int i = 0; i < users.length; i++) {
            view.displayToString(users[i].toString());
        }
    }

    private void blackList() {
        String name = view.blackList();
        if (internalResources == null) {
            internalResources = new LinkedList();
        }
        internalResources.add(name);
        service.deleteUser(name);
    }

    private boolean checkBlackList(String name) {
        if (internalResources != null) {
            for (Object a : internalResources) {
                if (a.equals(name)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void checkFillingData() {
        if (!service.checkFillingDataInService()) {
            view.dataIsAmpty();
            run(view, service);
        }
    }
}
