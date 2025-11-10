package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import customSwing.CustomSwing;

/**
 * Header of the TetrisWindow which contains JComponents to control the
 * TetrisGame.
 */
public final class Header extends JPanel {
	private final TetrisGame game;
	private final JPanel rightAlignedContainer = new JPanel();
	private final JButton restart = CustomSwing.getJButton(Images.RESTART_ICON);
	private final JButton pauseAndResume = CustomSwing.getJButton(Images.PAUSE_ICON);
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
		rightAlignedContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		rightAlignedContainer.add(restart);
		rightAlignedContainer.add(pauseAndResume);
		add(rightAlignedContainer, BorderLayout.EAST);
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (rightAlignedContainer != null) {
			rightAlignedContainer.setBackground(newBackground);
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
