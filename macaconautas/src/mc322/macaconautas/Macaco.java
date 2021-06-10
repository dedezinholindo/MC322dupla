package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle; //vai ter toda a colisão e todos os métodos necessários

public class Macaco extends Componente {

	private final static int MACACO_WIDTH = 32;
	private final static int MACACO_HEIGHT = 32;

	private final static int GOING_UP_SPEED = 5;
	private final static int GOING_DOWN_SPEED = 3;

	private boolean isGoingUp; // indica se o macaco está indo para cima.

	/**
	 * Inicializa um macaco.
	 * @param x coordenada x do macaco.
	 * @param y coordenada y do macaco.
	 */
	public Macaco (int x, int y) {
		super(x, y, MACACO_WIDTH, MACACO_HEIGHT); //os dois ultimos sao largura e altura
	}

	/**
	 * Altera o estado de movimento para cima do macaco.
	 * @param isGoingUp novo estado de movimento para cima do macaco.
	 */
	public void setIsGoingUp(boolean isGoingUp) {
		this.isGoingUp = isGoingUp;
	}

	/**
	 * Atualiza o estado do macaco em um frame.
	 */
	public void tick() {
		if (isGoingUp) {
			this.y -= GOING_UP_SPEED;
		} else { 
			this.y += GOING_DOWN_SPEED;
		}
		if (this.y + this.height > AppMacaconautas.HEIGHT * AppMacaconautas.SCALE) {
			this.y = AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - this.height;
		} else if (this.y < 0) {
			this.y = 0;
		}
	}

	/**
	 * Renderiza o macaco na tela.
	 * @param g
	 */
	public void render (Graphics g) {
		if (this.isVisible) {
			g.setColor(Color.black);
			g.fillRect(x, y, width, height);
		}
	}
}