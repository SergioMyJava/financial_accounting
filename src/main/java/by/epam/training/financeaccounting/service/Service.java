package by.epam.training.financeaccounting.service;

import by.epam.training.financeaccounting.dao.DaoInterface;
import by.epam.training.financeaccounting.dao.UserBean;


public class Service implements ServiceInterfase {
    DaoInterface data;
    UserBean userService;

    public void setData(DaoInterface data) {
        this.data = data;

    }

    public UserBean getUser() {
        return userService;
    }

    public void setUserService(UserBean userService) {
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
        if (forCheck != null) {
            String checkPass = forCheck.getPass();
            if (checkPass.equals(pass)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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
        String[] incomeArrForChange = arrForChange;
        String categoryForChange = incomeArrForChange[category - 1];
        addAmount += getCategoryValue(categoryForChange);
        incomeArrForChange[category - 1] = replaceOldValueWithNew(categoryForChange, addAmount);
        return incomeArrForChange;
    }

    public int getCategoryValue(String categoryForChange) {
        int index = categoryForChange.indexOf(':');
        int value = Integer.parseInt(categoryForChange.substring(index + 1));
        return value;
    }

    public String replaceOldValueWithNew(String categoryForChange, int newValue) {
        String changed = categoryForChange.substring(0, categoryForChange.indexOf(':') + 1);
        return changed + newValue;
    }
}
