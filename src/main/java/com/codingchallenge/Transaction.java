package com.codingchallenge;

/**
 * Transaction class
 */
public class Transaction {


    private String transactionID = "";
    private String fromAccountID = "";

    public Transaction(String transactionId, String fromAccountID, String toAccountID,
                       String creationDate, Double amount, TransactionType transactionType,
                       String relatedTransactionId) {
        this.transactionID = transactionId;
        this.fromAccountID = fromAccountID;
        this.toAccountID = toAccountID;
        this.creationDate = creationDate;
        this.amount = amount;
        this.transactionType = transactionType;
        this.relatedTransactionId = relatedTransactionId;
    }

    private String toAccountID = "";
    private String creationDate = "";
    private Double amount = 0.0;
    private TransactionType transactionType = null;
    private String relatedTransactionId = "";

    public String getTransactionID() {
        return transactionID;
    }

    public String getFromAccountID() {
        return fromAccountID;
    }

    public String getToAccountID() {
        return toAccountID;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getRelatedTransactionId() {
        return relatedTransactionId;
    }

    public enum TransactionType {
        PAYMENT, REVERSAL
    }
}
