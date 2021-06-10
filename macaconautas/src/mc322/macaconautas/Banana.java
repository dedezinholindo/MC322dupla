package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;

public class Banana extends PecaRegular {

	private final static int BANANA_WIDTH = 28;
	private final static int BANANA_HEIGHT = 28;

	/**
	 * Inicializa uma banana.
	 * @param x coordenada x da banana.
	 * @param y coordenada y da banana.
	 */
	public Banana(int x, int y) {
		super(x, y, BANANA_WIDTH, BANANA_HEIGHT);
	}

	/**
	 * Renderiza a banana na tela.
	 * @param g
	 */
	public void render(Graphics g) {
		if (this.isVisible) {
			g.setColor(Color.YELLOW);
			g.fillRect(this.x, this.y, this.width, this.height);
		}
	}
}