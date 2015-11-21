package me.noyhillel.snakegame.game;

import lombok.Data;

/**
 * Thank god for lombok
 */

@Data
public final class SnakeBlocks {

    /**
     * The reason I do not have to create a constructor, getters and setters is because of the Lombok dependency.
     * It's a lot easier to create data classes like this using lombok because we can actually determine that this is a data class instead of having boilerplate code
     * E.g.
     *
     *  public Integer getBlockX() {
     *      return this.blockX;
     *  }
     *
     *  or
     *
     *  public void setBlockX(Integer x) {
     *      this.block = x;
     *  }
     *
     * These Getters and Setters are automatically created by our 'Data' annotation :D
     *
     * It also generates and equals(), hashCode(), and a toString() method.
     *
     * Same concept goes for all other methods.
     *
     * This is just a little bit of what lombok can do.
     *
     * Read more here: http://projectlombok.org/
     *
     */
    private Integer blockX = 0;
    private Integer blockY = 0;
    private boolean blockPlaced = false;
    private SnakeBlocks block;
}
