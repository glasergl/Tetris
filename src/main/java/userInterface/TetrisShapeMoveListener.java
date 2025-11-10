package userInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import shape.TetrisShape;

/**
 * KeyListener implementation which allows controlling the given TetrisShape
 * with respect to position and orientation.
 */
public final class TetrisShapeMoveListener implements KeyListener {
	private final TetrisShape tetrisShapeToControl;
	private final int moveLeftKeyCode = KeyEvent.VK_A;
	private final int moveRightKeyCode = KeyEvent.VK_D;
	private final int moveDownKeyCode = KeyEvent.VK_S;
	private final int turnLeftKeyCode = KeyEvent.VK_LEFT;
	private final int turnRightKeyCode = KeyEvent.VK_RIGHT;

	public TetrisShapeMoveListener(final TetrisShape tetrisShapeToControl) {
		this.tetrisShapeToControl = tetrisShapeToControl;
	}

	@Override
	public void keyPressed(KeyEvent click) {
		switch (click.getKeyCode()) {
		case moveLeftKeyCode:
			tetrisShapeToControl.moveLeft();
			break;
		case moveRightKeyCode:
			tetrisShapeToControl.moveRight();
			break;
		case moveDownKeyCode:
			tetrisShapeToControl.moveDown();
			break;
		case turnLeftKeyCode:
			tetrisShapeToControl.turnLeft();
			break;
		case turnRightKeyCode:
			tetrisShapeToControl.turnRight();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
