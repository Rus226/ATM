package ru.ruslan.utility;

import ru.ruslan.dao.CardDAO;
import ru.ruslan.dao.impl.CardDAOImpl;
import ru.ruslan.model.Card;

public class CheckCardValidUtil {
    private CheckCardValidUtil() {}

    private static CardDAO cardDAO = new CardDAOImpl();

    public static boolean checkCard(String cardNumber){
        cardDAO.initializeCard(cardNumber);
        return Card.getInstance().isAvailable();
    }

}
