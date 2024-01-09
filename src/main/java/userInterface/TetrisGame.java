package userInterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JPanel;
import shapes.CannotOverdrawException;
import shapes.TetrisLocation;
import shapes.TetrisShape;
import standardComponents.Colors;

/**
 * @author Gabriel Glaser
 */
public final class TetrisGame extends JPanel {

	public static final int NUMBER_OF_ROWS = 20;
	public static final int NUMBER_OF_COLUMNS = 10;

	private final TetrisWindow ofThisGame;
	private final HashMap<TetrisLocation, TetrisField> labels = new HashMap<>();

	private TetrisShape currentNext;
	private TetrisShapeMoveListener ofCurrentNext;
	private NextShapes toVisualizeTheNextShapes;
	private int numberOfDeletedRows = 0;
	private int gameSpeed = 500;
	private int numberOfShapesFallenDown = 0;

	public TetrisGame(final TetrisWindow ofThisGame) {
		super();
		this.ofThisGame = ofThisGame;
		setBackground(Colors.TETRIS_BACKGROUND);
		setFocusable(true);
		setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
		createTetrisFields();
	}

	public void start(final NextShapes toVisualizeTheNextShapes) {
		this.toVisualizeTheNextShapes = toVisualizeTheNextShapes;
		playRound();
	}

	public void playRound() {
		currentNext = toVisualizeTheNextShapes.removeNext();
		ofCurrentNext = new TetrisShapeMoveListener(currentNext);
		startRound();
	}

	public void startRound() {
		try {
			currentNext.draw();
			addKeyListener(ofCurrentNext);
			currentNext.sinkAsMuchAsPossible();
			numberOfShapesFallenDown++;
			ofThisGame.updateStats(numberOfDeletedRows, numberOfShapesFallenDown, gameSpeed);
		} catch (final CannotOverdrawException e) {
			ofThisGame.stop();
		}
	}

	public void stopRound() {
		removeKeyListener(ofCurrentNext);
		removeFullLines();
		if (gameSpeed - 5 >= 200) {
			gameSpeed -= 5;
		}
		playRound();
	}

	public void reset() {
		for (final JLabel toReset : labels.values()) {
			toReset.setBackground(Colors.TETRIS_BACKGROUND);
			toReset.setBorder(null);
		}
		numberOfDeletedRows = 0;
		gameSpeed = 500;
		numberOfShapesFallenDown = 0;
	}

	public void restart() {
		currentNext.pause();
		reset();
		removeKeyListener(ofCurrentNext);
		start(toVisualizeTheNextShapes);
	}

	public void pause() {
		currentNext.pause();
		removeKeyListener(ofCurrentNext);
	}

	public void resume() {
		currentNext.resume();
		addKeyListener(ofCurrentNext);
	}

	public boolean isCovered(final Set<TetrisLocation> toTestIfIsCovered) {
		for (final TetrisLocation toTestIfIsFree : toTestIfIsCovered) {
			if (!hasColor(toTestIfIsFree, Colors.TETRIS_BACKGROUND)) {
				return true;
			}
		}
		return false;
	}

	public boolean isCovered(final Set<TetrisLocation> toTestIfIsCovered, final Set<TetrisLocation> exceptThis) {
		for (final TetrisLocation toTestIfIsFree : toTestIfIsCovered) {
			if (!hasColor(toTestIfIsFree, Colors.TETRIS_BACKGROUND) && !exceptThis.contains(toTestIfIsFree)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasColor(final TetrisLocation toTest, final Color toTestIfHasColor) {
		final TetrisField toTestForColor = labels.get(toTest);
		return toTestForColor.getBackground() == toTestIfHasColor;
	}

	public void colorLabelWithColor(final TetrisLocation toGetLabelOf, final Color forLabel) throws RuntimeException {
		final TetrisField toColor = labels.get(toGetLabelOf);
		toColor.setBackground(forLabel);
	}

	private void removeFullLines() {
		for (int currentRow = 0; currentRow < NUMBER_OF_ROWS; currentRow++) {
			if (isFullRow(currentRow)) {
				removeRow(currentRow);
				moveEverythingAboveRowDown(currentRow);
			}
		}
	}

	private boolean isFullRow(final int row) {
		assert 0 <= row && row < NUMBER_OF_ROWS;

		for (int currentColumn = 0; currentColumn < NUMBER_OF_COLUMNS; currentColumn++) {
			final JLabel current = labels.get(new TetrisLocation(row, currentColumn));
			if (current.getBackground().equals(Colors.TETRIS_BACKGROUND)) {
				return false;
			}
		}
		return true;
	}

	private void removeRow(final int row) {
		assert 0 <= row && row < NUMBER_OF_ROWS;

		for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
			final JLabel current = labels.get(new TetrisLocation(row, i));
			current.setBackground(Colors.TETRIS_BACKGROUND);
		}
		numberOfDeletedRows++;
	}

	private void moveEverythingAboveRowDown(final int row) {
		for (int currentRow = row - 1; currentRow >= 0; currentRow--) {
			for (int currentColumn = 0; currentColumn < NUMBER_OF_COLUMNS; currentColumn++) {
				final JLabel current = labels.get(new TetrisLocation(currentRow, currentColumn));
				final JLabel under = labels.get(new TetrisLocation(currentRow + 1, currentColumn));
				final Color ofCurrent = current.getBackground();
				current.setBackground(Colors.TETRIS_BACKGROUND);
				under.setBackground(ofCurrent);
			}
		}
	}

	private void createTetrisFields() {
		for (int currentRow = 0; currentRow < NUMBER_OF_ROWS; currentRow++) {
			for (int currentColumn = 0; currentColumn < NUMBER_OF_COLUMNS; currentColumn++) {
				final TetrisField label = new TetrisField();
				final TetrisLocation ofLabel = new TetrisLocation(currentRow, currentColumn);
				labels.put(ofLabel, label);
				add(label);
			}
		}

	}

	public int getGameSpeed() {
		return gameSpeed;
	}

	public int getNumberOfDeletedRows() {
		return numberOfDeletedRows;
	}

}
