package project.zti.expensesmanagerbackend.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Payment {
    private Integer PaymentID;
    private String name;
    private Double amount;
    private String currency;
    private LocalDate date;
    private String category;
    ArrayList<String> tags;

    public Payment() {}

    public Payment(String name, Double amount, String currency, LocalDate date, String category, ArrayList<String> tags) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.category = category;
        this.tags = tags;
    }

    public Payment(Integer paymentID, String name, Double amount, String currency, LocalDate date, String category, ArrayList<String> tags) {
        this.PaymentID = paymentID;
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.category = category;
        this.tags = tags;
    }

    public Integer getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(Integer paymentID) {
        PaymentID = paymentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "PaymentID=" + PaymentID +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", tags=" + tags +
                '}';
    }
}
