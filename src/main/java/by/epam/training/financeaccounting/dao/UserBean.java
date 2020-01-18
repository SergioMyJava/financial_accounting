package by.epam.training.financeaccounting.dao;

import java.io.Serializable;
import java.util.Arrays;

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

    public String getName() {
        return name;
    }

    public String getPass() {return pass;}

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o == this){return true;}
        if(o.getClass() != this.getClass()){
            return false;
        }

        UserBean eq = (UserBean)o;
        if(eq.getName() == this.getName() && eq.getPass() == this.getPass()
        && Arrays.equals(eq.getIncome(),this.getIncome()) && Arrays.equals(eq.getConsumption(),this.getConsumption())){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Name = " + name + " Pass = " + pass + " Income " + Arrays.asList(income) +
                " Consumption " + Arrays.asList(consumption);
    }
}
