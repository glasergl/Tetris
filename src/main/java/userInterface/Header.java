package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import standardComponents.Colors;
import standardComponents.Fonts;
import standardComponents.MyButton;

/**
 * Header of the TetrisWindow which contains JComponents to control the
 * TetrisGame.
 *
 * @author Gabriel Glaser
 * @version 27.08.2021
 */
public final class Header extends JPanel {

	private static final String RESTART_ICON_PATH = ClassLoader.getSystemResource("restart.png").getPath();
	private static final String PAUSE_ICON_PATH = ClassLoader.getSystemResource("pause.png").getPath();
	private static final String RESUME_ICON_PATH = ClassLoader.getSystemResource("resume.png").getPath();

	private final TetrisGame game;
	private final JPanel right = new JPanel();
	private final MyButton restart = new MyButton("Restart");
	private final MyButton pauseAndResume = new MyButton("Pause");
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
		restart.setFont(Fonts.HEADLINE);
		pauseAndResume.setFont(Fonts.HEADLINE);
		restart.setForeground(Colors.STANDARD_FOREGROUND);
		pauseAndResume.setForeground(Colors.STANDARD_FOREGROUND);
		restart.addMouseListener(new HoverEffect(restart));
		pauseAndResume.addMouseListener(new HoverEffect(pauseAndResume));
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
			pauseAndResume.setText("Resume");

		}

	}

	private class PauseAndResume implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (isPaused) {
				pauseAndResume.setText("Pause");
				game.resume();
			} else {
				pauseAndResume.setText("Resume");
				game.pause();
			}
			isPaused = !isPaused;
			game.requestFocus();
		}

	}

	private class HoverEffect implements MouseListener {

		private final MyButton toListenOn;
		private final Color storedBackground;

		public HoverEffect(final MyButton toListenOn) {
			this.toListenOn = toListenOn;
			this.storedBackground = toListenOn.getForeground();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			toListenOn.setForeground(storedBackground.darker());
		}

		@Override
		public void mouseExited(MouseEvent e) {
			toListenOn.setForeground(storedBackground);
		}

	}

}
