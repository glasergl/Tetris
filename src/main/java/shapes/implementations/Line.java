package shapes.implementations;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import shapes.Direction;
import shapes.TetrisLocation;
import shapes.TetrisShape;
import userInterface.TetrisGame;

public final class Line extends TetrisShape {

	public Line(TetrisGame whereThisShouldSpawn, Color ofThis, TetrisLocation start, Direction facingFirst) {
		super(whereThisShouldSpawn, ofThis, start, facingFirst, 0, 0);
		if (facingFirst == Direction.NORTH || facingFirst == Direction.SOUTH) {
			width = 1;
			height = 4;
		} else {
			width = 4;
			height = 1;
		}
	}

	@Override
	public Set<TetrisLocation> getCoveredLocations(TetrisLocation ifThisIsHere, Direction ifThisFacesThat) {
		final Set<TetrisLocation> thisWouldCover = new HashSet<>();
		if (ifThisFacesThat == Direction.NORTH || ifThisFacesThat == Direction.SOUTH) {
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 1, ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 2, ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow() + 3, ifThisIsHere.getColumn()));
		} else {
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn()));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 1));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 2));
			thisWouldCover.add(new TetrisLocation(ifThisIsHere.getRow(), ifThisIsHere.getColumn() + 3));
		}
		return thisWouldCover;
	}

}
