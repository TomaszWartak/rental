package org.example.rentalmysql.services;

import jakarta.transaction.Transactional;
import org.example.rentalmysql.dao.DeviceDAO;
import org.example.rentalmysql.dao.RentDAO;
import org.example.rentalmysql.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    @Autowired
    private DeviceDAO deviceDAO;

    @Autowired
    private RentDAO rentDAO;

    @Transactional
    public Rent rentDevice(Device device, Customer customer) {
        // powiązanie urządzenia z użytkownikiem = "wypożyczenie"
        device.setAvailability( device.getAvailability()-1 );
        deviceDAO.save( device );
        Rent rent = new RentBuilder()
                .withDevice( device )
                .withCustomer( customer )
                .withFinished( false )
                .build();
        return rentDAO.save( rent );
    }

    @Transactional
    public Rent receptRentalDevice( Device device ) {
        device.setAvailability( device.getAvailability()+1 );
        deviceDAO.save( device );
        List<Rent> rents = rentDAO.findAllByFinishedIsFalseAndDeviceId( device.getId() );
        // TODO Lipa jeśli jest więcej niż jedno...
        Rent rent = rents.get(0);
        rent.setFinished( true );
        rentDAO.save( rent );
        return rent;
    }

    public List<Device> getAvailableDevicesInCategory(Category category) {
        return null;
    }
}
