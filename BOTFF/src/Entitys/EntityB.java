package Entitys;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityB {// enemy

	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public double getX();

	public double getY();

	public double getHealth();

	public void bump(int xChange, int yChange);

	public boolean getBoss();

	public String getFaction();

	public boolean getBoss2();

}
