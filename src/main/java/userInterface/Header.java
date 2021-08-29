package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import standardComponents.MyButton;

/**
 * Header of the TetrisWindow which contains JComponents to control the
 * TetrisGame.
 *
 * @author Gabriel Glaser
 * @version 27.08.2021
 */
public final class Header extends JPanel {

    private static final String RESTART_ICON_PATH = "src/resources/restartWhite.png";
    private static final String PAUSE_ICON_PATH = "src/resources/pauseWhite.png";
    private static final String RESUME_ICON_PATH = "src/resources/resumeWhite.png";

    private final TetrisGame game;
    private final JPanel right = new JPanel();
    private final MyButton restart = new MyButton(new ImageIcon(RESTART_ICON_PATH), 50, 50);
    private final MyButton pauseAndResume = new MyButton(new ImageIcon(PAUSE_ICON_PATH), 50, 50);
    private boolean isPaused = false;

    public Header(final TetrisGame game) {
	super();
	this.game = game;
	setLayout(new BorderLayout());
	setupComponents();
    }

    private void setupComponents() {
	setupButtons();
	setupContainer();
    }

    private void setupButtons() {
	restart.addActionListener(new Restart());
	pauseAndResume.addActionListener(new PauseAndResume());
    }

    private void setupContainer() {
	right.setLayout(new FlowLayout(FlowLayout.RIGHT));
	right.add(restart);
	right.add(pauseAndResume);
	add(right, BorderLayout.EAST);
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (right != null) {
	    right.setBackground(newBackground);
	    restart.setBackground(newBackground);
	    pauseAndResume.setBackground(newBackground);
	}
    }

    private class Restart implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    game.restart();
	    game.requestFocus();
	    game.pause();
	    isPaused = true;
	    pauseAndResume.setIcon(new ImageIcon(RESUME_ICON_PATH), 50, 50);

	}

    }

    private class PauseAndResume implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (isPaused) {
		pauseAndResume.setIcon(new ImageIcon(PAUSE_ICON_PATH), 50, 50);
		game.resume();
	    } else {
		pauseAndResume.setIcon(new ImageIcon(RESUME_ICON_PATH), 50, 50);
		game.pause();
	    }
	    isPaused = !isPaused;
	    game.requestFocus();
	}

    }

}
