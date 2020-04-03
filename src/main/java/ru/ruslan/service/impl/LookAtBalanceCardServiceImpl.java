package ru.ruslan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.model.Card;
import ru.ruslan.service.LookAtBalanceCardService;

public class LookAtBalanceCardServiceImpl implements LookAtBalanceCardService {
    private static final Logger logger = LoggerFactory.getLogger(LookAtBalanceCardServiceImpl.class);

    @Override
    public void showBalanceCard(){
        logger.info("Баланс карты: " + Card.getInstance().getCardBalance());
    }
}
