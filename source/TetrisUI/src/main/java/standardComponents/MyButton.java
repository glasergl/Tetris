package standardComponents;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import main.GeneralFunctions;

public class MyButton extends JButton {

    public MyButton(final String text) {
	super(text);
	setBorder(null);
	setFocusPainted(false);
    }

    public MyButton(final ImageIcon icon, final int width, final int height) {
	this("");
	setIcon(GeneralFunctions.scale(icon, width, height));
    }

    public void setIcon(final ImageIcon toDisplay, final int width, final int height) {
	super.setIcon(GeneralFunctions.scale(toDisplay, width, height));
    }
}
