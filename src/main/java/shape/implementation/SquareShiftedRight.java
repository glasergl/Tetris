package shape.implementation;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import shape.Direction;
import shape.TetrisLocation;
import shape.TetrisShape;
import userInterface.TetrisGame;

/**
 * Tetris shape implementation which is shaped like a 2x2 square but the top 2
 * tiles are shifted right by 1.
 */
public final class SquareShiftedRight extends TetrisShape {
	public SquareShiftedRight(TetrisGame whereThisShouldSpawn, Color ofThis, TetrisLocation start,
			Direction facingFirst) {
		super(whereThisShouldSpawn, ofThis, start, facingFirst, 0, 0);
		if (facingFirst == Direction.NORTH || facingFirst == Direction.SOUTH) {
			width = 2;
			height = 3;
		} else {
			width = 3;
			height = 2;
		}
	}

	@Override
	public Set<TetrisLocation> getCoveredLocations(TetrisLocation ifThisIsHere, Direction ifThisFacesThat) {
		final Set<TetrisLocation> thisWouldCover = new HashSet<>();
		if (ifThisFacesThat == Direction.NORTH || ifThisFacesThat == Direction.SOUTH) {
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn() + 1));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 2, ifThisIsHere.getColumn() + 1));
		} else {
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 1));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 2));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn() + 1));
		}
		return thisWouldCover;
	}
}
