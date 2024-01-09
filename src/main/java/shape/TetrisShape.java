package shape;

import java.awt.Color;
import java.util.Set;
import javax.swing.Timer;

import customSwing.Colors;
import userInterface.TetrisGame;

/**
 * Parent-class of all TetrisShapes.
 * 
 * @author Gabriel Glaser
 */
public abstract class TetrisShape {
	protected final TetrisGame game;
	protected final Color color;
	protected final int gameSpeed;
	protected TetrisLocation currentLocation;
	protected Direction currentDirection;
	protected int width;
	protected int height;
	protected boolean isVisible = false;
	private Timer sinkTimer;

	protected TetrisShape(final TetrisGame game, final Color color, final TetrisLocation initialLocation,
			final Direction initialDirection, final int width, final int height) {
		this.game = game;
		this.color = color;
		this.currentLocation = initialLocation;
		this.gameSpeed = game.getGameSpeed();
		this.currentDirection = initialDirection;
		this.width = width;
		this.height = height;
	}

	/**
	 * This method calculates how the TetrisShape at a given TetrisLocation facing a
	 * given Direction would look like. This method defines how the TetrisShape
	 * looks, because it is used to fill in the respective tiles at the given
	 * location and direction.
	 * 
	 * @param location
	 * @param direction
	 * @return A Set of all TetrisLocation this would cover if it would be at
	 *         ifThisIsHere and facing ifThisFacesThat.
	 */
	public abstract Set<TetrisLocation> getCoveredLocations(final TetrisLocation location, final Direction direction);

	public Set<TetrisLocation> getCoveredLocations(final TetrisLocation location) {
		return getCoveredLocations(location, currentDirection);
	}

	public Set<TetrisLocation> getCoveredLocations(final Direction direction) {
		return getCoveredLocations(currentLocation, direction);
	}

	public Set<TetrisLocation> getCoveredLocations() {
		return getCoveredLocations(currentLocation, currentDirection);
	}

	public void sinkAsMuchAsPossible() {
		sinkTimer = new Timer(gameSpeed, null);
		sinkTimer.addActionListener((click) -> {
			if (canMoveDown()) {
				moveDown();
			} else {
				sinkTimer.stop();
				game.stopRound();
			}
		});
		sinkTimer.start();
	}

	public void pause() {
		if (sinkTimer != null) {
			sinkTimer.stop();
		}
	}

	public void resume() {
		if (sinkTimer != null) {
			sinkTimer.start();
		}
	}

	public void moveDown() {
		if (canMoveDown()) {
			final TetrisLocation down = new TetrisLocation(currentLocation.getRow() + 1, currentLocation.getColumn());
			repaint(down);
		}
	}

	public void moveLeft() {
		if (canMoveLeft()) {
			final TetrisLocation left = new TetrisLocation(currentLocation.getRow(), currentLocation.getColumn() - 1);
			repaint(left);
		}
	}

	public void moveRight() {
		if (canMoveRight()) {
			final TetrisLocation right = new TetrisLocation(currentLocation.getRow(), currentLocation.getColumn() + 1);
			repaint(right);
		}
	}

	public void turnLeft() {
		if (canTurnLeft()) {
			repaint(currentLocation, currentDirection.getLeft());
		}
	}

	public void turnRight() {
		if (canTurnRight()) {
			repaint(currentLocation, currentDirection.getRight());
		}
	}

	public boolean canMoveDown() {
		if (currentLocation.getRow() + height >= TetrisGame.NUMBER_OF_ROWS) {
			return false;
		} else {
			final TetrisLocation down = new TetrisLocation(currentLocation.getRow() + 1, currentLocation.getColumn());
			return canBeDrawn(down);
		}
	}

	public boolean canMoveLeft() {
		if (currentLocation.getColumn() - 1 < 0) {
			return false;
		} else {
			final TetrisLocation left = new TetrisLocation(currentLocation.getRow(), currentLocation.getColumn() - 1);
			return canBeDrawn(left);
		}
	}

	public boolean canMoveRight() {
		if (currentLocation.getColumn() + width >= TetrisGame.NUMBER_OF_COLUMNS) {
			return false;
		} else {
			final TetrisLocation right = new TetrisLocation(currentLocation.getRow(), currentLocation.getColumn() + 1);
			return canBeDrawn(right);
		}
	}

	public boolean canTurnLeft() {
		if (currentLocation.getColumn() + height - 1 >= TetrisGame.NUMBER_OF_COLUMNS
				|| currentLocation.getRow() + width - 1 >= TetrisGame.NUMBER_OF_ROWS) {
			return false;
		} else {
			return canBeDrawn(currentDirection.getLeft());
		}
	}

	public boolean canTurnRight() {
		if (currentLocation.getColumn() + height - 1 >= TetrisGame.NUMBER_OF_COLUMNS
				|| currentLocation.getRow() + width - 1 >= TetrisGame.NUMBER_OF_ROWS) {
			return false;
		} else {
			return canBeDrawn(currentDirection.getRight());
		}
	}

	public boolean canBeDrawn(final TetrisLocation ifThisWasHere, final Direction ifThisFacesThis) {
		return !game.isCovered(getCoveredLocations(ifThisWasHere, ifThisFacesThis), getCoveredLocations());
	}

	private void draw(final TetrisLocation toDrawThisAt, final Direction facing) throws CannotOverdrawException {
		final Set<TetrisLocation> covered = getCoveredLocations(toDrawThisAt, facing);
		if (!game.isCovered(covered)) {
			for (final TetrisLocation toCover : covered) {
				game.colorLabelWithColor(toCover, color);
			}
		} else {
			throw new CannotOverdrawException();
		}
	}

	private void repaint(final TetrisLocation toBeDrawnAt, final Direction facing) {
		try {
			delete();
			draw(toBeDrawnAt, facing);
			this.currentLocation = toBeDrawnAt;
			if (this.currentDirection.isOrthogonalTo(facing)) {
				swapWidthAndHeight();
			}
			this.currentDirection = facing;
		} catch (final CannotOverdrawException e) {
			e.printStackTrace();
		}
	}

	private void delete(final TetrisLocation toDeleteThisAt, final Direction facing) {
		final Set<TetrisLocation> covered = getCoveredLocations(toDeleteThisAt, facing);
		for (final TetrisLocation toDelete : covered) {
			game.colorLabelWithColor(toDelete, Colors.TETRIS_BACKGROUND);
		}
	}

	public boolean canBeDrawn() {
		return canBeDrawn(currentLocation, currentDirection);
	}

	public boolean canBeDrawn(final TetrisLocation ifThisIsHere) {
		return canBeDrawn(ifThisIsHere, currentDirection);
	}

	public boolean canBeDrawn(final Direction ifThisFacesThis) {
		return canBeDrawn(currentLocation, ifThisFacesThis);
	}

	private void repaint(final TetrisLocation toBeDrawnAt) {
		repaint(toBeDrawnAt, currentDirection);
	}

	public void draw() throws CannotOverdrawException {
		draw(currentLocation, currentDirection);
	}

	private void delete() {
		delete(currentLocation, currentDirection);
	}

	private void swapWidthAndHeight() {
		final int temp = width;
		width = height;
		height = temp;
	}

	public Color getColor() {
		return color;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Direction getDirection() {
		return currentDirection;
	}

	public void setLocation(final TetrisLocation toSetThis) {
		this.currentLocation = toSetThis;
	}
}
