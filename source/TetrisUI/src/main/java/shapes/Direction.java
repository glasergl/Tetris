package shapes;

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
	    throw new RuntimeException("No left found for " + this);
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
	    throw new RuntimeException("No right found for " + this);
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
	    // not reachable
	    throw new RuntimeException();
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
	    // not reachable
	    throw new RuntimeException();
	}
    }
}
