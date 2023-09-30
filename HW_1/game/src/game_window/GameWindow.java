package game_window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Этот класс представляет окно игры и расширяет класс JFrame.
 */
public class GameWindow extends JFrame {
    // Константы ширины и высоты окна.
    private static final int WIDTH = 555;
    private static final int HEIGHT = 507;
    
    JButton btnStart, btnExit; // Кнопки запуска новой игры и выхода из приложения

    SettingWindow settingWindow;  // Экземпляр класса SettingWindow для настроек игры.
    Map map; // Экземпляр класса Map для игрового поля.

    GameWindow(){ // Конструктор класса GameWindow.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Установка операции закрытия окна по умолчанию.
        setSize(WIDTH, HEIGHT); //Установка размера окна.
        setLocationRelativeTo(null); //Центрируйте окно на экране.
        setTitle("Игра: Крестики-нолики"); // Установить заголовок окна.
        setResizable(false); // Отключить изменение размера окна.

        btnStart = new JButton("New Game"); // Создаем новый экземпляр кнопки «Новая игра».
        btnExit = new JButton("Exit");  // Создайте новый экземпляр кнопки «Выход».
        settingWindow = new SettingWindow(this); // Создайте новый экземпляр класса SettingWindow.
        map = new Map();  // Создайте новый экземпляр класса Map.

        btnExit.addActionListener(new ActionListener() { // Добавляем прослушиватель действий к кнопке «Выход».
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Выход из приложения при нажатии кнопки.
            }
        });

        btnStart.addActionListener(new ActionListener() { // Добавляем прослушиватель действий к кнопке «Новая игра».
            @Override
            public void actionPerformed(ActionEvent e) { 
                settingWindow.setVisible(true); //Отображать окно настроек при нажатии кнопки
            }
        });

        JPanel panBottom = new JPanel(new GridLayout(1, 2));  //Создаем новую панель с сеткой 1x2 для кнопок.
        panBottom.add(btnStart); //Добавляем кнопки «Новая игра» и «Выход» на панель.
        panBottom.add(btnExit);

        add(panBottom, BorderLayout.SOUTH); //Добавляем панель внизу окна.
        add(map); // Добавьте карту (игровое поле) в окно.

        setVisible(true); //Сделаем окно видимым
    }

    void startNewGame(int mode, int sizeX, int sizeY, int winLen){ //Способ запуска новой игры с указанными настройками.
        map.startNewGame(mode, sizeX, sizeY, winLen); //Вызовите метод startNewGame карты (игрового поля) с указанными настройками.
    }
}
