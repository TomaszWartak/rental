package org.example.rentalmysql.utils;

import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class InputReader {

    public final static String NOT_VALID_NUMERICAL_VALUE_MESSAGE = "To nie jest prawidłowa wartość liczbowa";
    MessageDisplayer messageDisplayer;

    Scanner scanner;

    public InputReader(MessageDisplayer messageDisplayer, Scanner scanner) {
        this.messageDisplayer = messageDisplayer;
        this.scanner = scanner;
    }

    public String readText() {
        return scanner.nextLine();
    }

    public long readLong(String noProperValueMessage ) {
        long result = -1;
        while (true) {
            try {
                result = scanner.nextLong();
            } catch (InputMismatchException ex) {
                messageDisplayer.show( noProperValueMessage );
                result = -1;
            }
            scanner.nextLine();
            if (result>-1) {
                break;
            }
        }
        return result;
    }

    public int readInt(String noProperValueMessage ) {
        int result = -1;
        while (true) {
            try {
                result = scanner.nextInt();
            } catch (InputMismatchException ex) {
                messageDisplayer.show( noProperValueMessage );
                result = -1;
            }
            scanner.nextLine();
            if (result>-1) {
                break;
            }
        }
        return result;
    }

    public void close() {
        scanner.close();
    }
}
