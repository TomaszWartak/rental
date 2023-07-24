package org.example.rentalmysql.utils;

import org.springframework.stereotype.Service;

@Service
public class MessageDisplayer {

    public void show( String message ) {
        System.out.println( message );
    }

    public void makeCRLF() {
        System.out.println();
    }

}
