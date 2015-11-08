package me.noyhillel.snakegame.game.impl;

/**
 * <p/>
 * Latest Change: 12/09/2014.
 * <p/>
 *
 * @author Noy
 * @since 12/09/2014.
 */
// Just incase I ever wanted to create more games...
interface Game extends Runnable {
    void start();
    void finish();
    void restart();
}