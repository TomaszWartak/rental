package org.example.rentalmysql.dao;

import org.example.rentalmysql.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDAO  extends CrudRepository< Customer, Long > {
    Optional<Customer> findByPersonalID( String personalID );

}
