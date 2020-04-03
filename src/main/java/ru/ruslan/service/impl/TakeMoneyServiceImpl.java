package ru.ruslan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.model.Atm;
import ru.ruslan.model.Bank;
import ru.ruslan.model.Card;
import ru.ruslan.model.Client;
import ru.ruslan.service.TakeMoneyService;
import ru.ruslan.utility.BanknoteCountUtil;
import ru.ruslan.utility.TakeCommissionUtil;
import ru.ruslan.utility.UpdateForServiceUtil;

import java.util.Map;
import java.util.Scanner;

public class TakeMoneyServiceImpl implements TakeMoneyService {

    private static final Logger logger = LoggerFactory.getLogger(TakeMoneyServiceImpl.class);
    private Card card = Card.getInstance();
    private Atm atm = Atm.getInstance();
    private Client client = Client.getInstance();
    private Bank bankATM = new Bank();

    @Override
    public void take(){
        String msg = "выдана";

        Map<Integer, Integer> arrayOfBanknotes;
        Scanner inputFromConsole = new Scanner(System.in);

        logger.info("Введите требуемую сумму кратную 100, но не более 200 000");
        int valueOfMoney = inputFromConsole.nextInt();

        if (!BanknoteCountUtil.checkValidValueOfMoney(valueOfMoney) || card.getCardBalance() < valueOfMoney) {
            logger.info("Не корректная сумма, либо не достаточно средств на карте");
            return;
        }

        if (atm.getIdBank() != client.getIdBank()) {
            if (card.getCardBalance() < valueOfMoney + 50) {
                logger.info("Не достаточно средств на карте, учтите комиссию банка (50 рублей)");
                return;
            }
            bankATM = TakeCommissionUtil.takeCommission();
        }

        arrayOfBanknotes = BanknoteCountUtil.getCountOfBanknote(valueOfMoney);
        if (arrayOfBanknotes == null){
            logger.info("Купюр в банкомате не достаточно");
            return;
        }

        card.setCardBalance(card.getCardBalance()-valueOfMoney);
        atm.setCountOf100(atm.getCountOf100() - arrayOfBanknotes.get(100));
        atm.setCountOf500(atm.getCountOf500() - arrayOfBanknotes.get(500));
        atm.setCountOf1000(atm.getCountOf1000() - arrayOfBanknotes.get(1000));
        atm.setCountOf2000(atm.getCountOf2000() - arrayOfBanknotes.get(2000));
        atm.setCountOf5000(atm.getCountOf5000() - arrayOfBanknotes.get(5000));

        UpdateForServiceUtil.updateDB(bankATM, valueOfMoney, msg);
        logger.info("Клиент получил" + arrayOfBanknotes);
    }
}
