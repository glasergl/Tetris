package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import standardComponents.Colors;
import standardComponents.Fonts;
import standardComponents.MyLabel;

/**
 * Displays a vertical List of Labels which each shows a kind of stat about the
 * TetrisGame.
 *
 * @author Gabriel Glaser
 * @version 27.08.2021
 */
public final class StatTracker extends JPanel {

    private static final int DISTANCE_BETWEEN_TITLE_AND_STATS = 15;

    private final MyLabel title = new MyLabel("Stats:");
    private final MyLabel score = new MyLabel("Score: 0");
    private final MyLabel numberOfShapesFallenDown = new MyLabel("Shapes: 0");
    private final MyLabel gameSpeed = new MyLabel("Speed: 0");
    private final List<MyLabel> allStats = new ArrayList<>();

    public StatTracker() {
	setLayout(new BorderLayout());
	setPreferredSize(new Dimension(250, 1));
	setupComponents();
    }

    private void setupComponents() {
	setupTitle();
	setupLabels();
	setupContainer();
    }

    private void setupTitle() {
	title.setFont(Fonts.HEADLINE);
	add(title, BorderLayout.NORTH);
    }

    private void setupLabels() {
	allStats.add(score);
	allStats.add(numberOfShapesFallenDown);
	allStats.add(gameSpeed);
    }

    private void setupContainer() {
	final JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	panel.setBackground(Colors.STANDARD_BACKGROUND);
	panel.add(Box.createVerticalStrut(DISTANCE_BETWEEN_TITLE_AND_STATS));
	panel.add(score);
	panel.add(numberOfShapesFallenDown);
	panel.add(gameSpeed);
	add(panel);
    }

    public void setScore(final int newScore) {
	score.setText("Score: " + newScore);
    }

    public void setNumberOfShapesFallenDown(final int newNumberOfShapes) {
	numberOfShapesFallenDown.setText("Shapes: " + newNumberOfShapes);
    }

    public void setGameSpeed(final int newGameSpeed) {
	this.gameSpeed.setText("Speed: " + (1000 - newGameSpeed));
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (title != null && allStats != null) {
	    title.setBackground(newBackground);
	    for (final MyLabel stat : allStats) {
		stat.setBackground(newBackground);
	    }
	}
    }

}
