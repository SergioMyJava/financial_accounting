package by.epam.training.financeaccounting.service;

import by.epam.training.financeaccounting.dao.DaoInterface;
import by.epam.training.financeaccounting.dao.UserBean;

public interface ServiceInterfase {
    UserBean getUserFromData(String name, String pass);
    UserBean getUser();
    boolean addNewUser(String name,String password);
    void setData(DaoInterface data);
    String[] addChangeToIncomeOrConsumption(int addAmount, int category,String[] arrForChange);
}
