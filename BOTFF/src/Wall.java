import java.awt.Graphics;
import java.awt.Rectangle;

import Entitys.EntityC;

public class Wall extends GameObject implements EntityC {

	private int width, height;
	Map map;
	Game game;
	Controller c;

	public Wall(double x, double y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), getWidth(), getHeight());
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void tick() {

	}
}
