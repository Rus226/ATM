package ru.ruslan.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.AtmDAO;
import ru.ruslan.dao.BankDAO;
import ru.ruslan.dao.CardDAO;
import ru.ruslan.dao.CompanyDAO;
import ru.ruslan.dao.impl.AtmDAOImpl;
import ru.ruslan.dao.impl.BankDAOImpl;
import ru.ruslan.dao.impl.CardDAOImpl;
import ru.ruslan.dao.impl.CompanyDAOImpl;
import ru.ruslan.model.Bank;

import java.sql.SQLException;

public class UpdateForServiceUtil {
    private UpdateForServiceUtil() {}

    private static final Logger logger = LoggerFactory.getLogger(UpdateForServiceUtil.class);
    private static CardDAO cardDAO = new CardDAOImpl();
    private static AtmDAO atmDAO = new AtmDAOImpl();
    private static BankDAO bankDAO = new BankDAOImpl();
    private static CompanyDAO companyDAO = new CompanyDAOImpl();
    private static Bank bankATM;
    private static String msg;
    private static boolean isUpdateForCompany = false;

    public static void updateDB(Bank bankATM, Double sumOfMoney, String msg){
        isUpdateForCompany = true;
        UpdateForServiceUtil.bankATM = bankATM;
        UpdateForServiceUtil.msg = msg;
        update(sumOfMoney.toString());
    }

    public static void updateDB(Bank bankATM, int sumOfMoney, String msg){
        UpdateForServiceUtil.bankATM = bankATM;
        UpdateForServiceUtil.msg = msg;
        update(sumOfMoney+"");
    }

    private static void update(String sumOfMoney){
        TransactionConnectionUtil.turnOfAutoCommit();
        try {
            if (bankATM != null) {
                bankDAO.updateBank(bankATM);
            }
            if (isUpdateForCompany) {
                companyDAO.updateCompany();
            } else {
                atmDAO.updateAtm();
            }

            cardDAO.updateCard();
            TransactionConnectionUtil.commitConnection();
            logger.info("Сумма в размере " + sumOfMoney + " " + msg);
        } catch (SQLException e) {
            logger.error("Error with server", e);
            logger.info("Средства не зачислены");
            TransactionConnectionUtil.rollbackConnection();
        }
        TransactionConnectionUtil.closeConnection();
    }
}
