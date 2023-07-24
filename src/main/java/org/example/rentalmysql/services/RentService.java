package org.example.rentalmysql.services;

import jakarta.transaction.Transactional;
import org.example.rentalmysql.dao.RentDAO;
import org.example.rentalmysql.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    private DeviceService deviceService;
    private RentDAO rentDAO;

    public RentService(DeviceService deviceService, RentDAO rentDAO) {
        this.deviceService = deviceService;
        this.rentDAO = rentDAO;
    }

    @Transactional
    public Rent rentDevice(Device device, Customer customer) {
        // powiązanie urządzenia z użytkownikiem = "wypożyczenie"
        device.setAvailability( device.getAvailability()-1 );
        deviceService.updateDevice( device );
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
        deviceService.updateDevice( device );
        List<Rent> rents = rentDAO.findAllByFinishedIsFalseAndDeviceId( device.getId() );
        // TODO Lipa jeśli jest więcej niż jedno...
        Rent rent = rents.get(0);
        rent.setFinished( true );
        return rentDAO.save( rent );
    }

    public boolean isDeviceAvilable(Device device ) {
        Device foundDevice = deviceService.getDeviceWithId( device.getId() ).orElse( null );
        return
            (foundDevice!=null) &&
            rentDAO.findAllByFinishedIsFalseAndDeviceId( device.getId() ).isEmpty();
    }

    public boolean isDeviceRented(Device device ) {
        return !rentDAO.findAllByFinishedIsFalseAndDeviceId( device.getId() ).isEmpty();
    }
}
