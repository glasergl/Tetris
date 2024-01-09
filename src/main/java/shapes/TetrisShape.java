package shapes;

import java.awt.Color;
import java.util.Set;
import javax.swing.Timer;
import standardComponents.Colors;
import userInterface.TetrisGame;

/**
 * Parent-class of all TetrisShapes.
 * 
 * @author Gabriel Glaser
 */
public abstract class TetrisShape {

	protected final TetrisGame whereThisShouldSpawn;
	protected final Color ofThis;
	protected final int gameSpeed;
	protected TetrisLocation current;
	protected Direction facing;
	protected int width;
	protected int height;
	protected boolean isVisible = false;
	private Timer ofSinking;

	protected TetrisShape(final TetrisGame whereThisShouldSpawn, final Color ofThis, final TetrisLocation start,
			final Direction facingFirst, final int width, final int height) {
		this.whereThisShouldSpawn = whereThisShouldSpawn;
		this.ofThis = ofThis;
		this.current = start;
		this.gameSpeed = whereThisShouldSpawn.getGameSpeed();
		this.facing = facingFirst;
		this.width = width;
		this.height = height;
	}

	/**
	 * This method calculates how the TetrisShape at a given TetrisLocation facing a
	 * given Direction would look like. This method clearly defines the TetrisShape.
	 * 
	 * @param ifThisIsHere
	 * @param ifThisFacesThat
	 * @return A Set of all TetrisLocation this would cover if it would be at
	 *         ifThisIsHere and facing ifThisFacesThat.
	 */
	public abstract Set<TetrisLocation> getCoveredLocations(final TetrisLocation ifThisIsHere,
			final Direction ifThisFacesThat);

	public Set<TetrisLocation> getCoveredLocations(final TetrisLocation ifThisIsHere) {
		return getCoveredLocations(ifThisIsHere, facing);
	}

	public Set<TetrisLocation> getCoveredLocations(final Direction ifThisFacesThat) {
		return getCoveredLocations(current, ifThisFacesThat);
	}

	public Set<TetrisLocation> getCoveredLocations() {
		return getCoveredLocations(current, facing);
	}

	public void sinkAsMuchAsPossible() {
		ofSinking = new Timer(gameSpeed, null);
		ofSinking.addActionListener((click) -> {
			if (canMoveDown()) {
				moveDown();
			} else {
				ofSinking.stop();
				whereThisShouldSpawn.stopRound();
			}
		});
		ofSinking.start();
	}

	public void pause() {
		if (ofSinking != null) {
			ofSinking.stop();
		}
	}

	public void resume() {
		if (ofSinking != null) {
			ofSinking.start();
		}
	}

	public synchronized void moveDown() {
		if (canMoveDown()) {
			final TetrisLocation down = new TetrisLocation(current.getRow() + 1, current.getColumn());
			repaint(down);
		}
	}

	public synchronized void moveLeft() {
		if (canMoveLeft()) {
			final TetrisLocation left = new TetrisLocation(current.getRow(), current.getColumn() - 1);
			repaint(left);
		}
	}

	public synchronized void moveRight() {
		if (canMoveRight()) {
			final TetrisLocation right = new TetrisLocation(current.getRow(), current.getColumn() + 1);
			repaint(right);
		}
	}

	public synchronized void turnLeft() {
		if (canTurnLeft()) {
			repaint(current, facing.getLeft());
		}
	}

	public synchronized void turnRight() {
		if (canTurnRight()) {
			repaint(current, facing.getRight());
		}
	}

	public boolean canMoveDown() {
		if (current.getRow() + height >= TetrisGame.NUMBER_OF_ROWS) {
			return false;
		} else {
			final TetrisLocation down = new TetrisLocation(current.getRow() + 1, current.getColumn());
			return canBeDrawn(down);
		}
	}

	public boolean canMoveLeft() {
		if (current.getColumn() - 1 < 0) {
			return false;
		} else {
			final TetrisLocation left = new TetrisLocation(current.getRow(), current.getColumn() - 1);
			return canBeDrawn(left);
		}
	}

	public boolean canMoveRight() {
		if (current.getColumn() + width >= TetrisGame.NUMBER_OF_COLUMNS) {
			return false;
		} else {
			final TetrisLocation right = new TetrisLocation(current.getRow(), current.getColumn() + 1);
			return canBeDrawn(right);
		}
	}

	public boolean canTurnLeft() {
		if (current.getColumn() + height - 1 >= TetrisGame.NUMBER_OF_COLUMNS
				|| current.getRow() + width - 1 >= TetrisGame.NUMBER_OF_ROWS) {
			return false;
		} else {
			return canBeDrawn(facing.getLeft());
		}
	}

	public boolean canTurnRight() {
		if (current.getColumn() + height - 1 >= TetrisGame.NUMBER_OF_COLUMNS
				|| current.getRow() + width - 1 >= TetrisGame.NUMBER_OF_ROWS) {
			return false;
		} else {
			return canBeDrawn(facing.getRight());
		}
	}

	public boolean canBeDrawn(final TetrisLocation ifThisWasHere, final Direction ifThisFacesThis) {
		return !whereThisShouldSpawn.isCovered(getCoveredLocations(ifThisWasHere, ifThisFacesThis),
				getCoveredLocations());
	}

	private void draw(final TetrisLocation toDrawThisAt, final Direction facing) throws CannotOverdrawException {
		final Set<TetrisLocation> covered = getCoveredLocations(toDrawThisAt, facing);
		if (!whereThisShouldSpawn.isCovered(covered)) {
			for (final TetrisLocation toCover : covered) {
				whereThisShouldSpawn.colorLabelWithColor(toCover, ofThis);
			}
		} else {
			throw new CannotOverdrawException();
		}
	}

	private void repaint(final TetrisLocation toBeDrawnAt, final Direction facing) {
		try {
			delete();
			draw(toBeDrawnAt, facing);
			this.current = toBeDrawnAt;
			if (this.facing.isOrthogonalTo(facing)) {
				swapWidthAndHeight();
			}
			this.facing = facing;
		} catch (final CannotOverdrawException e) {
			e.printStackTrace();
		}
	}

	private void delete(final TetrisLocation toDeleteThisAt, final Direction facing) {
		final Set<TetrisLocation> covered = getCoveredLocations(toDeleteThisAt, facing);
		for (final TetrisLocation toDelete : covered) {
			whereThisShouldSpawn.colorLabelWithColor(toDelete, Colors.TETRIS_BACKGROUND);
		}
	}

	public boolean canBeDrawn() {
		return canBeDrawn(current, facing);
	}

	public boolean canBeDrawn(final TetrisLocation ifThisIsHere) {
		return canBeDrawn(ifThisIsHere, facing);
	}

	public boolean canBeDrawn(final Direction ifThisFacesThis) {
		return canBeDrawn(current, ifThisFacesThis);
	}

	private void repaint(final TetrisLocation toBeDrawnAt) {
		repaint(toBeDrawnAt, facing);
	}

	public void draw() throws CannotOverdrawException {
		draw(current, facing);
	}

	private void delete() {
		delete(current, facing);
	}

	private void swapWidthAndHeight() {
		final int temp = width;
		width = height;
		height = temp;
	}

	public Color getColor() {
		return ofThis;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Direction getDirection() {
		return facing;
	}

	public void setLocation(final TetrisLocation toSetThis) {
		this.current = toSetThis;
	}

}
