package main;

import javax.swing.SwingUtilities;
import userInterface.TetrisWindow;

/**
 * Entry class for Tetris.
 *
 * @author Gabriel Glaser
 */
public final class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new TetrisWindow();
		});
	}

}
