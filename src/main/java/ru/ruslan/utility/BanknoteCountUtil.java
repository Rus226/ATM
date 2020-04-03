package ru.ruslan.utility;

import ru.ruslan.model.Atm;

import java.util.Map;
import java.util.TreeMap;

public class BanknoteCountUtil {
    private BanknoteCountUtil(){}

    private static Atm atm = Atm.getInstance();
    private static final int  MAX = 200000;
    private static final int MIN = 100;

    private static final int NOMINAL100 = 100;
    private static final int NOMINAL500 = 500;
    private static final int NOMINAL1000 = 1000;
    private static final int NOMINAL2000 = 2000;
    private static final int NOMINAL5000 = 5000;
    private static int valueOfMoneyCalculate;

    private static int workWithNominal(int nominal, int currentCountBanknote){
        int countBanknote = valueOfMoneyCalculate/nominal;
        if (countBanknote > currentCountBanknote) countBanknote = currentCountBanknote;
        valueOfMoneyCalculate -= (nominal*countBanknote);

        return countBanknote;
    }

    public static Map<Integer, Integer> getCountOfBanknote(int valueOfMoney){

        Map<Integer, Integer> arrayOfBanknotes = new TreeMap<>();
        valueOfMoneyCalculate = valueOfMoney;

        int currentCount100 = atm.getCountOf100();
        int currentCount500 = atm.getCountOf500();
        int currentCount1000 = atm.getCountOf1000();
        int currentCount2000 = atm.getCountOf2000();
        int currentCount5000 = atm.getCountOf5000();

        arrayOfBanknotes.put(5000, workWithNominal(NOMINAL5000, currentCount5000));
        arrayOfBanknotes.put(2000, workWithNominal(NOMINAL2000, currentCount2000));
        arrayOfBanknotes.put(1000, workWithNominal(NOMINAL1000, currentCount1000));
        arrayOfBanknotes.put(500, workWithNominal(NOMINAL500, currentCount500));
        arrayOfBanknotes.put(100, workWithNominal(NOMINAL100, currentCount100));

        if (valueOfMoneyCalculate != 0) {
            return null;
        }

        return arrayOfBanknotes;
    }

    public static boolean checkValidValueOfMoney(int valueOfMoney){
        return valueOfMoney <= MAX && valueOfMoney % 100 == 0 && valueOfMoney >= MIN;
    }
}
