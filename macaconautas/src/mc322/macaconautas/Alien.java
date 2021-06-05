package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Alien extends PecaRegular{
	
	public final static int ALIEN_WIDTH = 10;
	public final static int ALIEN_HEIGHT = 10;
	public int tiros;
	private ArrayList <Laser> lasers;
	private int l;
	
	public Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		lasers = new ArrayList<Laser>();
		speed = 1;
		isVisible = true; 
		l = 0;
		
	}
	
	public void tick() {
		x -= speed;
		this.lasers.add(new Laser(x, y));
		lasers.get(l).tick();
	}
	
	public ArrayList<Laser> getLasers() {
		return lasers;
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		lasers.get(l).render(g);
		l++; 
		System.out.println(l);
	}
	
}
