package shape;

/**
 * Enum for celestial directions. Used to defined an orientation of Tetris
 * shapes such that they can be rotated.
 */
public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	public Direction getLeft() {
		switch (this) {
		case NORTH:
			return Direction.WEST;
		case EAST:
			return Direction.NORTH;
		case SOUTH:
			return Direction.EAST;
		case WEST:
			return Direction.SOUTH;
		default:
			throw new RuntimeException(); // unreachable
		}
	}

	public Direction getRight() {
		switch (this) {
		case NORTH:
			return Direction.EAST;
		case EAST:
			return Direction.SOUTH;
		case SOUTH:
			return Direction.WEST;
		case WEST:
			return Direction.NORTH;
		default:
			throw new RuntimeException(); // unreachable
		}
	}

	public boolean isOrthogonalTo(final Direction toTest) {
		switch (this) {
		case NORTH:
			return toTest == Direction.EAST || toTest == Direction.WEST;
		case EAST:
			return toTest == Direction.NORTH || toTest == Direction.SOUTH;
		case SOUTH:
			return toTest == Direction.EAST || toTest == Direction.WEST;
		case WEST:
			return toTest == Direction.NORTH || toTest == Direction.SOUTH;
		default:
			throw new RuntimeException(); // not reachable
		}
	}

	public static Direction getRandom() {
		final int random = (int) (Math.random() * 4);
		switch (random) {
		case 0:
			return NORTH;
		case 1:
			return EAST;
		case 2:
			return SOUTH;
		case 3:
			return WEST;
		default:
			throw new RuntimeException(); // not reachable
		}
	}
}
