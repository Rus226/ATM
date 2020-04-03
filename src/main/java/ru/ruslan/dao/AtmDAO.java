package ru.ruslan.dao;

import java.sql.SQLException;

public interface AtmDAO {
    void initializeAtm();
    void updateAtm() throws SQLException;
}
