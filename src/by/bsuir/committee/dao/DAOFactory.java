package by.bsuir.committee.dao;

public class DaoFactory {
    private static EnrolleeDao enrolleeDao = new EnrolleeDao();
    private static UserDao userDao = new UserDao();

    public static EnrolleeDao getEnrolleeDao() {
        return enrolleeDao;
    }

   public static UserDao getUserDao() {
        return userDao;
    }

}
