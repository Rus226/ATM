package ru.ruslan.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.BankDAO;
import ru.ruslan.model.Bank;
import ru.ruslan.utility.ConnectionToDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDAOImpl implements BankDAO {

    private static final Logger logger = LoggerFactory.getLogger(BankDAOImpl.class);

    @Override
    public Bank initializeBank(int idBank) {
             Bank bank = new Bank();

        try (Connection connection = ConnectionToDBUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM bank WHERE IdBank = " + idBank);
        ) {
            while (resultSet.next()) {
                bank.setIdBank(resultSet.getInt("IdBank"));
                bank.setBankName(resultSet.getString("BankName"));
                bank.setAccountNumber(resultSet.getString("AccountNumber"));
                bank.setBalanceBank(resultSet.getDouble("Balance"));
            }
        } catch (SQLException e) {
            logger.error("Error in Server", e);
        }

        return bank;
    }

    @Override
    public void updateBank(Bank bank) throws SQLException {

        Connection connection = ConnectionToDBUtil.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE bank SET Balance = ? WHERE idBank = ?")
        ) {
            preparedStatement.setDouble(1, bank.getBalanceBank());
            preparedStatement.setInt(2, bank.getIdBank());

            preparedStatement.execute();
        }
    }
}
