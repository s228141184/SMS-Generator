package com.example.sms_generator;

public class MessageModel {
    private  String bank;
    private String account;
    private String transactionType;
    private String dateOfTransaction;
    private String description;
    private float Amount;

    public MessageModel(String bank, String account, String transactionType, String dateOfTransaction, String description, float amount) {
        this.bank = bank;
        this.account = account;
        this.transactionType = transactionType;
        this.dateOfTransaction = dateOfTransaction;
        this.description = description;
        Amount = amount;
    }
    public MessageModel(String description) {
        this.description = description;
    }

    public String getBank() {
        return bank;
    }

    public String getAccount() {
        return account;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public String getDescription() {
        return description;
    }

    public float getAmount() {
        return Amount;
    }
}
