package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;

public class Banana extends PecaRegular{

	public final static int ALIEN_WIDTH = 4;
	public final static int ALIEN_HEIGHT = 12;
	
	public Banana(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
	}
	
	public void tick() {
		x -= speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
	}

}
