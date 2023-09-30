package game_window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;

public class Map extends JPanel {
    private static final Random RANDOM = new Random();
    private static final int HUMAN_DOT = 1;
    private static final int AI_DOT = 2;
    private static final int EMPTY_DOT = 0;
    private static final int PADDING = 10;

    private int gameStateType;
    private static final int STATE_GAME = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final int STATE_DRAW = 3;

    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!";

    private int width, height, cellWidth, cellHeight;
    private int mode, fieldSizeX, fieldSizeY, winLen;
    private int[][] field;
    private boolean gameWork;

    Map() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (gameWork) {
                    update(e);
                }
            }
        });
    }

    private void initMap() {
        field = new int[fieldSizeY][fieldSizeX];
    }

    void startNewGame(int mode, int sizeX, int sizeY, int winLen) {
        this.mode = mode;
        this.fieldSizeX = sizeX;
        this.fieldSizeY = sizeY;
        this.winLen = winLen;

        initMap();
        gameWork = true;
        gameStateType = STATE_GAME;

        repaint();
    }

    private void update(MouseEvent mouseEvent) { // Обновляет состояние игры на основе взаимодействия с пользователем.
        int x = mouseEvent.getX() / cellWidth;
        int y = mouseEvent.getY() / cellHeight;
        if (!isValidCell(x, y) || !isEmptyCell(x, y)) {
            return;
        }
        field[y][x] = HUMAN_DOT; // Нанесите на карту ход человека.
        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) {
            return;
        }
        aiTurn(); // очередь ИИ.
        repaint(); // Перекрасить карту.
        checkEndGame(AI_DOT, STATE_WIN_AI);
    }

    private void testBoard(){ // Тестовый метод печати игрового поля.
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
        System.out.println();
    }

    private boolean isValidCell(int x, int y) { // //Проверка, действительна ли ячейка.
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y) { // Проверка, пуста ли ячейка.
        return field[y][x] == EMPTY_DOT;
    }

    
    private void aiTurn() { // Очередь ИИ сделать ход
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }

    private boolean isMapFull() { // Проверка, заполнено ли поле.
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkEndGame(int dot, int gameOverType) { // Проверка, закончилась ли игра.
        if (checkWin(dot)) {
            this.gameStateType = gameOverType; // Установка состояния игры на окончания игры.
            repaint(); 
            return true;
        } else if (isMapFull()) { 
            this.gameStateType = STATE_DRAW;  // Установить состояние игры на ничью.
            repaint();
            return true;
        }
        return false;
    }

    private boolean checkWin(int dot){
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                //Проверьте выигрыш во всех возможных направлениях.
                if (checkLine(i, j, 1, 0, winLen, dot)) return true;
                if (checkLine(i, j, 1, 1, winLen, dot)) return true;
                if (checkLine(i, j, 0, 1, winLen, dot)) return true;
                if (checkLine(i, j, 1, -1, winLen, dot)) return true;
            }
        }
        return false;
    }

    private boolean checkLine(int x, int y, int vx, int vy, int len, int dot){ // Проверьте, является ли линия точек выигрышной.
        int far_x = x + (len - 1) * vx;
        int far_y = y + (len - 1) * vy;
        if (!isValidCell(far_x, far_y)){ // Проверьте, совпадают ли точки в строке с указанной точкой.
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (field[y + i * vy][x + i * vx] != dot){
                return false;
            }
        }
        return true;
    }

    @Override //Переопределям метод paintComponent для визализации игры.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameWork) {
            render(g);
        }
    }

    private void render(Graphics g) { // Визуализация компонентов игры.
        width = getWidth();
        height = getHeight();
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;

        g.setColor(Color.BLACK); // Нарисуйм линии сетки.
        for (int h = 0; h < fieldSizeX; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, width, y);
        }
        for (int w = 0; w < fieldSizeX; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, height);
        }

        for (int y = 0; y < fieldSizeY; y++) { // Нарисуем точки на сетке.
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT){
                    continue;
                }
                if (field[y][x] == HUMAN_DOT) { // Нарисуйм крест для игрока человека
                    g.drawLine(x * cellWidth + PADDING, y * cellHeight + PADDING,
                            (x + 1) * cellWidth - PADDING, (y + 1) * cellHeight - PADDING);
                    g.drawLine(x * cellWidth + PADDING, (y + 1) * cellHeight - PADDING,
                            (x + 1) * cellWidth - PADDING, y * cellHeight + PADDING);
                } else if (field[y][x] == AI_DOT) { // Нарисуйм крест для ИИ.
                    g.drawOval(x * cellWidth + PADDING, y * cellHeight + PADDING, 
                            cellWidth - PADDING * 2, cellHeight - PADDING * 2);
                } else { // //Выдать исключение для непроверенного значения в ячейке.
                    throw new RuntimeException("unchecked value " + field[y][x] +
                            " in cell: x=" + x + " y=" + y);
                }
            }
        }
        if (gameStateType != STATE_GAME){ //Показывать сообщение, если состояние игры отличается от STATE_GAME.
            showMessage(g);
        }
    }

    private void showMessage(Graphics g) {
        //Нарисуем темно-серый прямоугольник в котором будет выводится
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, getHeight() / 2, getWidth(), 70);
        // Установим цвет и шрифт сообщения
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameStateType){ //Отображение сообщения в зависимости от состояния игры.
            case STATE_DRAW -> g.drawString(MSG_DRAW, 180, getHeight() / 2 + 60);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 20, getHeight() / 2 + 60);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 70, getHeight() / 2 + 60);
            // Выдать исключение для непроверенного gameOverState.
            default -> throw new RuntimeException("Unchecked gameOverState: " + gameStateType); 
        }
        gameWork = false;
    }
}
