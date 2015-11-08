package me.noyhillel.snakegame.game;

import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Thank god for lombok
 */
@Data
public final class Snake {

    /**
     * Read lombok tutorial in SnakeBlocks class
     */
    private Integer snakeXCords = 160; // defaults to the middle
    private Integer snakeYCords = 160; // defaults to the middle
    private boolean snakeUp = false;
    private boolean snakeDown = false;
    private boolean snakeLeft = false;
    private boolean snakeRight = true;
    private List<Rectangle> list = new ArrayList<>();
}