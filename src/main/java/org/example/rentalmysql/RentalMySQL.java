package org.example.rentalmysql;

import org.example.rentalmysql.controllers.RentalController;
import org.example.rentalmysql.dao.*;
import org.example.rentalmysql.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class RentalMySQL {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RentalMySQL.class, args);
        RentalController rentalController = context.getBean( RentalController.class );
        rentalController.mainLoop();
    }

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }

/*    Category category = new CategoryBuilder()
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
        customerDao.save( customer );
    CategoryDAO categoryDAO = ctx.getBean(CategoryDAO.class);
    category = categoryDAO.save( category );
        device.setCategory( category );
    DeviceDAO deviceDao = ctx.getBean(DeviceDAO.class);
        deviceDao.save( device );*/

}