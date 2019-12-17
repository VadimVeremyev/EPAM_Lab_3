package by.bsuir.committee.dao;

import by.bsuir.committee.Db.EnrolleeDb;
import by.bsuir.committee.Db.ConnectionPool;
import by.bsuir.committee.Db.DbConnectionPool;
import by.bsuir.committee.entity.Enrollee;
import by.bsuir.committee.helpinfo.DaoHelpinfo;

import java.sql.SQLException;
import java.util.ArrayList;

public class EnrolleeDao implements by.bsuir.committee.daoInterfaces.ClientDao {
    private static ConnectionPool connectionPool;

    public EnrolleeDao() {
        try {
            connectionPool = DbConnectionPool.create(DaoHelpinfo.getUrl(), DaoHelpinfo.getUsername(), DaoHelpinfo.getPassword());
        } catch (SQLException e) {
            connectionPool = null;
        }
    }

    @Override
    public Enrollee getEnrollee(int id) {
        return EnrolleeDb.getEnrollee(id, connectionPool);
    }

    @Override
    public ArrayList<Enrollee> getEnrollees() {
        return EnrolleeDb.getEnrollees(connectionPool);
    }

    @Override
    public void deleteEnrollee(int id) {
        EnrolleeDb.deleteEnrollee(id, connectionPool);
    }

    @Override
    public void editEnrollee(Enrollee enrollee) {
        EnrolleeDb.updateEnrollee(enrollee, connectionPool);
    }

    @Override
    public void addEnrollee(Enrollee enrollee) {
        EnrolleeDb.createEnrollee(enrollee, connectionPool);
    }
}
