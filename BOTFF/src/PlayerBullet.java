import java.awt.Graphics;
import java.awt.Rectangle;
import Entitys.EntityA;
import Entitys.EntityB;
public class PlayerBullet extends GameObject implements EntityA 
{
	EntityB tempEnt;
	Controller c;
	Game game;
	private Textures tex;
	public int Xdirection;
	public int Ydirection;
	Phs phs;
	Player p;
	boolean L, R, D, U;/// left, right ,down up
	int power;
	public PlayerBullet(double x, double y, Textures tex, Game game, int Xdirection, int Ydirection, boolean L, boolean R, boolean D, boolean U, int power) 
	{
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.Xdirection = Xdirection;
		this.Ydirection = Ydirection;
		this.power = power;
		this.L = L;
		this.R = R;
		this.D = D;
		this.U = U;
	}
	public void tick() 
	{
		this.x += Xdirection;
		this.y += Ydirection;
	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int) x, (int) y, 32, 32);
	}
	public void render(Graphics g) 
	{
		if (this.D)
			g.drawImage(tex.getDBullet(), (int) x, (int) y, null);
		if (this.U)
			g.drawImage(tex.getUBullet(), (int) x, (int) y, null);

		if (this.L)
			g.drawImage(tex.getLBullet(), (int) x, (int) y, null);
		if (this.R)
			g.drawImage(tex.getRBullet(), (int) x, (int) y, null);
	}
	public double getY() 
	{
		return y;
	}
	public double getX() 
	{
		return x;
	}
	public int getPower() 
	{
		return power;
	}
}