package ru.ruslan.model;

public class Company {

    private static Company instance;
    private int idCompany;
    private String companyName;
    private Double balance;
    private int idBank;

    private Company(){}

    public static Company getInstance(){
        if (instance == null) instance = new Company();
        return instance;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getIdBank() {
        return idBank;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    @Override
    public String toString() {
        return "Company{" +
                "idCompany=" + idCompany +
                ", companyName='" + companyName + '\'' +
                ", Balance=" + balance +
                ", idBank=" + idBank +
                '}';
    }
}
