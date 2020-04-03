package ru.ruslan.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.CardDAO;
import ru.ruslan.dao.impl.CardDAOImpl;
import ru.ruslan.model.Card;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPinUtil {
    private CheckPinUtil() {}

    private static final Logger logger = LoggerFactory.getLogger(CheckPinUtil.class);
    private static CardDAO cardDAO = new CardDAOImpl();
    private static Scanner inputFromConsole = new Scanner(System.in);

    public static boolean checkPin(){
        String pinAfterDecoding = "null";

        for (int j = 0; j < 3; j++) {
            logger.info("Введите пин код (4 цифры)");
            String pinCode = inputFromConsole.nextLine();
            Pattern pattern = Pattern.compile("[0-9]{4}");
            Matcher matcher = pattern.matcher(pinCode);
            if (matcher.matches()) {
                pinAfterDecoding = CodingPinUtil.decode(Card.getInstance().getPinCode());
            }
            if (pinAfterDecoding.equals(pinCode)) {
                return true;
            }
            logger.info("Пин код введен не корректно или не верно, у вас осталось попыток: " + (2-j));
        }

        logger.info("Карта заблокирована!");
        Card.getInstance().setAvailable(false);
        try {
            cardDAO.updateCard();
        } catch (SQLException e) {
            logger.error("Error with Server", e);
        }

        return false;
    }
}
