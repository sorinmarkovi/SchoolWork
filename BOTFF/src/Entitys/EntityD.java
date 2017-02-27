package Entitys;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityD {//bullets
	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public double getX();

	public double getY();

	public double getDamage();

}
