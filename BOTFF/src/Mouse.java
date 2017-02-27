import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class Mouse implements MouseListener
{
	Player p;
	Controller c;
	Game game;
	Textures tex;
	Menu m;
	Map map;
	Audio upgrade 	= new Audio("/levelUP.wav");
	Audio click 	= new Audio("/menu click.wav");
	Audio no 		= new Audio("/no.wav");
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) 
	{
		e.getX();
		e.getY();
	}
	public void mouseExited(MouseEvent e) 
	{
		e.getX();
		e.getY();
	}
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		menu(mx, my);
		howToPlay(mx, my);
		pauseGame(mx, my);
		homeLand(mx, my);
		CVS(mx, my);
		endGame(mx, my);
		factionChoice(mx, my);
	}
	public void mouseReleased(MouseEvent e) {}
	public void factionChoice(int mx, int my) {
		// Subway
		if (mx >= 439 && mx <= 539 && Game.State == Game.STATE.FACTIONPICK && !Game.homeland && !Game.endgame
				&& !Game.pause) 
		{
			if (my >= 402 && my <= 425) 
			{
				// pressed
				Game.State = Game.STATE.GAME;
				Game.PlayerFaction = Faction.FACTION.SUBWAY;
				Map.Location = Map.MAP.a2;
				Game.started = true;
				Game.bulletspeed = 15;
				Game.speed = 5;
				Game.bossKilled = 4;
				Game.damage = 7;
				Game.MAXHEALTH = 150;// special
				Game.CURRENTHEALTH = 150;
				Game.CASH = 50;
				Game.special = "Piercing";
				click.play();
				// game.damage = 10000;game.MAXHEALTH = 450;game.CURRENTHEALTH =
				// 450;game.CASH = 500000;game.speed = 15;
			}
		}
		// Braums
		if (mx >= 451 && mx <= 551 && Game.State == Game.STATE.FACTIONPICK && !Game.homeland && !Game.endgame
				&& !Game.pause) 
		{
			if (my >= 289 && my <= 312) 
			{
				// pressed
				Game.State = Game.STATE.GAME;
				Game.PlayerFaction = Faction.FACTION.BRAUMS;
				Map.Location = Map.MAP.g9;
				click.play();
				Game.started = true;
				Game.bulletspeed = 5;
				Game.speed = 5;
				Game.damage = 15;// special
				Game.special = "Triple";
			}
		}
		// Taco Bell
		if (mx >= 341 && mx <= 441 && Game.State == Game.STATE.FACTIONPICK && !Game.homeland && !Game.endgame
				&& !Game.pause) 
		{
			if (my >= 519 && my <= 542) 
			{
				// pressed
				click.play();
				Game.State = Game.STATE.GAME;
				Map.Location = Map.MAP.k1;
				Game.PlayerFaction = Faction.FACTION.TACOBELL;
				Game.started = true;
				Game.damage = 12;// special
				Game.bulletspeed = 8;
				Game.gok = 5;
				Game.speed = 8;
				Game.special = "Double";
			}
		}
		// Little Caesar
		if (mx >= 168 && mx <= 268 && Game.State == Game.STATE.FACTIONPICK && !Game.homeland && !Game.endgame
				&& !Game.pause) 
		{
			if (my >= 519 && my <= 542) 
			{
				// pressed
				click.play();
				Game.State = Game.STATE.GAME;
				Game.PlayerFaction = Faction.FACTION.CAESAR;
				Map.Location = Map.MAP.l7;
				Game.started = true;
				Game.bulletspeed = 50;// special
				Game.damage = 10;
				Game.special = "Crazy";
				Game.speed = 8;
			}
		}
		// Arby's
		if (mx >= 72 && mx <= 172 && Game.State == Game.STATE.FACTIONPICK && !Game.homeland && !Game.endgame
				&& !Game.pause) 
		{
			if (my >= 402 && my <= 425) 
			{
				// pressed
				click.play();
				Game.State = Game.STATE.GAME;
				Game.PlayerFaction = Faction.FACTION.ARBYS;
				Map.Location = Map.MAP.i5;
				Game.started = true;
				Game.bulletspeed = 8;
				Game.damage = 10;
				Game.hpregen = 1;// special
				Game.special = "Poison";
				// game.CASH = 100000;game.damage = 100000;game.speed =
				// 25;game.currentLevel = 10;game.MAXHEALTH =
				// 500;game.CURRENTHEALTH = 500;game.eregen = 10;
			}
		}
		// McDonald's
		if (mx >= 60 && mx <= 160 && Game.State == Game.STATE.FACTIONPICK && !Game.homeland && !Game.endgame
				&& !Game.pause) 
		{
			if (my >= 289 && my <= 312) 
			{
				// pressed
				click.play();
				Game.State = Game.STATE.GAME;
				Game.PlayerFaction = Faction.FACTION.MCD;
				Map.Location = Map.MAP.f4;
				Game.started = true;
				Game.damage = 10;
				Game.speed = 10;
				Game.special = "Akimbo";
				Game.steal = 1;// special
			}
		}
		// back
		if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.FACTIONPICK && !Game.homeland && !Game.endgame
				&& !Game.pause) 
		{
			if (my >= 350 && my <= 400) 
			{
				// pressed
				click.play();
				Game.State = Game.STATE.MENU;
			}
		}
	}
	public void endGame(int mx, int my)
	{
		// ENDGAME---------------------------------------------------------------------------------------------------------
				// LOSE
				if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.MENU && Game.endgame && Game.bossKilled != 5) {
					if (my >= 550 && my <= 600) {
						// pressed
						Game.State = Game.STATE.MENU;
						Game.endgame = !Game.endgame;
						click.play();
						Game.active = false;
					} else
						no.play();
				}
				// WIN
				if (Game.State == Game.STATE.MENU && Game.endgame && Game.bossKilled == 5) {
					if (mx >= 25 && mx <= 125) {
						if (my >= 365 && my <= 465) {
							Game.eregen += 2;
						}
					}
					if (mx >= 175 && mx <= 275) {
						if (my >= 365 && my <= 465) {
							Game.damage += 10;
						}
					}
					if (mx >= 325 && mx <= 425) {
						if (my >= 365 && my <= 465) {
							Game.hpregen += 1;
						}
					}
					if (mx >= 475 && mx <= 575) {
						if (my >= 365 && my <= 465) {
							Game.steal += 5;
						}
					}
					// pressed
					Game.boolListFalse();
					Game.State = Game.STATE.GAME;
					Game.endgame = !Game.endgame;
					Game.currentLevel++;
					Game.bossKilled = 0;
					Player.levelUp = true;
					click.play();
					Game.active = false;
					if (Game.PlayerFaction == Faction.FACTION.SUBWAY)
						Map.Location = Map.MAP.a2;
					if (Game.PlayerFaction == Faction.FACTION.ARBYS)
						Map.Location = Map.MAP.i5;
					if (Game.PlayerFaction == Faction.FACTION.TACOBELL)
						Map.Location = Map.MAP.k1;
					if (Game.PlayerFaction == Faction.FACTION.CAESAR)
						Map.Location = Map.MAP.l7;
					if (Game.PlayerFaction == Faction.FACTION.MCD)
						Map.Location = Map.MAP.f4;
					if (Game.PlayerFaction == Faction.FACTION.BRAUMS)
						Map.Location = Map.MAP.g9;
					game.stopSpecials();
				}
	}
	public void CVS(int mx, int my)
	{
		// health up
			if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.MENU && Game.pharmacy && !Game.homeland && !Game.endgame && !Game.pause) 
			{
				if (my >= 50 && my <= 150) {
					// pressed
					if (Game.CASH >= Game.healthCost) 
					{
						Game.CASH -= Game.healthCost;
						Game.healthCost += 250;
						Game.MAXHEALTH += 50;
						upgrade.play();
						click.play();
					} 
					else
					{
						no.play();
					}
				}
				// full health
				if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.MENU && Game.pharmacy && !Game.homeland && !Game.endgame
						&& !Game.pause) {
					if (my >= 250 && my <= 350) {
						// pressed
						if (Game.CASH >= (Game.MAXHEALTH - Game.CURRENTHEALTH)) 
						{
							Game.CASH -= (Game.MAXHEALTH - Game.CURRENTHEALTH);
							Game.CURRENTHEALTH = Game.MAXHEALTH;
							upgrade.play();
							click.play();
						} 
						else
						{
							no.play();
						}
					}
				}
				// hp regen up
				if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.MENU && Game.pharmacy && !Game.homeland && !Game.endgame && !Game.pause) 
				{
					if (my >= 450 && my <= 550) 
					{
						// pressed
						if (Game.CASH >= Game.hpregenCost) 
						{
							Game.CASH -= Game.hpregenCost;
							Game.hpregenCost += 25;
							Game.hpregen += .25;
							upgrade.play();
							click.play();
						} else
						{
							no.play();
						}
					}
				}
				// back to gameg.fillRect(470, 540, 70, 40);
				if (mx >= 470 && mx <= 540 && Game.State == Game.STATE.MENU && Game.pharmacy && !Game.homeland && !Game.endgame
						&& !Game.pause) {
					if (my >= 500 && my <= 540) 
					{
						// pressed
						Game.State = Game.STATE.GAME;
						Game.pharmacy = !Game.pharmacy;
						click.play();
					}
				}
			}
	}
	public void homeLand(int mx, int my)
	{
		if (mx >= 25 && mx <= 125 && Game.State == Game.STATE.MENU && Game.homeland) 
		{
			if (my >= 150 && my <= 250) 
			{
				// pressed
				if (Game.CASH >= Game.eregenCost) 
				{
					Game.CASH -= Game.eregenCost;
					Game.eregenCost += 25;
					Game.eregen += .5;
					upgrade.play();
					click.play();
				} else
				{
					no.play();
				}
			}
		}
		// milk
		if (mx >= 175 && mx <= 275 && Game.State == Game.STATE.MENU && Game.homeland) 
		{
			if (my >= 150 && my <= 250) 
			{
				// pressed
				if (Game.CASH >= Game.milkCost) 
				{
					Game.CASH -= Game.milkCost;
					Game.milkCost += 100;
					Game.damage += 5;
					upgrade.play();
					click.play();
				} else
				{
					no.play();
				}
			}
		}
		// lucker
		if (mx >= 325 && mx <= 425 && Game.State == Game.STATE.MENU && Game.homeland) 
		{
			if (my >= 150 && my <= 250) {
				// pressed
				if (Game.CASH >= Game.luckerCost) 
				{
					Game.CASH -= Game.luckerCost;
					Game.luckerCost += 250;
					upgrade.play();
					click.play();
					Game.mone += 5;
				} else
				{
					no.play();	
				}
			}
		}
		// monster
		if (mx >= 475 && mx <= 575 && Game.State == Game.STATE.MENU && Game.homeland) 
		{
			if (my >= 150 && my <= 250) 
			{
				// pressed
				if (Game.CASH >= Game.stealCost) 
				{
					Game.CASH -= Game.stealCost;
					Game.stealCost *= 1.5;
					Game.steal += 5;
					upgrade.play();
					click.play();
				} 
				else
				{
					no.play();
				}
			}
		}
		// stop time
		if (mx >= 25 && mx <= 125 && Game.State == Game.STATE.MENU && Game.homeland) 
		{
			if (my >= 450 && my <= 550) {
				// pressed
				if (Game.CASH >= Game.stopCost) 
				{
					Game.CASH -= Game.stopCost;
					Game.stop = true;
					click.play();
				} 
				else
				{
					no.play();
				}
			}
		}
		// hyperspeed
		if (mx >= 175 && mx <= 275 && Game.State == Game.STATE.MENU && Game.homeland) 
		{
			if (my >= 450 && my <= 550) 
			{
				// pressed
				if (Game.CASH >= Game.hyperCost) 
				{
					Game.CASH -= Game.hyperCost;
					Game.hyper = true;
					click.play();
				} else
				{
					no.play();
				}
			}
		}
		// nuke
		if (mx >= 325 && mx <= 425 && Game.State == Game.STATE.MENU && Game.homeland) 
		{
			if (my >= 450 && my <= 550) {
				// pressed
				if (Game.CASH >= Game.nukeCost) 
				{
					Game.CASH -= Game.nukeCost;
					Game.nuke = true;
					click.play();
				} 
				else
				{
					no.play();
				}
			}
		}
		// Invincible. 
		if (mx >= 475 && mx <= 575 && Game.State == Game.STATE.MENU && Game.homeland) 
		{
			if (my >= 450 && my <= 550) 
			{
				// pressed
				if (Game.CASH >= Game.invCost) 
				{
					Game.CASH -= Game.invCost;
					Game.inv = true;
					click.play();
				} 
				else
				{
					no.play();
				}
			}
		}
		// back to game
		if (mx >= 270 && mx <= 340 && Game.State == Game.STATE.MENU && Game.homeland) 
		{
			if (my >= 300 && my <= 340) 
			{
				// pressed
				Game.State = Game.STATE.GAME;
				Game.homeland = !Game.homeland;
				click.play();
			}
		}
	}
	public void pauseGame(int mx, int my)
	{
		// PAUSE---------------------------------------------------------------------------------------------------------
		// resume
		if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.MENU && Game.pause) {
			if (my >= 550 && my <= 600) {
				// pressed
				Game.State = Game.STATE.GAME;
				Game.pause = !Game.pause;
				click.play();
			}
		}
		// back to main menu
		if (mx >= 450 && mx <= 525 && Game.State == Game.STATE.MENU && Game.pause) {
			if (my >= 250 && my <= 322) {
				// pressed
				Game.State = Game.STATE.MENU;
				Game.pause = !Game.pause;
				click.play();
			}
		}
	}
	public void howToPlay(int mx, int my)
	{
		// back
		if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.HOWTOPLAY && !Game.homeland && !Game.endgame && !Game.pause) 
		{
			if (my >= 550 && my <= 600) 
			{
				// pressed
				click.play();
				Game.State = Game.STATE.MENU;
			}
		}
	}
	public void menu(int mx, int my)
	{
		// MENU---------------------------------------------------------------------------------------------------------------
		// play
		if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.MENU && !Game.homeland && !Game.endgame && !Game.pause && !Game.pharmacy) 
		{
			if (my >= 150 && my <= 200) 
			{
				// pressed
				click.play();
				Game.State = Game.STATE.FACTIONPICK;
				Controller.change();
				Game.reset = 			true;
				Player.front = 				true;
				Player.right = 				false;
				Player.left = 				false;
				Player.back = 				false;
				Game.started = 			false;
				Game.akimbo = 			false;
				Game.pierce = 			false;
				Game.poison = 			false;
				Game.hyper = 			false;
				Game.stop = 			false;
				Game.stopped = 			false;
				Game.cannon = 			false;
				Game.inv = 				false;
				Game.noDamage = 		false;
				Game.nuke = 			false;
				Game.nukedo = 			false;
				Game.active = 			false;
				Game.crazy = 			false;
				Controller.battle = 				false;
				Controller.replenish = 			false;
				Game.special = 			null;
				Game.PlayerFaction = 	null;
				Map.Location = 			null;			
				
				Game.eregen = 			0;
				Game.hpregen = 			0;
				Game.score = 			0;
				Game.steal = 			0;
				Game.bossKilled = 		0;
				Game.currentLevel = 	1;
				Game.speed = 			5;
				Game.bulletspeed = 		10;
				Controller.rewards = 			0;
				
				Game.ENERGY = 			100;
				Game.CURRENTHEALTH = 	100;
				Game.MAXHEALTH = 		100;
				Game.CASH = 			100;
				
				Game.eregenCost = 		25;
				
				Game.milkCost = 		100;
				Game.hpregenCost = 		150;
				Game.luckerCost = 		250;
				Game.stealCost = 		500;
				Game.stopCost = 		1000;
				Game.hyperCost = 		1500;
				Game.nukeCost = 		2000;
				Game.invCost = 			3000;		
				Game.boolListFalse();
			}
		}
		// how to play
		if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.MENU && !Game.homeland && !Game.endgame && !Game.pause && !Game.pharmacy) 
		{
			if (my >= 250 && my <= 300) 
			{
				// pressed
				Game.State = Game.STATE.HOWTOPLAY;
				click.play();
			}
		}
		// quit
		if (mx >= 250 && mx <= 350 && Game.State == Game.STATE.MENU && !Game.homeland && !Game.endgame && !Game.pause && !Game.pharmacy) 
		{
			if (my >= 350 && my <= 400) 
			{
				// pressed
				click.play();
				System.exit(1);
			}
		}
	}
}