package by.epam.training.financeaccounting.service;

import by.epam.training.financeaccounting.dao.UserBean;

public interface ServiceInterfase {
    UserBean getUser(String name, String pass);
}
