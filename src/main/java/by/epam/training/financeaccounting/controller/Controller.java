package by.epam.training.financeaccounting.controller;

import by.epam.training.financeaccounting.service.Service;
import by.epam.training.financeaccounting.service.ServiceInterfase;

public class Controller implements ControllerInterface{
    ServiceInterfase serv;

    public Controller() {
        serv = new Service();
    }

    public boolean signIn(String name, String pass){
        if(serv.getUser(name,pass) != null){
            return true;
        }
        else
        return false;
    }

    public void displayUserState() {
        serv.displayUserState();
    }
}
