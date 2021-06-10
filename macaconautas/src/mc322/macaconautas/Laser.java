package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Laser extends Componente {

	private final static int LASER_WIDTH = 32;
	private final static int LASER_HEIGHT = 8;

	private final static int LASER_SPEED = 2;

	/**
	 * Inicializa um laser.
	 * @param x coordenada x do laser.
	 * @param y coordenada y do laser.
	 */
	public Laser(int x, int y) {
		super(x, y, LASER_WIDTH, LASER_HEIGHT);
		this.speed = LASER_SPEED;
	}

	/**
	 * Atualiza o estado do laser em um frame.
	 */
	public void tick() {
		this.x -= this.speed;
		if (this.x < 0) { //sumir quando sir da tela
			ArrayList <Laser> l = ControleJogo.getLasers();
			l.remove(this);
			ControleJogo.setLasers(l);
			return; // sempre retornar quando elimina o proprio objeto 
		}
	}

	/**
	 * Renderiza o laser na tela.
	 * @param g
	 */
	public void render(Graphics g) {
		if(this.isVisible) {
			g.setColor(Color.red);
			g.fillRect(this.x, this.y, this.width, this.height);
		}
	}
}