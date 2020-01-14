package by.epam.training.financeaccounting.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDao {

    Dao testDao = new Dao();
    HashMap<String,UserBean> createdInTest = new HashMap();

    @BeforeEach
    public void fillTxt() {
        UserBean t1 = new UserBean("vasa", "123");
        String[] incomt1 = {"зарплата:1300", "сдача квартиры:400", "сдача гаража:60"};
        String[] consumptiont1 = {"еда:500", "одежда:200", "машина:300", "непредвиденные расходы:150"};
        t1.setIncome(incomt1);
        t1.setConsumption(consumptiont1);

        UserBean t2 = new UserBean("Колян", "123zx");
        String[] incomt2 = {"зарплата:800"};
        String[] consumptiont2 = {"еда:400", "одежда:20", "непредвиденные расходы:150", "квартплата:120"};
        t2.setIncome(incomt2);
        t2.setConsumption(consumptiont2);

        createdInTest.put(t1.getName(), t1);
        createdInTest.put(t2.getName(), t2);
        testDao.setUsersMap(createdInTest);
        testDao.wrightToData();
    }

    @Test
    public void testReadFromData() {
        testDao.readFromData();
        HashMap<String,UserBean> fromTxt = testDao.getUsersMap();
        for(UserBean f : fromTxt.values()){
            for(UserBean a : createdInTest.values()){
                System.out.println(f.equals(a));
            }
        }

        assertTrue(fromTxt.equals(createdInTest));
    }
}
