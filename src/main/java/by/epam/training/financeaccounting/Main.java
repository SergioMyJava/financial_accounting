package by.epam.training.financeaccounting;

import by.epam.training.financeaccounting.controller.Controller;
import by.epam.training.financeaccounting.dao.Dao;
import by.epam.training.financeaccounting.dao.DaoInterface;
import by.epam.training.financeaccounting.service.Service;
import by.epam.training.financeaccounting.service.ServiceInterfase;
import by.epam.training.financeaccounting.view.ConsoleView;
import by.epam.training.financeaccounting.view.View;

public class Main {
    public static void main(String[] args){
        View v = new ConsoleView();
        ServiceInterfase service = new Service();
        DaoInterface dao = new Dao();
        service.setData(dao);
        Controller controller = new Controller();
        controller.run(v,service);
    }
}
