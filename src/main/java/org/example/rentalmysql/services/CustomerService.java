package org.example.rentalmysql.services;

import org.example.rentalmysql.dao.CustomerDAO;
import org.example.rentalmysql.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void addCustomer(Customer customer) {
        // TODO walidacja?
        customerDAO.save( customer );
    }

    public void updateCustomer(Customer customer) {
        customerDAO.save( customer );
    }

    public void deleteCustomer( Customer customer ) {
        customerDAO.delete( customer );
    }

    public void deleteCustomerWithId( Long customerId ) {
        customerDAO.deleteById( customerId );
    }

    public Optional<Customer> getCustomerWithId(Long customerId ) {
        return customerDAO.findById( customerId );
    }

    public List<Customer> getAllCustomers() {
        Iterable<Customer> customers = customerDAO.findAll();
        return StreamSupport.stream(customers.spliterator(), false)
                .toList();
    }

}
