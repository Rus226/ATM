package ru.ruslan.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.CompanyDAO;
import ru.ruslan.model.Company;
import ru.ruslan.utility.ConnectionToDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDAOImpl implements CompanyDAO {

    private static final Logger logger = LoggerFactory.getLogger(CompanyDAOImpl.class);
    private Company company = Company.getInstance();

    @Override
    public void initializeCompany(String companyName1) {
        try (Connection connection = ConnectionToDBUtil.getConnection();
             Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM company WHERE CompanyName = '" + companyName1 + "'");
        ) {
            while (resultSet.next()) {
                company.setIdCompany(resultSet.getInt("IdCompany"));
                company.setCompanyName(resultSet.getString("CompanyName"));
                company.setBalance(resultSet.getDouble("Balance"));
                company.setIdBank(resultSet.getInt("BankNameId"));
            }
        } catch (SQLException e) {
            logger.error("Error in Server", e);
        }
    }

    @Override
    public void updateCompany() throws SQLException {

        Connection connection = ConnectionToDBUtil.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Company SET Balance = ? WHERE idCompany = ?")
        ) {
            preparedStatement.setDouble(1, company.getBalance());
            preparedStatement.setInt(2, company.getIdCompany());

            preparedStatement.execute();
        }
    }
}
