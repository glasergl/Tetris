package userInterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JPanel;

import customSwing.Colors;
import shape.CannotOverdrawException;
import shape.TetrisLocation;
import shape.TetrisShape;

/**
 * Class which contains the Tetris field on which the game is played on.
 * Furthermore, implements the functionality of the goal of the game which is
 * having a full row filled with tiles of any color. If this happens, the row is
 * removed and everything above falls down as much as possible.
 * 
 * @author Gabriel Glaser
 */
public final class TetrisGame extends JPanel {
	public static final int NUMBER_OF_ROWS = 20;
	public static final int NUMBER_OF_COLUMNS = 10;

	private final TetrisFrame ofThisGame;
	private final HashMap<TetrisLocation, TetrisTile> labels = new HashMap<>();
	private final int initialGameSpeed = 500;

	private TetrisShape currentNext;
	private TetrisShapeMoveListener ofCurrentNext;
	private NextShapes toVisualizeTheNextShapes;
	private int numberOfDeletedRows = 0;
	private int gameSpeed = initialGameSpeed;
	private int numberOfShapesFallenDown = 0;

	public TetrisGame(final TetrisFrame ofThisGame) {
		super();
		this.ofThisGame = ofThisGame;
		setBackground(Colors.TETRIS_BACKGROUND);
		setFocusable(true);
		setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
		createTetrisFields();
	}

	/**
	 * Starts playing the game with the given next shapes. The given instance will
	 * provide any amount of next instances but stores five simultaneously.
	 * 
	 * @param toVisualizeTheNextShapes
	 */
	public void start(final NextShapes toVisualizeTheNextShapes) {
		this.toVisualizeTheNextShapes = toVisualizeTheNextShapes;
		playRound();
	}

	/**
	 * Playing a single round which means a single shape falls down which the user
	 * can control.
	 */
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

	/**
	 * Stops the round and increases the game speed (by decreasing the time between
	 * every fall down step).
	 */
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
		gameSpeed = initialGameSpeed;
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

	/**
	 * @param toTestIfIsCovered
	 * @return Whether any of the given locations are covered by any tile of any
	 *         Tetris shape which is defined that the color is unequal to the
	 *         background.
	 */
	public boolean isCovered(final Set<TetrisLocation> toTestIfIsCovered) {
		for (final TetrisLocation toTestIfIsFree : toTestIfIsCovered) {
			if (!hasColor(toTestIfIsFree, Colors.TETRIS_BACKGROUND)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param toTestIfIsCovered
	 * @param exceptThis
	 * @return Whether any of the given locations are covered by any tile of any
	 *         Tetris shape but ignoring the other set of locations.
	 */
	public boolean isCovered(final Set<TetrisLocation> toTestIfIsCovered, final Set<TetrisLocation> exceptThis) {
		for (final TetrisLocation toTestIfIsFree : toTestIfIsCovered) {
			if (!hasColor(toTestIfIsFree, Colors.TETRIS_BACKGROUND) && !exceptThis.contains(toTestIfIsFree)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasColor(final TetrisLocation toTest, final Color toTestIfHasColor) {
		final TetrisTile toTestForColor = labels.get(toTest);
		return toTestForColor.getBackground() == toTestIfHasColor;
	}

	public void colorLabelWithColor(final TetrisLocation toGetLabelOf, final Color forLabel) throws RuntimeException {
		final TetrisTile toColor = labels.get(toGetLabelOf);
		toColor.setBackground(forLabel);
	}

	/**
	 * Removes any vertical line on the game field which has a tile from any shape
	 * of any color. After each row removal, everything above falls down.
	 */
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

	/**
	 * Creates the rectangular game field by instantiating the required number of
	 * tiles and mapping them to their respective location.
	 */
	private void createTetrisFields() {
		for (int currentRow = 0; currentRow < NUMBER_OF_ROWS; currentRow++) {
			for (int currentColumn = 0; currentColumn < NUMBER_OF_COLUMNS; currentColumn++) {
				final TetrisTile label = new TetrisTile();
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
