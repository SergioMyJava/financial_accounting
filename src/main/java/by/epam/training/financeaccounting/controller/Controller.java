package by.epam.training.financeaccounting.controller;


import by.epam.training.financeaccounting.dao.UserBean;
import by.epam.training.financeaccounting.service.ServiceInterfase;
import by.epam.training.financeaccounting.view.View;

public class Controller {
    ServiceInterfase service;
    View view;

    public void run(View view, ServiceInterfase service) {
        this.service = service;
        this.view = view;
        boolean end = false;
        checkRegistration(this);
        while (!end) {
            int choisNumber = view.changeIncomeOrConsumption();
            if (choisNumber == 1) {
                workWithIncome();
            }
            if (choisNumber == 2) {
                workWithConsumption();
                run(view, service);
            }

            System.out.println("Exit");

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
        UserBean forChange = service.getUser();
        int userChoice = view.whatChangesMake();

        if (userChoice == 1) {
            view.displayUser(forChange.getIncome());
            int category = view.selectCategory();
            if(category <= forChange.getIncome().length && category > 0) {
                int addAmount = view.enterChange();
                String[] oldIncome = forChange.getIncome();
                String[] newIncom = service.addChangeToIncomeOrConsumption(addAmount,category, oldIncome);
                forChange.setIncome(newIncom);
                view.displayUser(forChange.getIncome());
            }
        }

        if (userChoice == 2) {
            String newCategory = view.addCategory();
            String[] oldIncome = forChange.getIncome();
            String[] newIncome = new String[oldIncome.length + 1];
            for(int i = 0;i < oldIncome.length;i++){
                newIncome[i] = oldIncome[i];
            }
            newIncome[newIncome.length-1] = newCategory;
            forChange.setIncome(newIncome);
            view.displayUser(forChange.getIncome());
            //System.out.println("New category name : " + newCategory );
        }
        if (userChoice == -1) {
            workWithIncome();
        }

    }



    public void workWithConsumption() {
    }

}
