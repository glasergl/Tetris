package main;

import javax.swing.SwingUtilities;
import userInterface.TetrisFrame;

/**
 * Entry class for Tetris.
 *
 * @author Gabriel Glaser
 */
public final class Main {
	/**
	 * Opens a frame with the game Tetris and starts the game.
	 * 
	 * @param args - unused
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new TetrisFrame();
		});
	}
}
