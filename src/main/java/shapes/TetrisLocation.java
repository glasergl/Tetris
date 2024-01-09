package shapes;

import java.util.Objects;
import userInterface.TetrisGame;

/**
 * @author Gabriel Glaser
 */
public final class TetrisLocation {

	private final int row;

	private final int column;

	public TetrisLocation(final int row, final int column) {
		if (column < 0 || column >= TetrisGame.NUMBER_OF_COLUMNS || row < 0 || row >= TetrisGame.NUMBER_OF_ROWS) {
			throw new IllegalArgumentException("Location (" + row + ", " + column + ") out of range");
		}
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public boolean equals(final Object toCompare) {
		if (!(toCompare instanceof TetrisLocation)) {
			return false;
		} else {
			final TetrisLocation locationToCompare = (TetrisLocation) toCompare;
			return row == locationToCompare.row && column == locationToCompare.column;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}

}
