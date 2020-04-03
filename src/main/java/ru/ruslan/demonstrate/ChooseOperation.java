package ru.ruslan.demonstrate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.ClientDAO;
import ru.ruslan.dao.impl.ClientDAOImpl;
import ru.ruslan.service.ChangePinCodeService;
import ru.ruslan.service.LookAtBalanceCardService;
import ru.ruslan.service.PayCompanyService;
import ru.ruslan.service.PutMoneyService;
import ru.ruslan.service.TakeMoneyService;
import ru.ruslan.service.impl.ChangePinCodeServiceImpl;
import ru.ruslan.service.impl.LookAtBalanceCardServiceImpl;
import ru.ruslan.service.impl.PayCompanyServiceImpl;
import ru.ruslan.service.impl.PutMoneyServiceImpl;
import ru.ruslan.service.impl.TakeMoneyServiceImpl;

import java.util.Scanner;

public class ChooseOperation {

    private ChooseOperation() {}

    private static final Logger logger = LoggerFactory.getLogger(ChooseOperation.class);

    public static void choose(){
        ChangePinCodeService changePinCodeService = new ChangePinCodeServiceImpl();
        PayCompanyService payCompanyService = new PayCompanyServiceImpl();
        PutMoneyService putMoneyService = new PutMoneyServiceImpl();
        TakeMoneyService takeMoneyService = new TakeMoneyServiceImpl();
        LookAtBalanceCardService lookAtBalanceCardService = new LookAtBalanceCardServiceImpl();

        ClientDAO clientDAO = new ClientDAOImpl();
        clientDAO.initializeClient();

        boolean isCorrectOperation = false;

        Scanner numberOperation = new Scanner(System.in);

        while (!isCorrectOperation) {

            logger.info("Выберете, что вы хотите сделать?");
            logger.info("Отправьте номер операции соответсвующий вашему запросу");
            logger.info("1 - \"Положить наличные\"");
            logger.info("2 - \"Снять наличные\"");
            logger.info("3 - \"Посмотреть баланс карты\"");
            logger.info("4 - \"Оплатить счет компании\"");
            logger.info("5 - \"Изменить пин-код\"");

            switch (numberOperation.nextLine()) {
                case ("1"):
                    putMoneyService.put();
                    isCorrectOperation = true;
                    break;
                case ("2"):
                    takeMoneyService.take();
                    isCorrectOperation = true;
                    break;
                case ("3"):
                    lookAtBalanceCardService.showBalanceCard();
                    isCorrectOperation = true;
                    break;
                case ("4"):
                    payCompanyService.pay();
                    isCorrectOperation = true;
                    break;
                case ("5"):
                    changePinCodeService.changePin();
                    isCorrectOperation = true;
                    break;
                default:
                    logger.info("Данной операции не существует, попробуйте еще раз");
            }
        }
    }
}
