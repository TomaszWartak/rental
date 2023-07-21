package org.example.rentalmysql.controllers;

import org.example.rentalmysql.utils.MessageDisplayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RentalController {

    @Autowired
    MessageDisplayer messageDisplayer;

    public void mainLoop() {
        showInviteMessage();
        Option option = null;
        do {
          printMenu();
          try {
            option = chooseOption();
            executeOption(option);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        } while (option != Option.EXIT);
    }

    private void showInviteMessage() {
        messageDisplayer.show("Witaj w aplikacji LinguApp");
    }

    private void printMenu() {
        // TODO
    }

    private Option chooseOption() {
        return Option.EXIT; // TODO
    }

    private void executeOption(Option option) {
        // TODO
    }

    private static enum Option {
    ADD_DEVICE(1, "Dodaj urządzenie"),
    DELETE_DEVICE(2, "Rozpocznij test"),
    LIST_ALL_DEVICES( 3, "Wyświetl listę urządzeń"),
    ADD_CATEGORY( 4, "Dodaj kategorię"),
    DELETE_CATEGORY( 5, "Usuń kategorię"),
    ADD_CUSTOMER ( 6, "Dodaj klienta"),
    DELETE_CUSTOMER( 7, "Usuń dane klienta"),
    RENT_DEVICE( 8, "Wypożycz urządzenie"),
    EXIT(0, "Koniec programu");
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
