package org.example.rentalmysql;

import org.example.rentalmysql.dao.*;
import org.example.rentalmysql.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RentalMySQL {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(RentalMySQL.class, args);
/*        Category category = new CategoryBuilder()
                .withName("wiertarki")
                .withDescription("wiertarki - opis")
                .build();
        Device device = new DeviceBuilder()
                .withName("wieratrka Makita CX100")
                .withDescription("wieratrka Makita CX100- opis")
                .withAvailability( 10 )
                .withPrice( 100.0 )
                .build();
        Customer customer = new CustomerBuilder()
                .withFirstName("Jan")
                .withLastName("Dzban")
                .withPersonalID("12345678901")
                .withIdCardNumber("DEF123456")
                .build();
        CustomerDAO customerDao = ctx.getBean(CustomerDAO.class);
        customer = customerDao.save( customer ).orElseThrow();
        CategoryDAO categoryDAO = ctx.getBean(CategoryDAO.class);
        category = categoryDAO.save( category ).orElseThrow();
        device.setCategory( category );
        DeviceDAO deviceDao = ctx.getBean(DeviceDAO.class);
        device = deviceDao.save( device ).orElseThrow();*/

    }
}