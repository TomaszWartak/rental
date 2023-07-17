package org.example.rentalmysql.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String firstName;

    private String lastName;

    private String personalID;

    private String idCardNumber;

//   TODO @ManyToMany(mappedBy = "customers")
//    private List<Device> rentDevices = new ArrayList<>();

    @OneToMany/* todo (mappedBy = "customerId")*/
    @JoinColumn(name = "customerId")
    private List<Rent> rents = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

/* TODO   public List<Device> getRentDevices() {
        return rentDevices;
    }

    public void setRentDevices(List<Device> rentDevices) {
        this.rentDevices = rentDevices;
    }
*/

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
