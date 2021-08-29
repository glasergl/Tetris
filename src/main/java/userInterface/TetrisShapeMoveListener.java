package userInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import shapes.TetrisShape;

public class TetrisShapeMoveListener implements KeyListener {

    private final TetrisShape toListenTo;

    private final int moveLeft = KeyEvent.VK_A;

    private final int moveRight = KeyEvent.VK_D;

    private final int moveDown = KeyEvent.VK_S;

    private final int turnLeft = KeyEvent.VK_LEFT;

    private final int turnRight = KeyEvent.VK_RIGHT;

    public TetrisShapeMoveListener(final TetrisShape toListenTo) {
	this.toListenTo = toListenTo;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent click) {
	switch (click.getKeyCode()) {
	case moveLeft:
	    toListenTo.moveLeft();
	    break;
	case moveRight:
	    toListenTo.moveRight();
	    break;
	case moveDown:
	    toListenTo.moveDown();
	    break;
	case turnLeft:
	    toListenTo.turnLeft();
	    break;
	case turnRight:
	    toListenTo.turnRight();
	    break;
	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
