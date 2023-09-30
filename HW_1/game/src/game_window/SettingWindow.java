package game_window;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Класс SettingWindow представляет окно, в котором пользователь может настроить 
 * параметры игры перед началом новой игры.
*/
public class SettingWindow extends JFrame {
    // Константы для надписей кнопок и другого текста, отображаемого в окне.
    public static final String BTN_START = "Start new game";
    public static final String LABEL_CHOICE_MODE = "Выберите режим игры";
    public static final String BTN_HUMAN_VERSUS_AI = "Человек против компьютера";
    public static final String BTN_HUMAN_VERSUS_HUMAN = "Человек против человека";
    public static final String SIZE_PREFIX = "Установленный размер поля: ";
    public static final String WIN_LENGTH_PREFIX = "Установленная длина: ";
    public static final String LABEL_CHOICE_SIZE = "Выберите размеры поля";
    public static final String LABEL_CHOICE_WIN_LENGTH = "Выберите длину для победы";
    public static final int MODE_HVA = 0; // Режим игры: Человек против ИИ
    public static final int MODE_HVH = 1; // //Режим игры: Человек против человека.

    public static final int MIN_SIZE = 3; 
    public static final int MAX_SIZE = 10;

    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;

    GameWindow gameWindow; // //Ссылка на главное окно игры.
    JButton btnStart; // Кнока запуска Новой игры
    JRadioButton humanVHuman, humanVAI; // Радиокнопки для выбора режима игры.
    Label labelCurSize, labelWinLength; // Метки для отображения текущего размера поля и длины выигрыша
    JSlider sizeSlider, winSlider; // Слайдеры для выбора размера поля и длины выигрыша
    
    SettingWindow(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        // Рассчитет центральное положение главного окна игры.
        int centerGameWindowX = gameWindow.getX() + gameWindow.getWidth()/2;
        int centerGameWindowY = gameWindow.getY() + gameWindow.getHeight()/2;
        // Установка положение и размер окна настроек.
        setLocation(centerGameWindowX - WIDTH/2, centerGameWindowY - HEIGHT/2);
        setSize(WIDTH, HEIGHT);

        add(createMainPanel()); // Создание главной панели и добавьте ее в окно настроек.
        add(createButtonStart(), BorderLayout.SOUTH); // Создание кнопки «Пуск» и добавьте ее в окно настроек внизу.
    }

    private Component createMainPanel(){ // Создание главной панели, содержащую панель режима выбора, панель размера выбора и панель выбора длины выигрыша.
        JPanel panel = new JPanel(new GridLayout(3, 1));

        panel.add(createChoiceModePanel());
        panel.add(createChoiceSizePanel());
        panel.add(createChoiceWinLengthPanel());
        return panel;
    }

    private Component createButtonStart(){ // Создание кнопки для начала новой игры.
        btnStart = new JButton(BTN_START);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                startGame();
            }
        });
        return btnStart;
    }

    private void startGame(){ // Метод для запуска игры.
        int mode;
        if (humanVAI.isSelected()){ // //Поверка, какой режим игры выбран
            mode = MODE_HVA;
        } else if (humanVHuman.isSelected()){
            mode = MODE_HVH;
        } else {
            throw new RuntimeException("Unknown game mode"); // Выдать исключение, если режим игры неизвестен.
        }
        // Get the size, win length, and start the new game with the specified parameters.
        int size = sizeSlider.getValue();
        int winLength = winSlider.getValue();
        gameWindow.startNewGame(mode, size, size, winLength);
    }

    private Component createChoiceModePanel(){ // Создаем панель выбора режима игры..
        JPanel panel = new JPanel(new GridLayout(3, 1)); // Создание новой JPanel с сеткой.
        Label title = new Label(LABEL_CHOICE_MODE); // Создание метки для панели
        ButtonGroup buttonGroup = new ButtonGroup(); // Создание группы переключателей.
        humanVAI = new JRadioButton(BTN_HUMAN_VERSUS_AI); // Создание переключателей для режимов «человек против ИИ» и «человек против человека».
        humanVHuman = new JRadioButton(BTN_HUMAN_VERSUS_HUMAN);
        buttonGroup.add(humanVAI); // Add the radio buttons to the button group
        buttonGroup.add(humanVHuman);
        humanVAI.setSelected(true); //По умолчанию установили режим "человек против искусственного интеллекта"
         // Добавление компанентов на панель.
        panel.add(title);
        panel.add(humanVAI);
        panel.add(humanVHuman);

        return panel; // Вернем созданную панель.
    }
    
    private Component createChoiceSizePanel(){  // Создание панели выбора размера игры.
        JPanel panel = new JPanel(new GridLayout(3, 1)); // Создание новой JPanel с сеткой.
        Label title = new Label(LABEL_CHOICE_SIZE); // Создание метки для панели
        labelCurSize = new Label(SIZE_PREFIX + MIN_SIZE); // Создание метки для отображения текущего размера
        sizeSlider = new JSlider(MIN_SIZE, MAX_SIZE, MIN_SIZE); // Создание ползунка для выбора размера.
        
        sizeSlider.addChangeListener(new ChangeListener() { 
            @Override
            public void stateChanged(ChangeEvent e) {  //Когда значение ползунка изменится, обновите метку и установите максимальное значение ползунка длины.
                int curSize = sizeSlider.getValue();
                labelCurSize.setText(SIZE_PREFIX + curSize);
                winSlider.setMaximum(curSize);
            }
        });

        panel.add(title);
        panel.add(labelCurSize);
        panel.add(sizeSlider);
        return panel;
    }

    }
    private Component createChoiceWinLengthPanel(){ // Создание панели для выбора размера.
        JPanel panel = new JPanel(new GridLayout(3, 1)); 
        Label title = new Label(LABEL_CHOICE_WIN_LENGTH);
        labelWinLength = new Label(WIN_LENGTH_PREFIX + MIN_SIZE);
        winSlider = new JSlider(MIN_SIZE, MAX_SIZE, MIN_SIZE);
        winSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWinLength.setText(SIZE_PREFIX + winSlider.getValue());
            }
        });

        panel.add(title);
        panel.add(labelWinLength);
        panel.add(winSlider);
        return panel;
    }
}
