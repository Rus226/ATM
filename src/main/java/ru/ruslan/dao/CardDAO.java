package ru.ruslan.dao;

import java.sql.SQLException;

public interface CardDAO {
     void initializeCard(String cardNumber);
     void updateCard() throws SQLException;
}
