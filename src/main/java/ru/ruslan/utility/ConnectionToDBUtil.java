package ru.ruslan.utility;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionToDBUtil {
    private ConnectionToDBUtil() {}

    private static final Logger logger = LoggerFactory.getLogger(ConnectionToDBUtil.class);
    private static Connection connection;
    private static String url;
    private static String username;
    private static String password;

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) return connection;
        return getNewConnection();
    }

    private static Connection getNewConnection(){
        try (FileInputStream fileInputStream =new FileInputStream("src/main/resources/application.properties")){
            Properties property = new Properties();
            property.load(fileInputStream);
            url = property.getProperty("jdbc.url");
            username = property.getProperty("jdbc.username");
            password = property.getProperty("jdbc.password");

        } catch(IOException e){
            logger.error("Error of properties", e);
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e){
            logger.error("Error in Connection", e);
        }
        return connection;
    }
}
