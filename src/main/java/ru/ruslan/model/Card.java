package ru.ruslan.model;

public class Card {

    private static Card instance;
    private int idCard;
    private String cardNumber;
    private Double cardBalance;
    private int idClient;
    private String pinCode;
    private boolean available;

    private Card(){}

    public static Card getInstance(){
        if (instance == null) {
            instance = new Card();
        }
        return instance;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Card{" +
                "idCard=" + idCard +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardBalance=" + cardBalance +
                ", idClient=" + idClient +
                ", pinCode='" + pinCode + '\'' +
                ", available=" + available +
                '}';
    }
}
