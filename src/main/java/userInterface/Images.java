package userInterface;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public final class Images {
	private static final int ICON_SIZE = 35;
	public static final Image TETRIS_ICON = getImage("Tetris_Icon.png");
	public static final Icon RESTART_ICON = getIcon("restart.png", ICON_SIZE, ICON_SIZE);
	public static final Icon PAUSE_ICON = getIcon("pause.png", ICON_SIZE, ICON_SIZE);
	public static final Icon RESUME_ICON = getIcon("resume.png", ICON_SIZE, ICON_SIZE);

	private static Image getImage(final String fileName) {
		try {
			final ClassLoader classLoader = Images.class.getClassLoader();
			final InputStream image = classLoader.getResourceAsStream(fileName);
			return ImageIO.read(image);
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static Image getImage(final String fileName, final int scaledWidth, final int scaledHeight) {
		final Image image = getImage(fileName);
		return image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
	}

	private static Icon getIcon(final String fileName, final int scaledWidth, final int scaledHeight) {
		final Image image = getImage(fileName, scaledWidth, scaledHeight);
		return new ImageIcon(image);
	}
}
