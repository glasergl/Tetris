package shape;

import java.awt.Color;

import customSwing.Colors;
import shape.implementation.*;
import userInterface.TetrisGame;

/**
 * Class to create random TetrisShapes.
 */
public final class TetrisShapeFactory {
	public static TetrisShape getRandomTetrisShape(final TetrisGame ofNewTetrisShape) {
		final Color randomColor = getRandomColor();
		final Direction randomDirection = Direction.getRandom();
		return getRandomTetrisShapeWith(ofNewTetrisShape, randomColor, randomDirection);
	}

	public static Color getRandomColor() {
		final int random = (int) (Math.random() * 7);
		switch (random) {
		case 0:
			return Colors.BRIGHTER_BLUE;
		case 1:
			return Colors.PURPLE;
		case 2:
			return Colors.GREEN;
		case 3:
			return Colors.RED;
		case 4:
			return Colors.YELLOW;
		case 5:
			return Colors.BLUE;
		case 6:
			return Colors.ORANGE;
		default:
			throw new RuntimeException(); // not reachable
		}
	}

	public static TetrisShape getRandomTetrisShapeWith(final TetrisGame ofNewTetrisShape, final Color ofNewShape,
			final Direction facingFirst) {
		final int random = (int) (Math.random() * 7);
		if (random == 0) {
			final int randomColumn = getRandomValidColumnForWidth(2);
			final TetrisLocation start = new TetrisLocation(0, randomColumn);
			return new Square(ofNewTetrisShape, ofNewShape, start, facingFirst);
		} else if (random == 1) {
			final int randomColumn = getRandomValidColumnForWidth(
					facingFirst == Direction.NORTH || facingFirst == Direction.SOUTH ? 3 : 2);
			final TetrisLocation start = new TetrisLocation(0, randomColumn);
			return new HalfPlus(ofNewTetrisShape, ofNewShape, start, facingFirst);
		} else if (random == 2) {
			final int randomColumn = getRandomValidColumnForWidth(
					facingFirst == Direction.NORTH || facingFirst == Direction.SOUTH ? 1 : 4);
			final TetrisLocation start = new TetrisLocation(0, randomColumn);
			return new Line(ofNewTetrisShape, ofNewShape, start, facingFirst);
		} else if (random == 3) {
			final int randomColumn = getRandomValidColumnForWidth(
					facingFirst == Direction.NORTH || facingFirst == Direction.SOUTH ? 3 : 2);
			final TetrisLocation start = new TetrisLocation(0, randomColumn);
			return new MirroredL(ofNewTetrisShape, ofNewShape, start, facingFirst);
		} else if (random == 4) {
			final int randomColumn = getRandomValidColumnForWidth(
					facingFirst == Direction.NORTH || facingFirst == Direction.SOUTH ? 3 : 2);
			final TetrisLocation start = new TetrisLocation(0, randomColumn);
			return new L(ofNewTetrisShape, ofNewShape, start, facingFirst);
		} else if (random == 5) {
			final int randomColumn = getRandomValidColumnForWidth(
					facingFirst == Direction.NORTH || facingFirst == Direction.SOUTH ? 2 : 3);
			final TetrisLocation start = new TetrisLocation(0, randomColumn);
			return new SquareShiftedRight(ofNewTetrisShape, ofNewShape, start, facingFirst);
		} else {
			final int randomColumn = getRandomValidColumnForWidth(
					facingFirst == Direction.NORTH || facingFirst == Direction.SOUTH ? 2 : 3);
			final TetrisLocation start = new TetrisLocation(0, randomColumn);
			return new SquareShiftedLeft(ofNewTetrisShape, ofNewShape, start, facingFirst);
		}

	}

	private static int getRandomValidColumnForWidth(final int width) {
		return (int) (Math.random() * (TetrisGame.NUMBER_OF_COLUMNS - width));
	}
}
