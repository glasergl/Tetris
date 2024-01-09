package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import customSwing.Colors;

/**
 * Single tile for the whole Tetris grid. Some part of a Tetris shape may lie on
 * this tile or not.
 * 
 * @author Gabriel Glaser
 */
public class TetrisTile extends JLabel {
	private static final int SIZE_OF_SINGLE_TETRIS_TILE = 42;

	public TetrisTile() {
		super();
		setOpaque(true);
		setBackground(Colors.TETRIS_BACKGROUND);
		setPreferredSize(new Dimension(SIZE_OF_SINGLE_TETRIS_TILE, SIZE_OF_SINGLE_TETRIS_TILE));
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (newBackground.equals(Colors.TETRIS_BACKGROUND) || newBackground.equals(Colors.STANDARD_BACKGROUND)) {
			setBorder(new EmptyBorder(0, 0, 0, 0));
		} else {
			setBorder(new TetrisFieldBorder(newBackground, Colors.TETRIS_BACKGROUND, 8));
		}
	}
}
