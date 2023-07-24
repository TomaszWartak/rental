package org.example.rentalmysql.controllers;

import org.example.rentalmysql.entities.*;
import org.example.rentalmysql.services.CategoryService;
import org.example.rentalmysql.services.CustomerService;
import org.example.rentalmysql.services.DeviceService;
import org.example.rentalmysql.services.RentService;
import org.example.rentalmysql.utils.InputReader;
import org.example.rentalmysql.utils.MessageDisplayer;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class RentalController {

    MessageDisplayer messageDisplayer;
    InputReader inputReader;
    CategoryService categoryService;
    DeviceService deviceService;
    CustomerService customerService;
    RentService rentService;

    public RentalController(
            MessageDisplayer messageDisplayer,
            CategoryService categoryService,
            InputReader inputReader,
            DeviceService deviceService,
            CustomerService customerService,
            RentService rentService) {
        this.messageDisplayer = messageDisplayer;
        this.categoryService = categoryService;
        this.inputReader = inputReader;
        this.deviceService = deviceService;
        this.customerService = customerService;
        this.rentService = rentService;
    }

    public void mainLoop() {
        showInviteMessage();
        Option option = null;
        do {
          printMenu();
          try {
            option = chooseOption();
            executeOption(option);
          } catch (IllegalArgumentException e) {
            messageDisplayer.show(e.getMessage());
          }
        } while (option != Option.EXIT);
    }

    private void showInviteMessage() {
        messageDisplayer.show("Witaj w aplikacji RENTAL");
    }

    private void printMenu() {
        messageDisplayer.show("Wybierz opcję:");
        for (Option option : Option.values()) {
          messageDisplayer.show( option.toString() );
        }
    }

    private Option chooseOption() {
        int option = inputReader.readInt( "To nie jest prawidłowa wartość");
        return Option.fromInt(option);
    }

    private void executeOption(Option option) {
        switch (option) {
          case ADD_DEVICE -> addDevice();
          case DELETE_DEVICE -> deleteDevice();
          case LIST_ALL_DEVICES -> listAllDevices();
          case ADD_CATEGORY -> addCategory();
          case DELETE_CATEGORY -> deleteCategory();
          case LIST_ALL_CATEGORIES -> listAllCategories();
          case ADD_CUSTOMER -> addCustomer();
          case DELETE_CUSTOMER -> deleteCustomer();
          case LIST_ALL_CUSTOMERS -> listAllCustomers();
          case RENT_DEVICE -> rentDevice();
          case RECEPT_DEVICE -> receptDevice();
          case POPULATE_DATA -> populateData();
          case EXIT -> exitApp();
        }
    }

    private void addDevice() {
        messageDisplayer.show("DODAJ URZĄDZENIE" );

        messageDisplayer.show( "Podaj nazwę urządzenia");
        String name = inputReader.readText();

        messageDisplayer.show("Podaj opis urządzenia");
        String description = inputReader.readText();

        messageDisplayer.show("Podaj ilość dostępnych urządzeń");
        int availability = inputReader.readInt( InputReader.NOT_VALID_NUMERICAL_VALUE_MESSAGE );

        messageDisplayer.show("Podaj id kategorii");
        long categoryId = inputReader.readLong( InputReader.NOT_VALID_NUMERICAL_VALUE_MESSAGE );

        categoryService.getCategoryWithId( categoryId ).ifPresentOrElse(
            category -> deviceService.addDevice(
                 new DeviceBuilder()
                    .withName( name )
                    .withDescription( description )
                    .withAvailability( availability )
                    .withCategory( category )
                    .build() ),
            ()-> messageDisplayer.show("nie ma kategorii o takim id")
        );
    }

    private void deleteDevice() {
        messageDisplayer.show("USUNIĘCIE URZĄDZENIA");
        messageDisplayer.show("Podaj id URZĄDZENIA do usunięcia");
        long id = inputReader.readLong( InputReader.NOT_VALID_NUMERICAL_VALUE_MESSAGE );
        deviceService.getDeviceWithId( id ).ifPresentOrElse(
            device -> {
                deviceService.deleteDevice(device);
                messageDisplayer.show("usunięto: " + device );
            },
            () -> messageDisplayer.show("W systemie nie ma urządzenia o takim id")
        );
    }

    private void listAllDevices() {
        messageDisplayer.show("LISTA URZĄDZEŃ");
        ArrayList<Device> devices = (ArrayList) deviceService.getAllDevices();
        if ((devices!=null)&&(!devices.isEmpty())) {
            devices.forEach( device -> messageDisplayer.show( device.toString() ) );
        } else {
            messageDisplayer.show("Brak urządzeń...");
        }
        messageDisplayer.makeCRLF();    }

    private void addCategory() {
        messageDisplayer.show("DODAJ KATEGORIĘ" );
        messageDisplayer.show( "Podaj nazwę kategorii");
        String name = inputReader.readText();
        messageDisplayer.show("Podaj opis kategorii");
        String description = inputReader.readText();
        categoryService.addCategory(
            new CategoryBuilder()
                    .withName( name )
                    .withDescription( description )
                    .build()
        );
    }

    private void deleteCategory() {
        messageDisplayer.show("USUNIĘCIE KATEGORII");
        messageDisplayer.show("Podaj id kategorii do usunięcia");
        long id = inputReader.readLong( InputReader.NOT_VALID_NUMERICAL_VALUE_MESSAGE );
        categoryService.getCategoryWithId(id).ifPresentOrElse(
            category -> {
                ArrayList<Device> devices = (ArrayList) deviceService.getAllDevicesInCategory( category );
                if (devices!=null) {
                    messageDisplayer.show("Nie można usunać kategorii!");
                    messageDisplayer.show("Istnieją urządzenia przypisane do niej:");
                    devices.forEach( device -> messageDisplayer.show( device.toString() ) );
                } else {
                    categoryService.deleteCategory(category);
                    messageDisplayer.show("usunięto: " + category);
                }
            },
            () -> messageDisplayer.show("W systemie nie ma kategorii o takim id")
        );
    }

    private void listAllCategories() {
        messageDisplayer.show("LISTA KATEGORII");
        ArrayList<Category> categories = (ArrayList) categoryService.getAllCategories();
        if ((categories!=null)&&(!categories.isEmpty())) {
            categories.forEach( category -> messageDisplayer.show( category.toString() ) );
        } else {
            messageDisplayer.show("Brak kategorii...");
        }
        messageDisplayer.makeCRLF();
    }

    private void addCustomer() {
        messageDisplayer.show("DODAJ KLIENTA" );

        messageDisplayer.show( "Podaj imię klienta");
        String firstName = inputReader.readText();

        messageDisplayer.show("Podaj nazwisko klienta");
        String lastName = inputReader.readText();

        messageDisplayer.show("Podaj nr PESEL klienta");
        String personalID = inputReader.readText();

        messageDisplayer.show("Podaj nr dowodu osobistego klienta");
        String idCardNumber = inputReader.readText();

        customerService.addCustomer(
            new CustomerBuilder()
                    .withFirstName( firstName )
                    .withLastName( lastName )
                    .withPersonalID( personalID )
                    .withIdCardNumber( idCardNumber )
                    .build()
        );
    }

    private void deleteCustomer() {
        messageDisplayer.show("USUNIĘCIE KLIENTA");
        messageDisplayer.show("Podaj id KLIENTA do usunięcia");
        long id = inputReader.readLong( InputReader.NOT_VALID_NUMERICAL_VALUE_MESSAGE );
        customerService.getCustomerWithId( id ).ifPresentOrElse(
                customer -> {
                    customerService.deleteCustomer( customer );
                    messageDisplayer.show("usunięto: " + customer );
                },
                () -> messageDisplayer.show("W systemie nie ma danych klienta o takim id")
        );
    }

    private void listAllCustomers() {
        messageDisplayer.show("LISTA KLIENTÓW");
        ArrayList<Customer> customers = (ArrayList) customerService.getAllCustomers();
        if ((customers!=null)&&(!customers.isEmpty())) {
            customers.forEach( customer -> messageDisplayer.show( customer.toString() ) );
        } else {
            messageDisplayer.show("Brak danych klientów...");
        }
        messageDisplayer.makeCRLF();
    }
    private void rentDevice() {
        messageDisplayer.show("WYPOŻYCZENIE URZĄDZENIA");

        messageDisplayer.show("Podaj id klienta");
        long customerId = inputReader.readLong( InputReader.NOT_VALID_NUMERICAL_VALUE_MESSAGE );
        Customer customer = customerService.getCustomerWithId( customerId ).orElse( null);
        if (customer == null) {
            messageDisplayer.show("W systemie nie ma danych klienta o takim id");
            return;
        }

        messageDisplayer.show("Podaj id urządzenia");
        long deviceId = inputReader.readLong( InputReader.NOT_VALID_NUMERICAL_VALUE_MESSAGE );
        Device device =  deviceService.getDeviceWithId( deviceId ).orElse( null);
        if (device == null) {
            messageDisplayer.show("W systemie nie ma urządzenia o takim id");
            return;
        }
        
        if (rentService.isDeviceRented( device )) {
            messageDisplayer.show("Urządzenie jest już wypożyczone");
            return;
        }

        rentService.rentDevice( device, customer);
    }

    private void receptDevice() {
        messageDisplayer.show("ZWROT URZĄDZENIA");

        messageDisplayer.show("Podaj id urządzenia");
        long deviceId = inputReader.readLong( InputReader.NOT_VALID_NUMERICAL_VALUE_MESSAGE );
        Device device =  deviceService.getDeviceWithId( deviceId ).orElse( null);
        if (device == null) {
            messageDisplayer.show("W systemie nie ma urządzenia o takim id");
            return;
        }

        if (rentService.isDeviceAvilable( device )) {
            messageDisplayer.show("Urządzenie nie jest wypożyczone");
            return;
        }

        rentService.receptRentalDevice( device );
    }

    private void populateData() {
        Category category = new CategoryBuilder()
                .withName("wiertarki")
                .withDescription("wiertarki - opis")
                .build();
        categoryService.addCategory( category );
        Device device = new DeviceBuilder()
                .withName("wieratrka Makita CX100")
                .withDescription("wiertarka Makita CX100- opis")
                .withAvailability( 10 )
                .withPrice( 100.0 )
                .build();
        device.setCategory( category );
        deviceService.addDevice( device );
        device = new DeviceBuilder()
                .withName("wieratrka ERBAUER")
                .withDescription("wieratrka ERBAUER - opis")
                .withAvailability( 5 )
                .withPrice( 100.0 )
                .build();
        device.setCategory( category );
        deviceService.addDevice( device );
        Customer customer = new CustomerBuilder()
                .withFirstName("Jan")
                .withLastName("Dzban")
                .withPersonalID("12345678901")
                .withIdCardNumber("DEF123456")
                .build();
        customerService.addCustomer( customer );
    }

    private void exitApp() {
        inputReader.close();
    }

    private enum Option {
    ADD_DEVICE(1, "Dodaj urządzenie"),
    DELETE_DEVICE(2, "Usuń urządzenie"),
    LIST_ALL_DEVICES( 3, "Wyświetl listę urządzeń"),
    ADD_CATEGORY( 4, "Dodaj kategorię"),
    DELETE_CATEGORY( 5, "Usuń kategorię"),
    LIST_ALL_CATEGORIES( 6, "Wyświetl listę kategorii"),
    ADD_CUSTOMER ( 7, "Dodaj klienta"),
    DELETE_CUSTOMER( 8, "Usuń dane klienta"),
    LIST_ALL_CUSTOMERS( 9, "Wyświetl listę klientów"),
    RENT_DEVICE( 10, "Wypożycz urządzenie"),
    RECEPT_DEVICE( 11, "Przyjmij urządzenie"),
    POPULATE_DATA( 12, "wgraj dane testowe"),
    EXIT(13, "Koniec programu");
    private final int optionNumber;
    private final String description;
    Option(int optionNumber, String description) {
      this.optionNumber = optionNumber;
      this.description = description;
    }
    static Option fromInt(int option) {
      if (option < 0 || option > values().length) {
        throw new IllegalArgumentException("Opcja o takim numerze nie istnieje");
      }
      return values()[option - 1];
    }
    @Override
    public String toString() {
      return String.format("%d - %s", optionNumber, description);
    }
  }

}
