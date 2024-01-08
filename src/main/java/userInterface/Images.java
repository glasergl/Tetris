package userInterface;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * @author Gabriel Glaser
 */
public final class Images {
	public static final Image TETRIS_ICON = getImage("Tetris_Icon.png");

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
}
