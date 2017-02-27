
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class HowToPlay {

	public Rectangle backButton = new Rectangle(250, 550, 100, 50);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font startingTitle = new Font("DejaVu Sans Light", Font.BOLD | Font.ITALIC, 24);
		g.setFont(startingTitle);
		g.setColor(Color.black);
		g.fillRect(125, 70, 350, 50);
		g.setColor(Color.RED);
		g.drawString("How to Play", 225, 100);

		g.setColor(Color.black);
		g.fillRect(60, 150, 500, 350);
		g.setColor(Color.red);
		g.drawString("WASD to move, Arrow keys to shoot", 75, 200);
		g.drawString("Defeat all other factions to win", 75, 300);
		g.drawString("Spacebar to use your special move", 75, 400);

		g.setColor(Color.black);
		g2d.draw(backButton);
		g.fillRect(250, 550, 100, 50);
		g.setColor(Color.red);
		g.drawString("Back", backButton.x + 19, backButton.y + 30);

	}

}