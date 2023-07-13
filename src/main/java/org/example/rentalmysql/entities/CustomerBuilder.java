package org.example.rentalmysql.entities;

public class CustomerBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String personalID;
    private String idCardNumber;

    public CustomerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder withPersonalID(String personalID) {
        this.personalID = personalID;
        return this;
    }

    public CustomerBuilder withIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPersonalID(personalID);
        customer.setIdCardNumber(idCardNumber);
        return customer;
    }
}

