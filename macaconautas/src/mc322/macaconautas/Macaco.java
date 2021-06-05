package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle; //vai ter toda a colisão e todos os métodos necessários

public class Macaco extends Componente {
	
	private boolean up;
	private final static int gravidade = 1;
	
	public Macaco (int x, int y, int width, int height) {
		super(x, y, width, height); //os dois ultimos sao largura e altura
		speed = 4;
	}

	public void setUp(boolean up) {
		this.up = up;
	}
	
	public void tick() {
		if (up) {
			y -= speed;
		}
		else { 
			y += gravidade;
		}
		if (y + this.height > AppMacaconautas.HEIGHT * AppMacaconautas.SCALE) {
			y = AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - this.height;
		}
		else if (y < 0) {
			y = 0;
		}
		
	}
	
	public void render (Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		
	}
}
