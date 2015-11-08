package me.noyhillel.snakegame;

import lombok.Getter;
import lombok.extern.java.Log;
import me.noyhillel.snakegame.gui.GUI;

/**
 * Snake Game written in Java by Noy Hillel
 * Hope you enjoy!
 */

@Log
public final class SnakeMain {

    // Creating an instance of the GUI class.
    @Getter private static GUI gui = new GUI();

    /**
     * Main method
     * @param args ignored
     */
    public static void main(String[] args) {
        log.info("Loading...");
        // Try block
        try {
            gui.playGame(); // Call our play game method.
            print("Game working, enjoy.");
            print("Hope you enjoy the classic all time favorite snake game! :D");
        } catch (Exception e) { // Something went wrong?
            e.printStackTrace(); // Show why
            print("Could not load the SnakeGame! \nSee console log errors for info!");
        } finally { // Finally prints out every time, even if there is an exception.
            print("Made by Noy Hillel.");
        }
    }

    // Print method, basically a short way of calling our 'System.out.println();'.
    // Generics ftw.
    @SafeVarargs
    public static <T> void print(T... args) {
        for (T item : args) {
            System.out.println(item);
        }
    }
}