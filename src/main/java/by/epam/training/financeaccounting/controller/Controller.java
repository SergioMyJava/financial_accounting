package by.epam.training.financeaccounting.controller;


import by.epam.training.financeaccounting.dao.UserBean;
import by.epam.training.financeaccounting.service.ServiceInterfase;
import by.epam.training.financeaccounting.view.View;

public class Controller {
    ServiceInterfase service;
    View view;

    public void setEnd() {
        this.end = true;
    }

    boolean end = false;

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
            if(choisNumber == 3){
                setEnd();
            }

        }
    }

    public void checkRegistration(Controller controller) {
        if (view.entry(controller)) {
            displayUserState(view);

        } else {
            setEnd();
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

    private void makeChanges(int flag){
        UserBean forChange = service.getUser();
        int userChoice = view.whatChangesMake();
        String[] arrForChange = null;
        if(flag == 1){
            arrForChange = forChange.getIncome();
        }

        if(flag == 2){
            arrForChange = forChange.getConsumption();
        }

        if (userChoice == 1) {
            view.displayUser(arrForChange);
            int category = view.selectCategory();
            if(category <= arrForChange.length && category > 0) {
                int addAmount = view.enterChange();
                String[] oldArr = arrForChange;
                String[] newArr = service.addChangeToIncomeOrConsumption(addAmount,category, oldArr);
                saveChange(flag,newArr,forChange);
            }
        }

        if (userChoice == 2) {
            String newCategory = view.addCategory();
            String[] oldArr = arrForChange;
            String[] newArr = new String[oldArr.length + 1];
            for(int i = 0;i < oldArr.length;i++){
                newArr[i] = oldArr[i];
            }
            newArr[newArr.length-1] = newCategory;
            saveChange(flag,newArr,forChange);
        }
        if (userChoice == -1) {
            workWithIncome();
        }
    }

    private void saveChange(int flag,String[] newArr,UserBean forChange){
        if(flag == 1){
            forChange.setIncome(newArr);
            view.displayUser(forChange.getIncome());
        }
        if(flag == 2){
            forChange.setConsumption(newArr);
            view.displayUser(forChange.getConsumption());
        }
    }
}
