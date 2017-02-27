import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;

	public SpriteSheet(BufferedImage image) {

		this.image = image;
	}

	public BufferedImage grabPlayerImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
		return img;
	}

	public BufferedImage grabMapImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 600) - 600, (row * 600) - 600, width, height);
		return img;
	}

	public BufferedImage grabBulletImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}

	public BufferedImage grabBossImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 128) - 128, (row * 128) - 128, width, height);
		return img;
	}
}
