package ru.ruslan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.model.Atm;
import ru.ruslan.model.Bank;
import ru.ruslan.model.Card;
import ru.ruslan.model.Client;
import ru.ruslan.service.PutMoneyService;
import ru.ruslan.utility.TakeCommissionUtil;
import ru.ruslan.utility.UpdateForServiceUtil;

import java.util.Scanner;

public class PutMoneyServiceImpl implements PutMoneyService {

    private static final Logger logger = LoggerFactory.getLogger(PutMoneyServiceImpl.class);
    private Card card = Card.getInstance();
    private Atm atm = Atm.getInstance();
    private Client client = Client.getInstance();
    private Bank bankATM = null;

    @Override
    public  void put (){
        Scanner inputValueOfMoney = new Scanner(System.in);

        boolean isBreakPutMoney = false;
        int valueOfMoney = 0;
        int sumOfMoney = 0;
        String msg = "зачислена";

        while (!isBreakPutMoney) {
            logger.info("Внесите купюры! Для окончания ввидите 0");
            valueOfMoney = inputValueOfMoney.nextInt();
            sumOfMoney += valueOfMoney;
            if (valueOfMoney == 100) atm.setCountOf100(atm.getCountOf100() + 1);
            if (valueOfMoney == 500) atm.setCountOf500(atm.getCountOf500() + 1);
            if (valueOfMoney == 1000) atm.setCountOf1000(atm.getCountOf1000() + 1);
            if (valueOfMoney == 2000) atm.setCountOf2000(atm.getCountOf2000() + 1);
            if (valueOfMoney == 5000) atm.setCountOf5000(atm.getCountOf5000() + 1);
            if (valueOfMoney == 0) isBreakPutMoney = true;
        }

        card.setCardBalance(card.getCardBalance() + sumOfMoney);

        if (atm.getIdBank() != client.getIdBank()) {
            bankATM = TakeCommissionUtil.takeCommission();
        }

        UpdateForServiceUtil.updateDB(bankATM, sumOfMoney, msg);
    }
}
