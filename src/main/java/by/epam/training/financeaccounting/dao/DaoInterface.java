package by.epam.training.financeaccounting.dao;

import java.util.HashMap;

public interface DaoInterface {
    boolean wrightToData();
    void readFromData();
    UserBean getUser(String name);
    void addUser( String name,UserBean user);
    HashMap<String, UserBean> getUsersMap();
    void delete(String name);
    boolean checkFillingDataInDao();
}
