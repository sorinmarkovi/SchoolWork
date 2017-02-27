package Entitys;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityC {// wall

	public Rectangle getBounds();

	public double getX();

	public double getY();

	public void tick();

	public int getWidth();

	public int getHeight();

}
