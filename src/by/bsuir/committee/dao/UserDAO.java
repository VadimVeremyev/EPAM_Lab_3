package by.bsuir.committee.dao;

import by.bsuir.committee.Db.ConnectionPool;
import by.bsuir.committee.Db.DbConnectionPool;
import by.bsuir.committee.Db.UserDb;
import by.bsuir.committee.entity.User;
import by.bsuir.committee.helpinfo.DaoHelpinfo;

import java.sql.SQLException;

public class UserDao implements by.bsuir.committee.daoInterfaces.UserDao {
    private static ConnectionPool connectionPool;

    public UserDao() {
        try {
            connectionPool = DbConnectionPool.create(DaoHelpinfo.getUrl(), DaoHelpinfo.getUsername(), DaoHelpinfo.getPassword());
        } catch (SQLException e) {
            connectionPool = null;
        }
    }

    public User getUser(String name, String password) {
        return UserDb.getUser(name, password, connectionPool);
    }

    public User getUserByName(String name) {
        return UserDb.getUserByName(name, connectionPool);
    }

    public void addUser(String name, String password) {
        UserDb.createUser(new User(name, password), connectionPool);
    }

}
