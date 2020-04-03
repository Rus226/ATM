package ru.ruslan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.CardDAO;
import ru.ruslan.dao.impl.CardDAOImpl;
import ru.ruslan.model.Card;
import ru.ruslan.service.ChangePinCodeService;
import ru.ruslan.utility.CheckPinUtil;
import ru.ruslan.utility.CodingPinUtil;
import ru.ruslan.utility.TransactionConnectionUtil;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePinCodeServiceImpl implements ChangePinCodeService {

    private static final Logger logger = LoggerFactory.getLogger(ChangePinCodeServiceImpl.class);
    private Card card = Card.getInstance();
    private CardDAO cardDAO = new CardDAOImpl();

    @Override
    public void changePin(){
        Scanner inputFromConsole = new Scanner(System.in);
        String pinAfterEncoding;

        logger.info("Введите старый пин код");
        CheckPinUtil.checkPin();

        logger.info("Введите новый пин код (4 цифры)");
        String pinCode = inputFromConsole.nextLine();
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher matcher = pattern.matcher(pinCode);
        if (!matcher.matches()) {
            logger.info("Не корректный пин!");
            return;
        }

        pinAfterEncoding = CodingPinUtil.encode(pinCode);
        card.setPinCode(pinAfterEncoding);

        try {
            cardDAO.updateCard();
        } catch (SQLException e) {
            logger.error("Error with Server", e);
        }
        TransactionConnectionUtil.closeConnection();
        logger.info("Пин код изменен!");
    }
}
