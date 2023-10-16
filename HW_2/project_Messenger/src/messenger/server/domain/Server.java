package messenger.server.domain;

import messenger.client.domain.Client;
import messenger.server.repository.Repository;
import messenger.server.userInterface.ServerView;

import java.util.ArrayList;
import java.util.List;
/*Этот класс представляет сервер, который обрабатывает клиентские соединения и сообщения.*/
public class Server {
    private boolean work;
    private ServerView serverView;
    private List<Client> clientList;
    private Repository repository;

    public Server(ServerView serverView, Repository repository) {
        this.serverView = serverView;
        this.repository = repository;
        clientList = new ArrayList<>();
    }
    /*Метод запуска сервера*/
    public void start(){
        if (work){
            showOnWindow("Сервер уже был запущен");
        } else {
            work = true;
            showOnWindow("Сервер запущен!");
        }
    }
    /*Метод остановки сервера */
    public void stop(){
        if (!work){
            showOnWindow("Сервер уже был остановлен");
        } else {
            work = false;
            for (Client client: clientList){
                disconnectUser(client);
            }
            showOnWindow("Сервер остановлен!");
        }
    }
    /*Способ отключения конкретного клиента от сервера*/
    public void disconnectUser(Client client){
        clientList.remove(client);
        if (client != null){
            client.disconnectFromServer();
            showOnWindow(client.getName() + " отключился от беседы");
        }
    }
    /*Способ подключения клиента к серверу*/
    public boolean connectUser(Client client){
        if (!work){
            return false;
        }
        clientList.add(client);
        showOnWindow(client.getName() + " подключился к беседе");
        return true;
    }
    /*Метод отправки сообщения всем подключенным клиентам*/
    public void message(String text){
        if (!work){
            return;
        }
        text += "";
        showOnWindow(text);
        answerAll(text);
        saveInHistory(text);
    }
    /*Метод получения истории сообщений из репозитория*/
    public String getHistory() {
        return (String) repository.load();
    }
    /*Метод отправки сообщения всем подключенным клиентам*/
    private void answerAll(String text){
        for (Client client: clientList){
            client.answerFromServer(text);
        }
    }
    /*Метод отображения сообщения в окне сервера*/
    private void showOnWindow(String text){
        serverView.showMessage(text + "\n");
    }
    /*Метод сохраняет сообщение в истории*/
    private void saveInHistory(String text){
        repository.save(text);
    }
}
