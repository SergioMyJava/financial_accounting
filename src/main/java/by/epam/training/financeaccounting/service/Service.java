package by.epam.training.financeaccounting.service;

import by.epam.training.financeaccounting.dao.DaoInterface;
import by.epam.training.financeaccounting.dao.UserBean;


public class Service implements ServiceInterfase {
    DaoInterface data;
    UserBean userService;

    public void setData(DaoInterface data){
            this.data = data;

    }

    public void setUserService(UserBean userService){
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

    public UserBean getUser(){
        return userService;
    }

    public boolean addNewUser(String name,String password){
        UserBean newUser = new UserBean(name,password);
        newUser = defaultIncomeConsumption(newUser);
        data.addUser( newUser.getName(),newUser);
        return userCheckPass(newUser,newUser.getPass());
    }

    private UserBean defaultIncomeConsumption(UserBean newUser){
        UserBean defaultUser = newUser;
        String[] defaultIncome = {"зарплата:0"};
        String[] defaultConsumption = {"еда:0","одежда:0","квартплата:0"};
        defaultUser.setIncome(defaultIncome);
        defaultUser.setConsumption(defaultConsumption);
        return defaultUser;
    }
}
