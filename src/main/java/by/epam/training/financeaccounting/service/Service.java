package by.epam.training.financeaccounting.service;

import by.epam.training.financeaccounting.dao.DaoInterface;
import by.epam.training.financeaccounting.dao.UserBean;

import java.util.Collection;


public class Service implements ServiceInterfase {
    private DaoInterface data;
    private UserBean userService;

    public void setData(DaoInterface data) {
        this.data = data;

    }

    public UserBean getUser() {
        return userService;
    }

    private void setUserService(UserBean userService) {
        this.userService = userService;
    }

    public UserBean getUserFromData(String name, String pass) {
        UserBean forCheck = data.getUser(name);
        if (userCheckPass(forCheck, pass)) {
            setUserService(forCheck);
            return forCheck;
        } else {
            return null;
        }
    }

    private boolean userCheckPass(UserBean forCheck, String pass) {
        String checkPass = "";
        if (forCheck != null) {
            checkPass = forCheck.getPass();
        }
        return checkPass.equals(pass);
    }

    public boolean addNewUser(String name, String password) {
        UserBean newUser = new UserBean(name, password);
        newUser = defaultIncomeConsumption(newUser);
        data.addUser(newUser.getName(), newUser);
        return userCheckPass(newUser, newUser.getPass());
    }

    private UserBean defaultIncomeConsumption(UserBean newUser) {
        UserBean defaultUser = newUser;
        String[] defaultIncome = {"зарплата:0"};
        String[] defaultConsumption = {"еда:0", "одежда:0", "квартплата:0"};
        defaultUser.setIncome(defaultIncome);
        defaultUser.setConsumption(defaultConsumption);
        return defaultUser;
    }

    public String[] addChangeToIncomeOrConsumption(int addAmount, int category, String[] arrForChange) {
        String[] arrChange = arrForChange;
        String categoryForChange = arrChange[category - 1];
        addAmount += getCategoryValue(categoryForChange);
        arrChange[category - 1] = replaceOldValueWithNew(categoryForChange, addAmount);
        return arrChange;
    }

    private int getCategoryValue(String categoryForChange) {
        int index = categoryForChange.indexOf(':');
        return Integer.parseInt(categoryForChange.substring(index + 1));
    }

    private String replaceOldValueWithNew(String categoryForChange, int newValue) {
        String changed = categoryForChange.substring(0, categoryForChange.indexOf(':') + 1);
        return changed + newValue;
    }

    public void saveToData() {
        data.wrightToData();
    }

    public UserBean[] getingAllUsers() {
        return getUsers();
    }

    private UserBean[] getUsers() {
        Collection userFromMap = data.getUsersMap().values();
        UserBean[] usersForReturn = new UserBean[userFromMap.size()];
        int i = 0;
        for (Object a : userFromMap) {
            UserBean us = (UserBean) a;
            usersForReturn[i] = us;
            ++i;
        }
        return usersForReturn;
    }

    public void deleteUser(String name) {
        deleteUserByName(name);
    }

    private void deleteUserByName(String name) {
        data.delete(name);
    }

    public boolean checkFillingDataInService() {
        return data.checkFillingDataInDao();

    }
}
