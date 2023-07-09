package org.example.app;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RentalMySQL {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("rentalmysqlPU");
        emFactory.close();
    }
}