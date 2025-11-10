package customSwing;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Factory class which returns the needed JComponents with default attributes.
 */
public class CustomSwing {
	public static final Font HEADLINE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 35);
	public static final Font TEXT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 25);

	public static JButton getJButton(final Icon icon) {
		final JButton jButton = new JButton(icon);
		jButton.setFocusPainted(false);
		return jButton;
	}

	public static JLabel getJLabel(final String labelText) {
		final JLabel jLabel = new JLabel(labelText);
		jLabel.setOpaque(true);
		jLabel.setFont(TEXT_FONT);
		jLabel.setForeground(Colors.TEXT_COLOR);
		return jLabel;
	}
}
