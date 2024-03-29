package userInterface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import customSwing.Colors;
import customSwing.CustomSwing;
import shape.TetrisLocation;
import shape.TetrisShape;
import shape.TetrisShapeFactory;

/**
 * This class represents numberOfNextShapes TetrisShape's. If a TetrisShape is
 * removed, a random TetrisShape is added.
 * 
 * @author Gabriel Glaser
 */
public final class NextShapes extends JPanel {
	private static final int DISTANCE_BETWEEN_TITLE_AND_NEXT_SHAPES = 20;
	private static final int NUMBER_OF_NEXT_SHAPES = 5;

	private final TetrisGame game;
	private final Queue<TetrisShapeContainer> allNext = new ArrayDeque<>(NUMBER_OF_NEXT_SHAPES);
	private final JPanel visualisedNextShapes = new JPanel();
	private final JLabel title = CustomSwing.getJLabel("Next:");

	public NextShapes(final TetrisGame game) {
		super();
		this.game = game;
		setup();
	}

	private void setup() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		visualisedNextShapes.setLayout(new BoxLayout(visualisedNextShapes, BoxLayout.Y_AXIS));
		visualisedNextShapes.add(title);
		visualisedNextShapes.add(Box.createVerticalStrut(DISTANCE_BETWEEN_TITLE_AND_NEXT_SHAPES));
		for (int i = 0; i < NUMBER_OF_NEXT_SHAPES; i++) {
			addRandomTetrisShape();
		}
		add(visualisedNextShapes);
	}

	/**
	 * @return The TetrisShape with the lowest rank.
	 */
	public TetrisShape removeNext() {
		final TetrisShapeContainer next = allNext.remove();
		visualisedNextShapes.remove(next);
		updateRanks();
		addRandomTetrisShape();
		visualisedNextShapes.repaint();
		return next.toContain;
	}

	private void addRandomTetrisShape() {
		final TetrisShape random = TetrisShapeFactory.getRandomTetrisShape(game);
		final TetrisShapeContainer ofRandom = new TetrisShapeContainer(random, allNext.size());
		allNext.add(ofRandom);
		visualisedNextShapes.add(ofRandom);
	}

	private void updateRanks() {
		for (final TetrisShapeContainer container : allNext) {
			container.decreaseRank();
			container.updateBorder();
		}
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (visualisedNextShapes != null && title != null) {
			visualisedNextShapes.setBackground(newBackground);
			title.setBackground(newBackground);
			for (final TetrisShapeContainer container : allNext) {
				container.setBackground(newBackground);
			}
		}
	}

	/**
	 * JPanel which displays a single TetrisShape with minimal grid size. Rank
	 * represents the order in which they are removed by calling "removeNext()".
	 * 
	 * @author Gabriel Glaser
	 */
	private class TetrisShapeContainer extends JPanel {
		private TetrisTile[][] tetrisShape;
		private final TetrisShape toContain;
		private int rank;
		private final int sizeOfBorder = 5;

		public TetrisShapeContainer(final TetrisShape toContain, final int rank) {
			this.toContain = toContain;
			this.rank = rank;
			this.tetrisShape = new TetrisTile[toContain.getHeight()][4];
			setLayout(new GridLayout(toContain.getHeight(), 4));
			updateBorder();
			setupTetrisFields();
			drawTetrisShape();
		}

		public void updateBorder() {
			if (rank < NUMBER_OF_NEXT_SHAPES - 1) {
				setBorder(new MatteBorder(0, 0, sizeOfBorder, 0, Color.GRAY));
			} else {
				setBorder(null);
			}

		}

		public void decreaseRank() {
			if (rank == 0) {
				throw new RuntimeException("rank cannot be negative");
			}
			rank--;
		}

		private void setupTetrisFields() {
			for (int i = 0; i < tetrisShape.length; i++) {
				for (int j = 0; j < tetrisShape[i].length; j++) {
					final TetrisTile toAdd = new TetrisTile();
					toAdd.setBackground(Colors.STANDARD_BACKGROUND);
					tetrisShape[i][j] = toAdd;
					add(toAdd);
				}
			}
		}

		private void drawTetrisShape() {
			final Set<TetrisLocation> coveredByTheShape = toContain.getCoveredLocations(new TetrisLocation(0, 0));
			for (final TetrisLocation covered : coveredByTheShape) {
				final TetrisTile toColor = tetrisShape[covered.getRow()][covered.getColumn()];
				toColor.setBackground(toContain.getColor());
			}
		}
	}
}
