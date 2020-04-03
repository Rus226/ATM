package ru.ruslan.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TransactionConnectionUtil {

    private TransactionConnectionUtil() {}

    private static final Logger logger = LoggerFactory.getLogger(TransactionConnectionUtil.class);

    public static void closeConnection(){
        try {
            ConnectionToDBUtil.getConnection().close();
            ConnectionToDBUtil.getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Error with close Connection", e);
        }
    }

    public static void commitConnection(){
        try {
            ConnectionToDBUtil.getConnection().commit();
        } catch (SQLException e) {
            logger.error("Error with commit Connection", e);
        }
    }
    public static void rollbackConnection(){
        try {
            ConnectionToDBUtil.getConnection().rollback();
        } catch (SQLException e) {
            logger.error("Error with Rollback", e);
        }
    }

    public static void turnOfAutoCommit(){
        try {
            ConnectionToDBUtil.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Error with AutoCommit", e);
        }
    }
}
