package com.example.sms_generator;

import androidx.annotation.NonNull;

import java.text.DateFormat;

public class MessageModel {
    private  String bank;
    private String account;
    private String transactionType;
    private String dateOfTransaction;
    private String description;
    private String startDate, endDate;
    private int numberOfBatch;
    private float Amount;

    public MessageModel(String bank, String account, String transactionType, String dateOfTransaction, String description, float amount) {
        this.bank = bank;
        this.account = account;
        this.transactionType = transactionType;
        this.dateOfTransaction = dateOfTransaction;
        this.description = description;
        Amount = amount;

    }
    public MessageModel(String message, String startDate, String endDate, int numberOfBatch) {
        this.description = message;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfBatch = numberOfBatch;
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

    @NonNull
    @Override
    public String toString() {
        return bank + ": " + account + ", " + transactionType +  ", " +  dateOfTransaction + " " + description + ", " + String.format("%.2f", Amount) +  ", Available R8,500.00. Help 0860008600; ACCID 001";
    }
}
