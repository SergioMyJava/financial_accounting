package by.epam.training.financeaccounting.controller;


import by.epam.training.financeaccounting.dao.UserBean;
import by.epam.training.financeaccounting.service.ServiceInterfase;
import by.epam.training.financeaccounting.view.View;

public class Controller {
    ServiceInterfase service;
    View view;
    boolean end = false;

    public void setEnd() {
        this.end = true;
    }

    public void run(View view, ServiceInterfase service) {
        this.service = service;
        this.view = view;
        checkRegistration(this);
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

    public void checkRegistration(Controller controller) {
        if (view.entry(controller)) {
            displayUserState(view);

        } else {
            run(view, service);
        }
    }

    public boolean signIn(String name, String pass) {
        if (service.getUserFromData(name, pass) != null) {
            return true;
        } else {
            return false;
        }
    }

    private void displayUserState(View view) {
        UserBean user = service.getUser();
        view.displayUser(user.getIncome());
        System.out.println();
        view.displayUser(user.getConsumption());
    }

    public boolean registerNewUser(String name, String password) {
        return service.addNewUser(name, password);
    }

    public void workWithIncome() {
        makeChanges(1);
    }

    public void workWithConsumption() {
        makeChanges(2);
    }

    private void makeChanges(int flag) {
        UserBean forChange = service.getUser();
        int userChoice = view.whatChangesMake();
        String[] arrForChange = null;
        if (flag == 1) {
            arrForChange = forChange.getIncome();
        }

        if (flag == 2) {
            arrForChange = forChange.getConsumption();
        }

        if (userChoice == 1) {
                view.displayUser(arrForChange);
                int category = view.selectCategory();
                if (checkCategory(category,arrForChange)) {
                    int addAmount = view.enterChange();
                    String[] newArr = service.addChangeToIncomeOrConsumption(addAmount, category, arrForChange);
                    saveChange(flag, newArr, forChange);
                } else {
                    view.tryAgain();
                }
        }

        if (userChoice == 2) {
            String newCategory = view.addCategory();
            String[] oldArr = arrForChange;
            String[] newArr = new String[oldArr.length + 1];
            for (int i = 0; i < oldArr.length; i++) {
                newArr[i] = oldArr[i];
            }
            newArr[newArr.length - 1] = newCategory;
            saveChange(flag, newArr, forChange);
        }
        if (userChoice == 3) {
            view.displayUser(arrForChange);
            int category = view.selectCategory();
            if(checkCategory(category,arrForChange)){
                arrForChange = deleteCategory(arrForChange,category);
                saveChange(flag,arrForChange,forChange);
            }
        }
        if (userChoice == -1) {
            workWithIncome();
        }
    }

    private void saveChange(int flag, String[] newArr, UserBean forChange) {
        if (flag == 1) {
            forChange.setIncome(newArr);
            view.displayUser(forChange.getIncome());
        }
        if (flag == 2) {
            forChange.setConsumption(newArr);
            view.displayUser(forChange.getConsumption());
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
                if(i >= category-1){
                    forReturn[i] = arr[i+1];
                }
                else {
                    forReturn[i] = arr[i];
                }
            }
            return forReturn;
        }
    }

    private boolean checkCategory(int category, String[] arrForCheck) {
        if (category <= arrForCheck.length && category > 0) {
            return true;
        } else {
            return false;
        }
    }
}
