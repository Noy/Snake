package me.noyhillel.snakegame.game.impl;

import lombok.Getter;
import lombok.Setter;
import me.noyhillel.snakegame.SnakeMain;
import me.noyhillel.snakegame.game.Snake;
import me.noyhillel.snakegame.game.SnakeBlocks;
import me.noyhillel.snakegame.gui.GUI;

import java.awt.*;

@Getter
@Setter
public final class SnakeGame implements Game {

    private boolean started = false;
    private boolean finished = false;
    private Integer score = 0;
    private boolean pause = false;
    private boolean restart = false;
    private boolean startMenu = true;
    private Integer difficultyLevel = 0;
    private Integer difficultyLeve2;
    private String headers = "Start game";

    private final Snake snake = new Snake();
    private SnakeGame snakeGame;
    private final SnakeBlocks block = new SnakeBlocks();

    /**
     * Overriding our method which is in our 'Runnable' interface (Extended by the 'Game' interface).
     */
    public void run() {
        block.setBlock(block);
        SnakeMain.getGui().setBlock(block);
        SnakeMain.getGui().setSnake(snake);
        snake.getList().add(new Rectangle(snake.getSnakeXCords(), snake.getSnakeYCords(), 10, 10));
        if (snakeGame.started) {
            do {
                if (!snakeGame.isPause()) {
                    goThroughWall();
                    spawnBlock();
                    touchSnakeBlock();
                    bashIntoSelf();
                    snake.getList().add(new Rectangle(snake.getSnakeXCords(), snake.getSnakeYCords(), 10, 10));
                    snake.getList().remove(0);
                }
                GUI.jFrame.repaint();
                try { Thread.sleep(snakeGame.getDifficultyLeve2()); }
                catch (InterruptedException e) { e.printStackTrace(); }
            } while (snakeGame.started);
        }
    }

    /**
     * Start method, called here and the GUI class.
     */
    public void start() {
        snakeGame.setStarted(true);
        snakeGame.setFinished(false);
        headers = "Restart game";
        Thread thread = new Thread(snakeGame);
        switch(snakeGame.getDifficultyLevel()) {
            case 0:
                snakeGame.setDifficultyLeve2(300);
                break;
            case 1:
                snakeGame.setDifficultyLeve2(200);
                break;
            case 2:
                snakeGame.setDifficultyLeve2(100);
                break;
        }
        thread.start();
    }

    /**
     * Finish Method.
     */
    public void finish() {
        snakeGame.setStarted(false);
        snakeGame.setFinished(true);
        headers = "Restart game";
        GUI.jFrame.repaint();
    }

    /**
     * Start method, called here and the GUI class.
     */
    public void restart() {
        snakeGame.setFinished(false);
        snakeGame.setScore(0);
        snakeGame.setPause(false);
        snake.setSnakeXCords(100);
        snake.setSnakeYCords(100);
        snake.setSnakeRight(true);
        snake.getList().clear();
    }

    /**
     * SpawnBlock method, notice the private access level
     */
    private void spawnBlock() {
        if (!block.isBlockPlaced()) {
            block.setBlockX((int)(35 + Math.random()*335));
            block.setBlockY((int)(35 + Math.random()*315));
            block.setBlockPlaced(true);
        }
    }

    /**
     * goThroughWall method, listens to when to snake goes through the wall.
     */
    private void goThroughWall() {
        if (snake.isSnakeUp()) {
            if (snake.getSnakeYCords() < 0) {
                snake.setSnakeYCords((int) (GUI.jFrame.getWidth() / 2.05));
            }
            else snake.setSnakeYCords(snake.getSnakeYCords() - 15);
        }
        else if (snake.isSnakeDown()) {
            if (snake.getSnakeYCords() > 335) {
                snake.setSnakeYCords(GUI.jFrame.getWidth() / 100);
            }
            else snake.setSnakeYCords(snake.getSnakeYCords() + 15);
        }
        else if (snake.isSnakeLeft()) {
            if (snake.getSnakeXCords() < 20) {
                snake.setSnakeXCords((int) (GUI.jFrame.getHeight() / 1.05));
            }
            else snake.setSnakeXCords(snake.getSnakeXCords() - 15);
        }
        else if (snake.isSnakeRight()) {
            if (snake.getSnakeXCords() > 370) {
                snake.setSnakeXCords(GUI.jFrame.getHeight() / 30);

            }
            else snake.setSnakeXCords(snake.getSnakeXCords() + 15);
        }
    }

    /**
     * @Deprecated
     * This used to be the snake hitting the wall, therefore finishing the game.
     *
    private void bashIntoWall() {
    if (snake.isSnakeUp()) {
    if (snake.getSnakeYCords() < 20) finish();
    else snake.setSnakeYCords(snake.getSnakeYCords() - 15);
    }
    else if (snake.isSnakeDown()) {
    if (snake.getSnakeYCords() > 335) finish();
    else snake.setSnakeYCords(snake.getSnakeYCords() + 15);
    }
    else if (snake.isSnakeLeft()) {
    if (snake.getSnakeXCords() < 20) finish();
    else snake.setSnakeXCords(snake.getSnakeXCords() - 15);
    }
    else if (snake.isSnakeRight()) {
    if (snake.getSnakeXCords() > 370) finish();
    else snake.setSnakeXCords(snake.getSnakeXCords() + 15);
    }
    }
     */

    /**
     * If snake bashes into itself.
     */
    private void bashIntoSelf() {
        for (int i = 1; i < snake.getList().size() - 1; i++) {
            if (i + 1 < snake.getList().size()) {
                if (snake.getList().get(0).intersects(snake.getList().get(i + 1))) finish();
            }
        }
    }

    /**
     * When the Snake actually touches the block.
     */
    private void touchSnakeBlock() {
        if (Math.abs(block.getBlockX() - snake.getSnakeXCords()) <= 8 && Math.abs(block.getBlockY() - snake.getSnakeYCords()) <= 8) {
            block.setBlockPlaced(false);
            snake.getList().add(new Rectangle(snake.getSnakeXCords(), snake.getSnakeYCords(), 10, 10)); // Places the block on the snake
            Integer score = (5);
            snakeGame.score += score;
        }
    }
}
