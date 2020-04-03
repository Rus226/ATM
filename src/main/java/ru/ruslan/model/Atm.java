package ru.ruslan.model;

public class Atm {

    private static Atm instance;
    private int idATM;
    private String numberATM;
    private int countOf100;
    private int countOf500;
    private int countOf1000;
    private int countOf2000;
    private int countOf5000;
    private int idBank;

    private Atm(){}

    public static Atm getInstance(){
        if (instance == null) instance = new Atm();
        return instance;
    }

    public int getIdATM() {
        return idATM;
    }

    public void setIdATM(int idATM) {
        this.idATM = idATM;
    }

    public String getNumberATM() {
        return numberATM;
    }

    public void setNumberATM(String numberATM) {
        this.numberATM = numberATM;
    }

    public int getCountOf100() {
        return countOf100;
    }

    public void setCountOf100(int countOf100) {
        this.countOf100 = countOf100;
    }

    public int getCountOf500() {
        return countOf500;
    }

    public void setCountOf500(int countOf500) {
        this.countOf500 = countOf500;
    }

    public int getCountOf1000() {
        return countOf1000;
    }

    public void setCountOf1000(int countOf1000) {
        this.countOf1000 = countOf1000;
    }

    public int getCountOf2000() {
        return countOf2000;
    }

    public void setCountOf2000(int countOf2000) {
        this.countOf2000 = countOf2000;
    }

    public int getCountOf5000() {
        return countOf5000;
    }

    public void setCountOf5000(int countOf5000) {
        this.countOf5000 = countOf5000;
    }

    public int getIdBank() {
        return idBank;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "idATM=" + idATM +
                ", numberATM='" + numberATM + '\'' +
                ", countOf100=" + countOf100 +
                ", countOf500=" + countOf500 +
                ", countOf1000=" + countOf1000 +
                ", countOf2000=" + countOf2000 +
                ", countOf5000=" + countOf5000 +
                ", idBank=" + idBank +
                '}';
    }
}
