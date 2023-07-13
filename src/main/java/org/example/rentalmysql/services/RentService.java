package org.example.rentalmysql.services;

import jakarta.transaction.Transactional;
import org.example.rentalmysql.entities.Customer;
import org.example.rentalmysql.entities.Device;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    @Transactional
    public void rentDevice(Device device, Customer customer) {
        // powiązanie urządzenia z użytkownikiem = "wypożyczenie"
        device.addCustomer( customer );
    }

}
