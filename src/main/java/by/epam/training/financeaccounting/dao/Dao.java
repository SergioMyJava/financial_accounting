package by.epam.training.financeaccounting.dao;

import java.io.*;
import java.util.*;

public class Dao implements DaoInterface {
    private HashMap<String, UserBean> usersMap;

    public void setUsersMap(HashMap<String, UserBean> usersMap) {
        this.usersMap = usersMap;
    }

    public HashMap<String, UserBean> getUsersMap() {
        return usersMap;
    }

    public Dao() {
        usersMap = new HashMap<String, UserBean>();
        readFromData();
    }

    public void addUser(String name, UserBean user) {
        usersMap.put(name, user);
    }

    public UserBean getUser(String name) {
        UserBean forReturn = usersMap.get(name);
        if (forReturn != null) {
            return forReturn;
        } else {
            return null;
        }
    }

    public boolean wrightToData() {
        try {
            FileOutputStream fos = new FileOutputStream("financedata.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(usersMap);
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
            File checkForNull = new File("financedata.txt");
            if (checkForNull.length() != 0) {
                FileInputStream fis = new FileInputStream("financedata.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                usersMap = (HashMap<String, UserBean>) ois.readObject();
                fis.close();
                ois.close();
            } else {
                System.out.println("File empty nothing to read.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        usersMap.remove(name);
    }

    public boolean checkFillingDataInDao() {
        int size = usersMap.size();
        if (size == 0) {
            return false;
        } else {
            return true;
        }
    }
}
