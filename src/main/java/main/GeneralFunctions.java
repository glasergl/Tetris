package main;

import java.awt.Image;

import javax.swing.ImageIcon;

public final class GeneralFunctions {

	public static ImageIcon scale(final ImageIcon toScale, final int width, final int height) {
		return new ImageIcon(toScale.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

}
