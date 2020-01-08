package by.epam.training.financeaccounting.service;


import by.epam.training.financeaccounting.dao.Dao;
import by.epam.training.financeaccounting.dao.DaoInterface;
import by.epam.training.financeaccounting.dao.UserBean;


public class Service implements ServiceInterfase{
    DaoInterface data;
    UserBean userFromData;

    public Service(){
      data = new Dao();
    }

    public UserBean getUser(String name,String pass){
        UserBean forCheck = data.getUser(name);
        if(userCheck(forCheck,pass)){
            userFromData = forCheck;
            return forCheck;
        }
        else
            return null;
    }

    public boolean userCheck(UserBean forCheck,String pass){
        if(forCheck != null){
            String checkPass = forCheck.getPass();
            if(checkPass.equals(pass)){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
}
