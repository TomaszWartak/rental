package org.example.rentalmysql.entities;

import java.time.LocalDateTime;

public class RentBuilder {
    private Long id;
    private Long deviceId;
    private Long customerId;
    private LocalDateTime rentalDate;
    private LocalDateTime scheduledReturnDate;
    private LocalDateTime returnDate;
    private Boolean finished;
    private String comments;

    public RentBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RentBuilder withDevice( Device device) {
        this.deviceId = device.getId();
        return this;
    }

    public RentBuilder withDeviceId(Long deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public RentBuilder withCustomer(Customer customer) {
        this.customerId = customer.getId();
        return this;
    }

    public RentBuilder withCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public RentBuilder withRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
        return this;
    }

    public RentBuilder withScheduledReturnDate(LocalDateTime scheduledReturnDate) {
        this.scheduledReturnDate = scheduledReturnDate;
        return this;
    }

    public RentBuilder withReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public RentBuilder withFinished(Boolean finished) {
        this.finished = finished;
        return this;
    }

    public RentBuilder withComments(String comments) {
        this.comments = comments;
        return this;
    }

    // Metoda build() tworząca obiekt Rent na podstawie wartości Buildera
    public Rent build() {
        Rent rent = new Rent();
        rent.setId( id );
        rent.setDeviceId( deviceId );
        rent.setCustomerId( customerId );
        rent.setRentalDate( rentalDate );
        rent.setScheduledReturnDate( scheduledReturnDate );
        rent.setReturnDate( returnDate );
        rent.setFinished( finished );
        rent.setComments( comments );
        return rent;
    }
}
