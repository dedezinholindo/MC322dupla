package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Laser extends PecaRegular {

	public final static int LASER_WIDTH = 5;
	public final static int LASER_HEIGHT = 5;
	
	public Laser(int x, int y) {
		super(x, y, LASER_WIDTH, LASER_HEIGHT);
		speed = 2;
	} 
	
	public void tick() {
		x -= speed;
	}
	

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x + Alien.ALIEN_WIDTH, y + (Alien.ALIEN_HEIGHT / 2), width, height);
	}

}
