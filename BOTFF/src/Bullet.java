import java.awt.Graphics;
import java.awt.Rectangle;

import Entitys.EntityA;
import Entitys.EntityB;

public class Bullet extends GameObject implements EntityA {

	EntityB tempEnt;
	Controller c;
	Game game;
	private Textures tex;
	public int Xdirection;
	public int Ydirection;
	Phs phs;
	Player p;

	public Bullet(double x, double y, Textures tex, Game game, int Xdirection, int Ydirection) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.Xdirection = Xdirection;
		this.Ydirection = Ydirection;
	}

	public void tick() {

		this.x += Xdirection;
		this.y += Ydirection;

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void render(Graphics g) {

		g.drawImage(tex.getBullet(), (int) x, (int) y, null);

	}

	public double getY() {
		return y;
	}

	public double getX() {

		return x;
	}

	@Override
	public int getPower() {
		// TODO Auto-generated method stub
		return 0;
	}

}
