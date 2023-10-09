package org.example;

public class Main {
    public static void main(String[] args) {

        ChatServer ChatServer = new ChatServer();
        new ChatClient(ChatServer);
        new ChatClient(ChatServer);
    }
}
