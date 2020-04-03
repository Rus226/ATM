package ru.ruslan.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.CardDAO;
import ru.ruslan.model.Card;
import ru.ruslan.utility.ConnectionToDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CardDAOImpl implements CardDAO {

    private static final Logger logger = LoggerFactory.getLogger(CardDAOImpl.class);
    private Card card = Card.getInstance();

    @Override
    public void initializeCard(String cardNumber) {
        try (Connection connection = ConnectionToDBUtil.getConnection();
             Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM card WHERE CardNumber = " + cardNumber);
        ) {
            while (resultSet.next()) {
                card.setIdCard(resultSet.getInt("IdCard"));
                card.setCardNumber(resultSet.getString("CardNumber"));
                card.setCardBalance(resultSet.getDouble("CardBalance"));
                card.setAvailable(resultSet.getBoolean("Available"));
                card.setPinCode(resultSet.getString("Pincode"));
                card.setIdClient(resultSet.getInt("ClientId"));
            }
        } catch (SQLException e) {
            logger.error("Error in Server", e);
        }
    }

    @Override
    public void updateCard() throws SQLException {

        Connection connection = ConnectionToDBUtil.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Card SET " +
                        "CardBalance = ?, " +
                        "Available = ?, " +
                        "Pincode = ? " +
                        "WHERE idCard = ?")
        ) {
            preparedStatement.setDouble(1, card.getCardBalance());
            preparedStatement.setBoolean(2, card.isAvailable());
            preparedStatement.setString(3, card.getPinCode());
            preparedStatement.setInt(4, card.getIdCard());

            preparedStatement.execute();
        }
    }
}
