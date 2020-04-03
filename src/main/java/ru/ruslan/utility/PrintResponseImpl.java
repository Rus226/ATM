package ru.ruslan.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruslan.model.Client;

public class PrintResponseImpl {

    private PrintResponseImpl(){}

    private static final Logger logger = LoggerFactory.getLogger(PrintResponseImpl.class);

    public static void print(String msg){
        logger.info("Уважаемый " + Client.getInstance().getFirstName() + " " + Client.getInstance().getLastName());
        logger.info(msg);
    }
}
