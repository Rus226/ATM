package ru.ruslan.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.AtmDAO;
import ru.ruslan.model.Atm;
import ru.ruslan.utility.ConnectionToDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AtmDAOImpl implements AtmDAO {

    private static final Logger logger = LoggerFactory.getLogger(AtmDAOImpl.class);
    private Atm atm = Atm.getInstance();

    @Override
    public void initializeAtm() {

        String numberOfThisATM = "542367782215";

        try {
            Connection connection = ConnectionToDBUtil.getConnection();

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM atm WHERE ATMnumber = " + numberOfThisATM)
            ) {
                while (resultSet.next()) {
                    atm.setIdATM(resultSet.getInt("IdATM"));
                    atm.setNumberATM(resultSet.getString("ATMnumber"));
                    atm.setCountOf100(resultSet.getInt("Count100"));
                    atm.setCountOf500(resultSet.getInt("Count500"));
                    atm.setCountOf1000(resultSet.getInt("Count1000"));
                    atm.setCountOf2000(resultSet.getInt("Count2000"));
                    atm.setCountOf5000(resultSet.getInt("Count5000"));
                    atm.setIdBank(resultSet.getInt("BankNameId"));
                }
            }
        } catch (SQLException e) {
            logger.error("Error in Server", e);
        }
    }

    @Override
    public void updateAtm() throws SQLException {
        Connection connection = ConnectionToDBUtil.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE atm SET " +
                        "Count100 = ?, " +
                        "Count500 = ?, " +
                        "Count1000 = ?, " +
                        "Count2000 = ?, " +
                        "Count5000 = ? " +
                        "WHERE idATM = ?")
        ) {
            preparedStatement.setInt(1, atm.getCountOf100());
            preparedStatement.setInt(2, atm.getCountOf500());
            preparedStatement.setInt(3, atm.getCountOf1000());
            preparedStatement.setInt(4, atm.getCountOf2000());
            preparedStatement.setInt(5, atm.getCountOf5000());
            preparedStatement.setInt(6, atm.getIdATM());

            preparedStatement.execute();
        }
    }
}
