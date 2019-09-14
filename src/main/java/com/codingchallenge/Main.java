package com.codingchallenge;

import java.io.File;
import java.io.FileNotFoundException;
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

        Result result = TransactionUtil.getResult(fileName, accountID.trim(), fromDateTime.trim(),
                endDateTime.trim());

        System.out.println(result);



    }
}
