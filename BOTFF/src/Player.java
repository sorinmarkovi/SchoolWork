import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entitys.EntityA;
import Entitys.EntityC;
import Entitys.EntityD;

public class Player extends GameObject implements EntityA {

	static double velX;
	static double velY;
	public static boolean front = true;
	public static boolean back, left, right;
	Map map;
	private Textures tex;
	public static BufferedImage nextmap;
	public static BufferedImage currentmap;
	Controller c;
	Game game;
	PlayerBullet b;
	Phs phs;
	static boolean levelUp = false;
	Audio playerHit = new Audio("/punch 1.wav");

	public Player(double x, double y, Textures tex) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.tex = tex;

	}

	public void tick() {

		x += velX;
		y += velY;

		for (int i = 0; i < c.ed.size(); i++) {
			EntityD tempEnt = c.ed.get(i);

			if (Phs.Collision(this, tempEnt)) {

				c.removeEntity(tempEnt);
				if (!game.noDamage)
					game.CURRENTHEALTH -= tempEnt.getDamage();
				playerHit.play();

			}
		}
		///////////////////// Map
		///////////////////// changes//////////////////////////////////////////////////////////
		///////////////////// All bound that include 608 or -8

		///////////////////// if player was to go left then bound is -16
		///////////////////// if player was to go right then bound is 584
		///////////////////// if player was to go up then bound is -16
		///////////////////// if player was to go down then bound is 584

		///////////////////// All player x/y changes are 584 or 16
		///////////////////// Judge doors based on y rather than x
		///////////////////// Make sure current map and next map satisfy the
		///////////////////// location of the player
		if (levelUp == true) {
			x = 300;
			y = 300;
			if (Game.PlayerFaction == Faction.FACTION.SUBWAY) {
				Map.Location = Map.MAP.a2;// fix
				nextmap = tex.ms.grabMapImage(2, 1, 600, 600);
				c.change();
			}
			if (Game.PlayerFaction == Faction.FACTION.ARBYS) {
				Map.Location = Map.MAP.i5;
				nextmap = tex.ms.grabMapImage(5, 9, 600, 600);
				c.change();
			}
			if (Game.PlayerFaction == Faction.FACTION.TACOBELL) {
				Map.Location = Map.MAP.k1;
				nextmap = tex.ms.grabMapImage(1, 11, 600, 600);
				c.change();
			}
			if (Game.PlayerFaction == Faction.FACTION.MCD) {
				Map.Location = Map.MAP.f4;
				nextmap = tex.ms.grabMapImage(4, 6, 600, 600);
				c.change();
			}
			if (Game.PlayerFaction == Faction.FACTION.SONIC) {
				Map.Location = Map.MAP.l7;
				nextmap = tex.ms.grabMapImage(7, 12, 600, 600);
				c.change();
			}
			if (Game.PlayerFaction == Faction.FACTION.BRAUMS) {
				Map.Location = Map.MAP.l7;
				nextmap = tex.ms.grabMapImage(7, 12, 600, 600);
				c.change();
			}
			if (Game.PlayerFaction == Faction.FACTION.CAESAR) {
				Map.Location = Map.MAP.l7;
				nextmap = tex.ms.grabMapImage(7, 12, 600, 600);
				c.change();
			}
			levelUp = false;
		}

		if (map.Location == Map.MAP.a1) {
			currentmap = tex.ms.grabMapImage(1, 1, 600, 600);
			if (x >= 575 && (y >= 29 && y <= 126)) {
				map.Location = Map.MAP.a2;// top rug
				nextmap = tex.ms.grabMapImage(2, 1, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
		}

		if (map.Location == Map.MAP.a2) {
			currentmap = tex.ms.grabMapImage(2, 1, 600, 600);
			if (x <= -8 && (y >= 18 && y <= 138)) {
				map.Location = Map.MAP.a1;// top rug
				nextmap = tex.ms.grabMapImage(1, 1, 600, 600);
				x = 530;
				y = getY();
				c.change();
			}
			if (y >= 608 && (x >= 269 && x <= 369)) {
				map.Location = Map.MAP.b2;
				nextmap = tex.ms.grabMapImage(2, 2, 600, 600);
				x = 300;
				y = 50;
				c.change();
			}
		}
		if (map.Location == Map.MAP.a3) {
			currentmap = tex.ms.grabMapImage(3, 1, 600, 600);
			if (y >= 608 && (x >= -8 && x <= 608)) {
				map.Location = Map.MAP.b3;// top rug
				nextmap = tex.ms.grabMapImage(3, 2, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
		}
		if (map.Location == Map.MAP.b1) {
			currentmap = tex.ms.grabMapImage(1, 2, 600, 600);
			if ((y >= -8 && y <= 608) && x >= 608) {
				map.Location = Map.MAP.b2;
				nextmap = tex.ms.grabMapImage(2, 2, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
		}

		if (map.Location == Map.MAP.b2) {
			currentmap = tex.ms.grabMapImage(2, 2, 600, 600);
			if (y <= 32 && (x >= 259 && x <= 379)) {
				map.Location = Map.MAP.a2;
				nextmap = tex.ms.grabMapImage(2, 1, 600, 600);
				x = 300;
				y = 570;
				c.change();
			}
			if ((y >= -16 && y <= 608) && x <= -8) {// left
				map.Location = Map.MAP.b1;
				nextmap = tex.ms.grabMapImage(1, 2, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((y >= -16 && y <= 608) && x >= 608) {
				map.Location = Map.MAP.b3;
				nextmap = tex.ms.grabMapImage(3, 2, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.c2;
				nextmap = tex.ms.grabMapImage(2, 3, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
		}
		if (map.Location == Map.MAP.b3) {
			currentmap = tex.ms.grabMapImage(3, 2, 600, 600);
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.b2;
				nextmap = tex.ms.grabMapImage(2, 2, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.a3;
				nextmap = tex.ms.grabMapImage(3, 1, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
		}
		if (map.Location == Map.MAP.c2) {
			currentmap = tex.ms.grabMapImage(2, 3, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.b2;
				nextmap = tex.ms.grabMapImage(2, 2, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.d2;
				nextmap = tex.ms.grabMapImage(2, 4, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
		}
		if (map.Location == Map.MAP.d2) {
			currentmap = tex.ms.grabMapImage(2, 4, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.c2;
				nextmap = tex.ms.grabMapImage(2, 3, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.e2;
				nextmap = tex.ms.grabMapImage(2, 5, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
		}
		if (map.Location == Map.MAP.e2) {
			currentmap = tex.ms.grabMapImage(2, 5, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.d2;
				nextmap = tex.ms.grabMapImage(2, 4, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((y >= -8 && y <= 608) && x >= 608) {
				map.Location = Map.MAP.e3;
				nextmap = tex.ms.grabMapImage(3, 5, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.f2;
				nextmap = tex.ms.grabMapImage(2, 6, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((y >= -8 && y <= 608) && x <= -8) {// homeland entrence
				x = getX() + 32;
				y = getY();
				c.change();
				Game.State = Game.STATE.MENU;
				game.homeland = true;
			}
		}
		if (map.Location == Map.MAP.e3) {
			currentmap = tex.ms.grabMapImage(3, 5, 600, 600);
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.e2;
				nextmap = tex.ms.grabMapImage(2, 5, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((y >= -8 && y <= 608) && x >= 608) {
				map.Location = Map.MAP.e4;
				nextmap = tex.ms.grabMapImage(4, 5, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.f3;
				nextmap = tex.ms.grabMapImage(3, 6, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
		}
		if (map.Location == Map.MAP.e4) {
			currentmap = tex.ms.grabMapImage(4, 5, 600, 600);
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.e3;
				nextmap = tex.ms.grabMapImage(3, 5, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((x >= 249 && x <= 349) && y >= 608) {
				map.Location = Map.MAP.f4;
				nextmap = tex.ms.grabMapImage(4, 6, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((y >= -8 && y <= 608) && x >= 608) {
				map.Location = Map.MAP.e5;
				nextmap = tex.ms.grabMapImage(5, 5, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.e5) {
			currentmap = tex.ms.grabMapImage(5, 5, 600, 600);
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.e4;
				nextmap = tex.ms.grabMapImage(4, 5, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.f5;
				nextmap = tex.ms.grabMapImage(5, 6, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
		}
		if (map.Location == Map.MAP.f2) {
			currentmap = tex.ms.grabMapImage(2, 6, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.e2;
				nextmap = tex.ms.grabMapImage(2, 5, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.g2;
				nextmap = tex.ms.grabMapImage(2, 7, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((y >= -8 && y <= 608) && x <= -8) {// pharmacy entrence
				x = getX() + 32;
				y = getY();
				c.change();
				Game.State = Game.STATE.MENU;
				game.pharmacy = true;
			}
			if ((y >= -8 && y <= 608) && x >= 608) {
				map.Location = Map.MAP.f3;
				nextmap = tex.ms.grabMapImage(3, 6, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}

		}
		if (map.Location == Map.MAP.f3) {
			currentmap = tex.ms.grabMapImage(3, 6, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.e3;
				nextmap = tex.ms.grabMapImage(3, 5, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.g3;
				nextmap = tex.ms.grabMapImage(3, 7, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.f2;
				nextmap = tex.ms.grabMapImage(2, 6, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.f4) {
			currentmap = tex.ms.grabMapImage(4, 6, 600, 600);
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.g4;
				nextmap = tex.ms.grabMapImage(4, 7, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((x >= 249 && x <= 349) && y <= -8) {
				map.Location = Map.MAP.e4;
				nextmap = tex.ms.grabMapImage(4, 5, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
		}
		if (map.Location == Map.MAP.f5) {
			currentmap = tex.ms.grabMapImage(5, 6, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.e5;
				nextmap = tex.ms.grabMapImage(5, 5, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= 80 && x <= 180) && y >= 608) {
				map.Location = Map.MAP.g5;
				nextmap = tex.ms.grabMapImage(5, 7, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
		}
		if (map.Location == Map.MAP.f8) {
			currentmap = tex.ms.grabMapImage(8, 6, 600, 600);
			if ((x >= -8 && x <= 608) && y >= 584) {
				map.Location = Map.MAP.g8;
				nextmap = tex.ms.grabMapImage(8, 7, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 608) {
				map.Location = Map.MAP.f9;
				nextmap = tex.ms.grabMapImage(9, 6, 600, 600);
				x = 8;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.f9) {
			currentmap = tex.ms.grabMapImage(9, 6, 600, 600);
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.f8;
				nextmap = tex.ms.grabMapImage(8, 6, 600, 600);
				x = 580;
				y = getY();
				c.change();
			}
			if ((y >= -8 && y <= 600) && x >= 584) {
				map.Location = Map.MAP.f10;
				nextmap = tex.ms.grabMapImage(10, 6, 600, 600);
				x = 8;
				y = getY();
				c.change();
			}
			if ((x >= -8 && x <= 600) && y >= 584) {
				map.Location = Map.MAP.g9;
				nextmap = tex.ms.grabMapImage(9, 7, 600, 600);
				x = getX();
				y = 0;
				c.change();
			}
		}
		if (map.Location == Map.MAP.f10) {
			currentmap = tex.ms.grabMapImage(10, 6, 600, 600);
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.f9;
				nextmap = tex.ms.grabMapImage(9, 6, 600, 600);
				x = 580;
				y = getY();
				c.change();
			}
			if ((x >= 0 && x <= 600) && y >= 608) {
				map.Location = Map.MAP.g10;
				nextmap = tex.ms.grabMapImage(10, 7, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
		}
		if (map.Location == Map.MAP.g1) {
			currentmap = tex.ms.grabMapImage(1, 7, 600, 600);
			if ((y >= -8 && y <= 608) && x >= 584) {
				map.Location = Map.MAP.g2;
				nextmap = tex.ms.grabMapImage(2, 7, 600, 600);
				y = getY();
				x = 16;
				c.change();
			}

		}
		if (map.Location == Map.MAP.g2) {
			currentmap = tex.ms.grabMapImage(2, 7, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.f2;
				nextmap = tex.ms.grabMapImage(2, 6, 600, 600);
				x = getX();
				y = 590;
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.h2;
				nextmap = tex.ms.grabMapImage(2, 8, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((y >= -8 && y <= 608) && x >= 608) {
				map.Location = Map.MAP.g3;
				nextmap = tex.ms.grabMapImage(3, 7, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
			if ((y >= -8 && y <= 608) && x <= -16) {
				map.Location = Map.MAP.g1;
				nextmap = tex.ms.grabMapImage(1, 7, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.g3) {
			currentmap = tex.ms.grabMapImage(3, 7, 600, 600);
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.h3;
				nextmap = tex.ms.grabMapImage(3, 8, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.f3;
				nextmap = tex.ms.grabMapImage(3, 6, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.g2;
				nextmap = tex.ms.grabMapImage(2, 7, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.g4) {
			currentmap = tex.ms.grabMapImage(4, 7, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.f4;
				nextmap = tex.ms.grabMapImage(4, 6, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}

		}
		if (map.Location == Map.MAP.g5) {
			currentmap = tex.ms.grabMapImage(5, 7, 600, 600);
			if ((x >= 0 && x <= 600) && y >= 608) {
				map.Location = Map.MAP.h5;
				nextmap = tex.ms.grabMapImage(5, 8, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((x >= 80 && x <= 180) && y <= -8) {
				map.Location = Map.MAP.f5;
				nextmap = tex.ms.grabMapImage(5, 6, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}

		}
		if (map.Location == Map.MAP.g8) {
			currentmap = tex.ms.grabMapImage(8, 7, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -16) {
				map.Location = Map.MAP.f8;
				nextmap = tex.ms.grabMapImage(8, 6, 600, 600);
				x = getX();
				y = 550;
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 584) {
				map.Location = Map.MAP.g9;
				nextmap = tex.ms.grabMapImage(9, 7, 600, 600);
				x = 8;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.g9) {
			currentmap = tex.ms.grabMapImage(9, 7, 600, 600);
			if ((x >= 0 && x <= 600) && y >= 590) {
				map.Location = Map.MAP.h9;
				nextmap = tex.ms.grabMapImage(9, 8, 600, 600);
				x = getX();
				y = 8;
				c.change();
			}
			if ((y >= 0 && y <= 600) && x <= -8) {
				map.Location = Map.MAP.g8;
				nextmap = tex.ms.grabMapImage(8, 7, 600, 600);
				x = 580;
				y = getY();
				c.change();
			}
			if ((x >= 0 && x <= 600) && y <= -8) {
				map.Location = Map.MAP.f9;
				nextmap = tex.ms.grabMapImage(9, 6, 600, 600);
				x = getX();
				y = 550;
				c.change();
			}
		}
		if (map.Location == Map.MAP.g10) {
			currentmap = tex.ms.grabMapImage(10, 7, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -8) {
				map.Location = Map.MAP.f10;
				nextmap = tex.ms.grabMapImage(10, 6, 600, 600);
				x = getX();
				y = 580;
				c.change();
			}
		}
		if (map.Location == Map.MAP.h1) {

			currentmap = tex.ms.grabMapImage(1, 8, 600, 600);
			if (x >= 584 && (y <= 600 && y >= 0)) {
				nextmap = tex.ms.grabMapImage(2, 8, 600, 600);
				map.Location = Map.MAP.h2;
				x = 16;
				y = getY();
				c.change();

			}

		}

		if (map.Location == Map.MAP.h2) {
			currentmap = tex.ms.grabMapImage(2, 8, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.g2;
				nextmap = tex.ms.grabMapImage(2, 7, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.i2;
				nextmap = tex.ms.grabMapImage(2, 9, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if (x <= -16 && (y >= -8 && y <= 608)) {
				map.Location = Map.MAP.h1;
				nextmap = tex.ms.grabMapImage(1, 8, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if (x >= 608 && (y >= -8 && y <= 608)) {
				map.Location = Map.MAP.h3;
				nextmap = tex.ms.grabMapImage(3, 8, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.h3) {
			currentmap = tex.ms.grabMapImage(3, 8, 600, 600);
			if ((x >= -8 && x <= 608) && y >= 608) {
				nextmap = tex.ms.grabMapImage(3, 9, 600, 600);
				map.Location = Map.MAP.i3;
				x = getX();
				y = 16;
				c.change();
			}
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.g3;
				nextmap = tex.ms.grabMapImage(3, 7, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if (x <= -16 && (y >= -8 && y <= 608)) {
				map.Location = Map.MAP.h2;
				nextmap = tex.ms.grabMapImage(2, 8, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
		}

		if (map.Location == Map.MAP.h4) {
			currentmap = tex.ms.grabMapImage(4, 8, 600, 600);
			if ((y >= 40 && y <= 90) && (x <= 90 && x >= 40)) {
				map.Location = Map.MAP.i4;
				nextmap = tex.ms.grabMapImage(4, 9, 600, 600);
				x = 50;
				y = 120;
				c.change();
			}

		}
		if (map.Location == Map.MAP.h5) {
			currentmap = tex.ms.grabMapImage(5, 8, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -8) {
				map.Location = Map.MAP.g5;
				nextmap = tex.ms.grabMapImage(5, 7, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}

		}
		if (map.Location == Map.MAP.h6) {
			currentmap = tex.ms.grabMapImage(6, 8, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -8) {
				map.Location = Map.MAP.g6;
				nextmap = tex.ms.grabMapImage(6, 7, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= 0 && x <= 600) && y >= 584) {
				map.Location = Map.MAP.i6;
				nextmap = tex.ms.grabMapImage(6, 9, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 590) {
				map.Location = Map.MAP.h7;
				nextmap = tex.ms.grabMapImage(7, 8, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}

		}
		if (map.Location == Map.MAP.h7) {
			currentmap = tex.ms.grabMapImage(7, 8, 600, 600);
			if ((y >= 0 && y <= 600) && x <= -8) {
				map.Location = Map.MAP.h6;
				nextmap = tex.ms.grabMapImage(6, 8, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 608) {
				map.Location = Map.MAP.h8;
				nextmap = tex.ms.grabMapImage(8, 8, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.h8) {
			currentmap = tex.ms.grabMapImage(8, 8, 600, 600);
			if ((y >= 0 && y <= 600) && x <= -8) {
				map.Location = Map.MAP.h7;
				nextmap = tex.ms.grabMapImage(7, 8, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 608) {
				map.Location = Map.MAP.h9;
				nextmap = tex.ms.grabMapImage(9, 8, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.h9) {
			currentmap = tex.ms.grabMapImage(9, 8, 600, 600);
			if ((y >= 0 && y <= 600) && x <= -8) {
				map.Location = Map.MAP.h8;
				nextmap = tex.ms.grabMapImage(8, 8, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((x >= 0 && x <= 600) && y <= -8) {
				map.Location = Map.MAP.g9;
				nextmap = tex.ms.grabMapImage(9, 7, 600, 600);
				x = getX();
				y = 560;
				c.change();
			}
		}
		if (map.Location == Map.MAP.i1) {
			currentmap = tex.ms.grabMapImage(1, 9, 600, 600);
			if ((y >= -8 && y <= 608) && x >= 608) {
				map.Location = Map.MAP.i2;
				nextmap = tex.ms.grabMapImage(2, 9, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}

		}
		if (map.Location == Map.MAP.i2) {
			currentmap = tex.ms.grabMapImage(2, 9, 600, 600);
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.h2;
				nextmap = tex.ms.grabMapImage(2, 8, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.j2;
				nextmap = tex.ms.grabMapImage(2, 10, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.i1;
				nextmap = tex.ms.grabMapImage(1, 9, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((y >= -8 && y <= 608) && x >= 608) {
				map.Location = Map.MAP.i3;
				nextmap = tex.ms.grabMapImage(3, 9, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.i3) {
			currentmap = tex.ms.grabMapImage(3, 9, 600, 600);
			if ((x >= -8 && x <= 608) && y >= 608) {
				map.Location = Map.MAP.j3;
				nextmap = tex.ms.grabMapImage(3, 10, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((y >= -8 && y <= 608) && x <= -8) {
				map.Location = Map.MAP.i2;
				nextmap = tex.ms.grabMapImage(2, 9, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.h3;
				nextmap = tex.ms.grabMapImage(3, 8, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
		}
		if (map.Location == Map.MAP.i4) {
			currentmap = tex.ms.grabMapImage(4, 9, 600, 600);
			if ((y >= 0 && y <= 600) && x >= 616) {
				map.Location = Map.MAP.i5;
				nextmap = tex.ms.grabMapImage(5, 9, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
			if ((y >= 40 && y <= 90) && (x <= 90 && x >= 40)) {
				map.Location = Map.MAP.h4;
				nextmap = tex.ms.grabMapImage(4, 8, 600, 600);
				x = 50;
				y = 120;
				c.change();
			}

		}
		if (map.Location == Map.MAP.i5) {
			currentmap = tex.ms.grabMapImage(5, 9, 600, 600);
			if ((y >= 0 && y <= 600) && x <= -16) {
				map.Location = Map.MAP.i4;
				nextmap = tex.ms.grabMapImage(4, 9, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((x >= 240 && x <= 350) && y >= 584) {
				map.Location = Map.MAP.j5;
				nextmap = tex.ms.grabMapImage(5, 10, 600, 600);
				x = getX();
				y = 300;
				c.change();
			}

		}
		if (map.Location == Map.MAP.i6) {
			currentmap = tex.ms.grabMapImage(6, 9, 600, 600);
			if ((x >= 0 && x <= 600) && y >= 584) {
				map.Location = Map.MAP.j6;
				nextmap = tex.ms.grabMapImage(6, 10, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((x >= 0 && x <= 600) && y <= -16) {
				map.Location = Map.MAP.h6;
				nextmap = tex.ms.grabMapImage(6, 8, 600, 600);
				x = getX();
				y = 570;
				c.change();
			}

		}
		if (map.Location == Map.MAP.j1) {
			currentmap = tex.ms.grabMapImage(1, 10, 600, 600);
			if ((x >= 0 && x <= 600) && y >= 616) {
				map.Location = Map.MAP.k1;
				nextmap = tex.ms.grabMapImage(1, 11, 600, 600);
				x = 26;
				y = 16;
				c.change();
			}
		}
		if (map.Location == Map.MAP.j2) {
			currentmap = tex.ms.grabMapImage(2, 10, 600, 600);
			if ((y >= 0 && y <= 600) && x >= 616) {
				map.Location = Map.MAP.j3;
				nextmap = tex.ms.grabMapImage(3, 10, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
			if ((x >= 0 && x <= 600) && y <= -16) {
				map.Location = Map.MAP.i2;
				nextmap = tex.ms.grabMapImage(2, 9, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= 0 && x <= 600) && y >= 608) {
				map.Location = Map.MAP.k2;
				nextmap = tex.ms.grabMapImage(2, 11, 600, 600);
				x = getX();
				y = 8;
				c.change();
			}
		}
		if (map.Location == Map.MAP.j3) {
			currentmap = tex.ms.grabMapImage(3, 10, 600, 600);
			if ((y >= 0 && y <= 600) && x <= -16) {
				map.Location = Map.MAP.j2;
				nextmap = tex.ms.grabMapImage(2, 10, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 616) {
				map.Location = Map.MAP.j4;
				nextmap = tex.ms.grabMapImage(4, 10, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
			if ((x >= -8 && x <= 608) && y <= -8) {
				map.Location = Map.MAP.i3;
				nextmap = tex.ms.grabMapImage(3, 9, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
		}
		if (map.Location == Map.MAP.j4) {
			currentmap = tex.ms.grabMapImage(4, 10, 600, 600);
			if ((y >= 0 && y <= 600) && x >= 616) {
				map.Location = Map.MAP.j5;
				nextmap = tex.ms.grabMapImage(5, 10, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
			if ((y >= 0 && y <= 600) && x <= -16) {
				map.Location = Map.MAP.j3;
				nextmap = tex.ms.grabMapImage(3, 10, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.j5) {
			currentmap = tex.ms.grabMapImage(5, 10, 600, 600);
			if ((y >= 0 && y <= 600) && x <= -16) {
				map.Location = Map.MAP.j4;
				nextmap = tex.ms.grabMapImage(4, 10, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((x >= 240 && x <= 350) && y <= 275) {
				map.Location = Map.MAP.i5;
				nextmap = tex.ms.grabMapImage(5, 9, 600, 600);
				x = getX();
				y = 550;
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 616) {
				map.Location = Map.MAP.j6;
				nextmap = tex.ms.grabMapImage(6, 10, 600, 600);
				x = 32;
				y = getY();
				c.change();
			}

		}
		if (map.Location == Map.MAP.j6) {
			currentmap = tex.ms.grabMapImage(6, 10, 600, 600);
			if ((y >= 0 && y <= 600) && x <= -16) {
				map.Location = Map.MAP.j5;
				nextmap = tex.ms.grabMapImage(5, 10, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
			if ((x >= 0 && x <= 600) && y >= 584) {
				map.Location = Map.MAP.k6;
				nextmap = tex.ms.grabMapImage(6, 11, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}
			if ((x >= 0 && x <= 600) && y <= -16) {
				map.Location = Map.MAP.i6;
				nextmap = tex.ms.grabMapImage(6, 9, 600, 600);
				x = getX();
				y = 570;
				c.change();
			}

		}
		if (map.Location == Map.MAP.k6) {
			currentmap = tex.ms.grabMapImage(6, 11, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -16) {
				map.Location = Map.MAP.j6;
				nextmap = tex.ms.grabMapImage(6, 10, 600, 600);
				x = getX();
				y = 570;
				c.change();
			}
			if ((x >= 0 && x <= 600) && y >= 584) {
				map.Location = Map.MAP.l6;
				nextmap = tex.ms.grabMapImage(6, 12, 600, 600);
				x = getX();
				y = 16;
				c.change();
			}

		}
		if (map.Location == Map.MAP.k1) {
			currentmap = tex.ms.grabMapImage(1, 11, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -8) {
				map.Location = Map.MAP.j1;
				nextmap = tex.ms.grabMapImage(1, 10, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= 0 && x <= 600) && y >= 608) {
				map.Location = Map.MAP.l1;
				nextmap = tex.ms.grabMapImage(1, 12, 600, 600);
				x = getX();
				y = 8;
				c.change();
			}

		}
		if (map.Location == Map.MAP.k2) {
			currentmap = tex.ms.grabMapImage(2, 11, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -8) {
				map.Location = Map.MAP.j2;
				nextmap = tex.ms.grabMapImage(2, 10, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((x >= 0 && x <= 600) && y >= 608) {
				map.Location = Map.MAP.l2;
				nextmap = tex.ms.grabMapImage(2, 12, 600, 600);
				x = getX();
				y = 8;
				c.change();
			}

		}
		if (map.Location == Map.MAP.l1) {
			currentmap = tex.ms.grabMapImage(1, 12, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -8) {
				map.Location = Map.MAP.k1;
				nextmap = tex.ms.grabMapImage(1, 11, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 608) {
				map.Location = Map.MAP.l2;
				nextmap = tex.ms.grabMapImage(2, 12, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.l2) {
			currentmap = tex.ms.grabMapImage(2, 12, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -8) {
				map.Location = Map.MAP.k2;
				nextmap = tex.ms.grabMapImage(2, 11, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((y >= 0 && y <= 600) && x <= -8) {
				map.Location = Map.MAP.l1;
				nextmap = tex.ms.grabMapImage(1, 12, 600, 600);
				x = 584;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.l6) {
			currentmap = tex.ms.grabMapImage(6, 12, 600, 600);
			if ((x >= 0 && x <= 600) && y <= -16) {
				map.Location = Map.MAP.k6;
				nextmap = tex.ms.grabMapImage(6, 11, 600, 600);
				x = getX();
				y = 584;
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 584) {
				map.Location = Map.MAP.l7;
				nextmap = tex.ms.grabMapImage(7, 12, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}

		}
		if (map.Location == Map.MAP.l7) {
			currentmap = tex.ms.grabMapImage(7, 12, 600, 600);
			if ((y >= 0 && y <= 600) && x <= -16) {
				map.Location = Map.MAP.l6;
				nextmap = tex.ms.grabMapImage(6, 12, 600, 600);
				x = 570;
				y = getY();
				c.change();
			}
			if ((y >= 0 && y <= 600) && x >= 584) {
				map.Location = Map.MAP.l8;
				nextmap = tex.ms.grabMapImage(8, 12, 600, 600);
				x = 16;
				y = getY();
				c.change();
			}
		}
		if (map.Location == Map.MAP.l8) {
			currentmap = tex.ms.grabMapImage(8, 12, 600, 600);
			if ((y >= 0 && y <= 600) && x <= -16) {
				map.Location = Map.MAP.l7;
				nextmap = tex.ms.grabMapImage(7, 12, 600, 600);
				x = 570;
				y = getY();
				c.change();
			}
		}
	}

	public void render(Graphics g) {
		if (front)
			g.drawImage(tex.getPlayerFront(), (int) x, (int) y, null);
		if (back)
			g.drawImage(tex.getPlayerBack(), (int) x, (int) y, null);
		if (left)
			g.drawImage(tex.getPlayerLeft(), (int) x, (int) y, null);
		if (right)
			g.drawImage(tex.getPlayerRight(), (int) x, (int) y, null);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public void bump(double xChange, double yChange) {
		this.x += xChange;
		this.y += yChange;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}

	@Override
	public int getPower() {// whatever
		int power = (int) game.damage;
		return power;
	}
}
