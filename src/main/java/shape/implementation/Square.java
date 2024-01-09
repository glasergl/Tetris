package shape.implementation;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import shape.Direction;
import shape.TetrisLocation;
import shape.TetrisShape;
import userInterface.TetrisGame;

/**
 * Tetris shape implementation which is shaped like a 2x2 square.
 * 
 * @author Gabriel Glaser
 */
public final class Square extends TetrisShape {

	public Square(TetrisGame whereThisShouldSpawn, Color ofThis, TetrisLocation start, Direction facingFirst) {
		super(whereThisShouldSpawn, ofThis, start, facingFirst, 2, 2);
	}

	@Override
	public Set<TetrisLocation> getCoveredLocations(final TetrisLocation ifThisIsHere, final Direction ifThisFacesThat) {
		final Set<TetrisLocation> thisWouldCover = new HashSet<>();
		thisWouldCover.add(ifThisIsHere);
		thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn()));
		thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 1));
		thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn() + 1));
		return thisWouldCover;
	}

}
