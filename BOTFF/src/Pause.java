import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pause {

	public Rectangle resumeButton = new Rectangle(250, 550, 100, 50);
	public Rectangle menuButton = new Rectangle(250, 550, 100, 50);
	Game game;
	Player p;

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font startingTitle = new Font("DejaVu Sans Light", Font.BOLD, 16);
		g.setFont(startingTitle);
		g.setColor(Color.black);

		g.setColor(Color.black);
		g2d.draw(resumeButton);
		g.fillRect(250, 550, 100, 50);
		g.setColor(Color.red);
		g.drawString("Resume", resumeButton.x + 19, resumeButton.y + 30);
	}

}
