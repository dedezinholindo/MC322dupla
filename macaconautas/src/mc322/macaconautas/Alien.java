package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Alien extends PecaRegular{
	
	public final static int ALIEN_WIDTH = 10;
	public final static int ALIEN_HEIGHT = 10;
	private int deslocamento;
	private boolean shoot;
	
	public Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
		isVisible = true; 
		shoot = true; //comecar atirando a a partir do momento que chegar na tela
		deslocamento = 0;
		
	}
	
	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public void tick() {
		x -= speed;
		if (x <= AppMacaconautas.WIDTH * AppMacaconautas.SCALE) {
			deslocamento += speed;
			if (deslocamento == 50) {
				shoot = true;
				deslocamento = 0;
			}
			if (shoot) {
				Laser laser = new Laser((int)this.getX(),(int) this.getY());
				ControleJogo.lasers.add(laser);
			}
			shoot = false;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
	
}
