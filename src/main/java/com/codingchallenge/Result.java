package com.codingchallenge;

/**
 * Result classS
 */
public class Result {

    public Result(Double relativeBalance, int totalTransactions) {
        this.relativeBalance = relativeBalance;
        this.totalTransactions = totalTransactions;
    }

    private Double relativeBalance = 0.0;
    private int totalTransactions = 0 ;

    public Double getRelativeBalance() {
        return relativeBalance;
    }

    public void setRelativeBalance(Double relativeBalance) {
        this.relativeBalance = relativeBalance;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    @Override
    public String toString() {
        return "Relative balance for the period is: " + getRelativeBalanceAsString() +
                "\nNumber of transactions included is:" + totalTransactions ;
    }

    public String getRelativeBalanceAsString() {
        String prefix = "$";
        if(relativeBalance  >= 0) {
            return prefix + relativeBalance;
        }
        return "-" + prefix + Math.abs(relativeBalance);

    }

}
