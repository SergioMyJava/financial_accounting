package by.epam.training.financeaccounting.dao;

public interface DaoInterface {
    boolean wrightToData();
    void readFromData();
    UserBean getUser(String name);
}
