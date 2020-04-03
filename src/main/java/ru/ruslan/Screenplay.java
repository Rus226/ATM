package ru.ruslan;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.dao.AtmDAO;
import ru.ruslan.dao.impl.AtmDAOImpl;
import ru.ruslan.demonstrate.ChooseOperation;
import ru.ruslan.utility.CheckCardValidUtil;
import ru.ruslan.utility.CheckPinUtil;


public class Screenplay {

    private static final Logger logger = LoggerFactory.getLogger(Screenplay.class);

    public void showApp(){
        boolean isNumberCardValid = false;
        boolean isPinValid = false;
        AtmDAO atmDAO = new AtmDAOImpl();

        while (true) {
            atmDAO.initializeAtm();
            while (!isPinValid) {
                while (!isNumberCardValid) {
                    logger.info("Добрый день!");
                    logger.info("Вставьте карту!");
                    isNumberCardValid = CheckCardValidUtil.checkCard("6479427873264717");
                }
                isPinValid = CheckPinUtil.checkPin();
                isNumberCardValid = isPinValid;
            }

            ChooseOperation.choose();
            isNumberCardValid = false;
            isPinValid = false;
            logger.info("Спасибо за визит!");
        }
    }
}
