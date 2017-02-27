import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Endgame {
	
	public Rectangle okButton = new Rectangle(250, 550, 100, 50);
	
	public void render(Graphics g){
	
	Graphics2D g2d = (Graphics2D) g;
	
	Font startingTitle = new Font("DejaVu Sans Light", Font.BOLD , 16);
	g.setFont(startingTitle);
	g.setColor(Color.black);
	
	g.setColor(Color.black);
	g2d.draw(okButton);
	g.fillRect(250, 550, 100, 50);
	g.setColor(Color.red);
	g.drawString("Ok", okButton.x + 19, okButton.y + 30);
	
	}
}
