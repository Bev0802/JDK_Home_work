package messenger.client.domain;

import messenger.client.userInterface.ClientView;
import messenger.server.domain.Server;

/*Этот класс представляет клиента, который взаимодействует с сервером.
*/
public class Client {
    /* Переменная для отслеживания статуса соединения */
    private boolean connected;

    /* Переменная для хранения имени клиента */
    private String name;

    /* Ссылка на представление клиента (UI) */
    private ClientView clientView;

    /* Ссылка на сервер */
    private Server server;

    /*
     * Конструктор для инициализации клиента с использованием представления клиента
     * и ссылок на сервер
     */
    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    /* Метод подключения к серверу с заданным именем */
    public boolean connectToServer(String name) {
        this.name = name;

        if (server.connectUser(this)) {

            showOnWindow("Вы успешно подключились!\n");
            connected = true;

            String log = server.getHistory();

            if (log != null) {
                showOnWindow(log);
            }

            return true;
        } else {

            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    /* Метод получения и отображения сообщений с сервера */
    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    /* Метод отключения от сервера */
    public void disconnectFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            showOnWindow("Вы были отключены от сервера!");
        }
    }

    /* Метод отправки сообщения на сервер */
    public void message(String text) {
        if (connected) {
            // If connected, send the message to the server with the client's name
            if (!text.isEmpty()) {
                server.message(name + ": " + text);
            }
        } else {
            showOnWindow("Нет подключения к серверу");
        }
    }

    /* Метод получения имени клиента */
    public String getName() {
        return name;
    }

    /*
     * Частный вспомогательный метод для отображения сообщения в представлении
     * клиента (UI)
     */
    private void showOnWindow(String text) {
        clientView.showMessage(text + "\n");
    }
}