package org.example.rentalmysql.dao;

import org.example.rentalmysql.entities.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceDAO  extends CrudRepository< Device, Long > {
    Optional<Device> findByName( String name );
}
