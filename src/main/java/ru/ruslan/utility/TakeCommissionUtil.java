package ru.ruslan.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.BankDAO;
import ru.ruslan.dao.impl.BankDAOImpl;
import ru.ruslan.model.Atm;
import ru.ruslan.model.Bank;
import ru.ruslan.model.Card;

public class TakeCommissionUtil {

    private TakeCommissionUtil() {}

    private static final Logger logger = LoggerFactory.getLogger(TakeCommissionUtil.class);
    private static BankDAO bankDAO = new BankDAOImpl();
    private static Atm atm = Atm.getInstance();
    private static Card card = Card.getInstance();

    public static Bank takeCommission() {
        Bank bankATM = bankDAO.initializeBank(atm.getIdBank());
        logger.info("Комиссия составит 50 рублей");
        int valueOfCommission = 50;
        bankATM.setBalanceBank(bankATM.getBalanceBank() + valueOfCommission);
        card.setCardBalance(card.getCardBalance() - valueOfCommission);

        return bankATM;
    }
}
