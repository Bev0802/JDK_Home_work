package messenger.server.userInterface;

import messenger.server.domain.Server;
import messenger.server.repository.FileStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*ServerWindow — это класс, расширяющий JFrame и реализующий ServerView.*/
public class ServerWindow extends JFrame implements ServerView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    JButton btnStart, btnStop;
    JTextArea log;

    private Server server;

    public ServerWindow(){
        setting();
        createPanel();

        setVisible(true);
    }
    /*Метод настройки параметров окна*/
    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        server = new Server(this, new FileStorage());
    }
    /*Метод получения экземпляра сервера*/
    public Server getConnection(){
        return server;
    }
    /*/*Метод создания компонентов графического интерфейса*/
    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }
    /*Метод создает панель кнопок*/
    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.start();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stop();
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
    /*Метод из интерфейса ServerView для отображения сообщений в журнале*/
    @Override
    public void showMessage(String msg) {
        log.append(msg);
    }
}
