package com.codingchallenge;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * TransactionUtil class
 */
public class TransactionUtil {



    public static Result getResult(String fileName, String accountId,
                            String fromDateString, String toDateString){
        List<Transaction> transactionList = getTransactions(fileName);
        Result result = new Result(0.00, 0);
        List<Transaction> accountTransactions = transactionList.stream().
                filter(transaction -> accountId.equals(transaction.getFromAccountID())
                        || accountId.equals(transaction.getToAccountID())).collect(Collectors.toList());
        if(accountTransactions.isEmpty()) {
            return result;
        }
        //stored the related transaction ids from the reversal transactions
        List<String> reversalTransaction  = accountTransactions.stream().
                filter(t -> t.getTransactionType().equals(Transaction.TransactionType.REVERSAL)).
                map(t -> t.getRelatedTransactionId()).collect(Collectors.toList());

        for (Transaction transaction: accountTransactions) {
            //ignore reversal transactions & transactionId's in reversalTransactionList
            if(Transaction.TransactionType.REVERSAL.
                    equals(transaction.getTransactionType()) ||
                    reversalTransaction.contains(transaction.getTransactionID())) {
                continue;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime fromDate = LocalDateTime.parse(fromDateString, formatter
                    );
            LocalDateTime toDate = LocalDateTime.parse(toDateString, formatter);
            LocalDateTime transactionDate = LocalDateTime.parse(transaction.getCreationDate(),formatter);
            if(transactionDate.isAfter(toDate)) {
                 break;
            }else if(transactionDate.isBefore(fromDate)) {
                continue;
            }
            if(accountId.equals(transaction.getFromAccountID())) {
                result.setRelativeBalance(result.getRelativeBalance() - transaction.getAmount());
                result.setTotalTransactions(result.getTotalTransactions() + 1);

            }
            else if(accountId.equals(transaction.getToAccountID())) {
                result.setRelativeBalance(result.getRelativeBalance() + transaction.getAmount());
                result.setTotalTransactions(result.getTotalTransactions() + 1);
            }


        }
        return result;
    }

    /**
     * Returns List of transactions from the file
     * @param filePath
     * @return
     */
    private static List<Transaction> getTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try{
            File inputF = new File(filePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            transactions = br.lines().skip(1).map(l -> constructLineToTransaction(l)).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            System.out.println("Error when reading file");
        } finally {
            return transactions;
        }

    }

    /**
     * Converts each row in the file to Transaction object
     * @param line
     * @return
     */
    private static Transaction constructLineToTransaction(String line) {
        //TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL, TX10002
        String[] lineTokens = line.split(", ");
        if(lineTokens.length < 6 || lineTokens.length > 7) {
            return null;
        }
        Transaction transaction = new Transaction(lineTokens[0], lineTokens[1], lineTokens[2],
                lineTokens[3], new Double(lineTokens[4]), Transaction.TransactionType.valueOf(lineTokens[5]),
                lineTokens.length == 7 ? lineTokens[6] : "");

        return transaction;
    }
}
