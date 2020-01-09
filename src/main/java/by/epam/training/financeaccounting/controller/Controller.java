package by.epam.training.financeaccounting.controller;

import by.epam.training.financeaccounting.dao.DaoInterface;
import by.epam.training.financeaccounting.dao.UserBean;
import by.epam.training.financeaccounting.service.ServiceInterfase;
import by.epam.training.financeaccounting.view.View;

public class Controller {
    ServiceInterfase serv;
    DaoInterface dao;

    public void run(View view, ServiceInterfase service, DaoInterface dao) {
        this.serv = service;
        serv.setData(dao);
        this.dao = dao;

        boolean end = false;
        while (!end){
            checkRegistration(view,this);
            end = true;
        }
    }

    public void checkRegistration(View view,Controller controller){

        if (view.entry(controller)) {
            displayUserState(view);

        } else {
            run(view, serv, dao);
        }
    }

    public boolean signIn(String name, String pass) {
        if (serv.getUserFromData(name, pass) != null) {
            return true;
        } else {
            return false;
        }
    }

    private void displayUserState(View view) {
        UserBean user = serv.getUser();
        view.displayUser(user.getIncome());
        System.out.println();
        view.displayUser(user.getConsumption());
    }

    public boolean registerNewUser(String name,String password){
        return serv.addNewUser(name,password);
    }
}
