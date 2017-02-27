import java.awt.Graphics;
import java.awt.Rectangle;

import Entitys.EntityB;
import Entitys.EntityC;
import Entitys.EntityE;

public class Drops extends GameObject implements EntityE {

	EntityB tempEnt;
	Controller c;
	Game game;
	private Textures tex;
	public int Xdirection;
	public int Ydirection;
	Phs phs;
	Player p;
	private int hp, cash, token;

	public Drops(double x, double y, Textures tex, int hp, int cash, int token) {
		super(x, y);
		this.tex = tex;
		this.cash = cash;
		this.hp = hp;
		this.token = token;

	}

	public void tick() {

		for (int i = 0; i < Controller.ee.size(); i++) {

			EntityE tempEntE = Controller.ee.get(i);

			for (int e = 0; e < Controller.ec.size(); e++) {

				EntityC tempEntC = Controller.ec.get(e);

				if (Phs.Collision(tempEntE, tempEntC)) {

					Controller.removeEntity(tempEntE);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.getDrops(), (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getCash() {
		return cash;
	}

	public int getHp() {
		return hp;
	}

	public int getscore() {
		return token;
	}
}
