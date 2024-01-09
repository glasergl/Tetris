package standardComponents;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * @author Gabriel Glaser
 */
public class CustomizedSwing {
	public static JButton getJButton(final Icon icon) {
		final JButton jButton = new JButton(icon);
		jButton.setFocusPainted(false);
		return jButton;
	}
}
