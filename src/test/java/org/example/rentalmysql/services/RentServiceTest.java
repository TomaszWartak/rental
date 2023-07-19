package org.example.rentalmysql.services;

import org.assertj.core.api.Assertions;
import org.example.rentalmysql.dao.CustomerDAO;
import org.example.rentalmysql.dao.RentDAO;
import org.example.rentalmysql.entities.*;
import org.example.rentalmysql.dao.CategoryDAO;
import org.example.rentalmysql.dao.DeviceDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class RentServiceTest {

    private Category category;
    private Device device;
    private Customer customer;

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private RentDAO rentDAO;

    @Autowired
    private RentService rentService;

    @BeforeEach
    void prepareData() {

        // przygotowanie kategorii, urządzenia i użytkownika
        prepareCategoryDeviceAndCustomer();
        // zarejestrowanie ich
        saveCategoryDeviceAndCustomer();
    }

    @AfterEach
    void clearData() {
        rentDAO.deleteAll();
        deviceDAO.deleteAll();
        customerDAO.deleteAll();
        rentDAO.deleteAll();
    }

    @Test
    void rentDevice__should_register_the_rental() {
        /* GIVEN */

        /* WHEN */
        // zarejestrowanie "wypożyczenia"
        Rent rent = rentService.rentDevice( device, customer );

        /* THEN */
        // sprawdzenie, czy "wypożyczenie" jest zarejestrowane w bd
//        Device deviceFromDB = deviceDAO.findById( device.getId() ).orElseThrow();
//        Assertions.assertThat( deviceFromDB.getId() ).isEqualTo( device.getId() );
//        Assertions.assertThat( deviceFromDB.getDescription() ).isEqualTo( device.getDescription() );
//        Assertions.assertThat( deviceFromDB.getAvailability() ).isEqualTo( device.getAvailability() );
//        Assertions.assertThat( deviceFromDB.getPrice() ).isEqualTo( device.getPrice() );
//        Assertions.assertThat( deviceFromDB.getCategory().getId() ).isEqualTo( device.getCategory().getId() );
//        Assertions.assertThat( deviceFromDB.getCustomers().get(0).getId() ).isEqualTo( device.getCustomers().get(0).getId() );
        Rent rentFromDB = rentDAO.findById( rent.getId() ).orElseThrow();
        Assertions.assertThat( rentFromDB.getDeviceId() ).isEqualTo( device.getId() );
        Assertions.assertThat( rentFromDB.getCustomerId() ).isEqualTo( customer.getId() );
        Assertions.assertThat( rentFromDB.isFinished() ).isFalse();
    }

    @Test
    void rentDevice__should_decrease_availability_after_the_rental() {
        /* GIVEN */

        /* WHEN */
        // zarejestrowanie "wypożyczenia"
        int deviceAvailabilityBeforeRent = device.getAvailability();
        Rent rent = rentService.rentDevice( device, customer );

        /* THEN */
        Device rentedDevice = deviceDAO.findById( rent.getDeviceId() ).orElseThrow();
        Assertions.assertThat( rentedDevice.getAvailability() ).isEqualTo( deviceAvailabilityBeforeRent-1 );
    }

    @Test
    void receptRentalDevice__should_register_the_reception_of_device() {
        /* GIVEN */
        customer = customerDAO.findByPersonalID( "12345678901" ).orElseThrow();
        device = deviceDAO.findByName( "wieratrka Makita CX100" ).orElseThrow();

        Rent rented = rentService.rentDevice( device, customer );

        /* WHEN */
        Rent recepted = rentService.receptRentalDevice( device );

        /* THEN */
        Assertions.assertThat( recepted.getDeviceId() ).isEqualTo( device.getId() );
        Assertions.assertThat( recepted.getCustomerId() ).isEqualTo( customer.getId() );
        Assertions.assertThat( recepted.isFinished() ).isTrue();
    }

    @Test
    void receptRentalDevice__should_increase_availability_after_the_reception() {
        /* GIVEN */
        customer = customerDAO.findByPersonalID( "12345678901" ).orElseThrow();
        device = deviceDAO.findByName( "wieratrka Makita CX100" ).orElseThrow();

        Rent rented = rentService.rentDevice( device, customer );
        Device rentedDevice = deviceDAO.findById( device.getId() ).orElseThrow();

        /* WHEN */
        // zarejestrowanie "wypożyczenia"
        Rent recepted = rentService.receptRentalDevice( device );

        /* THEN */
        Device receptedDevice = deviceDAO.findById( recepted.getDeviceId() ).orElseThrow();
        Assertions.assertThat( receptedDevice.getAvailability() ).isEqualTo( rentedDevice.getAvailability()+1 );
    }
    private void prepareCategoryDeviceAndCustomer() {
        category = new CategoryBuilder()
                .withName("wiertarki")
                .withDescription("wiertarki - opis")
                .build();
        device = new DeviceBuilder()
                .withName("wieratrka Makita CX100")
                .withDescription("wieratrka Makita CX100- opis")
                .withAvailability( 10 )
                .withPrice( 100.0 )
                .withCategory( category )
                .build();
        customer = new CustomerBuilder()
                .withFirstName("Jan")
                .withLastName("Dzban")
                .withPersonalID("12345678901")
                .withIdCardNumber("DEF123456")
                .build();
    }

    private void saveCategoryDeviceAndCustomer() {
        categoryDAO.save( category );
        device = deviceDAO.save( device );
        customer = customerDAO.save( customer );
    }
}