import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import Entitys.EntityA;
import Entitys.EntityB;
import Entitys.EntityC;
import Entitys.EntityD;
import Entitys.EntityE;
public class Game extends Canvas implements Runnable {
	final String patch = "v0.9.1";
	static int currentLevel = 1;
	static int timer;
	int updates;
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 750;
	public static final int HEIGTH = 600;
	public static final int SCALE = 1;
	public final String TITLE = "Battle of the Fast Foods";
	private boolean running = false;
	private Thread thread;
	static int key;
	private BufferedImage background = null;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage mapSheet = null;
	private BufferedImage bulletSheet = null;
	private BufferedImage playerSheet = null;
	private int enemy_count = 1;
	private int enemy_killed = 0;
	Menu menu;
	Map map;
	Wall w;
	HowToPlay howtoplay;
	FactionPick factionpick;
	HUD hud;
	PlayerBullet b;
	Enemy e;
	Audio menuMusic;// check
	Audio gameMusic;// check
	Audio playerShot;// check
	Audio pickUp;// check
	Audio bossDefeated;// yay
	Audio walk;//
	Audio braums_special;
	static int score;
	static boolean active = false;
	static int degen = 5;
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	public LinkedList<EntityC> ec;
	public LinkedList<EntityD> ed;
	public LinkedList<EntityE> ee;
	public static String special;
	public static double CURRENTHEALTH = 100, CASH = 100, MAXHEALTH = 100;
	public static boolean border = true;
	public static boolean akimbo = false;
	public static boolean pierce = false;
	public static boolean poison = false;
	public static boolean crazy = false;
	public static boolean ddamage = false;
	public static boolean cannon = false;
	private boolean shooting = false;
	public static boolean stopped = false;
	public static boolean noDamage = false;
	public static boolean nukedo = false;
	public static double ENERGY = 100;
	public static boolean endgame = false, pause = false, winner = false, homeland = false, pharmacy = false;
	public static double bulletspeed = 10, speed = 5, eregen = 0, hpregen = 0, damage = 10, steal = 0, gok = 0;
	public static boolean started = false;
	public static int bossKilled = 0;
	public static int mone = 5;
	public static boolean hyper, inv, nuke, stop, hpregenItem;
	public static int eregenCost = 25, stealCost = 500, milkCost = 100, luckerCost = 250, hyperCost = 1500,
			invCost = 3000, nukeCost = 2000, stopCost = 1000, hpregenCost = 150, healthCost = 500;
	public static enum STATE {
		MENU, GAME, HOWTOPLAY, FACTIONPICK,
	};
	public static boolean // all ENEMYs KILLED
	a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, // a row
	b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, // b row
	c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, // c row
	d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, // d row
	e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, // e row
	f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, // f row
	g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, // g row
	h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, // h row
	i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, // i row
	j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, // j row
	k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, // k row
	l1, l2, l3, l4, l5, l6, l7, l8, l9, l10; // l row
	public static STATE State = STATE.MENU;
	public static Faction.FACTION PlayerFaction;
	public static Faction.FACTION EnemyFaction;
	public static Faction.FACTION BulletFaction;
	public static synchronized void play(final String fileName) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
				}
			}
		}).start();
	}
	public int getEnemy_count() {
		return enemy_count;
	}
	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}
	public int getEnemy_killed() {
		return enemy_killed;
	}
	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	private Player p;
	private Controller c;
	Textures tex;
	public void init() 
	{
		requestFocus(); //Calls focus to the application. 
		Loader load = new Loader(); //gets the path to the files. 
		try {
			//getting the pictures
			background = load.loadImage("/background.png");
			mapSheet = load.loadImage("/Map Sheet.png");
			bulletSheet = load.loadImage("/Bullet Sheet.png");
			playerSheet = load.loadImage("/Player Sheet.png");

		} catch (IOException e) {
			e.printStackTrace();
		}

		addKeyListener(new Key(this)); 	//listens to the keyboard
		addMouseListener(new Mouse());	//listens to the muse. 
		tex = new Textures(this);		//sets textures. 
		p = new Player(300, 300, tex);	//creates player
		c = new Controller(tex, this);	//ton of crap
		menu = new Menu();				//loads start/pause
		howtoplay = new HowToPlay();	//how to play
		factionpick = new FactionPick();//picks the faction
		hud = new HUD();
		map = new Map();
		ea = Controller.getEntityA();			//player
		eb = Controller.getEntityB();			//enemy
		ec = Controller.getEntityC();			//wall
		ed = Controller.getEntityD();			//bullets
		ee = Controller.getEntityE();			//loot
		menuMusic = new Audio("/flan.wav");
		gameMusic = new Audio("/guile.wav");
		menuMusic.play();
		playerShot = new Audio("/swoosh 3.wav");
		pickUp = new Audio("/coins.wav");
		walk = new Audio("/LEFTWK2.wav");
		bossDefeated = new Audio("/success.wav");
		braums_special = new Audio("/braums_special.wav");
	}
	public synchronized void start() {

		if (running)
			return;
		running = true;

		thread = new Thread(this);
		thread.start();
		//starts the game and stuff. 
	}
	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0; //tick rate; bullets and ms go off it. 
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			//rendering
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				delta--;
				updates++;

			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				//regenerations
				timer += 1000;
				System.out.println(updates + " Ticks, fps " + frames);
				updates = 0;
				frames = 0;
				if (State == STATE.GAME && pause == false && endgame == false) {

					if (active) {
						if (ENERGY >= 0)
							ENERGY -= degen;
						if (ENERGY <= 0) {
							akimbo = false;
							pierce = false;
							poison = false;
							crazy = false;
							cannon = false;
							ddamage = false;

							active = false;
						}
					}

					else if (ENERGY != 100 && ENERGY >= 0 && !active) // energy
																		// regen
						ENERGY += eregen;
					if (ENERGY >= 100)
						ENERGY = 100;

					if ((CURRENTHEALTH <= MAXHEALTH))
						CURRENTHEALTH += hpregen;
					if (CURRENTHEALTH >= MAXHEALTH)
						CURRENTHEALTH = MAXHEALTH;
				}
			}

		}
		stop();
	}
	private void tick() {
		if (State == STATE.GAME) {
			if (CURRENTHEALTH <= 0) {
				State = STATE.MENU;
				endgame = !endgame;
			}
			if (bossKilled == 5) {
				State = STATE.MENU;
				endgame = !endgame;
				bossDefeated.play();
			}
			p.tick();
			c.tick();

			if (Map.Location == map.Location.a1 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(182, 103, 80, 89));
				c.addEntity(new Wall(383, 169, 80, 89));
				c.addEntity(new Wall(101, 305, 80, 89));
				c.addEntity(new Wall(258, 414, 80, 89));
				c.addEntity(new Wall(420, 358, 80, 89));
				c.addEntity(new Wall(0, 0, 24, 600));
				c.addEntity(new Wall(0, 0, 600, 22));
				c.addEntity(new Wall(0, 576, 600, 24));
				c.addEntity(new Wall(576, 0, 24, 30));
				c.addEntity(new Wall(576, 129, 24, 600));
				if (!a1) {
					c.addEntity(new Enemy(300, 300, "SUBWAY", 1000 * currentLevel, tex, true, false));
					c.addEntity(new Enemy(182 + 8, 103 + 13, "SUBWAY", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(383 + 8, 169 + 13, "SUBWAY", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(101 + 8, 305 + 13, "SUBWAY", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(258 + 8, 414 + 13, "SUBWAY", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(420 + 8, 358 + 13, "SUBWAY", 100 * currentLevel, tex, false, false));
					c.rewards = 5;
				}
			}
			if (map.Location == map.Location.a2 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(400, 25, 100, 449));
				c.addEntity(new Wall(576, 0, 64, 600));
				c.addEntity(new Wall(0, 576, 269, 64));
				c.addEntity(new Wall(390, 574, 600, 24));
				c.addEntity(new Wall(0, 130, 24, 600));
				c.addEntity(new Wall(0, 0, 600, 28));
				c.addEntity(new Wall(25, 122, 160, 155));
				c.addEntity(new Wall(25, 421, 170, 170));
				if (!a2) {
					c.addEntity(new Enemy(100, 300, "SUBWAY", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(300, 200, "SUBWAY", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(515, 30, "SUBWAY", 100 * currentLevel, tex, false, false));
					c.rewards = 3;
				}
			}
			if (map.Location == map.Location.a3 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(452, 0, 63, 600));
				c.addEntity(new Wall(1, 0, 24, 600));
				c.addEntity(new Wall(-5, -20, 600, 25));
				if (!a3) {

				}
			}
			if (map.Location == map.Location.b1 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, -5, 605, 102));
				c.addEntity(new Wall(0, -5, 5, 605));
				if (!b1) {

				}
			}
			if (map.Location == map.Location.b2 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 269, 103));
				c.addEntity(new Wall(369, 0, 300, 98));
				c.addEntity(new Wall(-5, -5, 25, 610));
				c.addEntity(new Wall(575, -5, 25, 610));

				if (!b2) {
					c.addEntity(new Enemy(90, 100, "SUBWAY", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(450, 100, "SUBWAY", 200 * currentLevel, tex, false, false));
					Controller.rewards = 2;
				}
			}
			if (map.Location == map.Location.b3 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(1, 0, 24, 108));
				c.addEntity(new Wall(452, 0, 63, 600));
				if (!b3) {

				}
			}
			if (map.Location == map.Location.c2 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 25, 610));// left
				c.addEntity(new Wall(575, -5, 25, 610));// right
				if (!c2) {

				}
			}
			if (map.Location == map.Location.d2 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 30, 600));
				c.addEntity(new Wall(575, -5, 25, 610));//right
				if (!d2) {

				}
			}
			if (map.Location == map.Location.e2 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, -10, 30, 270));
				c.addEntity(new Wall(0, 360, 30, 300));

				if (!e2) {

				}
			}
			if (map.Location == map.Location.e3 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(575, 575, 25, 25));
				c.addEntity(new Wall(-5, -5, 610, 25));// top

			}
			if (map.Location == map.Location.e4 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(349, 575, 600, 25));
				c.addEntity(new Wall(0, 575, 249, 25));
				c.addEntity(new Wall(-5, -5, 610, 25));// top
				if (!e4) {
					c.addEntity(new Enemy(200, 500, "MCD", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(350, 500, "MCD", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(275, 100, "MCD", 100 * currentLevel, tex, false, false));
					c.rewards = 3;
				}
			}
			if (map.Location == map.Location.e5 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(-10, 570, 40, 30));
				c.addEntity(new Wall(587, -10, 13, 620));
				c.addEntity(new Wall(575, -5, 25, 610));// right
				c.addEntity(new Wall(-5, -5, 610, 25));// top
				if (!e5) {

				}
			}
			if (map.Location == map.Location.f2 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, -10, 30, 270));
				c.addEntity(new Wall(0, 360, 30, 300));

				if (!f2) {

				}
			}
			if (map.Location == map.Location.f3 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(-20, 575, 25, 640));
				c.addEntity(new Wall(575, 575, 25, 25));
				c.addEntity(new Wall(575, -5, 25, 610));// right
				if (!f3) {

				}
			}
			if (map.Location == map.Location.f4 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 25, 600));
				c.addEntity(new Wall(0, 0, 249, 25));
				c.addEntity(new Wall(349, 0, 600, 25));
				c.addEntity(new Wall(575, 0, 25, 600));
				c.addEntity(new Wall(25, 121, 176, 155));
				c.addEntity(new Wall(25, 395, 176, 155));
				c.addEntity(new Wall(399, 25, 176, 155));
				c.addEntity(new Wall(399, 270, 176, 155));
				if (!f4) {
					c.addEntity(new Enemy(30, 30, "MCD", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(500, 200, "MCD", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(30, 300, "MCD", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(500, 500, "MCD", 100 * currentLevel, tex, false, false));
					c.rewards = 4;
				}
			}
			if (map.Location == map.Location.f5 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 25, 600));
				c.addEntity(new Wall(26, 300, 361, 91));
				c.addEntity(new Wall(26, 300, 80, 300));
				c.addEntity(new Wall(214, 498, 386, 102));
				c.addEntity(new Wall(480, 300, 120, 300));
				c.addEntity(new Wall(587, 0, 13, 198));
				c.addEntity(new Wall(575, -5, 25, 610));// right
				if (!f5) {
					c.addEntity(new Enemy(325, 220, "MCD", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(500, 220, "MCD", 200 * currentLevel, tex, false, false));
					c.rewards = 2;

				}
			}
			if (map.Location == map.Location.f10 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 600, 29));
				c.addEntity(new Wall(571, 0, 29, 600));
				c.addEntity(new Wall(392, 59, 133, 262));
				c.addEntity(new Wall(67, 401, 262, 133));

				if (!f10) {

					c.addEntity(new Enemy(430, 190, "BRAUMS", 205 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(160, 455, "BRAUMS", 205 * currentLevel, tex, false, false));
					c.rewards = 2;

				}
			}
			if (map.Location == map.Location.f9 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 600, 29));
				c.addEntity(new Wall(0, 0, 29, 249));
				c.addEntity(new Wall(0, 351, 29, 249));
				c.addEntity(new Wall(127, 101, 473, 99));
				c.addEntity(new Wall(126, 401, 473, 99));

				if (!f9) {

					c.addEntity(new Enemy(220, 210, "BRAUMS", 175 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(440, 210, "BRAUMS", 175 * currentLevel, tex, false, false));
					c.rewards = 2;

				}
			}
			if (map.Location == map.Location.f8 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 29, 600));
				c.addEntity(new Wall(0, 0, 600, 29));
				c.addEntity(new Wall(571, 0, 29, 249));
				c.addEntity(new Wall(571, 351, 29, 249));
				c.addEntity(new Wall(301, 60, 150, 150));
				c.addEntity(new Wall(69, 318, 150, 150));

				if (!f8) {

					c.addEntity(new Enemy(50, 150, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(150, 150, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(50, 50, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(150, 50, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.rewards = 4;

				}
			}
			if (map.Location == map.Location.g1 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 25, 610));// left
				c.addEntity(new Wall(-5, -5, 610, 25));// top
				c.addEntity(new Wall(-5, 575, 610, 25));// bottom
				c.addEntity(new Wall(-20, 575, 25, 640));

				if (!g1) {

				}
			}
			if (map.Location == map.Location.g3 && Controller.ec.size() == 0) {

				c.addEntity(new Wall(-20, 575, 25, 640));
				c.addEntity(new Wall(575, -5, 25, 610));// right
				if (!g3) {

				}
			}
			if (map.Location == map.Location.g4 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 25, 600));
				c.addEntity(new Wall(0, 575, 600, 25));
				c.addEntity(new Wall(575, 0, 25, 600));

				c.addEntity(new Wall(125, 292, 450, 100));

				if (!g4) {

					c.addEntity(new Enemy(120, 300, "MCD", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(220, 300, "MCD", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(320, 300, "MCD", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(420, 300, "MCD", 100 * currentLevel, tex, false, false));
					c.rewards = 4;
					c.addEntity(new Enemy(500, 500, "MCD", 2000 * currentLevel, tex, false, true));

				}
			}
			if (map.Location == map.Location.g5 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 110, 25));
				c.addEntity(new Wall(210, 0, 600, 25));
				c.addEntity(new Wall(0, 0, 25, 600));
				c.addEntity(new Wall(575, 0, 25, 600));
				c.addEntity(new Wall(0, 575, 250, 25));
				c.addEntity(new Wall(350, 575, 600, 25));

				c.addEntity(new Wall(310, 66, 150, 150));
				c.addEntity(new Wall(37, 230, 150, 150));
				c.addEntity(new Wall(409, 385, 150, 150));

				if (!g5) {
					c.addEntity(new Enemy(50, 50, "MCD", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(50, 450, "MCD", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(450, 300, "MCD", 200 * currentLevel, tex, false, false));
					c.rewards = 3;

				}
			}
			if (map.Location == map.Location.g9 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 571, 249, 29));
				c.addEntity(new Wall(350, 571, 250, 29));
				c.addEntity(new Wall(571, 0, 29, 600));
				c.addEntity(new Wall(1, 82, 572, 92));

				if (!g9) {

					c.addEntity(new Enemy(50, 3, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(500, 3, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(450, 300, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.rewards = 3;

				}
			}
			if (map.Location == map.Location.g8 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 29, 600));
				c.addEntity(new Wall(0, 572, 600, 29));
				c.addEntity(new Wall(222, 252, 133, 600));
				c.addEntity(new Wall(222, 0, 133, 253));
				c.addEntity(new Wall(122, 252, 278, 58));

				if (!g8) {

					c.addEntity(new Enemy(50, 150, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(50, 450, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(270, 100, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(270, 400, "BRAUMS", 75 * currentLevel, tex, false, false));
					c.rewards = 4;

				}
			}
			if (map.Location == map.Location.g10 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 29, 600));
				c.addEntity(new Wall(571, 0, 29, 600));
				c.addEntity(new Wall(0, 571, 600, 29));
				c.addEntity(new Wall(347, 351, 223, 123));

				if (!g10) {

					c.addEntity(new Enemy(100, 100, "BRAUMS", 205 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(100, 270, "BRAUMS", 205 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(450, 100, "BRAUMS", 205 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(450, 270, "BRAUMS", 205 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(300, 500, "BRAUMS", 1500 * currentLevel, tex, false, true));
					c.rewards = 4;

				}
			}
			if (map.Location == map.Location.h2 && Controller.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 25 , 610));//left
				if (!h2) {

				}
			}
			if (map.Location == map.Location.h3 && c.ec.size() == 0) {
				c.addEntity(new Wall(575, -5, 25, 610));//right
				if (!h3) {

				}
			}

			if (map.Location == map.Location.h4 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, -5, 25, 600));
				c.addEntity(new Wall(0, -5, 600, 30));
				c.addEntity(new Wall(575, 0, 25, 600));
				c.addEntity(new Wall(0, 575, 600, 25));
				c.addEntity(new Wall(331, 367, 224, 123));
				if (!h4) {
					c.addEntity(new Enemy(400, 510, "ARBYS", 1000 * currentLevel, tex, false, true));
					c.addEntity(new Enemy(510, 48, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(28, 300, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(250, 400, "ARBYS", 100 * currentLevel, tex, false, false));
					c.rewards = 3;

				}
			}
			if (map.Location == map.Location.h5 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 25, 600));
				c.addEntity(new Wall(0, 575, 110, 25));
				c.addEntity(new Wall(210, 575, 600, 25));
				c.addEntity(new Wall(575, 0, 25, 600));
				c.addEntity(new Wall(0, 575, 600, 25));
				if (!h5) {
				}
			}
			if (map.Location == map.Location.h6 && c.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 25 , 610));//left
				c.addEntity(new Wall(-5, -5, 610 , 25));//top
				if (!h6) {

				}
			}
			if (map.Location == map.Location.h7 && c.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 610 , 25));//top
				c.addEntity(new Wall(-5, 575, 610, 25));//bottom
				if (!h7) {

				}
			}
			if (map.Location == map.Location.h8 && c.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 610 , 25));//top
				c.addEntity(new Wall(-5, 575, 610, 25));//bottom
				if (!h8) {

				}
			}
			if (map.Location == map.Location.h9 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 249, 25));
				c.addEntity(new Wall(349, 0, 600, 25));
				c.addEntity(new Wall(575, -5, 25, 610));//right
				c.addEntity(new Wall(-5, 575, 610, 25));//bottom
				if (!h9) {
					c.addEntity(new Enemy(275, 300, "BRAUMS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(170, 55, "BRAUMS", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(330, 55, "BRAUMS", 200 * currentLevel, tex, false, false));
					c.rewards = 3;

				}
			}
			if (map.Location == map.Location.i1 && c.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 25 , 610));//left
				c.addEntity(new Wall(-5, -5, 610 , 25));//top
				c.addEntity(new Wall(-5, 575, 610, 25));//bottom
				
				c.addEntity(new Wall(149, 275, 600, 50));
				

				if (!i1) {
					c.addEntity(new Enemy(100, 450, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 500, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(300, 400, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(400, 350, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.rewards = 4;
				}
			}
			if (map.Location == map.Location.i2 && c.ec.size() == 0) {

				c.addEntity(new Wall(0, 575, 25, 25));
				c.addEntity(new Wall(11, 275, 300, 50));
				c.addEntity(new Wall(277, 325, 50, 300));

				if (!i2) {
					c.addEntity(new Enemy(100, 450, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 400, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.rewards = 2;
				}
			}
			if (map.Location == map.Location.i3 && c.ec.size() == 0) {

				c.addEntity(new Wall(575, -5, 620, 25));
				c.addEntity(new Wall(550, -5, 20, 50));
				c.addEntity(new Wall(575, -5, 25, 610));//right
				if (!i3) {
					c.addEntity(new Enemy(100, 450, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 400, "ARBYS", 100 * currentLevel, tex, false, false));
					c.rewards = 2;
				}
			}
			if (map.Location == map.Location.i4 && c.ec.size() == 0) {

				c.addEntity(new Wall(0, -5, 650, 30));
				c.addEntity(new Wall(0, 0, 30, 650));
				c.addEntity(new Wall(306, 109, 90, 279));
				c.addEntity(new Wall(118, 298, 187, 90));
				c.addEntity(new Wall(0, 572, 650, 30));
				if (!i4) {

					c.addEntity(new Enemy(26, 300, "ARBYS", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(300, 26, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(225, 225, "ARBYS", 200 * currentLevel, tex, false, false));
					c.rewards = 3;

				}
			}
			if (map.Location == map.Location.i5 && c.ec.size() == 0) {
				c.addEntity(new Wall(94, 78, 127, 127));
				c.addEntity(new Wall(391, 77, 127, 127));
				c.addEntity(new Wall(405, 350, 127, 127));
				c.addEntity(new Wall(0, -5, 650, 30));
				c.addEntity(new Wall(572, 0, 30, 650));
				c.addEntity(new Wall(0, 572, 248, 30));
				c.addEntity(new Wall(348, 572, 650, 30));
				if (!i5) {
					c.addEntity(new Enemy(94 + 30, 78 + 36, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(391 + 30, 77 + 36, "ARBYS", 50 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(405 + 30, 350 + 36, "ARBYS", 50 * currentLevel, tex, false, false));
					c.rewards = 3;
				}
			}
			if (map.Location == map.Location.i6 && c.ec.size() == 0) {
				
				c.addEntity(new Wall(-5, -5, 25 , 610));//left
				c.addEntity(new Wall(575, -5, 25, 610));//right
				if (!i6) {
				
				}
			}
			if (map.Location == map.Location.j1 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 25, 600));
				c.addEntity(new Wall(0, 0, 600, 25));
				c.addEntity(new Wall(576, 0, 25, 600));

				if (!j1) {
					c.addEntity(new Enemy(275, 26, "TACOBELL", 1337 * currentLevel, tex, true, true));
					c.addEntity(new Enemy(80, 200, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(480, 200, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 100, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(400, 100, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.rewards = 4;

				}
			}
			if (map.Location == map.Location.j2 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 25, 600));
				c.addEntity(new Wall(275, -10, 50, 620));

				if (!j2) {

					c.addEntity(new Enemy(200, 150, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 250, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 350, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 450, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.rewards = 4;
				}
			}
			if (map.Location == map.Location.j3 && c.ec.size() == 0) {
				c.addEntity(new Wall(575, -5, 25, 300));
				c.addEntity(new Wall(-5, 575, 610, 25));//bottom
				if (!j3) {

					c.addEntity(new Enemy(500, 180, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(500, 250, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(400, 180, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(400, 250, "ARBYS", 100 * currentLevel, tex, false, false));
					c.rewards = 4;
				}
			}

			if (map.Location == map.Location.j4 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, -5, 615, 299));
				c.addEntity(new Wall(-5, 575, 610, 25));//bottom
				if (!j4) {
					c.addEntity(new Enemy(100, 500, "ARBYS", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(300, 500, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(500, 500, "ARBYS", 200 * currentLevel, tex, false, false));
					c.rewards = 3;
				}
			}

			if (map.Location == map.Location.j5 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, -5, 253, 299));
				c.addEntity(new Wall(353, 0, 600, 299));
				c.addEntity(new Wall(-5, 575, 610, 25));//bottom
				if (!j5) {
					c.addEntity(new Enemy(100, 300, "ARBYS", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(270, 300, "ARBYS", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(425, 300, "ARBYS", 200 * currentLevel, tex, false, false));
					c.rewards = 3;
				}
			}
			if (map.Location == map.Location.j6 && c.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 25, 299));//left
				c.addEntity(new Wall(575, -5, 25, 610));//right
				if (!j6) {
				
				}
			}
			if (map.Location == map.Location.k1 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 25, 600));
				c.addEntity(new Wall(0, 575, 249, 25));
				c.addEntity(new Wall(349, 575, 349, 25));
				c.addEntity(new Wall(575, -10, 25, 600));
				c.addEntity(new Wall(126, 51, 600, 99));

				if (!k1) {

					c.addEntity(new Enemy(200, 70, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(300, 70, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(400, 70, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.rewards = 3;

				}
			}
			if (map.Location == map.Location.k2 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 25, 600));
				c.addEntity(new Wall(275, -10, 50, 620));
				c.addEntity(new Wall(575, -5, 25, 610));//right

				if (!k2) {
					c.addEntity(new Enemy(200, 150, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 250, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 350, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(200, 450, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.rewards = 4;

				}
			}
			if (map.Location == map.Location.k6 && c.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 25 , 610));//left
				c.addEntity(new Wall(575, -5, 25, 610));//right

				if (!k6) {
					
				}
			}
			if (map.Location == map.Location.l1 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 249, 25));
				c.addEntity(new Wall(349, 0, 600, 25));
				c.addEntity(new Wall(0, 595, 610, 25));
				c.addEntity(new Wall(0, 26, 50, 600));
				c.addEntity(new Wall(145, 133, 600, 50));
				c.addEntity(new Wall(49, 311, 410, 50));
				c.addEntity(new Wall(150, 440, 600, 50));
				c.addEntity(new Wall(556, 185, 50, 305));

				if (!l1) {

					c.addEntity(new Enemy(190, 29, "TACOBELL", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(350, 29, "TACOBELL", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(60, 250, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(470, 220, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(470, 350, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.rewards = 5;

				}
			}
			if (map.Location == map.Location.l2 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, 595, 610, 25));
				c.addEntity(new Wall(0, 595, 610, 25));
				c.addEntity(new Wall(-5, -5, 25, 25));
				c.addEntity(new Wall(-5, 440, 275, 50));
				c.addEntity(new Wall(275, -5, 50, 500));
				c.addEntity(new Wall(-5, 140, 20, 270));
				c.addEntity(new Wall(575, -5, 25, 610));//right

				if (!l2) {
					c.addEntity(new Enemy(200, 200, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(150, 255, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(150, 120, "TACOBELL", 100 * currentLevel, tex, false, false));
					c.rewards = 3;

				}
			}
			if (map.Location == map.Location.l6 && c.ec.size() == 0) {
				c.addEntity(new Wall(-5, -5, 25 , 610));//left
				c.addEntity(new Wall(575, -5, 25, 300));
				c.addEntity(new Wall(575, -5, 380, 300));
				c.addEntity(new Wall(-5, 575, 610, 25));//bottom
				if (!l6) {
					c.addEntity(new Enemy(270, 400, "CAESAR", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(320, 155, "CAESAR", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(470, 400, "CAESAR", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(470, 300, "CAESAR", 100 * currentLevel, tex, false, false));
					c.rewards = 4;

				}
			}
			if (map.Location == map.Location.l7 && c.ec.size() == 0) {

				c.addEntity(new Wall(0, 0, 600, 24));
				c.addEntity(new Wall(0, 0, 24, 299));
				c.addEntity(new Wall(0, 400, 24, 200));
				c.addEntity(new Wall(0, 575, 600, 24));
				c.addEntity(new Wall(575, 0, 24, 450));
				c.addEntity(new Wall(575, 551, 24, 50));
				c.addEntity(new Wall(352, 25, 99, 449));
				if (!l7) {
					c.addEntity(new Enemy(200, 200, "CAESAR", 100 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(150, 255, "CAESAR", 100 * currentLevel, tex, false, false));
					c.rewards = 2;

				}
			}
			if (map.Location == map.Location.l8 && c.ec.size() == 0) {
				c.addEntity(new Wall(0, 0, 600, 24));
				c.addEntity(new Wall(0, 0, 24, 449));
				c.addEntity(new Wall(0, 550, 24, 50));
				c.addEntity(new Wall(0, 575, 600, 24));
				c.addEntity(new Wall(575, 0, 24, 452));
				c.addEntity(new Wall(575, 452, 24, 100));
				c.addEntity(new Wall(575, 552, 99, 48));
				c.addEntity(new Wall(150, 100, 300, 150));//
				c.addEntity(new Wall(150, 351, 300, 150));

				if (!l8) {
					c.addEntity(new Enemy(50, 30, "CAESAR", 2000 * currentLevel, tex, true, true));
					c.addEntity(new Enemy(200, 300, "CAESAR", 200 * currentLevel, tex, false, false));
					c.addEntity(new Enemy(400, 300, "CAESAR", 200 * currentLevel, tex, false, false));
					c.replenish = true;

				}
			}

			for (int i = 0; i < c.ee.size(); i++) {
				EntityE tempEnt = c.ee.get(i);

				if (Phs.Collision(p, tempEnt)) {
					c.removeEntity(tempEnt);
					pickUp.play();
					CASH += tempEnt.getCash();
					score += tempEnt.getscore();
					CURRENTHEALTH += tempEnt.getHp();

					if (CURRENTHEALTH >= MAXHEALTH)
						CURRENTHEALTH = MAXHEALTH;
				}
			}
		}

		if (updates == 0) {
			if (stopped) {
				timer++;
				if (timer >= 5) {
					timer = 0;
					stopped = false;
				}
			}
			if (noDamage) {
				timer++;
				if (timer >= 5) {
					timer = 0;
					noDamage = false;
				}
			}
		}

		for (int i = 0; i < c.eb.size(); i++) {

			EntityB tempEntB = c.eb.get(i);

			if (((tempEntB.getFaction() == "SUBWAY") && PlayerFaction != Faction.FACTION.SUBWAY)
					|| ((tempEntB.getFaction() == "BRAUMS") && PlayerFaction != Faction.FACTION.BRAUMS)
					|| ((tempEntB.getFaction() == "ARBYS") && PlayerFaction != Faction.FACTION.ARBYS)
					|| ((tempEntB.getFaction() == "MCD") && PlayerFaction != Faction.FACTION.MCD)
					|| ((tempEntB.getFaction() == "CAESAR") && PlayerFaction != Faction.FACTION.CAESAR)
					|| ((tempEntB.getFaction() == "TACOBELL") && PlayerFaction != Faction.FACTION.TACOBELL)
							&& !stopped) {// enemy bullet ai

				double x = 0, y = 0;
				if (p.getY() >= tempEntB.getY())
					y += 2;
				else if (p.getY() <= tempEntB.getY())

					y -= 2;

				if (p.getX() >= tempEntB.getX())
					x += 2;

				else if (p.getX() <= tempEntB.getX())
					x -= 2;

				if (p.getX() < tempEntB.getX() && p.getY() >= tempEntB.getY() - 32
						&& p.getY() <= tempEntB.getY() + 32) {// enemy left
																// shooting
					x = -3;
					y = 0;
				}
				if (p.getX() > tempEntB.getX() && p.getY() >= tempEntB.getY() - 32
						&& p.getY() <= tempEntB.getY() + 32) {// enemy right
																// shooting
					x = 3;
					y = 0;
				}
				if (p.getY() < tempEntB.getY() && p.getX() >= tempEntB.getX() - 32
						&& p.getX() <= tempEntB.getX() + 32) {// enemy up
																// shooting
					x = 0;
					y = -3;

				}
				if (p.getY() > tempEntB.getY() && p.getX() >= tempEntB.getX() - 32
						&& p.getX() <= tempEntB.getX() + 32) {// enemy down
																// shooting
					y = 3;
					x = 0;
				}
				if (tempEntB.getBoss2() == true && tempEntB.getBoss() == false && updates == 0 && !stopped
						&& State == STATE.GAME) {
					c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, 2.5, -2.5,
							5 * currentLevel));
					c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, -2.5, -2.5,
							5 * currentLevel));
					c.addEntity(
							new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, 0, -5, 5 * currentLevel));

					if (updates == 50 && !stopped && State == STATE.GAME) {
						c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, x, y,
								10 * currentLevel));
					}
				}
				if (tempEntB.getBoss() == true) {
					if (tempEntB.getBoss2() == true && updates == 0 && !stopped && State == STATE.GAME) {
						c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, -2.5, 2.5,
								5 * currentLevel));
						c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, 2.5, 2.5,
								5 * currentLevel));
						c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, 0, 5,
								5 * currentLevel));
					} else if (updates == 0 && !stopped && State == STATE.GAME) {
						c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, -10, 0,
								5 * currentLevel));
						c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, 10, 0,
								5 * currentLevel));
						c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, 0, -10,
								5 * currentLevel));
						c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, 0, 10,
								5 * currentLevel));
					}

					if (updates == 50 && !stopped && State == STATE.GAME) {
						c.addEntity(new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, x, y,
								10 * currentLevel));
					}
				} else if (updates == 0 && !stopped && State == STATE.GAME)
					c.addEntity(
							new EnemyBullet(tempEntB.getX() + 16, tempEntB.getY() + 16, tex, x, y, 10 * currentLevel));

			}
		}
		for (int i = 0; i < c.ec.size(); i++) {

			EntityC tempEnt = c.ec.get(i);

			if (Phs.Collision(p, tempEnt)) {

				if (p.y >= tempEnt.getY()) {
					p.bump(0, speed);
				}
				if (p.y + 64 <= (tempEnt.getY() + tempEnt.getHeight())) {
					p.bump(0, -speed);
				}
			}
			if (Phs.Collision(p, tempEnt)) {

				if (p.x >= tempEnt.getX()) {
					p.bump(speed, 0);
					if (p.y >= tempEnt.getY()) /// slide fix
						p.bump(0, -speed);
					if (p.y + 64 <= (tempEnt.getY() + tempEnt.getHeight()))
						p.bump(0, speed);
				}
				if (p.x + 64 <= (tempEnt.getX() + tempEnt.getWidth())) {
					p.bump(-speed, 0);
					if (p.y + 64 <= (tempEnt.getY() + tempEnt.getHeight()))
						p.bump(0, speed);
					if (p.y >= tempEnt.getY())
						p.bump(0, -speed);
				}
			}
		}
	}
	static boolean reset = true;
	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {

			createBufferStrategy(2);
			return;

		}

		Graphics g = bs.getDrawGraphics();
		////////////////////////////
		g.drawImage(background, 0, 0, 850, getHeight(), this);

		if (State == State.GAME) {

			if (menuMusic.on())
				menuMusic.stop();
			if (!gameMusic.on())
				gameMusic.play();

			if (started == true) {
				g.drawImage(tex.getStartingMap(), 0, 0, 600, getHeight(), this);

				if (reset) {
					p.setX(300);
					p.setY(300);
					reset = false;
				}
			}

			else if (p.nextmap != p.currentmap && started == false) {
				g.drawImage(p.nextmap, 0, 0, 600, getHeight(), this);
			}

			p.render(g);
			c.render(g);
			hud.render(g);

		} else if (State == State.MENU) {
			menu.render(g);
			hud.render(g);
			if (!menuMusic.on())
				menuMusic.play();
			if (gameMusic.on())
				gameMusic.stop();

			if (pause) {
				//pause menu
				g.setColor(Color.BLACK);
				g.fillRect(100, 50, 300, 475);
				g.fillRect(450, 250, 125, 72);
				g.setColor(Color.RED);
				g.drawString("Back to", 455, 275);
				g.drawString("main menu", 455, 305);
				g.drawString("" + (int) hpregen, 150, 50);
				g.setColor(Color.CYAN);
				g.drawString("" + (int) eregen, 150, 100);
				g.setColor(Color.YELLOW);
				g.drawString("" + (int) speed, 150, 150);
				g.setColor(Color.LIGHT_GRAY);
				g.drawString("" + (int) bulletspeed, 150, 200);
				g.setColor(Color.RED);
				g.drawString("Health Regeneration", 200, 50);
				g.setColor(Color.CYAN);
				g.drawString("Energy Regeneration", 200, 100);
				g.setColor(Color.YELLOW);
				g.drawString("Player Speed", 200, 150);
				g.setColor(Color.LIGHT_GRAY);
				g.drawString("Bullet Speed", 200, 200);
				g.setColor(Color.ORANGE);
				g.drawString("Damage", 200, 250);
				g.drawString("" + (int) damage, 150, 250);
				g.setColor(Color.RED);
				g.drawString("Health", 250, 300);
				g.drawString("" + (int) CURRENTHEALTH + " / " + (int) MAXHEALTH, 150, 300);
				g.setColor(Color.RED);
				g.drawString("LifeSteal", 200, 350);
				g.drawString("" + (int) steal, 150, 350);
				g.setColor(Color.GREEN);
			}
		} else if (State == State.HOWTOPLAY) {
			howtoplay.render(g);
			hud.render(g);
		} else if (State == State.FACTIONPICK) {
			factionpick.render(g);
			hud.render(g);
		}

		g.setColor(Color.GRAY);
		g.fillRect(625, 125, 100, 25);
		g.fillRect(625, 225, 100, 25);

		g.setColor(Color.CYAN);
		g.fillRect(625, 225, (int) ENERGY, 25);
		g.setColor(Color.BLACK);
		if (active)
			g.setColor(Color.ORANGE);
		if (special != null)
			g.drawString("" + special, 645, 245);
		g.setColor(Color.YELLOW);
		g.drawString("" + (int) CASH, 655, 325);
		g.setColor(Color.ORANGE);
		g.drawString("" + (int) score, 655, 425);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("" + patch, 680, 600);

		if (CURRENTHEALTH <= 100 && CURRENTHEALTH >= 0) {
			g.setColor(Color.GREEN);
			g.fillRect(625, 125, (int) CURRENTHEALTH, 25);
		}
		if (CURRENTHEALTH <= 200 && CURRENTHEALTH >= 100) {
			g.setColor(Color.YELLOW);
			g.fillRect(625, 125, (int) CURRENTHEALTH - 100, 25);
		}
		if (CURRENTHEALTH <= 300 && CURRENTHEALTH >= 200) {
			g.setColor(Color.BLUE);
			g.fillRect(625, 125, (int) CURRENTHEALTH - 200, 25);
		}
		if (CURRENTHEALTH <= 400 && CURRENTHEALTH >= 300) {
			g.setColor(Color.MAGENTA);
			g.fillRect(625, 125, (int) CURRENTHEALTH - 300, 25);
		}
		if (CURRENTHEALTH <= 500 && CURRENTHEALTH >= 400) {
			g.setColor(Color.WHITE);
			g.fillRect(625, 125, (int) CURRENTHEALTH - 400, 25);
		}
		////////////////////////////
		g.dispose();
		bs.show();
	}
	public static void main(String args[]) {

		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGTH * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGTH * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGTH * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
	public BufferedImage getPlayerSheet() {
		return playerSheet;
	}
	public BufferedImage getMapSheet() {
		return mapSheet;
	}
	public BufferedImage getBulletSheet() {
		return bulletSheet;
	}
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (State == State.GAME) {
			if (key == KeyEvent.VK_D) {// right moving
				p.setVelX(speed);
				// if(updates >= 0 && updates <= 1)
				// walk.play();
				p.right = true;
				p.left = false;
				p.front = false;
				p.back = false;
			} else if (key == KeyEvent.VK_A) {// left moving
				p.setVelX(-speed);
				// if(updates >= 0 && updates <= 1)
				// walk.play();
				p.right = false;
				p.left = true;
				p.front = false;
				p.back = false;
			} else if (key == KeyEvent.VK_S) {// down moving
				p.setVelY(speed);
				// if(updates >= 0 && updates <= 1)
				// walk.play();
				p.right = false;
				p.left = false;
				p.front = true;
				p.back = false;
			} else if (key == KeyEvent.VK_W) {// up moving
				p.setVelY(-speed);
				// if(updates >= 0 && updates <= 1)
				// walk.play();
				p.right = false;
				p.left = false;
				p.front = false;
				p.back = true;
			} else if (key == KeyEvent.VK_RIGHT && !shooting) {// right shooting
				shooting = true;
				playerShot.play();
				if (!crazy && !akimbo && !cannon)
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, (int) bulletspeed, 0, false,
							true, false, false, (int) damage));
				if (crazy) {
					pizzaFrenzy();
				}
				if (akimbo) {
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 6, tex, this, (int) bulletspeed, 0, false,
							true, false, false, (int) damage));
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 26, tex, this, (int) bulletspeed, 0, false,
							true, false, false, (int) damage));
				}
				if (cannon) {
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 6, tex, this, (int) bulletspeed, 0, false,
							true, false, false, (int) damage / 2));
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 26, tex, this, (int) bulletspeed, 0, false,
							true, false, false, (int) damage / 2));
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, (int) bulletspeed + 1, 0,
							false, true, false, false, (int) damage));
				}

				p.right = true;
				p.left = false;
				p.front = false;
				p.back = false;
			} else if (key == KeyEvent.VK_LEFT && !shooting) {// left shooting
				shooting = true;
				playerShot.play();
				if (!crazy && !akimbo && !cannon)
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, -(int) bulletspeed, 0, true,
							false, false, false, (int) damage));
				if (crazy) {
					pizzaFrenzy();
				}
				if (akimbo) {
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 6, tex, this, -(int) bulletspeed, 0, true,
							false, false, false, (int) damage));
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 26, tex, this, -(int) bulletspeed, 0, true,
							false, false, false, (int) damage));
				}
				if (cannon) {
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 6, tex, this, -(int) bulletspeed, 0, true,
							false, false, false, (int) damage / 2));
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 26, tex, this, -(int) bulletspeed, 0, true,
							false, false, false, (int) damage / 2));
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, -(int) bulletspeed - 1, 0,
							true, false, false, false, (int) damage));
				}

				p.right = false;
				p.left = true;
				p.front = false;
				p.back = false;
			} else if (key == KeyEvent.VK_DOWN && !shooting) {// down Shooting
				shooting = true;
				playerShot.play();
				if (!crazy && !akimbo && !cannon)
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, 0, (int) bulletspeed, false,
							false, true, false, (int) damage));

				if (crazy) {
					pizzaFrenzy();
				}
				if (akimbo) {
					c.addEntity(new PlayerBullet(p.getX() + 6, p.getY() + 16, tex, this, 0, (int) bulletspeed, false,
							false, true, false, (int) damage));
					c.addEntity(new PlayerBullet(p.getX() + 26, p.getY() + 16, tex, this, 0, (int) bulletspeed, false,
							false, true, false, (int) damage));
				}
				if (cannon) {
					c.addEntity(new PlayerBullet(p.getX() + 6, p.getY() + 16, tex, this, 0, (int) bulletspeed, false,
							false, true, false, (int) damage / 2));
					c.addEntity(new PlayerBullet(p.getX() + 26, p.getY() + 16, tex, this, 0, (int) bulletspeed, false,
							false, true, false, (int) damage / 2));
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, 0, (int) bulletspeed + 1,
							false, false, true, false, (int) damage));
				}

				p.right = false;
				p.left = false;
				p.front = true;
				p.back = false;
			} else if (key == KeyEvent.VK_UP && !shooting) {// up shooting
				shooting = true;
				playerShot.play();
				if (!crazy && !akimbo && !cannon)
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, 0, -(int) bulletspeed, false,
							false, false, true, (int) damage));
				if (crazy) {
					pizzaFrenzy();
				}
				if (akimbo) {
					c.addEntity(new PlayerBullet(p.getX() + 6, p.getY() + 16, tex, this, 0, -(int) bulletspeed, false,
							false, false, true, (int) damage));
					c.addEntity(new PlayerBullet(p.getX() + 26, p.getY() + 16, tex, this, 0, -(int) bulletspeed, false,
							false, false, true, (int) damage));
				}
				if (cannon) {
					c.addEntity(new PlayerBullet(p.getX() + 6, p.getY() + 16, tex, this, 0, -(int) bulletspeed, false,
							false, false, true, (int) damage / 2));
					c.addEntity(new PlayerBullet(p.getX() + 26, p.getY() + 16, tex, this, 0, -(int) bulletspeed, false,
							false, false, true, (int) damage / 2));
					c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, 0, -(int) bulletspeed - 1,
							false, false, false, true, (int) damage));
				}

				p.right = false;
				p.left = false;
				p.front = false;
				p.back = true;
			} else if (key == KeyEvent.VK_SPACE && !shooting) {
				shooting = true;

				if (ENERGY >= 100) {

					active = true;
					if (hyper)
						speed *= 1.5;
					if (stop)
						stopped = true;
					if (inv)
						noDamage = true;
					if (nuke) {
						nukedo = true;
					}
					if (Game.PlayerFaction == Game.PlayerFaction.SUBWAY) {
						pierce();
					}
					if (Game.PlayerFaction == Game.PlayerFaction.BRAUMS) {
						cannonBall();
						braums_special.play();
					}
					if (Game.PlayerFaction == Game.PlayerFaction.TACOBELL) {
						splitingBullet();// double damage
					}
					if (Game.PlayerFaction == Game.PlayerFaction.CAESAR) {
						crazy = true;
					}
					if (Game.PlayerFaction == Game.PlayerFaction.ARBYS) {
						poisonDamage();
					}
					if (Game.PlayerFaction == Game.PlayerFaction.MCD) {
						akimbo();
					}
				}
			}
			if (key == KeyEvent.VK_ESCAPE) {
				State = STATE.MENU;
				pause = !pause;
			}
			if (key == KeyEvent.VK_E) {

			}
		}
	}
	public void keyReleased(KeyEvent e) {

		key = e.getKeyCode();

		if (key == KeyEvent.VK_D) {// right
			p.setVelX(0);
			// walk.stop();
		} else if (key == KeyEvent.VK_A) {// left
			p.setVelX(0);
			// walk.stop();
		} else if (key == KeyEvent.VK_S) {// down
			p.setVelY(0);
			// walk.stop();
		} else if (key == KeyEvent.VK_W) {// up
			p.setVelY(0);
			// walk.stop();
		} else if (key == KeyEvent.VK_RIGHT) {// right shooting
			shooting = false;
		} else if (key == KeyEvent.VK_LEFT) {// left shooting
			shooting = false;
		} else if (key == KeyEvent.VK_DOWN) {// down Shooting
			shooting = false;
		} else if (key == KeyEvent.VK_UP) {// up shooting
			shooting = false;
		} else if (key == KeyEvent.VK_SPACE) {// spec
			shooting = false;
		}
	}
	public void pierce() {
		pierce = true;
	}
	public void cannonBall() {
		cannon = true;
	}
	public void splitingBullet() {
		ddamage = true;
	}
	public void pizzaFrenzy() {
		c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, (int) bulletspeed + 3, 0, true, false,
				false, false, (int) damage));
		c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, -(int) bulletspeed - 3, 0, true, false,
				false, false, (int) damage));
		c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, 0, (int) bulletspeed + 3, true, false,
				false, false, (int) damage));
		c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, 0, -(int) bulletspeed - 3, true, false,
				false, false, (int) damage));
		c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, -(int) bulletspeed - 3,
				-(int) bulletspeed - 3, true, false, false, false, (int) damage));
		c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, (int) bulletspeed + 3,
				(int) bulletspeed + 3, true, false, false, false, (int) damage));
		c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, -(int) bulletspeed - 3,
				(int) bulletspeed + 3, true, false, false, false, (int) damage));
		c.addEntity(new PlayerBullet(p.getX() + 16, p.getY() + 16, tex, this, (int) bulletspeed + 3,
				-(int) bulletspeed - 3, true, false, false, false, (int) damage));
	}
	public void poisonDamage() {
		poison = true;
	}
	public void akimbo() {
		akimbo = true;
	}
	public void stopSpecials(){
		ddamage = false;
		cannon = false;
		pierce = false;
		poison = false;
		akimbo = false;
		crazy = false;
		if(hyper)
			speed *= 2/3;
		stopped = true;
		noDamage = true;
		nukedo = true;
	}
	public static void boolListFalse()
	{
		a1 = false;
		a2 = false;
		a3 = false;
		a4 = false;
		a5 = false;
		a6 = false;
		a7 = false;
		a8 = false;
		a9 = false;
		a10 = false;// a row

		b1 = false;
		b2 = false;
		b3 = false;
		b4 = false;
		b5 = false;
		b6 = false;
		b7 = false;
		b8 = false;
		b9 = false;
		b10 = false;// b row

		c1 = false;
		c2 = false;
		c3 = false;
		c4 = false;
		c5 = false;
		c6 = false;
		c7 = false;
		c8 = false;
		c9 = false;
		c10 = false;// c row

		d1 = false;
		d2 = false;
		d3 = false;
		d4 = false;
		d5 = false;
		d6 = false;
		d7 = false;
		d8 = false;
		d9 = false;
		d10 = false;// d row

		e1 = false;
		e2 = false;
		e3 = false;
		e4 = false;
		e5 = false;
		e6 = false;
		e7 = false;
		e8 = false;
		e9 = false;
		e10 = false;// e row

		f1 = false;
		f2 = false;
		f3 = false;
		f4 = false;
		f5 = false;
		f6 = false;
		f7 = false;
		f8 = false;
		f9 = false;
		f10 = false;// f row

		g1 = false;
		g2 = false;
		g3 = false;
		g4 = false;
		g5 = false;
		g6 = false;
		g7 = false;
		g8 = false;
		g9 = false;
		g10 = false;// g row

		h1 = false;
		h2 = false;
		h3 = false;
		h4 = false;
		h5 = false;
		h6 = false;
		h7 = false;
		h8 = false;
		h9 = false;
		h10 = false;// h row

		i1 = false;
		i2 = false;
		i3 = false;
		i4 = false;
		i5 = false;
		i6 = false;
		i7 = false;
		i8 = false;
		i9 = false;
		i10 = false;// i row

		j1 = false;
		j2 = false;
		j3 = false;
		j4 = false;
		j5 = false;
		j6 = false;
		j7 = false;
		j8 = false;
		j9 = false;
		j10 = false;// j row

		k1 = false;
		k2 = false;
		k3 = false;
		k4 = false;
		k5 = false;
		k6 = false;
		k7 = false;
		k8 = false;
		k9 = false;
		k10 = false;// k row

		l1 = false;
		l2 = false;
		l3 = false;
		l4 = false;
		l5 = false;
		l6 = false;
		l7 = false;
		l8 = false;
		l9 = false;
		l10 = false; // l row
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
}
/*
 Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
	at Mouse.mousePressed(Mouse.java:389)
	at java.awt.Component.processMouseEvent(Unknown Source)
	at java.awt.Component.processEvent(Unknown Source)
	at java.awt.Component.dispatchEventImpl(Unknown Source)
	at java.awt.Component.dispatchEvent(Unknown Source)
	at java.awt.EventQueue.dispatchEventImpl(Unknown Source)
	at java.awt.EventQueue.access$500(Unknown Source)
	at java.awt.EventQueue$3.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown Source)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown Source)
	at java.awt.EventQueue$4.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown Source)
	at java.awt.EventQueue.dispatchEvent(Unknown Source)
	at java.awt.EventDispatchThread.pumpOneEventForFilters(Unknown Source)
	at java.awt.EventDispatchThread.pumpEventsForFilter(Unknown Source)
	at java.awt.EventDispatchThread.pumpEventsForHierarchy(Unknown Source)
	at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
	at java.awt.EventDispatchThread.pumpEvents(Unknown Source)
	at java.awt.EventDispatchThread.run(Unknown Source)
*/