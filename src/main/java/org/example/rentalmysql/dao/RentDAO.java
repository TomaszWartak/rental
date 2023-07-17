package org.example.rentalmysql.dao;

import org.example.rentalmysql.entities.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentDAO  extends CrudRepository<Rent, Long > {
    List<Rent> findAllByFinishedIsFalseAndCustomerIdAndDeviceId(Long deviceId, Long customerId );

    List<Rent> findAllByFinishedIsFalseAndDeviceId(Long deviceId );

    List<Rent> findAllByFinishedIsFalseAndCustomerId(Long customerId );

}

