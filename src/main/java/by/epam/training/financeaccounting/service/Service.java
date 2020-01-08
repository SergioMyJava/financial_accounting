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
        if(userCheckPass(forCheck,pass)){
            userFromData = forCheck;
            return forCheck;
        }
        else
            return null;
    }

    public boolean userCheckPass(UserBean forCheck,String pass){
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

    public void displayUserState(){
        System.out.println("Current state of finance : ");
        showArrayContents(userFromData.getIncome());
        System.out.println();
        showArrayContents(userFromData.getConsumption());
    }

    private void showArrayContents(String[] arr){
        int k = 1;
        for(int i = 0;i<arr.length;i++){
            System.out.println( (k++) + "." + " " + arr[i]);
        }
    }
}
