package org.example.rentalmysql.dao;

import org.example.rentalmysql.entities.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDAO  extends CrudRepository< Device, Long > {
}
