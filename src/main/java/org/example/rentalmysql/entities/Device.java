package org.example.rentalmysql.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="device")
public class Device {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String name;

    @Column(length = 100)
    private String description;

    private int availability; // ile jest dostępnych

    private double price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

/*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "device_customers",
            joinColumns = { @JoinColumn(name="device_id", referencedColumnName="id" )},
            inverseJoinColumns = { @JoinColumn(name="customer_id", referencedColumnName="id" )}
    )
    private List<Customer> customers = new ArrayList<>();

*/
    @OneToMany/* todo (mappedBy = "deviceId")*/
    @JoinColumn(name = "deviceId")
    private List<Rent> rents = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
/* TODO
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer( Customer customer ) {
        customers.add( customer );
    }*/

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
