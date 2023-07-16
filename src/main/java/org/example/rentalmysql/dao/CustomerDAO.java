package org.example.rentalmysql.dao;

import org.example.rentalmysql.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO  extends CrudRepository< Customer, Long > {
}
