import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import Entitys.EntityA;
import Entitys.EntityB;
import Entitys.EntityC;
import Entitys.EntityD;
import Entitys.EntityE;

public class Controller {

	public static LinkedList<EntityA> ea = new LinkedList<EntityA>();
	public static LinkedList<EntityB> eb = new LinkedList<EntityB>();
	public static LinkedList<EntityC> ec = new LinkedList<EntityC>();
	public static LinkedList<EntityD> ed = new LinkedList<EntityD>();
	public static LinkedList<EntityE> ee = new LinkedList<EntityE>();
	static EntityB entb;
	static EntityA enta;
	static EntityC entc;
	static EntityD entd;
	static EntityE ente;
	private static Textures tex;
	static Game game;
	static Random r = new Random();
	Player p;
	static boolean replenish;
	static boolean battle;
	static int rewards;
	static Audio reward_sound = new Audio("/coins.wav");

	public Controller(Textures tex, Game game) {
		Controller.setTex(tex);
		Controller.game = game;

	}

	public static void enemyKilled(EntityB entb) {
		int mone = r.nextInt(Game.mone) + 1;
		int mone2 = r.nextInt(Game.mone) + 1;
		int mone3 = r.nextInt(Game.mone) + 1;

		if (eb.size() == 0 && battle == true) {
			Game.CASH += (mone + mone2 + mone3) * rewards;

			for (int i = 0; rewards >= 1; i++) {
				int rando = r.nextInt(100) + 1;
				if (rando <= 25) {
					Game.CURRENTHEALTH += 25;
					System.out.println("Health dropped!");
				}
				Game.score++;

				if (Game.CURRENTHEALTH >= Game.MAXHEALTH)
					Game.CURRENTHEALTH = Game.MAXHEALTH;
				if (i == 0)
					System.out.println("Rewards earned: " + rewards);
				rewards--;
				reward_sound.play();

			}

			if (replenish == true) {
				Game.CURRENTHEALTH = Game.MAXHEALTH;
				Game.ENERGY = 100;
				Game.CASH += 100;
				Game.score += 10;
				replenish = false;
			}
			battle = false;

		}
		for (int i = 0; i < eb.size(); i++) {

			EntityB tempEntB = eb.get(i);

			if (eb.size() != 0) {
				battle = true;

				if (tempEntB.getBoss() == true || tempEntB.getBoss2() == true) {
					replenish = true;
				}
			}
		}

	}

	public static void change() {

		for (int i = 0; i < ea.size(); i++) {// a
			enta = ea.get(i);
			enta.tick();

			while (ea.size() != 0)
				removeEntity(ea.get(0));
		}
		for (int i = 0; i < ee.size(); i++) {// e
			ente = ee.get(i);
			ente.tick();

			while (ee.size() != 0)
				removeEntity(ee.get(0));
		}
		for (int i = 0; i < ed.size(); i++) {// d
			entd = ed.get(i);
			entd.tick();

			while (ed.size() != 0)
				removeEntity(ed.get(0));
		}

		for (int i = 0; i < ec.size(); i++) {// e
			entc = ec.get(i);
			entc.tick();
			while (ec.size() != 0)
				removeEntity(ec.get(0));

		}
		for (int i = 0; i < eb.size(); i++) {// b
			entb = eb.get(i);
			entb.tick();
			while (eb.size() != 0)
				removeEntity(eb.get(0));

		}

		if (Game.hyper)
			Game.speed /= 1.5;
		Game.hyper = false;
		Game.stopped = false;
		Game.noDamage = false;
		Game.nukedo = false;
		Game.started = false;

	}

	///////////////
	public void tick() {
		// A
		for (int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			enta.tick();
			if (enta.getY() < -50)
				removeEntity(enta);
			if (enta.getX() < -50)
				removeEntity(enta);
			if (enta.getY() > 650)
				removeEntity(enta);
			if (enta.getX() > 650)
				removeEntity(enta);
		}
		// b
		for (int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			entb.tick();

		}
		// d
		for (int i = 0; i < ed.size(); i++) {
			entd = ed.get(i);
			entd.tick();
			if (entd.getY() < -50)
				removeEntity(entd);
			if (entd.getX() < -50)
				removeEntity(entd);
			if (entd.getY() > 650)
				removeEntity(entd);
			if (entd.getX() > 650)
				removeEntity(entd);
		}

		// c
		for (int i = 0; i < ec.size(); i++) {
			entc = ec.get(i);
			entc.tick();

		}

		// e
		for (int i = 0; i < ee.size(); i++) {
			ente = ee.get(i);
			ente.tick();

		}
	}

	/////////////
	public void render(Graphics g) {
		// a
		for (int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			enta.render(g);
		}
		// b
		for (int i = 0; i < eb.size(); i++) {
			entb = eb.get(i);
			entb.render(g);
		}
		// d
		for (int i = 0; i < ed.size(); i++) {
			entd = ed.get(i);
			entd.render(g);
		}
		// e
		for (int i = 0; i < ee.size(); i++) {
			ente = ee.get(i);
			ente.render(g);
		}

	}

	public void addEntity(EntityA block) {

		ea.add(block);
	}

	public static void removeEntity(EntityA block) {

		ea.remove(block);

	}

	public void addEntity(EntityB block) {

		eb.add(block);
	}

	public static void removeEntity(EntityB block) {

		eb.remove(block);
	}

	public void addEntity(EntityC block) {

		ec.add(block);
	}

	public static void removeEntity(EntityC block) {

		ec.remove(block);
	}

	public void addEntity(EntityD block) {

		ed.add(block);
	}

	public static void removeEntity(EntityD block) {

		ed.remove(block);
	}

	public static void addEntity(EntityE block) {

		ee.add(block);
	}

	public static void removeEntity(EntityE block) {

		ee.remove(block);
	}

	public static LinkedList<EntityA> getEntityA() {
		return ea;
	}

	public static LinkedList<EntityB> getEntityB() {
		return eb;
	}

	public static LinkedList<EntityC> getEntityC() {
		return ec;
	}

	public static LinkedList<EntityD> getEntityD() {
		return ed;
	}

	public static LinkedList<EntityE> getEntityE() {
		return ee;
	}

	public static Textures getTex() {
		return tex;
	}

	public static void setTex(Textures tex) {
		Controller.tex = tex;
	}

}
