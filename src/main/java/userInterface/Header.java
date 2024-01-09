package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import standardComponents.CustomizedSwing;

/**
 * Header of the TetrisWindow which contains JComponents to control the
 * TetrisGame.
 *
 * @author Gabriel Glaser
 */
public final class Header extends JPanel {
	private final TetrisGame game;
	private final JPanel right = new JPanel();
	private final JButton restart = CustomizedSwing.getJButton(Images.RESTART_ICON);
	private final JButton pauseAndResume = CustomizedSwing.getJButton(Images.PAUSE_ICON);
	private boolean isPaused = false;

	public Header(final TetrisGame game) {
		super();
		this.game = game;
		setLayout(new BorderLayout());
		setupComponents();
	}

	private void setupComponents() {
		restart.addActionListener(new Restart());
		pauseAndResume.addActionListener(new PauseAndResume());
		setupContainer();
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
			pauseAndResume.setIcon(Images.RESUME_ICON);
		}

	}

	private class PauseAndResume implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (isPaused) {
				pauseAndResume.setIcon(Images.PAUSE_ICON);
				game.resume();
			} else {
				pauseAndResume.setIcon(Images.RESUME_ICON);
				game.pause();
			}
			isPaused = !isPaused;
			game.requestFocus();
		}
	}
}