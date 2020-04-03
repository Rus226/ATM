package ru.ruslan.model;

public class Bank {

    private int idBank;
    private String bankName;
    private String accountNumber;
    private Double balanceBank;

    public int getIdBank() {
        return idBank;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalanceBank() {
        return balanceBank;
    }

    public void setBalanceBank(Double balanceBank) {
        this.balanceBank = balanceBank;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "idBank=" + idBank +
                ", bankName='" + bankName + '\'' +
                ", AccountNumber='" + accountNumber + '\'' +
                ", BalanceBank=" + balanceBank +
                '}';
    }
}
