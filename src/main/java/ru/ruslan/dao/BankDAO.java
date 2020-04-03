package ru.ruslan.dao;

import ru.ruslan.model.Bank;

import java.sql.SQLException;

public interface BankDAO {
    Bank initializeBank(int idBank);
    void updateBank(Bank bank) throws SQLException;
}
