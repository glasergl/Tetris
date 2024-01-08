package userInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import standardComponents.Colors;

/**
 * Frame to contain all components to play Tetris.
 *
 * @author Gabriel Glaser
 * @version 27.08.2021
 */
public final class TetrisWindow extends JFrame {

	private static final String ICON_PATH = ClassLoader.getSystemResource("Icon.png").getPath();
	private static final int SEPERATOR_THICKNESS = 3;
	private final StatTracker stats = new StatTracker();
	private final TetrisGame game = new TetrisGame(this);
	private final NextShapes toVisualizeTheNextShapes = new NextShapes(game);
	private final Header header = new Header(game);

	public TetrisWindow() {
		super("Tetris");
		setup();
		game.requestFocus();
		start();
	}

	public void start() {
		game.start(toVisualizeTheNextShapes);
	}

	public void stop() {
		final int option = JOptionPane.showOptionDialog(this, "Retry?", "Game Over", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (option == JOptionPane.YES_OPTION) {
			game.reset();
			game.start(toVisualizeTheNextShapes);
		} else {
			dispose();
		}
	}

	public void updateStats(final int newScore, final int numberOfShapesFallenDown, final int gameSpeed) {
		stats.setScore(newScore);
		stats.setNumberOfShapesFallenDown(numberOfShapesFallenDown);
		stats.setGameSpeed(gameSpeed);
	}

	private void setup() {
		setLayout(new BorderLayout(SEPERATOR_THICKNESS, SEPERATOR_THICKNESS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		designTetrisComponents();
		addAllTetrisComponents();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addAllTetrisComponents() {
		final Container ofThis = getContentPane();
		ofThis.add(header, BorderLayout.NORTH);
		ofThis.add(stats, BorderLayout.WEST);
		ofThis.add(game, BorderLayout.CENTER);
		ofThis.add(toVisualizeTheNextShapes, BorderLayout.EAST);
	}

	private void designTetrisComponents() {
		header.setBackground(Colors.STANDARD_BACKGROUND_1);
		stats.setBackground(Colors.STANDARD_BACKGROUND);
		toVisualizeTheNextShapes.setBackground(Colors.STANDARD_BACKGROUND);
	}
}
