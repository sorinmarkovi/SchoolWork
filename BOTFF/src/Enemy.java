import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entitys.EntityA;
import Entitys.EntityB;
import Entitys.EntityC;

import java.util.Random;

public class Enemy extends GameObject implements EntityB {

	EntityA tempEnt;
	private Textures tex;
	private Game game;
	Controller c;
	private boolean friendly = false;
	private boolean ranged = false;
	private double HEALTH;
	private int ms = 5;
	BufferedImage image;
	Player p;
	Map map;
	Audio enemyHit = new Audio("/hitmarker.wav");
	Audio bossDefeated = new Audio("/success.wav");

	boolean boss, boss2;

	String faction;

	public Enemy(double x, double y, String faction, double health, Textures tex, boolean boss, boolean boss2) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.ranged = ranged;
		this.HEALTH = health;
		this.tex = tex;
		this.faction = faction;
		this.boss = boss;
		this.boss2 = boss2;
	}

	public void tick() {

		if (game.nukedo) {
			this.HEALTH -= 10000;
			game.nukedo = false;
		}

		if (game.poison == true) {
			if (((this.faction == "SUBWAY") && Game.PlayerFaction != Game.PlayerFaction.SUBWAY)
					|| ((this.faction == "ARBYS") && game.PlayerFaction != Game.PlayerFaction.ARBYS)
					|| ((this.faction == "MCD") && game.PlayerFaction != Game.PlayerFaction.MCD)
					|| ((this.faction == "BRAUMS") && game.PlayerFaction != game.PlayerFaction.BRAUMS)
					|| ((this.faction == "CAESAR") && game.PlayerFaction != game.PlayerFaction.CAESAR)
					|| ((this.faction == "TACOBELL") && game.PlayerFaction != game.PlayerFaction.TACOBELL)) {
				this.HEALTH -= .2 * game.currentLevel;

				if (this.HEALTH <= 0) {
					c.removeEntity(this);
					c.enemyKilled(this);

					if (this.getBoss() || this.getBoss2()) 
						game.bossKilled++;

					if (game.ENERGY <= 100 && !game.active)
						game.ENERGY += 10;

					if (game.ENERGY >= 100)
						game.ENERGY = 100;

					game.CURRENTHEALTH += game.steal;
					game.CASH += game.gok;
					if (game.CURRENTHEALTH >= game.MAXHEALTH)
						game.CURRENTHEALTH = game.MAXHEALTH;

					spawned();
				}
			}
		}
		if ((this.getBoss() || this.getBoss2()) && !game.stopped) {
			if (((this.faction == "SUBWAY") && game.PlayerFaction != game.PlayerFaction.SUBWAY)
					|| ((this.faction == "ARBYS") && game.PlayerFaction != game.PlayerFaction.ARBYS)
					|| ((this.faction == "MCD") && game.PlayerFaction != game.PlayerFaction.MCD)
					|| ((this.faction == "BRAUMS") && game.PlayerFaction != game.PlayerFaction.BRAUMS)
					|| ((this.faction == "CAESAR") && game.PlayerFaction != game.PlayerFaction.CAESAR)
					|| ((this.faction == "TACOBELL") && game.PlayerFaction != game.PlayerFaction.TACOBELL)) {
				x += ms;
				if (x <= 0) {
					ms = -ms;
				}
				if (x >= 556)
					ms = -ms;
			}
		}
		for (int i = 0; i < c.ea.size(); i++) {
			EntityA tempEntA = c.ea.get(i);

			if (game.nukedo) {
				this.HEALTH -= 500;
				game.nukedo = false;
			}

			if (Phs.Collision(tempEntA, this)) {

				if (((this.faction == "SUBWAY") && game.PlayerFaction != game.PlayerFaction.SUBWAY)
						|| ((this.faction == "ARBYS") && game.PlayerFaction != game.PlayerFaction.ARBYS)
						|| ((this.faction == "MCD") && game.PlayerFaction != game.PlayerFaction.MCD)
						|| ((this.faction == "SONIC") && game.PlayerFaction != game.PlayerFaction.SONIC)
						|| ((this.faction == "BRAUMS") && game.PlayerFaction != game.PlayerFaction.BRAUMS)
						|| ((this.faction == "CAESAR") && game.PlayerFaction != game.PlayerFaction.CAESAR)
						|| ((this.faction == "TACOBELL") && game.PlayerFaction != game.PlayerFaction.TACOBELL)) {
					enemyHit.play();

					if (Game.pierce == false)
						c.removeEntity(tempEntA);
					if (Game.ddamage == true)
						this.HEALTH -= 1.5 * tempEntA.getPower();

					else
						this.HEALTH -= tempEntA.getPower();

					if (Game.pierce)
						this.HEALTH += tempEntA.getPower() / 2;

					if (this.HEALTH <= 0) {
						c.removeEntity(this);
						c.enemyKilled(this);

						if (this.getBoss() || this.getBoss2()) 
							game.bossKilled++;

						if (game.ENERGY <= 100 && !game.active)
							game.ENERGY += 10;
						if (game.ENERGY >= 100)
							game.ENERGY = 100;

						game.CURRENTHEALTH += game.steal;
						game.CASH += game.gok;
						if (game.CURRENTHEALTH >= game.MAXHEALTH)
							game.CURRENTHEALTH = game.MAXHEALTH;

						spawned();
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.getEnemy(), (int) x, (int) y, null);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}

	public double getHealth() {
		return HEALTH;
	}

	public void setHealth(int HEALTH) {
		this.HEALTH = HEALTH;
	}

	public void bump(int xChange, int yChange) {
		this.x += xChange;
		this.y += yChange;

	}

	public boolean getBoss() {
		return boss;
	}

	public boolean getBoss2() {
		return boss2;
	}

	public String getFaction() {
		return faction;
	}

	public void spawned() {
		if (c.eb.size() == 0 && map.Location == map.Location.a1) {
			game.a1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.a2) {
			game.a2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.a3) {
			game.a3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.a4) {
			game.a4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.a5) {
			game.a5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.a6) {
			game.a6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.a7) {
			game.a7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.a8) {
			game.a8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.a9) {
			game.a9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.a10) {
			game.a10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b1) {
			game.b1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b2) {
			game.b2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b3) {
			game.b3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b4) {
			game.b4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b5) {
			game.b5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b6) {
			game.b6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b7) {
			game.b7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b8) {
			game.b8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b9) {
			game.b9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.b10) {
			game.b10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c1) {
			game.c1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c2) {
			game.c2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c3) {
			game.c3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c4) {
			game.c4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c5) {
			game.c5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c6) {
			game.c6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c7) {
			game.c7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c8) {
			game.c8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c9) {
			game.c9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.c10) {
			game.c10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d1) {
			game.d1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d2) {
			game.d2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d3) {
			game.d3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d4) {
			game.d4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d5) {
			game.d5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d6) {
			game.d6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d7) {
			game.d7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d8) {
			game.d8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d9) {
			game.d9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.d10) {
			game.d10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e1) {
			game.e1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e2) {
			game.e2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e3) {
			game.e3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e4) {
			game.e4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e5) {
			game.e5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e6) {
			game.e6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e7) {
			game.e7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e8) {
			game.e8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e9) {
			game.e9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.e10) {
			game.e10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f1) {
			game.f1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f2) {
			game.f2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f3) {
			game.f3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f4) {
			game.f4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f5) {
			game.f5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f6) {
			game.f6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f7) {
			game.f7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f8) {
			game.f8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f9) {
			game.f9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.f10) {
			game.f10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g1) {
			game.g1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g2) {
			game.g2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g3) {
			game.g3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g4) {
			game.g4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g5) {
			game.g5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g6) {
			game.g6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g7) {
			game.g7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g8) {
			game.g8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g9) {
			game.g9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.g10) {
			game.g10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h1) {
			game.h1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h2) {
			game.h2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h3) {
			game.h3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h4) {
			game.h4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h5) {
			game.h5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h6) {
			game.h6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h7) {
			game.h7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h8) {
			game.h8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h9) {
			game.h9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.h10) {
			game.h10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i1) {
			game.i1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i2) {
			game.i2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i3) {
			game.i3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i4) {
			game.i4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i5) {
			game.i5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i6) {
			game.i6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i7) {
			game.i7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i8) {
			game.i8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i9) {
			game.i9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.i10) {
			game.i10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j1) {
			game.j1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j2) {
			game.j2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j3) {
			game.j3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j4) {
			game.j4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j5) {
			game.j5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j6) {
			game.j6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j7) {
			game.j7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j8) {
			game.j8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j9) {
			game.j9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.j10) {
			game.j10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k1) {
			game.k1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k2) {
			game.k2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k3) {
			game.k3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k4) {
			game.k4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k5) {
			game.k5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k6) {
			game.k6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k7) {
			game.k7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k8) {
			game.k8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k9) {
			game.k9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.k10) {
			game.k10 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l1) {
			game.l1 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l2) {
			game.l2 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l3) {
			game.l3 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l4) {
			game.l4 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l5) {
			game.l5 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l6) {
			game.l6 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l7) {
			game.l7 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l8) {
			game.l8 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l9) {
			game.l9 = true;
		}
		if (c.eb.size() == 0 && map.Location == map.Location.l10) {
			game.l10 = true;
		}

	}

}
