package com.codingchallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Main method
 */
public class Main {

    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("Invalid arguments");
            return;
        }
        System.out.println("Please enter the input file path ");

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        String accountID = args[0];
        String fromDateTime = args[1];

        String endDateTime = args[2];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse(fromDateTime, formatter);
        LocalDateTime toDate = LocalDateTime.parse(endDateTime, formatter);
        if(fromDate.isAfter(toDate)) {
            System.out.println("Invalid from datetime & to datetime");
            return;
        }

        Result result = TransactionUtil.getResult(fileName, accountID.trim(), fromDateTime.trim(),
                endDateTime.trim());

        System.out.println(result);



    }
}
