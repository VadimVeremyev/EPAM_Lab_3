package by.bsuir.committee.Db;

import by.bsuir.committee.entity.Enrollee;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class EnrolleeDb {
    private static Logger logger = Logger.getLogger(String.valueOf(EnrolleeDb.class));
    private static String selectEnrollees = "select c.id, c.first_name, c.middle_name, c.last_name, c.faculty_name from `erollee` where c.id = ?";
    private static final String insertEnrollee = "insert into `enrollee` (`first_name`, `middle_name`, `last_name`, `faculty_name`) values (?, ?, ?, ?)";
    private static final String updateEnrollee  = "update `enrollee` set first_name = ?, middle_name = ?, last_name = ?, faculty_name = ? where id = ?";
    private static final String deleteEnrollee = "delete from enrolle where id = ?";

    public static ArrayList<Enrollee> getEnrollees(ConnectionPool connectionPool) {
        ArrayList<Enrollee> enrollees = new ArrayList<>();
        try {
            logger.info("DB selecting routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(selectEnrollees.replace("?", "c.id"));

                while (resultSet.next()) {
                    Enrollee enrollee = new Enrollee();

                    enrollee.setId(resultSet.getInt("id"));
                    enrollee.setFirstName(resultSet.getString("first_name"));
                    enrollee.setMiddleName(resultSet.getString("middle_name"));
                    enrollee.setLastName(resultSet.getString("last_name"));
                    enrollee.setFacultyName(resultSet.getString("faculty_name"));

                    enrollees.add(enrollee);
                }

                resultSet.close();
                logger.info("Select: Successful");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return enrollees;
    }

    public static Enrollee getEnrollee(int id, ConnectionPool connectionPool) {
    	Enrollee enrollee = new Enrollee();

        try {
            logger.info("DB selecting one routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(selectEnrollees)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        enrollee.setId(resultSet.getInt("id"));
                        enrollee.setFirstName(resultSet.getString("first_name"));
                        enrollee.setMiddleName(resultSet.getString("middle_name"));
                        enrollee.setLastName(resultSet.getString("last_name"));
                        enrollee.setFacultyName(resultSet.getString("faculty_name"));
                    }

                    resultSet.close();
                    logger.info("Select one: Successful");
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return enrollee;
    }

    public static int createEnrollee(Enrollee enrollee, ConnectionPool connectionPool) {
        try {
            logger.info("DB inserting routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(insertEnrollee)) {
                    preparedStatement.setString(1, enrollee.getFirstName());
                    preparedStatement.setString(2, enrollee.getMiddleName());
                    preparedStatement.setString(3, enrollee.getLastName());
                    preparedStatement.setString(4, enrollee.getFacultyName());

                    logger.info("Insert: Successful");

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return 0;
    }

    public static int updateEnrollee(Enrollee enrollee, ConnectionPool connectionPool) {
        try {
            logger.info("DB updating routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(updateEnrollee)) {
                    preparedStatement.setString(1, enrollee.getFirstName());
                    preparedStatement.setString(2, enrollee.getMiddleName());
                    preparedStatement.setString(3, enrollee.getLastName());
                    preparedStatement.setString(4, enrollee.getFacultyName());

                    preparedStatement.setInt(5, enrollee.getId());
                    logger.info("Update: Successful");

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return 0;
    }
    
    public static int deleteEnrollee(int id, ConnectionPool connectionPool) {
        try {
            logger.info("DB deleting routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(deleteEnrollee)) {
                    preparedStatement.setInt(1, id);
                    logger.info("Delete: Successful");

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return 0;
    }

}
