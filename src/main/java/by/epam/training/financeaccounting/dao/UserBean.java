package by.epam.training.financeaccounting.dao;

import java.io.Serializable;

public class UserBean implements Serializable {
    String name;
    String pass;
    String[] income;
    String[] consumption;

    public UserBean(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public void setIncome(String[] income) {
        this.income = income;
    }

    public void setConsumption(String[] consumption) {
        this.consumption = consumption;
    }

    public String[] getIncome() {
        return income;
    }

    public String[] getConsumption() {
        return consumption;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getPass() {return pass;}
}
