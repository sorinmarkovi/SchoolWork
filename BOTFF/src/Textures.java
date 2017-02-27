import java.awt.Image;
import java.awt.image.BufferedImage;

public class Textures {

	public static BufferedImage player, missle, enemy, startingMap, drop;
	SpriteSheet ps = null;// sprites
	SpriteSheet ms = null;// maps
	SpriteSheet bs = null;// bullets
	PlayerBullet b;
	Player p;
	Map m;

	public Textures(Game game) {
		ps = new SpriteSheet(game.getPlayerSheet());
		ms = new SpriteSheet(game.getMapSheet());
		bs = new SpriteSheet(game.getBulletSheet());
	}

	public BufferedImage getPlayerFront() {

		if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {

			player = ps.grabPlayerImage(1, 2, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
			player = ps.grabPlayerImage(1, 6, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.SONIC) {
			player = ps.grabPlayerImage(1, 7, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {

			player = ps.grabPlayerImage(1, 4, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {
			player = ps.grabPlayerImage(1, 5, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
			player = ps.grabPlayerImage(1, 1, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.MCD) {
			player = ps.grabPlayerImage(1, 3, 64, 64);
			return player;

		}
		return null;

	}

	public BufferedImage getPlayerBack() {
		if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {

			player = ps.grabPlayerImage(2, 2, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
			player = ps.grabPlayerImage(2, 6, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.SONIC) {
			player = ps.grabPlayerImage(2, 7, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {
			player = ps.grabPlayerImage(2, 4, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {
			player = ps.grabPlayerImage(2, 5, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
			player = ps.grabPlayerImage(2, 1, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.MCD) {
			player = ps.grabPlayerImage(2, 3, 64, 64);
			return player;

		}
		return null;
	}

	public BufferedImage getPlayerLeft() {
		if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {

			player = ps.grabPlayerImage(3, 2, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
			player = ps.grabPlayerImage(3, 6, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.SONIC) {
			player = ps.grabPlayerImage(3, 7, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {

			player = ps.grabPlayerImage(3, 4, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {
			player = ps.grabPlayerImage(3, 5, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
			player = ps.grabPlayerImage(3, 1, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.MCD) {
			player = ps.grabPlayerImage(3, 3, 64, 64);
			return player;

		}
		return null;
	}

	public BufferedImage getPlayerRight() {
		if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {

			player = ps.grabPlayerImage(4, 2, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
			player = ps.grabPlayerImage(4, 6, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.SONIC) {
			player = ps.grabPlayerImage(4, 7, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {
			player = ps.grabPlayerImage(4, 4, 64, 64);
			return player;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {
			player = ps.grabPlayerImage(4, 5, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
			player = ps.grabPlayerImage(4, 1, 64, 64);
			return player;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.MCD) {
			player = ps.grabPlayerImage(4, 3, 64, 64);
			return player;

		}

		return null;
	}

	public BufferedImage getStartingMap() {

		if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {

			startingMap = ms.grabMapImage(2, 1, 600, 600);
			return startingMap;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
			startingMap = ms.grabMapImage(9, 7, 600, 600);
			return startingMap;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.SONIC) {

			startingMap = ms.grabMapImage(8, 2, 600, 600);
			return startingMap;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {

			startingMap = ms.grabMapImage(1, 11, 600, 600);
			return startingMap;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {

			startingMap = ms.grabMapImage(7, 12, 600, 600);
			return startingMap;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
			startingMap = ms.grabMapImage(5, 9, 600, 600);
			return startingMap;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.MCD) {
			startingMap = ms.grabMapImage(4, 6, 600, 600);
			return startingMap;

		}
		return null;

	}

	public BufferedImage getDBullet() {

		if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {

			missle = bs.grabBulletImage(8, 1, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
			missle = bs.grabBulletImage(4, 3, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.SONIC) {
			missle = bs.grabBulletImage(5, 1, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {

			missle = bs.grabBulletImage(1, 2, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {

			missle = bs.grabBulletImage(2, 1, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
			missle = bs.grabBulletImage(6, 1, 32, 32);
			return missle;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.MCD) {
			missle = bs.grabBulletImage(4, 1, 32, 32);
			return missle;

		}
		return null;
	}

	public BufferedImage getUBullet() {

		if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {

			missle = bs.grabBulletImage(8, 1, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
			missle = bs.grabBulletImage(3, 1, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.SONIC) {
			missle = bs.grabBulletImage(5, 1, 32, 32);
			return missle;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {

			missle = bs.grabBulletImage(1, 2, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {

			missle = bs.grabBulletImage(2, 1, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
			missle = bs.grabBulletImage(6, 1, 32, 32);
			return missle;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.MCD) {
			missle = bs.grabBulletImage(4, 1, 32, 32);
			return missle;

		}
		return null;
	}

	public BufferedImage getLBullet() {

		if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {
			missle = bs.grabBulletImage(4, 2, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
			missle = bs.grabBulletImage(7, 2, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.SONIC) {
			missle = bs.grabBulletImage(5, 3, 32, 32);
			return missle;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {

			missle = bs.grabBulletImage(6, 2, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {
			missle = bs.grabBulletImage(2, 1, 32, 32);
			return missle;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
			missle = bs.grabBulletImage(6, 1, 32, 32);
			return missle;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.MCD) {

			missle = bs.grabBulletImage(8, 2, 32, 32);
			return missle;
		}
		return null;
	}

	public BufferedImage getRBullet() {

		if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {
			missle = bs.grabBulletImage(4, 2, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
			missle = bs.grabBulletImage(3, 3, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.SONIC) {
			missle = bs.grabBulletImage(5, 3, 32, 32);
			return missle;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {

			missle = bs.grabBulletImage(6, 2, 32, 32);
			return missle;
		}
		if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {
			missle = bs.grabBulletImage(2, 1, 32, 32);
			return missle;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
			missle = bs.grabBulletImage(6, 1, 32, 32);
			return missle;

		}
		if (Game.PlayerFaction == Game.PlayerFaction.MCD) {

			missle = bs.grabBulletImage(8, 2, 32, 32);
			return missle;
		}
		return null;
	}

	public BufferedImage getEnemyBullet() {
		missle = bs.grabBulletImage(1, 1, 32, 32);
		return missle;
	}

	public BufferedImage getEnemy() {
		if (m.Location == m.Location.a1) {
			enemy = ps.grabPlayerImage(1, 2, 64, 64);
			return enemy;

		}

		if (m.Location == m.Location.a2) {
			enemy = ps.grabPlayerImage(1, 2, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.b1) {
			enemy = ps.grabPlayerImage(1, 2, 64, 64);
			return enemy;

		}

		if (m.Location == m.Location.b2) {
			enemy = ps.grabPlayerImage(1, 2, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.h4) {
			enemy = ps.grabPlayerImage(1, 1, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.i3) {
			enemy = ps.grabPlayerImage(1, 1, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.i4) {
			enemy = ps.grabPlayerImage(1, 1, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.i5) {
			enemy = ps.grabPlayerImage(1, 1, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.j3) {
			enemy = ps.grabPlayerImage(1, 1, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.j4) {
			enemy = ps.grabPlayerImage(1, 1, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.j5) {
			enemy = ps.grabPlayerImage(1, 1, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.e4) {
			enemy = ps.grabPlayerImage(1, 3, 64, 64);
			return enemy;

		}

		if (m.Location == m.Location.f4) {
			enemy = ps.grabPlayerImage(1, 3, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.g4) {
			enemy = ps.grabPlayerImage(1, 3, 64, 64);
			return enemy;
		}
		if (m.Location == m.Location.f5) {
			enemy = ps.grabPlayerImage(1, 3, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.f8) {
			enemy = ps.grabPlayerImage(1, 6, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.f9) {
			enemy = ps.grabPlayerImage(1, 6, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.f10) {
			enemy = ps.grabPlayerImage(1, 6, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.g5) {
			enemy = ps.grabPlayerImage(1, 3, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.g8) {
			enemy = ps.grabPlayerImage(1, 6, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.g9) {
			enemy = ps.grabPlayerImage(1, 6, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.g10) {
			enemy = ps.grabPlayerImage(1, 6, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.e5) {
			enemy = ps.grabPlayerImage(1, 3, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.h8) {
			enemy = ps.grabPlayerImage(1, 6, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.h9) {
			enemy = ps.grabPlayerImage(1, 6, 64, 64);
			return enemy;

		}
		///////////////////////
		if (m.Location == m.Location.i1) {
			enemy = ps.grabPlayerImage(1, 4, 64, 64);
			return enemy;

		}

		if (m.Location == m.Location.i2) {
			enemy = ps.grabPlayerImage(1, 4, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.j1) {
			enemy = ps.grabPlayerImage(1, 4, 64, 64);
			return enemy;

		}

		if (m.Location == m.Location.j2) {
			enemy = ps.grabPlayerImage(1, 4, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.k1) {
			enemy = ps.grabPlayerImage(1, 4, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.k2) {
			enemy = ps.grabPlayerImage(1, 4, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.l1) {
			enemy = ps.grabPlayerImage(1, 4, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.l2) {
			enemy = ps.grabPlayerImage(1, 4, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.l6) {
			enemy = ps.grabPlayerImage(1, 5, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.l7) {
			enemy = ps.grabPlayerImage(1, 5, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.l8) {
			enemy = ps.grabPlayerImage(1, 5, 64, 64);
			return enemy;

		}
		if (m.Location == m.Location.l9) {
			enemy = ps.grabPlayerImage(1, 5, 64, 64);
			return enemy;

		}

		return null;

	}

	public BufferedImage getDrops() {
		drop = bs.grabBulletImage(8, 3, 32, 32);
		return drop;
	}

	public Image getBullet() {
		// TODO Auto-generated method stub
		return null;
	}

}
