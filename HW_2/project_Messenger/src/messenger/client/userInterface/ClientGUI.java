package messenger.client.userInterface;

import messenger.client.domain.Client;
import messenger.server.userInterface.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    private Client client;

    public ClientGUI(ServerWindow server) {
        setting(server);
        createPanel();

        setVisible(true);
    }

    /*Метод настройки параметров графического интерфейса*/   
    private void setting(ServerWindow server) {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(server.getX() - 500, server.getY());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        client = new Client(this, server.getConnection());
    }
    /*Метод отображения сообщения в журнале JTextArea*/
    public void showMessage(String msg) {
        log.append(msg);
    }
    /*Метод отключения от сервера*/
    public void disconnectFromServer(){
        hideHeaderPanel(true);
        client.disconnectFromServer();
    }
    /*Метод для скрытия или отображения headerPanel*/
    public void hideHeaderPanel(boolean visible){
        headerPanel.setVisible(visible);
    }
    /*Метод выполнения операции входа в систему*/
    public void login(){
        if (client.connectToServer(tfLogin.getText())){
            headerPanel.setVisible(false);
        }
    }
    /*Метод отправки сообщения на сервер*/
    private void message(){
        client.message(tfMessage.getText());
        tfMessage.setText("");
    }
    /*Метод создания панели графического интерфейса*/
    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }
    /*Метод создания headerPanel*/
    private Component createHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;
    }
    /*Создание нового элемента панели JTextArea для ведения журнала.*/
    private Component createLog() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }
    /*Создание нового элемента панели для ведения сообщений*/
    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    message();
                }
            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }
    
    /*Вызов метод DisconnectFromServer() при закрытии окна.*/
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            disconnectFromServer();
        }
    }
}
