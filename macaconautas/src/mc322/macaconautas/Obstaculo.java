package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Obstaculo extends PecaRegular {

	public final static int OBSTACULO_WIDTH = 36;
	public final static int OBSTACULO_HEIGHT = 36;

	/**
	 * Inicializa um obstáculo.
	 * @param x coordenada x do obstáculo.
	 * @param y coordenada y do obstáculo.
	 */
	public Obstaculo(int x, int y) {
		super(x, y, OBSTACULO_WIDTH, OBSTACULO_HEIGHT);
	}

	/**
	 * Renderiza o obstáculo na tela.
	 * @param g
	 */
	public void render(Graphics g) {
		if(this.isVisible) {
			g.setColor(Color.gray);
			g.fillRect(this.x, this.y, this.width, this.height);
		}
	}
}