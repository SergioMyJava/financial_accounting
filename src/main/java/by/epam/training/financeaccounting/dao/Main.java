package by.epam.training.financeaccounting.dao;

import by.epam.training.financeaccounting.controller.Controller;

import java.util.HashMap;

public class Main {
    public static void main(String[] args){

        Controller r = new Controller();
      int[] e = new int[12];
      e[1] = 1;
      e[2] = 43;
        e[3] = 43;
        System.out.println(e.length);


//        UserBean t1 = new UserBean("vasa","123");
//        String[] incomt1 = {"зарплата:1300","сдача квартиры:400","сдача гаража:60"};
//        String[] consumptiont1 = {"еда:500","одежда:200","машина:300","непредвиденные расходы:150"};
//        t1.setIncome(incomt1);
//        t1.setConsumption(consumptiont1);
//
//        UserBean t2 = new UserBean("Колян","123zx");
//        String[] incomt2 = {"зарплата:800"};
//        String[] consumptiont2 = {"еда:400","одежда:20","непредвиденные расходы:150","квартплата:120"};
//        t2.setIncome(incomt2);
//        t2.setConsumption(consumptiont2);
//
//        Dao testDao  = new Dao();
//        HashMap right = new HashMap();
//        right.put(t1.getName(),t1);
//        right.put(t2.getName(),t2);
//        testDao.setUsersMap(right);
//        testDao.wrightToData();
//
//        Dao nextTest = new Dao();
//        nextTest.readFromData();
//        HashMap yeork = nextTest.getUser();
//        UserBean r = (UserBean) yeork.get("vasa");
//        System.out.println(r.getPass());
    }
}
