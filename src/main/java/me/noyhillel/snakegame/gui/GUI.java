package me.noyhillel.snakegame.gui;

import lombok.Data;
import me.noyhillel.snakegame.SnakeMain;
import me.noyhillel.snakegame.game.Snake;
import me.noyhillel.snakegame.game.SnakeBlocks;
import me.noyhillel.snakegame.game.impl.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

@Data
public final class GUI {

    public static JFrame jFrame;
    private Snake snake;
    private SnakeGame snakeGame = new SnakeGame();
    private SnakeBlocks block;
    private Font regFont;
    private Font fontHeader;
    private Font fontScoreAndTime;

    // Constructor
    public GUI() {
        snakeGame.setSnakeGame(snakeGame);
    }

    @SuppressWarnings("MagicConstant")
    public void playGame() {
        jFrame = new JFrame("SnakeGame -- Created by Noy.");
        jFrame.setSize(700, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.requestFocus();
        jFrame.repaint();
        jFrame.setVisible(true);
        Design design = new Design();
        design.setLayout(null);
        jFrame.add(design);
        regFont = new Font(Font.DIALOG, Font.ITALIC + Font.BOLD, 12);
        fontScoreAndTime = new Font (Font.DIALOG, Font.BOLD, 30);
        fontHeader = new Font( Font.DIALOG, Font.BOLD, 40 );
        jFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // No typing, no need for this method.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (snakeGame.isStarted()) {
                    if (snakeGame.isPause()) checkPause(e);
                    else checkGame(e);
                }
                else if (snakeGame.isFinished()) checkGameOver(e);
                else if (snakeGame.isStartMenu()) checkStart(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No need for this method, not releasing the key.
            }
        });
    }

    private final class Design extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            Graphics graphics;
            graphics = g;
            if (snakeGame.isStarted()) {
                if (snakeGame.isPause()) drawPauseMenu(graphics);
                else drawCurrentGame(graphics);
            }
            else if (snakeGame.isFinished()) drawGameOver(graphics);
            else if (snakeGame.isStartMenu()) drawStartMenu(graphics);
        }
    }

    private void exit() {
        SnakeMain.print("Thanks for playing! Hope you enjoyed the game!");
        System.exit(0);
    }

    private void drawString(String name, Integer width, Integer length, Graphics g) {
        g.drawString(name, width, length);
    }

    private void setColor(Color color, Graphics g) {
        g.setColor(color);
    }

    private void setFont(Font font, Graphics g) {
        g.setFont(font);
    }

    private void fillRect(Integer length, Integer height, Integer width, Integer base, Graphics g) {
        g.fillRect(length, height, width, base);
    }

    private void backToMainMenu() {
        snakeGame.setPause(false);
        snakeGame.setStarted(false);
        snakeGame.restart();
        snakeGame.setStartMenu(true);
        snakeGame.setHeaders("Start game");
        GUI.jFrame.repaint();
    }

    private void goUp() {
        snake.setSnakeDown(false);
        snake.setSnakeRight(false);
        snake.setSnakeLeft(false);
        snake.setSnakeUp(true);
    }

    private void goDown() {
        snake.setSnakeUp(false);
        snake.setSnakeRight(false);
        snake.setSnakeLeft(false);
        snake.setSnakeDown(true);
    }

    private void goLeft() {
        snake.setSnakeUp(false);
        snake.setSnakeRight(false);
        snake.setSnakeDown(false);
        snake.setSnakeLeft(true);
    }

    private void goRight() {
        snake.setSnakeUp(false);
        snake.setSnakeLeft(false);
        snake.setSnakeDown(false);
        snake.setSnakeRight(true);
    }

    // This took bloody ages, omg.
    private void checkPause(KeyEvent e) {
        //The Escape key.
        if (e.getKeyCode() == 27) {
            snakeGame.setPause(false);
        }
        //Up Arrow key.
        else if (e.getKeyCode() == 38) {
            if (snakeGame.getHeaders().equals("Exit game")) {
                snakeGame.setHeaders("Back to main menu");
                GUI.jFrame.repaint();
            }
            else if (snakeGame.getHeaders().equals("Back to main menu")) {
                snakeGame.setHeaders("Restart game");
                GUI.jFrame.repaint();
            }
        }
        // Down Arrow key
        else if (e.getKeyCode() == 40) {
            if (snakeGame.getHeaders().equals("Restart game")) {
                snakeGame.setHeaders("Back to main menu");
                GUI.jFrame.repaint();
            } else if (snakeGame.getHeaders().equals("Back to main menu")) {
                snakeGame.setHeaders("Exit game");
                GUI.jFrame.repaint();
            }
        }
        // Enter key
        else if (e.getKeyCode() == 10) {
            switch(snakeGame.getHeaders()) {
                case "Restart game":
                    snakeGame.restart();
                    snakeGame.start();
                    break;
                case "Back to main menu":
                    backToMainMenu();
                    break;
                case "Exit game":
                    exit();
                    break;
            }
        }
    }

    private void checkGame(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            if (!snake.isSnakeDown()) goUp();
        }
        else if (e.getKeyCode() == 40) {
            if (!snake.isSnakeUp()) goDown();
        }
        else if (e.getKeyCode() == 37) {
            if (!snake.isSnakeRight()) goLeft();
        }
        else if (e.getKeyCode() == 39) {
            if (!snake.isSnakeLeft()) goRight();
        }
        else if (e.getKeyCode() == 27) {
            snakeGame.setPause(true);
        }
    }

    private void checkGameOver(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            if (snakeGame.getHeaders().equals("Exit game")) {
                snakeGame.setHeaders("Back to main menu");
                GUI.jFrame.repaint();
            }
            else if (snakeGame.getHeaders().equals("Back to main menu")) {
                snakeGame.setHeaders("Restart game");
                GUI.jFrame.repaint();
            }
        }
        else if (e.getKeyCode() == 40) {
            if (snakeGame.getHeaders().equals("Restart game")) {
                snakeGame.setHeaders("Back to main menu");
                GUI.jFrame.repaint();
            }
            else if (snakeGame.getHeaders().equals("Back to main menu")) {
                snakeGame.setHeaders("Exit game");
                GUI.jFrame.repaint();
            }
        }
        else if (e.getKeyCode() == 10) {
            switch(snakeGame.getHeaders()) {
                case "Restart game":
                    snakeGame.restart();
                    snakeGame.start();
                    break;
                case "Back to main menu":
                    backToMainMenu();
                    break;
                case "Exit game":
                    exit();
                    break;
            }
        }
    }

    // This method went wrong SO MANY TIMES.
    private void checkStart(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            if (snakeGame.getHeaders().equals("Exit game")) {
                snakeGame.setHeaders("Difficulty");
                GUI.jFrame.repaint();
            }
            else if (snakeGame.getHeaders().equals("Difficulty")) {
                snakeGame.setHeaders("Start game");
                GUI.jFrame.repaint();
            }
        }
        else if (e.getKeyCode() == 40) {
            if (snakeGame.getHeaders().equals("Start game")) {
                snakeGame.setHeaders("Difficulty");
                GUI.jFrame.repaint();
            }
            else if (snakeGame.getHeaders().equals("Difficulty")) {
                snakeGame.setHeaders("Exit game");
                GUI.jFrame.repaint();
            }
        }
        else if (e.getKeyCode() == 39) {
            if (snakeGame.getHeaders().equals("Difficulty") && snakeGame.getDifficultyLevel()!=2) {
                snakeGame.setDifficultyLevel(snakeGame.getDifficultyLevel()+1);
                GUI.jFrame.repaint();
            }
        }
        else if (e.getKeyCode() == 37) {
            if (snakeGame.getHeaders().equals("Difficulty") && snakeGame.getDifficultyLevel()!=0) {
                snakeGame.setDifficultyLevel(snakeGame.getDifficultyLevel()-1);
                GUI.jFrame.repaint();
            }
        }
        //Enter
        else if (e.getKeyCode() == 10) {
            switch(snakeGame.getHeaders()) {
                case "Start game":
                    snakeGame.start();
                    break;
                case "Exit game":
                    exit();
                    break;
            }
        }
    }

    private void drawPauseMenu(Graphics g) {
        setColor(Color.CYAN, g);
        fillRect(400, 400, 400, 400, g);
        setColor(Color.BLUE, g);
        setFont(fontHeader, g);
        drawString("Pause menu", 200, 100, g);
        setFont(regFont, g);
        drawString("Restart game", 100, 200, g);
        drawString("Back to main menu", 100, 250, g);
        drawString("Exit game", 100, 300, g);
        switch (snakeGame.getHeaders()) {
            case "Restart game":
                g.fillOval(70, 190, 10, 10);
                break;
            case "Back to main menu":
                g.fillOval(70, 240, 10, 10);
                break;
            case "Exit game":
                g.fillOval(70, 290, 10, 10);
                break;
        }
    }

    private void drawCurrentGame(Graphics g) {
        setColor(Color.YELLOW, g);
        fillRect(0, 0, 400, 400, g);
        setColor(Color.BLACK, g);
        fillRect(395, 0, 10, 400, g);
        fillRect(0, 0, 10, 400, g);
        fillRect(0, 0, 400, 10, g);
        fillRect(0, 350, 400, 10, g);
        Integer integer = 1;
        while (integer <= snake.getList().size()) {
            g.fillRect(snake.getList().get(integer - 1).x, snake.getList().get(integer - 1).y, 10, 10);
            integer++;
        }

        if (block.isBlockPlaced()) {
            Random rand = new Random();
            Float red = rand.nextFloat();
            Float green = rand.nextFloat();
            Float blue = rand.nextFloat();
            Color randomColor = new Color(red, green, blue);
            setColor(randomColor.darker(), g); // Again, so we can see it..
            fillRect(block.getBlockX(), block.getBlockY(), 10, 10, g);
        }
        setColor(Color.DARK_GRAY, g);
        setFont(fontScoreAndTime, g);
        drawString("Score: " + snakeGame.getScore(), 480, 200, g);
    }

    private void drawGameOver(Graphics g) {
        setColor(Color.DARK_GRAY, g);
        fillRect(400, 400, 400, 400, g);
        setColor(Color.RED, g);
        setFont(fontHeader, g);
        drawString("Your score: " + snakeGame.getScore(), 200, 100, g);
        setFont(regFont, g);
        drawString("Restart game", 100, 200, g);
        drawString("Exit game", 100, 300, g);
        drawString("Back to main menu", 100, 250, g);
        switch (snakeGame.getHeaders()) {
            case "Restart game":
                g.fillOval(70, 190, 10, 10);
                break;
            case "Exit game":
                g.fillOval(70, 290, 10, 10);
                break;
            case "Back to main menu":
                g.fillOval(70, 240, 10, 10);
                break;
        }
    }

    private void drawStartMenu(Graphics g) {
        Random rand = new Random();
        Float red = rand.nextFloat();
        Float green = rand.nextFloat();
        Float blue = rand.nextFloat();
        Color randomColor = new Color(red, green, blue);
        setColor(randomColor.darker(), g); // So I can bloody read it
        fillRect(0, 0, 400, 400, g);
        setColor(Color.WHITE, g);
        setFont(fontHeader, g);
        drawString("SnakeGame", 100, 100, g);
        setFont(regFont, g);
        drawString("Start game", 100, 200, g);
        drawString("Difficulty:", 100, 250, g);
        drawString("Easy", 205, 250, g);
        drawString("Medium", 251, 250, g);
        drawString("Hard", 305, 250, g);
        drawString("Exit game", 100, 300, g);
        switch (snakeGame.getHeaders()) {
            case "Start game":
                g.fillOval(70, 190, 10, 10);
                break;
            case "Difficulty":
                g.fillOval(70, 240, 10, 10);
                break;
            case "Exit game":
                g.fillOval(70, 290, 10, 10);
                break;
        }
        switch(snakeGame.getDifficultyLevel()) {
            case 0:
                g.drawRect(202, 239, 31, 14);
                break;
            case 1:
                g.drawRect(252, 239, 45, 14);
                break;
            case 2:
                g.drawRect(302, 239, 32, 14);
                break;
        }
    }
}
