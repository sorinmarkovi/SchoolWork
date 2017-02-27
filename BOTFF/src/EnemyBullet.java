import java.awt.Graphics;
import java.awt.Rectangle;

import Entitys.EntityB;
import Entitys.EntityD;

public class EnemyBullet extends GameObject implements EntityD {

	EntityB tempEnt;
	Controller c;
	Game game;
	private Textures tex;
	public double Xdirection;
	public double Ydirection;
	Phs phs;
	Player p;
	public double damage;
	Faction.FACTION faction;

	public EnemyBullet(double x, double y, Textures tex, double Xdirection, double Ydirection, double damage) 
	{
		super(x, y);
		this.tex = tex;
		this.Xdirection = Xdirection;
		this.Ydirection = Ydirection;
		this.damage = damage;
		//this.faction = faction;
	}
	public void tick() 
	{
		if (!Game.stopped) 
		{
			this.x += Xdirection;
			this.y += Ydirection;
		}
	}
	public void render(Graphics g) 
	{
		g.drawImage(tex.getEnemyBullet(), (int) x, (int) y, null);
	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}
	public double getX() 
	{
		return x;
	}
	public double getY() 
	{
		return y;
	}
	public double getDamage() 
	{
		return damage;
	}
}