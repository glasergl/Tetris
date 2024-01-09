package userInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import customSwing.Colors;

/**
 * Frame which contains all components to play Tetris.
 *
 * @author Gabriel Glaser
 */
public final class TetrisFrame extends JFrame {
	private static final int COMPONENT_MARGIN = 0;

	private final StatTracker stats = new StatTracker();
	private final TetrisGame game = new TetrisGame(this);
	private final NextShapes nextShapesVisualization = new NextShapes(game);
	private final Header header = new Header(game);

	public TetrisFrame() {
		super("Tetris");
		setup();
		game.requestFocus();
		start();
	}

	public void start() {
		game.start(nextShapesVisualization);
	}

	public void stop() {
		final int option = JOptionPane.showOptionDialog(this, "Retry?", "Game Over", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (option == JOptionPane.YES_OPTION) {
			game.reset();
			game.start(nextShapesVisualization);
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
		setIconImage(Images.TETRIS_ICON);
		setLayout(new BorderLayout(COMPONENT_MARGIN, COMPONENT_MARGIN));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		designTetrisComponents();
		addAllTetrisComponents();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addAllTetrisComponents() {
		final Container contentPane = getContentPane();
		contentPane.add(header, BorderLayout.NORTH);
		contentPane.add(stats, BorderLayout.WEST);
		contentPane.add(game, BorderLayout.CENTER);
		contentPane.add(nextShapesVisualization, BorderLayout.EAST);
	}

	private void designTetrisComponents() {
		header.setBackground(Colors.STANDARD_BACKGROUND_1);
		stats.setBackground(Colors.STANDARD_BACKGROUND);
		nextShapesVisualization.setBackground(Colors.STANDARD_BACKGROUND);
	}
}
