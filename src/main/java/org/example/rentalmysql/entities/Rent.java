package org.example.rentalmysql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Rent {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private Long deviceId;
    private Long customerId;
    private LocalDateTime rentalDate;
    private LocalDateTime scheduledReturnDate;
    private LocalDateTime  returnDate;

    public Rent() {
    }
}
