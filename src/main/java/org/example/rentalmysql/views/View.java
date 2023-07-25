package org.example.rentalmysql.views;

/*
TODO
1. Z RentalController trzeba wydzielić AppControlller, który będzie odpowiadał jedynie obsługę wyboru opcji.
2. Pozostałe metody trzeba przenieść do kontrolerów obsługujących logikę poszczególnych obiektów domenowych
    (kategoria, klient, urządzenie, wypożyczenie)
3. Po wyborze opcji będzie w AppController będą wołane metody odpowiednich kontrolerów.
4. W metodach wszystkich (również AppController) kontrolerów będą tworzone odpowiednie widoki (de facto napisy
    wyświetlane w konsoli)
5. Widoki będa przyjmować dane od użytkownika i "wysyłać" komplet danych (przez DTO) do kontrolerów


 */
public interface View {

    void show();

    void update();

    void sendDataToController();

}
