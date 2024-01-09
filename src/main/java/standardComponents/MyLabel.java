package standardComponents;

import javax.swing.JLabel;

/**
 * Klasse f�r das standardm��ige Label.
 * 
 * @author Gabriel Glaser
 */
public class MyLabel extends JLabel {

	public MyLabel() {
		this("");
	}

	public MyLabel(final String text) {
		super(text);
		setOpaque(true);
		setFont(Fonts.TEXT);
		setForeground(Colors.STANDARD_FOREGROUND);
	}

}
