package ru.ruslan.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.ClientDAO;
import ru.ruslan.model.Card;
import ru.ruslan.model.Client;
import ru.ruslan.utility.ConnectionToDBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDAOImpl implements ClientDAO {

    private static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);
    private Client client = Client.getInstance();

    @Override
    public void initializeClient() {
        try (Connection connection = ConnectionToDBUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM client WHERE idClient = " +
                     Card.getInstance().getIdClient());
        ) {
            while (resultSet.next()) {
                client.setIdClient(resultSet.getInt("IdClient"));
                client.setFirstName(resultSet.getString("FirstName"));
                client.setLastName(resultSet.getString("LastName"));
                client.setIdBank(resultSet.getInt("BankNameId"));
            }
        } catch (SQLException e) {
            logger.error("Error in Server", e);
        }
    }
}
