package by.bsuir.committee.daoInterfaces;

import by.bsuir.committee.entity.User;

public interface UserDao {

    User getUser(String name, String password);

    void addUser(String name, String password);

    User getUserByName(String name);
}
