package messenger;

import messenger.client.userInterface.ClientGUI;
import messenger.server.userInterface.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}
