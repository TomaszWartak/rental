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
    private Boolean finished;
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDateTime getScheduledReturnDate() {
        return scheduledReturnDate;
    }

    public void setScheduledReturnDate(LocalDateTime scheduledReturnDate) {
        this.scheduledReturnDate = scheduledReturnDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean isFinished() {
        return finished;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", customerId=" + customerId +
                ", finished=" + finished +
                ", comments='" + comments + '\'' +
                '}';
    }
}
