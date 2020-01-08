package by.epam.training.financeaccounting.dao;

import java.io.*;
import java.util.HashMap;

public class Dao implements DaoInterface {
    private HashMap<String, UserBean> users;

    public Dao() {
        users = new HashMap<String, UserBean>();
        readFromData();
    }

    public void setUsers(HashMap<String, UserBean> users) {
        this.users = users;
    }

    public UserBean getUser(String name) {
        UserBean forReturn = users.get(name);
        if (forReturn != null) {
            return forReturn;
        } else
            return null;
    }

    public boolean wrightToData() {
        try {
            FileOutputStream fos = new FileOutputStream("financedata.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
            return true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    public void readFromData() {
        try {
            FileInputStream fis = new FileInputStream("financedata.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (HashMap<String, UserBean>) ois.readObject();
            fis.close();
            ois.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
