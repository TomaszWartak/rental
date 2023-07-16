package org.example.rentalmysql.services;

import jakarta.transaction.Transactional;
import org.example.rentalmysql.dao.DeviceDAO;
import org.example.rentalmysql.entities.Customer;
import org.example.rentalmysql.entities.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    @Autowired
    private DeviceDAO deviceDAO;

    @Transactional
    public Device rentDevice(Device device, Customer customer) {
        // powiązanie urządzenia z użytkownikiem = "wypożyczenie"
        device.addCustomer( customer );
        return deviceDAO.update( device ).orElseThrow();
    }

}
