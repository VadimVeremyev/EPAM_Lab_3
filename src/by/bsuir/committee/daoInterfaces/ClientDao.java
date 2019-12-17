package by.bsuir.committee.daoInterfaces;

import by.bsuir.committee.entity.Enrollee;

import java.util.ArrayList;

public interface ClientDao {

    Enrollee getEnrollee(int id);

    ArrayList<Enrollee> getEnrollees();

    void deleteEnrollee(int id);

    void editEnrollee(Enrollee enrollee);

    void addEnrollee(Enrollee enrollee);
}
