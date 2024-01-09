package customSwing;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * @author Gabriel Glaser
 */
public class CustomSwing {
	public static JButton getJButton(final Icon icon) {
		final JButton jButton = new JButton(icon);
		jButton.setFocusPainted(false);
		return jButton;
	}

	public static JLabel getJLabel(final String labelText) {
		final JLabel jLabel = new JLabel(labelText);
		jLabel.setOpaque(true);
		jLabel.setFont(Fonts.TEXT);
		jLabel.setForeground(Colors.STANDARD_FOREGROUND);
		return jLabel;
	}
}
