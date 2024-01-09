package shapes.implementations;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import shapes.Direction;
import shapes.TetrisLocation;
import shapes.TetrisShape;
import userInterface.TetrisGame;

/**
 * @author Gabriel Glaser
 */
public class Jay extends TetrisShape {

	public Jay(TetrisGame whereThisShouldSpawn, Color ofThis, TetrisLocation start, Direction facingFirst) {
		super(whereThisShouldSpawn, ofThis, start, facingFirst, 0, 0);
		if (facingFirst == Direction.NORTH || facingFirst == Direction.SOUTH) {
			width = 3;
			height = 2;
		} else {
			width = 2;
			height = 3;
		}
	}

	@Override
	public Set<TetrisLocation> getCoveredLocations(TetrisLocation ifThisIsHere, Direction ifThisFacesThat) {
		final Set<TetrisLocation> thisWouldCover = new HashSet<>();
		switch (ifThisFacesThat) {
		case NORTH:
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn() + 1));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn() + 2));
			break;
		case EAST:
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 1));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 2, ifThisIsHere.getColumn()));
			break;
		case SOUTH:
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 1));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 2));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn() + 2));
			break;
		case WEST:
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 1));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn() + 1));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 2, ifThisIsHere.getColumn() + 1));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 2, ifThisIsHere.getColumn()));
			break;
		}
		return thisWouldCover;
	}

}
