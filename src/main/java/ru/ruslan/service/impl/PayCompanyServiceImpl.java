package ru.ruslan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.CompanyDAO;
import ru.ruslan.dao.impl.CompanyDAOImpl;
import ru.ruslan.model.Atm;
import ru.ruslan.model.Bank;
import ru.ruslan.model.Card;
import ru.ruslan.model.Client;
import ru.ruslan.model.Company;
import ru.ruslan.service.PayCompanyService;
import ru.ruslan.utility.TakeCommissionUtil;
import ru.ruslan.utility.UpdateForServiceUtil;

import java.util.Scanner;

public class PayCompanyServiceImpl implements PayCompanyService {
    
    private static final Logger logger = LoggerFactory.getLogger(PayCompanyServiceImpl.class);
    private CompanyDAO companyDAO = new CompanyDAOImpl();
    private Company company = Company.getInstance();
    private Card card = Card.getInstance();
    private Atm atm = Atm.getInstance();
    private Client client = Client.getInstance();
    private Bank bankATM = null;

    @Override
    public void pay(){
        Scanner inputFromConsole = new Scanner(System.in);
        boolean isCorrectOperation = false;

        while (!isCorrectOperation) {

            logger.info("Услугу какой компании вы хотите оплатить?");
            logger.info("Отправьте номер операции соответсвующий вашему запросу");
            logger.info("1 - \"Megafon\"");
            logger.info("2 - \"MTS\"");
            logger.info("3 - \"Rostelekom\"");
            switch (inputFromConsole.nextLine()) {
                case ("1"):
                    pay("Megafon");
                    isCorrectOperation = true;
                    break;
                case ("2"):
                    pay("MTS");
                    isCorrectOperation = true;
                    break;
                case ("3"):
                    pay("Rostelekom");
                    isCorrectOperation = true;
                    break;
                default:
                    logger.info("Данной операции не существует, попробуйте еще раз");
            }
        }
    }

    private void pay(String companyName){
        companyDAO.initializeCompany(companyName);
        Scanner inputFromConsole2 = new Scanner(System.in);
        double valueOfMoney;
        String msg = "зачислена на счет " + companyName;

        logger.info("Какую сумму вы хотите перевести?");
        valueOfMoney = Double.parseDouble(inputFromConsole2.nextLine());

        if (card.getCardBalance() < valueOfMoney) {
            logger.info("Не достаточно средств на карте");
            return;
        }

        if (atm.getIdBank() != client.getIdBank()) {
            if (card.getCardBalance() < valueOfMoney + 50) {
                logger.info("Не достаточно средств на карте, учтите комиссию банка (50 рублей)");
                return;
            }
            bankATM = TakeCommissionUtil.takeCommission();
        }

        card.setCardBalance(card.getCardBalance() - valueOfMoney);
        company.setBalance(company.getBalance() + valueOfMoney);

        UpdateForServiceUtil.updateDB(bankATM, valueOfMoney, msg);
    }
}
