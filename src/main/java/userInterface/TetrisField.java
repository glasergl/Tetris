package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import standardComponents.Colors;

public class TetrisField extends JLabel {

	private static final int SIZE_OF_LABELS = 42;

	public TetrisField() {
		makeStandardFormat();
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (newBackground.equals(Colors.TETRIS_BACKGROUND) || newBackground.equals(Colors.STANDARD_BACKGROUND)) {
			setBorder(null);
		} else {
			setBorder(new TetrisFieldBorder(newBackground, Colors.TETRIS_BACKGROUND, 8));
		}
	}

	public void makeStandardFormat() {
		setOpaque(true);
		setBackground(Colors.TETRIS_BACKGROUND);
		setPreferredSize(new Dimension(SIZE_OF_LABELS, SIZE_OF_LABELS));
	}

}
