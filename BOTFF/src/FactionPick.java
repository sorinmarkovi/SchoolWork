import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FactionPick {

	public Rectangle subwayButton = new Rectangle(439, 402, 100, 23);
	public Rectangle braumButton = new Rectangle(451, 289, 100, 23);
	public Rectangle tacobellButton = new Rectangle(341, 519, 100, 23);
	public Rectangle caesarButton = new Rectangle(168, 519, 100, 23);
	public Rectangle arbyButton = new Rectangle(72, 402, 100, 23);
	public Rectangle mcdButton = new Rectangle(60, 289, 100, 23);

	public Rectangle backButton = new Rectangle(245, 354, 105, 59);

	public void render(Graphics g) {
		Font startingTitle = new Font("DejaVu Sans Light", Font.BOLD | Font.ITALIC, 24);
		g.setFont(startingTitle);
		g.setColor(Color.black);
		g.fillRect(125, 70, 350, 50);
		g.setColor(Color.RED);
		g.drawString("Choose your faction", 150, 100);

		g.setColor(Color.black);
		g.fillRect(250, 350, 100, 50);
		g.setColor(Color.red);
		g.drawString("Back", backButton.x + 19, backButton.y + 30);

		g.setColor(Color.black);
		g.fillRect(439, 402, 100, 23);
		g.fillRect(451, 289, 100, 23);
		g.fillRect(341, 519, 100, 23);
		g.fillRect(168, 519, 100, 23);
		g.fillRect(72, 402, 100, 23);
		g.fillRect(60, 289, 100, 23);

		Font factionbuttons = new Font("Tahoma", Font.BOLD, 11);
		g.setFont(factionbuttons);
		g.setColor(Color.red);

		g.drawString("Subway", 469, 417);
		g.drawString("Braum's", 481, 305);
		g.drawString("Taco Bell", 371, 534);
		g.drawString("Little Caesars", 185, 534);
		g.drawString("Arby's", 102, 417);
		g.drawString("McDonalds", 85, 305);

	}

}