package org.example.rentalmysql.services;

import org.assertj.core.api.Assertions;
import org.example.rentalmysql.dao.CustomerDAO;
import org.example.rentalmysql.entities.*;
import org.example.rentalmysql.dao.CategoryDAO;
import org.example.rentalmysql.dao.DeviceDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringJUnitConfig
//@ExtendWith(SpringExtension.class)
@SpringBootTest/*(classes = RentalMySQL.class)*/
/*@ComponentScan("main.java.org.example.services")*/
/*@ContextConfiguration( classes= {
        Category.class,
        CategoryDAO.class,
        RentService.class,
})*/
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
    private RentService rentService;

//    public RentServiceTest( CategoryDAO categoryDAO, CustomerDAO customerDAO, DeviceDAO deviceDAO, RentService rentService ) {
//        this.categoryDAO = categoryDAO;
//        this.customerDAO = customerDAO;
//        this.deviceDAO = deviceDAO;
//        this.rentService = rentService;
//    }

    @Test
    void rentDevice__should_register_the_rental() {
        /* GIVEN */

        // przygotowanie kategorii, urządzenia i użytkownika
        prepareCategoryDeviceAndCustomer();
        // zarejestrowanie ich
        saveCategoryDeviceAndCustomer();

        /* WHEN */
        // zarejestrowanie "wypożyczenia"
        rentService.rentDevice( device, customer );

        /* THEN */
        // sprawdzenie, czy "wypożyczenie" jest zarejestrowane w bd
        Device deviceFromDB = deviceDAO.read( device.getId() ).orElseThrow();
        Assertions.assertThat( deviceFromDB.getId() ).isEqualTo( device.getId() );
        Assertions.assertThat( deviceFromDB.getDescription() ).isEqualTo( device.getDescription() );
        Assertions.assertThat( deviceFromDB.getAvailability() ).isEqualTo( device.getAvailability() );
        Assertions.assertThat( deviceFromDB.getPrice() ).isEqualTo( device.getPrice() );
        Assertions.assertThat( deviceFromDB.getCategory().getId() ).isEqualTo( device.getCategory().getId() );
        Assertions.assertThat( deviceFromDB.getCustomers().get(0).getId() ).isEqualTo( device.getCustomers().get(0).getId() );
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
        device = deviceDAO.save( device ).orElseThrow();
        customer = customerDAO.save( customer ).orElseThrow();
    }
}