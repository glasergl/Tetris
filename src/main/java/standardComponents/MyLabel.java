package standardComponents;

import javax.swing.JLabel;

/**
 * Klasse für das standardmäßige Label.
 * 
 * @author Gabriel Glaser
 * @version 24.06.2021
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
