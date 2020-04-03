package ru.ruslan.dao;

import java.sql.SQLException;

public interface CompanyDAO {
    void initializeCompany(String companyName);
    void updateCompany() throws SQLException;
}
