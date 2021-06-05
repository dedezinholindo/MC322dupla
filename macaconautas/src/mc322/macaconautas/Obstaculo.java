package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Obstaculo extends PecaRegular{
	
	public Obstaculo(int x, int y, int width, int height) {
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
