package Entitys;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityE {//loot bags
	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public double getX();

	public double getY();

	public int getHp();

	public int getCash();

	public int getscore();
}
