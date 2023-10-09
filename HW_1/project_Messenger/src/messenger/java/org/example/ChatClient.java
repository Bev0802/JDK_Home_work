package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatClient extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private ChatServer server;
    private boolean connected;
    private String name;

    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfsendMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    ChatClient(ChatServer server){
        this.server = server;

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(server.getX() - 500, server.getY());

        createPanel();

        setVisible(true);
    }
    // Создание панелей

     private void createPanel() {
        add(createLoginPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private Component createLoginPanel(){
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel()); //добавление пустой панели для выравнивания
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;
  }

  private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

  private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfsendMessage = new JTextField();
        tfsendMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    sendMessage();
                }
            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel.add(tfsendMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }  
    // Обработка работы сервера

    private void connectToServer() {
        if (server.connectUser(this)) {
            appendLog("Вы подключились!\n");
            headerPanel.setVisible(false);
            connected = true;
            name = tfLogin.getText();
            String log = server.getLog();
            if (log != null) appendLog(log);
        } else appendLog("Вы не подключились!");
    }

    public void disconnectFromServer() {
        if (connected) {
            headerPanel.setVisible(true);
            connected = false;
            server.disconnectUser(this);
            appendLog("Вы отключены от сервера!");
        }
    }

    // Обработка сообщений
    public void sendMessage(){
        if (connected){
            String text = tfsendMessage.getText();
            if (!text.equals("")){
                server.sendMessage(name + ": \n" + text);
                tfsendMessage.setText("");
            }
        } else {
            appendLog("Нет подключения к серверу");
        }
    }

    // Логирование
     public void answer(String text){
        appendLog(text);
    }

    private void appendLog(String text){
        log.append(text + "\n");
        
    }    

    @Override
    public int getDefaultCloseOperation() {
        disconnectFromServer();
        return super.getDefaultCloseOperation();
    }
}
