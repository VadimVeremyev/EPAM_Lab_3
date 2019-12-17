package by.bsuir.committee.Db;

import by.bsuir.committee.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class UserDb {
    private static Logger logger = Logger.getLogger(String.valueOf(UserDb.class));
    private static String selectUserByNameSql = "select * from `user` where `name`=?";
    private static String selectUserByNameAndPasswordSql = "select * from `user` where `name`=? and password=?";
    private static String insertUserSql = "insert into `user` (`name`, password) values (?, ?)";

    public static User getUser(String name, String password, ConnectionPool connectionPool) {
        User user = null;

        try {
            logger.info("DB selecting one routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(selectUserByNameAndPasswordSql)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, password);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        user = new User(name, password);
                    }

                    logger.info("Select one: Successful");
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return user;
    }

    public static User getUserByName(String name, ConnectionPool connectionPool) {
        User user = null;

        try {
            logger.info("DB selecting one routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(selectUserByNameSql)) {
                    preparedStatement.setString(1, name);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        user = new User(name, resultSet.getString(2));
                    }

                    logger.info("Select one by name: Successful");
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return user;
    }

    public static int createUser(User user, ConnectionPool connectionPool) {
        try {
            logger.info("DB inserting   routine started...");

            try (Connection conn = connectionPool.getConnection()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(insertUserSql)) {
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setString(2, user.getPassword());
                    logger.info("Insert: Successful");

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return 0;
    }
}